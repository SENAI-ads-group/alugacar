package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Configuracoes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Modelo;
import model.entidades.Veiculo;
import model.exceptions.DBException;
import util.Utilities;
import model.servicos.persistencia.VeiculoDAO;

/**
 *
 * @author Alexsander
 */
public class VeiculoCSV implements VeiculoDAO {

    private final File ARQUIVO_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;

    public VeiculoCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.veiculo");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        CONEXAO = new CSVConnection();
    }

    @Override
    public void inserir(Veiculo veiculo) throws DBException {
        if (veiculo.getId() == null) {
            veiculo.setId(getUltimoID() + 1);

        }
        if (buscar(veiculo.getId()) != null) {
            throw new DBException("O veículo já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(veiculo.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Veiculo veiculo) {

        File arquivoDBTemp = new File(PASTA_RAIZ + "\\temp\\veiculos-temp.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Veiculo veiculoEncontrado = new Veiculo(linha.split(";"));

            if (veiculoEncontrado.equals(veiculo)) {
                conexaoTemp.writer().write(veiculo.toCSV());
            } else {
                conexaoTemp.writer().write(veiculoEncontrado.toCSV());
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
    public List<Veiculo> buscar(String filtro) {
        if (filtro == null) {
            throw new IllegalStateException("Filtro de pesquisa vazio");
        }
        List<Veiculo> veiculos = new ArrayList<>();
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Veiculo veiculo = new Veiculo(linha.split(";"));
            if (veiculo.getPlaca().contains(filtro) || veiculo.getRenavam().contains(filtro)
                    || veiculo.getModelo().getDescricao().contains(filtro) || veiculo.getModelo().getCategoria().getDescricao().contains(filtro)
                    || veiculo.getModelo().getMarca().getDescricao().contains(filtro) || veiculo.getModelo().getCombustivel().toString().contains(filtro)) {
                veiculos.add(veiculo);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        if (veiculos.size() > 0) {
            return veiculos;
        } else {
            return buscarTodos();
        }
    }

    @Override
    public Veiculo buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Veiculo veiculoEncontrado = new Veiculo(linha.split(";"));
            if (veiculoEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return veiculoEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Veiculo> buscar(Modelo modelo
    ) {
        CONEXAO.open(ARQUIVO_DB);
        List<Veiculo> veiculos = new ArrayList<>();

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Veiculo veiculo = new Veiculo(linha.split(";"));
            if (veiculo.getModelo().equals(modelo)) {
                veiculos.add(veiculo);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return veiculos;
    }

    @Override
    public List<Veiculo> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);
        List<Veiculo> veiculos = new ArrayList<>();

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Veiculo veiculo = new Veiculo(linha.split(";"));
            veiculos.add(veiculo);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return veiculos;
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
