package ui.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author patrick-ribeiro
 */
public class MouseListenerPanelButtonMenu implements MouseListener {

    private JPanel panel;

    public MouseListenerPanelButtonMenu(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackground(new Color(200, 200, 200));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(new Color(240, 240, 240));
    }

}
