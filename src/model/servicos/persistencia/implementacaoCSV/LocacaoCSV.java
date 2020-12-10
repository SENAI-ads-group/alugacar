package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entidades.Cliente;
import model.entidades.Locacao;
import model.entidades.Motorista;
import model.entidades.Vistoria;
import model.entidades.enums.StatusLocacao;
import model.entidades.enums.StatusVeiculo;
import model.exceptions.DBException;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.LocacaoDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class LocacaoCSV implements LocacaoDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "locacoes.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void registrar(Locacao locacao) throws DBException {
        locacao.setDataRegistro(new Date());
        locacao.getVeiculo().setStatusVeiculo(StatusVeiculo.PENDENTE_DE_ENTREGA);
        locacao.setStatus(StatusLocacao.PENDENTE);
        DAOFactory.createVeiculoDAO().atualizar(locacao.getVeiculo());
        inserir(locacao);
    }

    @Override
    public void entregarVeiculo(Locacao locacao, Vistoria vistoria) {
        locacao.setVistoriaEntrega(vistoria);
        locacao.setDataEntrega(new Date());
        locacao.getVeiculo().setStatusVeiculo(StatusVeiculo.EM_LOCACAO);
        locacao.setStatus(StatusLocacao.INICIADA);
        DAOFactory.createVeiculoDAO().atualizar(locacao.getVeiculo());
        atualizar(locacao);
    }

    @Override
    public void devolverVeiculo(Locacao locacao, Vistoria vistoria) {
        locacao.setVistoriaDevolucao(vistoria);
        locacao.setDataDevolucao(new Date());
        locacao.getVeiculo().setStatusVeiculo(StatusVeiculo.INDISPONIVEL);
        locacao.setStatus(StatusLocacao.FINALIZADA);
        DAOFactory.createVeiculoDAO().atualizar(locacao.getVeiculo());
        locacao.checarVistorias();
        atualizar(locacao);
    }

    @Override
    public Locacao buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("O ID da locação está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));

            if (locacaoEncontrada.getId().equals(id)) {
                CONEXAO.close();
                new TaxaLocacaoCSV().importar(locacaoEncontrada);
                new DescontoLocacaoCSV().importar(locacaoEncontrada);
                return locacaoEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Locacao> buscar(Cliente cliente) {
        CONEXAO.open(ARQUIVO_DB);

        List<Locacao> locacoesEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));
            if (locacaoEncontrada.getCliente().equals(cliente)) {
                new TaxaLocacaoCSV().importar(locacaoEncontrada);
                new DescontoLocacaoCSV().importar(locacaoEncontrada);
                locacoesEncontradas.add(locacaoEncontrada);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return locacoesEncontradas;
    }

    @Override
    public List<Locacao> buscar(Motorista motorista) {
        CONEXAO.open(ARQUIVO_DB);

        List<Locacao> locacoesEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));
            if (locacaoEncontrada.getMotorista().equals(motorista)) {
                new TaxaLocacaoCSV().importar(locacaoEncontrada);
                new DescontoLocacaoCSV().importar(locacaoEncontrada);
                locacoesEncontradas.add(locacaoEncontrada);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return locacoesEncontradas;
    }

    @Override
    public List<Locacao> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Locacao> locacoesEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));
            new TaxaLocacaoCSV().importar(locacaoEncontrada);
            new DescontoLocacaoCSV().importar(locacaoEncontrada);
            locacoesEncontradas.add(locacaoEncontrada);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return locacoesEncontradas;
    }

    private void inserir(Locacao locacao) {
        if (locacao.getId() == null) {
            locacao.setId(getUltimoID() + 1);
        }
        if (buscar(locacao.getId()) != null) {
            throw new DBException("A locação " + locacao.getId() + " já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(locacao.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Locacao locacao) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-locacoes.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));

            if (locacaoEncontrada.equals(locacao)) {
                conexaoTemp.writer().write(locacao.toCSV());
            } else {
                conexaoTemp.writer().write(locacaoEncontrada.toCSV());
            }
            conexaoTemp.writer().flush();
            conexaoTemp.writer().newLine();
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
        new TaxaLocacaoCSV().atualizarLocacao(locacao);
        new DescontoLocacaoCSV().atualizarLocacao(locacao);
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
