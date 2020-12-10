package model.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
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
    private List<Taxa> taxas = new ArrayList<>();
    private List<Desconto> descontos = new ArrayList<>();

    // <editor-fold defaultstate="collapsed" desc="construtores">  
    public Locacao() {
    }

    public Locacao(TipoLocacao tipoLocacao) {
        status = StatusLocacao.PENDENTE;
        this.tipo = tipoLocacao;
        //tipo.getContrato().setLocacao(this);
    }

    public Locacao(TipoLocacao tipo, Date dataEntrega, Date dataDevolucao, Veiculo veiculo, Cliente cliente, Motorista motorista) {
        this.tipo = tipo;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.motorista = motorista;
        //tipo.getContrato().setLocacao(this);
    }

    public Locacao(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        status = StatusLocacao.valueOf(csv[1]);
        switch (status) {
            case INICIADA:
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
        //tipo.getContrato().setLocacao(this);
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

    public List<Taxa> getTaxas() {
        return taxas;
    }

    public void setTaxas(List<Taxa> taxas) {
        this.taxas = taxas;
    }

    public List<Desconto> getDescontos() {
        return descontos;
    }

    public void setDescontos(List<Desconto> descontos) {
        this.descontos = descontos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="equals e hashCode"> 
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
    // </editor-fold>

    public void addTaxa(Taxa taxa) {
        taxas.add(taxa);
    }

    public void removeTaxa(Taxa taxa) {
        taxas.remove(taxa);
    }

    public void addDesconto(Desconto desconto) {
        descontos.add(desconto);
    }

    public void removeDesconto(Desconto desconto) {
        descontos.remove(desconto);
    }

    public void checarVistorias() {
        if (status != StatusLocacao.FINALIZADA) {
            throw new IllegalStateException("A locação deve ser finalizada para o processamento");
        }
        for (int i = 0; i < vistoriaEntrega.getItens().size(); i++) {
            ItemVistoria itemEntrega = vistoriaEntrega.getItem(i);
            ItemVistoria itemDevolucao = vistoriaDevolucao.getItem(i);

            if (!itemEntrega.isAdequado()) {
                addDesconto(Desconto.getDescontoItemVistoria());
                JOptionPane.showMessageDialog(null, "Adicionado desconto automático de R$ " + Desconto.getDescontoItemVistoria().getValor()
                        + " devido o item " + itemEntrega.getNome() + " não estar adequado na vistoria de entrega.", "Adição de desconto", JOptionPane.WARNING_MESSAGE);
            } else if (!itemDevolucao.isAdequado()) {
                addTaxa(Taxa.getTaxaItemVistoria());
                JOptionPane.showMessageDialog(null, "Adicionada taxa automática de R$ " + Taxa.getTaxaItemVistoria().getValor()
                        + " devido o item " + itemDevolucao.getNome() + " não estar adequado na vistoria de devolução.", "Adição de taxa", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public double getValorTaxas() {
        double valorTotal = 0;
        for (Taxa taxa : taxas) {
            valorTotal += taxa.getValor();
        }
        return valorTotal;
    }

    public double getValorDescontos() {
        double valorTotal = 0;
        for (Desconto desconto : descontos) {
            valorTotal += desconto.getValor();
        }
        return valorTotal;
    }

    public double getValorBruto() {
        if (tipo == TipoLocacao.DIARIA) {
            Calendar calendarEntrega = new GregorianCalendar();
            Calendar calendarDevolucao = new GregorianCalendar();
            calendarEntrega.setTime(dataEntrega);
            calendarDevolucao.setTime(dataDevolucao);

            int diasPercorridos = calendarDevolucao.get(Calendar.DAY_OF_YEAR) - calendarEntrega.get(Calendar.DAY_OF_YEAR);
            double valorDiaria = veiculo.getModelo().getCategoria().getValorDiaria();
            return valorDiaria * diasPercorridos;
        } else {
            double kmRodado = vistoriaDevolucao.getKmVeiculo() - vistoriaEntrega.getKmVeiculo();
            double valorKM = getVeiculo().getModelo().getCategoria().getValorKM();
            return kmRodado * valorKM;
        }
    }

    public double getValorTotal() {
        return (getValorBruto() + getValorTaxas()) - getValorDescontos();
    }

    public String toCSV() {
        switch (status) {
            case INICIADA:
                return toCSVIniciado();
            case FINALIZADA:
                return toCSVFinalizado();
            case PENDENTE:
                return toCSVPendente();
            default:
                throw new IllegalStateException("Status inválido");
        }
    }

    private String toCSVPendente() {
        return "" + id + ";"
                + status.toCSV() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId();
    }

    private String toCSVIniciado() {
        //tipo.getContrato().setLocacao(this);
        return "" + id + ";"
                + status.toCSV() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId() + ";"
                + vistoriaEntrega.getId();
    }

    private String toCSVFinalizado() {
        //tipo.getContrato().setLocacao(this);
        return "" + id + ";"
                + status.toCSV() + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + DateUtilities.formatData(dataEntrega) + ";"
                + DateUtilities.formatData(dataDevolucao) + ";"
                + veiculo.getId() + ";"
                + motorista.getId() + ";"
                + cliente.getId() + ";"
                + vistoriaEntrega.getId() + ";"
                + vistoriaDevolucao.getId();
    }

}
