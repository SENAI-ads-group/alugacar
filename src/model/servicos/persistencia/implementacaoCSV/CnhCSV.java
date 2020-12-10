package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import aplicacao.Programa;
import model.entidades.CNH;
import model.exceptions.DBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.servicos.persistencia.interfaces.CnhDAO;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.interfaces.ImagemDAO;

/**
 *
 * @author Alexsander
 */
public class CnhCSV implements CnhDAO {
    
    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "CNH.csv");
    private final CSVConnection CONEXAO = new CSVConnection();
    private final ImagemDAO IMAGEM_DAO = DAOFactory.createImagemDAO();
    
    @Override
    public void inserir(CNH cnh) throws DBException {
        if (buscar(cnh.getNumeroRegistro()) != null) {
            throw new DBException("A CNH " + cnh.getNumeroRegistro() + " já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(cnh.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
        IMAGEM_DAO.exportar(cnh);
    }
    
    @Override
    public void atualizar(CNH cnh) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-CNH.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);
        
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            CNH cnhEncontrada = new CNH(linha.split(";"));
            
            if (cnhEncontrada.equals(cnh)) {
                conexaoTemp.writer().write(cnh.toCSV());
                IMAGEM_DAO.exportar(cnh);
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
                IMAGEM_DAO.importar(cnhEncontrada);
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
            IMAGEM_DAO.importar(cnhEncontrada);
            cnhList.add(cnhEncontrada);
            linha = CONEXAO.reader().readLine();
        }        
        CONEXAO.close();
        return cnhList;
    }
    
}
