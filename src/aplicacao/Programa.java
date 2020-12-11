package aplicacao;

import java.util.Locale;
import java.util.Properties;
import ui.FrameLoader;

/**
 *
 * @author patrick-ribeiro
 */
public class Programa {

    private static Properties propriedades;

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        propriedades = Configuracoes.getProperties();
        FrameLoader.load();
    }

    public static String getPropriedade(String chave) {
        return (String) propriedades.get(chave);
    }

}
