package ui;

import ui.frames.FrameMain;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.CoreManagers;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class FrameLoader {

    private static FrameMain frameMain;

    public static FrameMain getFrameMain() {
        return frameMain;
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {
            // Install WebLaF as application L&F
            WebLookAndFeel.install();
            WebLookAndFeel.initializeManagers();
            CoreManagers.initialize();

            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            frameMain = new FrameMain();
            frameMain.setExtendedState(FrameMain.MAXIMIZED_BOTH);
            frameMain.setVisible(true);
        });
    }
}
