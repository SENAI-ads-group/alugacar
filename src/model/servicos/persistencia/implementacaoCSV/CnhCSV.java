package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Configuracoes;
import model.entidades.CNH;
import model.exceptions.PersistenciaException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.servicos.persistencia.CnhDAO;

/**
 *
 * @author Alexsander
 */
public class CnhCSV implements CnhDAO {

    private final File ARQUIVO_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;

    public CnhCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.cnh");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        CONEXAO = new CSVConnection();
    }

    @Override
    public void inserir(CNH cnh) throws PersistenciaException {
        if (buscar(cnh.getNumeroRegistro()) != null) {
            throw new PersistenciaException("A CNH " + cnh.getNumeroRegistro() + " já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(cnh.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(CNH cnh) {
        File arquivoDBTemp = new File(PASTA_RAIZ + "\\temp\\cnh-temp.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));

            if (cnhEncontrada.equals(cnh)) {
                conexaoTemp.writer().write(cnh.toCSV());
            } else {
                conexaoTemp.writer().write(cnhEncontrada.toCSV());
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
    public CNH buscar(Integer numeroRegistro) {
        if (numeroRegistro == null) {
            throw new IllegalStateException("numeroRegistro está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));

            if (cnhEncontrada.getNumeroRegistro().equals(numeroRegistro)) {
                CONEXAO.close();
                return cnhEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<CNH> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<CNH> cnhList = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));
            cnhList.add(cnhEncontrada);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return cnhList;
    }

}
