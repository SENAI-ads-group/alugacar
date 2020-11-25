package util;

import javax.swing.JPanel;

/**
 *
 * @author patrick-ribeiro
 */
public class PanelUtilities {

    public static void loadPanelToPanel(JPanel loaded, JPanel loader) {
        loader.removeAll();
        loader.revalidate();
        loader.repaint();

        loader.add(loaded);
        loader.revalidate();
        loader.repaint();
    }
}
