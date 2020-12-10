package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Programa;
import model.entidades.Marca;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.exceptions.DBException;
import model.servicos.persistencia.DAOFactory;
import util.Utilities;
import model.servicos.persistencia.MarcaDAO;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaCSV implements MarcaDAO {
    
    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "marcas.csv");
    private final CSVConnection CONEXAO = new CSVConnection();
    
    @Override
    public void inserir(Marca marca) throws DBException {
        if (marca.getId() == null) {
            marca.setId(getUltimoID() + 1);
        }
        if (buscar(marca.getId()) != null) {
            throw new DBException("A marca já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(marca.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }
    
    @Override
    public void atualizar(Marca marca) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-marcas.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);
        
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Marca marcaEncontrada = new Marca(linha.split(";"));
            
            if (marcaEncontrada.equals(marca)) {
                conexaoTemp.writer().write(marca.toCSV());
            } else {
                conexaoTemp.writer().write(marcaEncontrada.toCSV());
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
    public void excluir(int id) throws DBException {
        if (buscar(id) == null) {
            throw new DBException("A marca não existe");
        }
        if (DAOFactory.createModeloDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("A marca não pode ser excluída pois está associada a uma ou mais modelos");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-marca.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);
        
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Marca marcaEncontrada = new Marca(linha.split(";"));
            if (!marcaEncontrada.getId().equals(id)) {
                conexaoTemp.writer().write(marcaEncontrada.toCSV());
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
    public Marca buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);
            
            if (marcaEncontrada.getId().equals(id)) {
                CONEXAO.close();
                return marcaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }
    
    @Override
    public List<Marca> buscar(String filtro) {
        if (filtro == null) {
            throw new IllegalStateException("Filtro de pesquisa vazio");
        }
        List<Marca> marcas = new ArrayList<>();
        CONEXAO.open(ARQUIVO_DB);
        
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Marca marca = new Marca(linha.split(";"));
            if (marca.getDescricao().contains(filtro) || ("" + marca.getId()).contains(filtro)) {
                marcas.add(marca);
            }
            linha = CONEXAO.reader().readLine();
        }
        
        CONEXAO.close();
        if (marcas.size() > 0) {
            return marcas;
        } else {
            return buscarTodos();
        }
    }
    
    @Override
    public List<Marca> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);
        
        List<Marca> marcasEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);
            marcasEncontradas.add(marcaEncontrada);
            linha = CONEXAO.reader().readLine();
        }
        
        CONEXAO.close();
        return marcasEncontradas;
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
