package model.entidades.enums;

/**
 *
 * @author Alexsander
 */
public enum TipoCliente {
    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private final String formatado;

    private TipoCliente(String formatado) {
        this.formatado = formatado;
    }

    @Override
    public String toString() {
        return formatado;
    }

    public String toCSV() {
        return name();
    }

}
