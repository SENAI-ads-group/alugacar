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
        labelDescricao.setText(item.getDescricao());
        checkBoxAdequado.setSelected(item.isAdequado());
    }

    public ItemVistoria getItem() {
        item.setAdequado(checkBoxAdequado.isSelected());
        return item;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkBoxAdequado = new javax.swing.JCheckBox();
        labelDescricao = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 250, 250));
        setMaximumSize(new java.awt.Dimension(400, 25));
        setMinimumSize(new java.awt.Dimension(400, 25));
        setPreferredSize(new java.awt.Dimension(400, 25));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkBoxAdequado.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxAdequado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkBoxAdequado.setText("Adequado");
        checkBoxAdequado.setPreferredSize(new java.awt.Dimension(95, 25));
        add(checkBoxAdequado, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 0, -1, -1));

        labelDescricao.setBackground(new java.awt.Color(255, 255, 255));
        labelDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelDescricao.setOpaque(true);
        labelDescricao.setPreferredSize(new java.awt.Dimension(305, 25));
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxAdequado;
    private javax.swing.JLabel labelDescricao;
    // End of variables declaration//GEN-END:variables
}
