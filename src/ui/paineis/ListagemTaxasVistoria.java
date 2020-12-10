package ui.paineis;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.entidades.Taxa;

/**
 *
 * @author usuario
 */
public class ListagemTaxasVistoria extends javax.swing.JPanel {

    private List<Taxa> taxas;

    public ListagemTaxasVistoria(List<Taxa> taxas) {
        initComponents();
        this.taxas = taxas;
    }

    public void setTaxas(List<Taxa> taxas) {
        this.taxas = taxas;
    }

    public void atualizarListagem() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);
        for (Taxa taxa : taxas) {
            Object[] row = {
                taxa.getDescricao(),
                taxa.getValor()
            };

            tableModel.addRow(row);
        }
        table.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPane.setViewportView(table);

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
