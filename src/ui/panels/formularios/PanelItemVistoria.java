package ui.panels.formularios;

import model.entidades.ItemVistoria;

/**
 *
 * @author patrick-ribeiro
 */
public class PanelItemVistoria extends javax.swing.JPanel {
    
    private ItemVistoria item;
    
    public PanelItemVistoria(ItemVistoria item) {
        initComponents();
        this.item = item;
    }
    
    public void setItem(ItemVistoria item) {
        this.item = item;
    }
    
    public void updateItemForm() {
        checkBoxAdequado.setSelected(item.isAdequado());
        checkBoxAdequado.setText(item.getDescricao());
    }
    
    public ItemVistoria getItem() {
        item.setAdequado(checkBoxAdequado.isSelected());
        return item;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkBoxAdequado = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 25));
        setMinimumSize(new java.awt.Dimension(400, 25));
        setPreferredSize(new java.awt.Dimension(400, 25));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkBoxAdequado.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxAdequado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        checkBoxAdequado.setText("Adequado");
        checkBoxAdequado.setPreferredSize(new java.awt.Dimension(375, 25));
        add(checkBoxAdequado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxAdequado;
    // End of variables declaration//GEN-END:variables
}
