package ui.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author patrick-ribeiro
 */
public class MouseListenerPanelButtonMenu implements MouseListener {

    private Component component;

    public MouseListenerPanelButtonMenu(Component component) {
        this.component = component;
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
        component.setBackground(new Color(200, 200, 200));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(new Color(239, 239, 239));
    }

}
