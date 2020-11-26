package model.entidades.services.persistence.csv;

import application.Configurations;
import model.entidades.CNH;
import model.entidades.services.persistence.CNHPersistenseService;
import model.entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public class CNHPersistenceServiceCSV implements CNHPersistenseService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public CNHPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.cnh");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(CNH cnh) throws PersistenceException {
        if (buscar(cnh.getNumeroRegistro()) != null) {
            throw new PersistenceException("A cnh já existe");
        }
        connection.open(arquivoDB);

        connection.writer().write(cnh.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(CNH cnh) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\cnh-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));

            if (cnhEncontrada.equals(cnh)) {
                connectionTemp.writer().write(cnh.toCSV());
            } else {
                connectionTemp.writer().write(cnhEncontrada.toCSV());
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
    public CNH buscar(Integer numeroRegistro) {
        if (numeroRegistro == null) {
            throw new IllegalStateException("numeroRegistro está nulo");
        }
        connection.open(arquivoDB);
        
        String linha = connection.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));

            if (cnhEncontrada.getNumeroRegistro().equals(numeroRegistro)) {
                connection.close();
                return cnhEncontrada;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<CNH> buscarTodos() {
        connection.open(arquivoDB);

        List<CNH> cnhList = new ArrayList<>();
        String linha = connection.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));
            cnhList.add(cnhEncontrada);
            linha = connection.reader().readLine();
        }

        connection.close();
        return cnhList;
    }

}
