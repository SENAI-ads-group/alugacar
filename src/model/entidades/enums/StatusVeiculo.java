package model.entidades.enums;

/**
 *
 * @author Patrick-Ribeiro
 */
public enum StatusVeiculo {
    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível"),
    EM_LOCACAO("Em Locação"),
    PENDENTE_DE_ENTREGA("Pendente de Entrega"),
    PENDENTE_DE_DEVOLUCAO("Pendente de Devolução");

    private final String NOME_FORMATADO;

    private StatusVeiculo(String NOME_FORMATADO) {
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
