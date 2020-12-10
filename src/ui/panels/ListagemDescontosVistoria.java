package ui.panels;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.entidades.Desconto;

/**
 *
 * @author usuario
 */
public class ListagemDescontosVistoria extends javax.swing.JPanel {

    private List<Desconto> descontos;

    public ListagemDescontosVistoria(List<Desconto> descontos) {
        initComponents();
        this.descontos = descontos;
    }

    public void setTaxas(List<Desconto> descontos) {
        this.descontos = descontos;
    }

    public void atualizarListagem() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);
        for (Desconto desconto : descontos) {
            Object[] row = {
                desconto.getDescricao(),
                desconto.getValor()
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
        setMaximumSize(new java.awt.Dimension(390, 280));
        setMinimumSize(new java.awt.Dimension(390, 280));
        setPreferredSize(new java.awt.Dimension(390, 280));
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

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 280));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
