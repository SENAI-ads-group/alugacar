package ui;

import ui.frames.FrameMain;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.CoreManagers;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class FrameLoader {

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {
            // Install WebLaF as application L&F
            WebLookAndFeel.install();
            WebLookAndFeel.initializeManagers();
            CoreManagers.initialize();

            JFrame.setDefaultLookAndFeelDecorated(true);
            new FrameMain().setVisible(true);
        });
    }
}
