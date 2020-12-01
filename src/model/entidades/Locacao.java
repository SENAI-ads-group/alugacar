package model.entidades;

import java.util.Date;
import model.entidades.enums.StatusLocacao;
import model.entidades.enums.StatusVeiculo;
import model.entidades.enums.TipoLocacao;
import model.servicos.persistencia.DAOFactory;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class Locacao {

    private Integer id;
    private StatusLocacao status;
    private TipoLocacao tipo;
    private Date dataRegistro;
    private Date dataEntrega;
    private Date dataDevolucao;
    private Veiculo veiculo;
    private Cliente cliente;
    private Motorista motorista;
    private Vistoria vistoriaEntrega;
    private Vistoria vistoriaDevolucao;

    public Locacao() {
    }

    public Locacao(TipoLocacao tipo, Date dataEntrega, Date dataDevolucao, Veiculo veiculo, Cliente cliente, Motorista motorista) {
        this.tipo = tipo;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.motorista = motorista;
        tipo.getContrato().setLocacao(this);
    }

    public Locacao(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        status = StatusLocacao.valueOf(csv[0]);
        if (status == StatusLocacao.EM_ABERTO) {
            instanciarLocacaoEmAberto(csv);
        } else {
            instanciarLocacaoFinalizada(csv);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoLocacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoLocacao tipo) {
        tipo.getContrato().setLocacao(this);
        this.tipo = tipo;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public StatusLocacao getStatus() {
        return status;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public Vistoria getVistoriaEntrega() {
        return vistoriaEntrega;
    }

    public Vistoria getVistoriaDevolucao() {
        return vistoriaDevolucao;
    }

    public void entregarVeiculo(Vistoria vistoriaEntrega) {
        this.vistoriaEntrega = vistoriaEntrega;
        dataRegistro = new Date();
        veiculo.setStatusVeiculo(StatusVeiculo.EM_LOCACAO);
        status = StatusLocacao.EM_ABERTO;
    }

    public void devolverVeiculo(Vistoria vistoriaDevolucao) {
        if (status != StatusLocacao.EM_ABERTO) {
            throw new IllegalStateException("A locação precisa ser iniciada antes");
        }
        status = StatusLocacao.FINALIZADA;
        this.vistoriaDevolucao = vistoriaDevolucao;
        dataDevolucao = new Date();
    }

    public String toCSV() {
        if (status != StatusLocacao.EM_ABERTO && status != StatusLocacao.FINALIZADA) {
            throw new IllegalStateException("Status inválido");
        }
        switch (status) {
            case EM_ABERTO:
                return toCsvStatusAberto();
            case FINALIZADA:
                return toCsvStatusFinalizado();
            default:
                throw new IllegalStateException("Status inválido");
        }
    }

    private String toCsvStatusAberto() {
        return "" + id + ";"
                + status.name() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + vistoriaEntrega.toCSV() + ";"
                + tipo.getContrato().getValorBruto();
    }

    private String toCsvStatusFinalizado() {
        return "" + id
                + status.name() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + vistoriaEntrega.toCSV() + ";"
                + vistoriaDevolucao.toCSV() + ";"
                + tipo.getContrato().getValorTotal();
    }

    private void instanciarLocacaoEmAberto(String[] csv) {
        tipo = TipoLocacao.valueOf(csv[2]);
        dataRegistro = DateUtilities.tryParseToDate(csv[3]);
        dataEntrega = DateUtilities.tryParseToDate(csv[4]);
        dataDevolucao = DateUtilities.tryParseToDate(csv[5]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
        Double kmEntrega = Utilities.tryParseToDouble(csv[8]);
        Boolean veiculoAdequado = Boolean.parseBoolean(csv[9]);

        veiculo = DAOFactory.createVeiculoDAO().buscar(idVeiculo);
        motorista = DAOFactory.createMotoristaDAO().buscar(idMotorista);
        vistoriaEntrega = new Vistoria(kmEntrega, veiculoAdequado);
    }

    private void instanciarLocacaoFinalizada(String[] csv) {
        tipo = TipoLocacao.valueOf(csv[2]);
        dataRegistro = DateUtilities.tryParseToDate(csv[3]);
        dataEntrega = DateUtilities.tryParseToDate(csv[4]);
        dataDevolucao = DateUtilities.tryParseToDate(csv[5]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
        Double kmEntrega = Utilities.tryParseToDouble(csv[8]);
        Boolean veiculoAdequadoEntrega = Boolean.parseBoolean(csv[9]);
        Double kmDevolucao = Utilities.tryParseToDouble(csv[10]);
        Boolean veiculoAdequadoDevolucao = Boolean.parseBoolean(csv[11]);

        veiculo = DAOFactory.createVeiculoDAO().buscar(idVeiculo);
        motorista = DAOFactory.createMotoristaDAO().buscar(idMotorista);
        vistoriaEntrega = new Vistoria(kmEntrega, veiculoAdequadoEntrega);
        vistoriaDevolucao = new Vistoria(kmDevolucao, veiculoAdequadoDevolucao);
    }

}
