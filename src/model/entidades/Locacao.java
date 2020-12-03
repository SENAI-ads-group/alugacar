package model.entidades;

import java.util.Date;
import java.util.Objects;
import model.entidades.enums.StatusLocacao;
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

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Locacao(TipoLocacao tipoLocacao) {
        status = StatusLocacao.PENDENTE;
        this.tipo = tipoLocacao;
        tipo.getContrato().setLocacao(this);
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
        status = StatusLocacao.valueOf(csv[1]);
        switch (status) {
            case EM_ABERTO:
                instanciarLocacaoEmAberto(csv);
                break;
            case FINALIZADA:
                instanciarLocacaoFinalizada(csv);
                break;
            case PENDENTE:
                instanciarLocacaoPendente(csv);
            default:
                break;
        }
        tipo.getContrato().setLocacao(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getters e setters">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusLocacao getStatus() {
        return status;
    }

    public void setStatus(StatusLocacao status) {
        this.status = status;
    }

    public TipoLocacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoLocacao tipo) {
        this.tipo = tipo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public Vistoria getVistoriaEntrega() {
        return vistoriaEntrega;
    }

    public void setVistoriaEntrega(Vistoria vistoriaEntrega) {
        this.vistoriaEntrega = vistoriaEntrega;
    }

    public Vistoria getVistoriaDevolucao() {
        return vistoriaDevolucao;
    }

    public void setVistoriaDevolucao(Vistoria vistoriaDevolucao) {
        this.vistoriaDevolucao = vistoriaDevolucao;
    }

    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locacao other = (Locacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String toCSV() {
        if (status != StatusLocacao.EM_ABERTO && status != StatusLocacao.FINALIZADA && status != StatusLocacao.PENDENTE) {
            throw new IllegalStateException("Status inválido");
        }
        switch (status) {
            case EM_ABERTO:
                return toCsvStatusAberto();
            case FINALIZADA:
                return toCsvStatusFinalizado();
            case PENDENTE:
                return toCsvstatusPendente();
            default:
                throw new IllegalStateException("Status inválido");
        }
    }

    private String toCsvstatusPendente() {
        return "" + id + ";"
                + status.name() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId();
    }

    private String toCsvStatusAberto() {
        tipo.getContrato().setLocacao(this);
        return "" + id + ";"
                + status.name() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId() + ";"
                + vistoriaEntrega.toCSV() + ";"
                + tipo.getContrato().getValorBruto();
    }

    private String toCsvStatusFinalizado() {
        tipo.getContrato().setLocacao(this);
        return "" + id
                + status.name() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId() + ";"
                + vistoriaEntrega.toCSV() + ";"
                + vistoriaDevolucao.toCSV() + ";"
                + tipo.getContrato().getValorTotal();
    }

    private void instanciarLocacaoPendente(String[] csv) {
        tipo = TipoLocacao.valueOf(csv[2]);
        dataRegistro = DateUtilities.tryParseToDate(csv[3]);
        dataEntrega = DateUtilities.tryParseToDate(csv[4]);
        dataDevolucao = DateUtilities.tryParseToDate(csv[5]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
        Integer idCliente = Utilities.tryParseToInteger(csv[8]);

        veiculo = DAOFactory.createVeiculoDAO().buscar(idVeiculo);
        motorista = DAOFactory.createMotoristaDAO().buscar(idMotorista);
        cliente = DAOFactory.createClienteDAO().buscar(idCliente);
    }

    private void instanciarLocacaoEmAberto(String[] csv) {
        tipo = TipoLocacao.valueOf(csv[2]);
        dataRegistro = DateUtilities.tryParseToDate(csv[3]);
        dataEntrega = DateUtilities.tryParseToDate(csv[4]);
        dataDevolucao = DateUtilities.tryParseToDate(csv[5]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
        Integer idCliente = Utilities.tryParseToInteger(csv[8]);
        Integer idVistoriaEntrega = Utilities.tryParseToInteger(csv[9]);

        veiculo = DAOFactory.createVeiculoDAO().buscar(idVeiculo);
        motorista = DAOFactory.createMotoristaDAO().buscar(idMotorista);
        cliente = DAOFactory.createClienteDAO().buscar(idCliente);
        vistoriaEntrega = DAOFactory.createVistoriaDAO().buscar(idVistoriaEntrega);
    }

    private void instanciarLocacaoFinalizada(String[] csv) {
        tipo = TipoLocacao.valueOf(csv[2]);
        dataRegistro = DateUtilities.tryParseToDate(csv[3]);
        dataEntrega = DateUtilities.tryParseToDate(csv[4]);
        dataDevolucao = DateUtilities.tryParseToDate(csv[5]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
        Integer idCliente = Utilities.tryParseToInteger(csv[8]);
        Integer idVistoriaEntrega = Utilities.tryParseToInteger(csv[9]);
        Integer idVistoriaDevolucao = Utilities.tryParseToInteger(csv[10]);

        veiculo = DAOFactory.createVeiculoDAO().buscar(idVeiculo);
        motorista = DAOFactory.createMotoristaDAO().buscar(idMotorista);
        cliente = DAOFactory.createClienteDAO().buscar(idCliente);
        vistoriaEntrega = DAOFactory.createVistoriaDAO().buscar(idVistoriaEntrega);
        vistoriaDevolucao = DAOFactory.createVistoriaDAO().buscar(idVistoriaDevolucao);
    }

}
