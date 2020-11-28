package model.entidades;

import java.util.Date;
import model.entidades.enums.TipoLocacao;
import model.entidades.enums.TipoVistoria;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class Locacao {

    private Integer id;
    private TipoLocacao tipo;
    private Date dataRegistro;
    private Vistoria vistoriaRetirada;
    private Vistoria vistoriaDevolucao;
    private Veiculo veiculo;
    private Cliente cliente;
    private Motorista motorista;

    public Locacao() {
    }

    public Locacao(Integer id, TipoLocacao tipo, Date dataRegistro, Veiculo veiculo, Cliente cliente, Motorista motorista) {
        this.id = id;
        this.tipo = tipo;
        this.dataRegistro = dataRegistro;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.motorista = motorista;
    }

    public Locacao(String[] csv) {
        id = Utilities.tryParseToInteger(csv[0]);
        tipo = TipoLocacao.valueOf(csv[1]);
        dataRegistro = DateUtilities.tryParseToDate(csv[2]);
        Integer idVistoriaRetirada = Utilities.tryParseToInteger(csv[3]);
        Integer idVistoriaDevolucao = Utilities.tryParseToInteger(csv[4]);
        Integer idVeiculo = Utilities.tryParseToInteger(csv[5]);
        Integer idCliente = Utilities.tryParseToInteger(csv[6]);
        Integer idMotorista = Utilities.tryParseToInteger(csv[7]);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public Vistoria getVistoriaRetirada() {
        return vistoriaRetirada;
    }

    public Vistoria getVistoriaDevolucao() {
        return vistoriaDevolucao;
    }

    public void registrarVistoria(TipoVistoria tipoVistoria, Vistoria vistoria) {
        if (tipoVistoria == TipoVistoria.DEVOLUCAO) {
            vistoriaDevolucao = vistoria;
        } else {
            vistoriaRetirada = vistoria;
        }
    }

    public TipoLocacao getTipo() {
        return tipo;
    }

    public String toCSV() {
        return "" + id + ";"
                + tipo.toCSV() + ";"
                + DateUtilities.formatData(dataRegistro) + ";"
                + vistoriaRetirada.getId() + ";"
                + vistoriaDevolucao.getId() + ";"
                + veiculo.getId() + ";"
                + cliente.getId() + ";"
                + motorista.getId() + ";"
                + tipo.getContratoService().getValorBruto() + ";"
                + tipo.getContratoService().getValorTaxas() + ";"
                + tipo.getContratoService().getValorTotal();
    }

}
