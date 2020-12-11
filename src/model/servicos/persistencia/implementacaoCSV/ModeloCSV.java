package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import aplicacao.Programa;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.exceptions.DBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Categoria;
import model.servicos.persistencia.DAOFactory;
import util.Utilities;
import model.servicos.persistencia.interfaces.ModeloDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class ModeloCSV implements ModeloDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "modelos.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Modelo modelo) throws DBException {
        if (modelo.getId() == null) {
            modelo.setId(getUltimoID() + 1);
        }
        if (buscar(modelo.getId()) != null) {
            throw new DBException("O modelo já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(modelo.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Modelo modelo) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-modelos.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modeloEncontrado = new Modelo(linha.split(";"));

            if (modeloEncontrado.equals(modelo)) {
                conexaoTemp.writer().write(modelo.toCSV());
            } else {
                conexaoTemp.writer().write(modeloEncontrado.toCSV());
            }
            conexaoTemp.writer().flush();
            conexaoTemp.writer().newLine();
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
    }

    @Override
    public void excluir(Integer id) {
        if (buscar(id) == null) {
            throw new DBException("O modelo não existe");
        }
        if (DAOFactory.createVeiculoDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("Não foi possível excluir o modelo pois está associado à um ou mais veículo(s)");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-modelos.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modeloEncontraado = new Modelo(linha.split(";"));
            if (!modeloEncontraado.getId().equals(id)) {
                conexaoTemp.writer().write(modeloEncontraado.toCSV());
                conexaoTemp.writer().flush();
                conexaoTemp.writer().newLine();
            }
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
    }

    @Override
    public Modelo buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modeloEcontrado = new Modelo(linha.split(";"));
            if (modeloEcontrado.getId().equals(id)) {
                CONEXAO.close();
                return modeloEcontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Modelo> buscar(Marca marca) {
        CONEXAO.open(ARQUIVO_DB);
        List<Modelo> modelos = new ArrayList<>();

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            if (modelo.getMarca().equals(marca)) {
                modelos.add(modelo);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return modelos;
    }

    @Override
    public List<Modelo> buscar(Categoria categoria) {
        CONEXAO.open(ARQUIVO_DB);
        List<Modelo> modelos = new ArrayList<>();

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            if (modelo.getCategoria().equals(categoria)) {
                modelos.add(modelo);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return modelos;
    }

    @Override
    public List<Modelo> buscar(String filtro) {
        if (filtro == null) {
            throw new IllegalStateException("Filtro de pesquisa vazio");
        }
        List<Modelo> modelos = new ArrayList<>();
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            if (modelo.getDescricao().contains(filtro) || ("" + modelo.getId()).contains(filtro)
                    || modelo.getCodigoFipe().contains(filtro) || modelo.getCategoria().getDescricao().contains(filtro)
                    || modelo.getMarca().getDescricao().contains(filtro)) {
                modelos.add(modelo);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        if (modelos.size() > 0) {
            return modelos;
        } else {
            return buscarTodos();
        }
    }

    @Override
    public List<Modelo> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);
        List<Modelo> modelos = new ArrayList<>();

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            modelos.add(modelo);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return modelos;
    }

    private Integer getUltimoID() {
        CONEXAO.open(ARQUIVO_DB);

        Integer ultimoID = 0;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 0;
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return ultimoID;
    }

}
