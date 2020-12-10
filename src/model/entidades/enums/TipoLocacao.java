package model.entidades.enums;

/**
 *
 * @author patrick-ribeiro
 */
public enum TipoLocacao {
    DIARIA("Diária"),
    KM("KM Rodado");

    private final String NOME_FORMATADO;

    private TipoLocacao(String NOME_FORMATADO) {
        this.NOME_FORMATADO = NOME_FORMATADO;
    }

    @Override
    public String toString() {
        return NOME_FORMATADO;
    }

    public String toCSV() {
        return name();
    }

}
