package model.entidades.enums;

/**
 *
 * @author Patrick-Ribeiro
 */
public enum Combustivel {
    GASOLINA("Gasolina"),
    DIESEL("Diesel"),
    ALCOOL("Álcoool"),
    FLEX("Flex");

    private final String NOME_FORMATADO;

    private Combustivel(String nomeFormatado) {
        this.NOME_FORMATADO = nomeFormatado;
    }

    @Override
    public String toString() {
        return NOME_FORMATADO;
    }

    public String toCSV() {
        return name();
    }
}
