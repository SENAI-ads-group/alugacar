package model.entidades.enums;

/**
 *
 * @author Alexsander
 */
public enum StatusLocacao {
    PENDENTE("Pendente"),
    INICIADA("Iniciada"),
    FINALIZADA("Finalizada");

    private final String NOME_FORMATADO;

    private StatusLocacao(String NOME_FORMATADO) {
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
