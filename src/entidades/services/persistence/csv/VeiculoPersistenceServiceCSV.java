package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Modelo;
import entidades.Veiculo;
import entidades.services.persistence.VeiculoPersistenceService;
import entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class VeiculoPersistenceServiceCSV implements VeiculoPersistenceService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public VeiculoPersistenceServiceCSV() {

        String caminhoDB = Configurations.getProperties().getProperty("db.veiculo");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Veiculo veiculo) throws PersistenceException {
        if (veiculo.getId() == null) {
            veiculo.setId(getUltimoID() + 1);

        }
        if (buscar(veiculo.getId()) != null) {
            throw new PersistenceException("O veículo já existe");
        }
        connection.open(arquivoDB);

        connection.writer().write(veiculo.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Veiculo veiculo) {

        File arquivoDBTemp = new File(canonicalPath + "\\temp\\veiculos-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Veiculo veiculoEncontrado = new Veiculo(linha.split(";"));

            if (veiculoEncontrado.equals(veiculo)) {
                connectionTemp.writer().write(veiculo.toCSV());
            } else {
                connectionTemp.writer().write(veiculoEncontrado.toCSV());
            }
            connectionTemp.writer().flush();
            connectionTemp.writer().newLine();
            linha = connection.reader().readLine();
        }

        connectionTemp.close();
        connection.close();
        arquivoDB.delete();
        arquivoDBTemp.renameTo(arquivoDB);

    }

    @Override
    public Veiculo buscar(String placa) {

        if (placa == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Veiculo veiculoEncontrado = new Veiculo(linha.split(";"));
            if (veiculoEncontrado.getPlaca().equals(placa)) {
                connection.close();
                return veiculoEncontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;

    }
    
        public Veiculo buscar(Integer id) {

        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Veiculo veiculoEncontrado = new Veiculo(linha.split(";"));
            if (veiculoEncontrado.getId().equals(id)) {
                connection.close();
                return veiculoEncontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;

    }
    
    
    
     

    @Override
    public List<Veiculo> buscarTodos() {
        
             connection.open(arquivoDB);
        List<Veiculo> veiculos = new ArrayList<>();

        String linha = connection.reader().readLine();
        while (linha != null) {
            Veiculo veiculo = new Veiculo(linha.split(";"));
            veiculos.add(veiculo);
            linha = connection.reader().readLine();
        }

        connection.close();
        return veiculos;
        
    }
    
     private Integer getUltimoID() {
        connection.open(arquivoDB);
        
        Integer ultimoID = 1;
        String linha = connection.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 1;
            }
            linha = connection.reader().readLine();
        }
        
        connection.close();
        return ultimoID;
    }

}
