package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import aplicacao.Programa;
import model.entidades.Motorista;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;
import model.servicos.persistencia.interfaces.MotoristaDAO;
import model.servicos.persistencia.interfaces.CnhDAO;

/**
 *
 * @author Alexsander
 */
public class MotoristaCSV implements MotoristaDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "motoristas.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Motorista motorista) throws DBException {
        CnhDAO cnhPersistenceService = DAOFactory.createCnhDAO();
        cnhPersistenceService.inserir(motorista.getCnh());
        if (motorista.getId() == null) {
            motorista.setId(getUltimoID() + 1);
        }
        if (buscar(motorista.getId()) != null) {
            throw new DBException("O motorista já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(motorista.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Motorista motorista) {
        if (motorista.getCnh() != null) {
            DAOFactory.createCnhDAO().atualizar(motorista.getCnh());
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-motoristas.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Motorista motoristaEncontrado = new Motorista(linha.split(";"));

            if (motoristaEncontrado.equals(motorista)) {
                conexaoTemp.writer().write(motorista.toCSV());
            } else {
                conexaoTemp.writer().write(motoristaEncontrado.toCSV());
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
    public void excluir(int id) {
        if (buscar(id) == null) {
            throw new DBException("A marca não existe");
        }
        if (DAOFactory.createLocacaoDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("O motorista não pode ser excluído pois está associado à uma ou mais locações");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-modelos.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Motorista motoristaEncontrado = new Motorista(linha.split(";"));
            if (!motoristaEncontrado.getId().equals(id)) {
                conexaoTemp.writer().write(motoristaEncontrado.toCSV());
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
    public Motorista buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Motorista motoristaEncontrado = new Motorista(csv);

            if (motoristaEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return motoristaEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Motorista> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Motorista> motoristasEncontrados = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();

        while (linha != null) {
            Motorista motoristaEncontrado = new Motorista(linha.split(";"));
            motoristasEncontrados.add(motoristaEncontrado);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return motoristasEncontrados;
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
