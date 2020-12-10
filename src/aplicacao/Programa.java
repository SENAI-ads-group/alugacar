package aplicacao;

import java.util.Properties;
import ui.FrameLoader;

/**
 *
 * @author patrick-ribeiro
 */
public class Programa {

    private static Properties propriedades;

    public static void main(String[] args) {
        propriedades = Configuracoes.getProperties();
        FrameLoader.load();
    }

    public static String getPropriedade(String chave) {
        return (String) propriedades.get(chave);
    }

}
