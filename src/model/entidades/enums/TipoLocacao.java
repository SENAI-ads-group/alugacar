package model.entidades.enums;

import model.servicos.contrato.ContratoDiaria;
import model.servicos.contrato.ContratoKM;
import model.servicos.contrato.ContratoService;

/**
 *
 * @author patrick-ribeiro
 */
public enum TipoLocacao {
    DIARIA("Diária", new ContratoDiaria()),
    KM("KM Rodado", new ContratoKM());

    private final String NOME_FORMATADO;
    private final ContratoService CONTRATO;

    private TipoLocacao(String NOME_FORMATADO, ContratoService CONTRATO) {
        this.NOME_FORMATADO = NOME_FORMATADO;
        this.CONTRATO = CONTRATO;
    }

    public ContratoService getContrato() {
        return CONTRATO;
    }

    @Override
    public String toString() {
        return NOME_FORMATADO;
    }

    public String toCSV() {
        return name();
    }

}
