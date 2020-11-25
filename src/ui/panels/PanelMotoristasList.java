package ui.panels;

import entidades.Motorista;
import entidades.services.persistence.MotoristaPersistenceService;
import entidades.services.persistence.PersistenceFactory;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ui.FrameLoader;
import ui.dialogs.DialogMotoristaForm;
import ui.listeners.DataChangeListener;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelMotoristasList extends javax.swing.JPanel implements DataChangeListener {
    
    private MotoristaPersistenceService persistenceService = PersistenceFactory.createMotoristaService();
    
    public PanelMotoristasList() {
        initComponents();
        updateTable();
    }
    
    public void updateTable() {
        List<Motorista> motoristas = persistenceService.buscarTodos();
        
        DefaultTableModel tableModel = (DefaultTableModel) tableMotoristas.getModel();
        tableModel.setNumRows(0);
        
        for (Motorista motorista : motoristas) {
            Object[] row = {
                motorista.getId(),
                motorista.getPessoa().getNome(),
                motorista.getPessoa().getCpf(),
                motorista.getPessoa().getRegistroGeral(),
                motorista.getCnh().getNumeroRegistro(),
                motorista.getCnh().getCategoria().toString(),
                motorista.isAtivo()
            };
            tableModel.addRow(row);
        }
        tableMotoristas.setModel(tableModel);
        if (tableModel.getRowCount() > 0) {
            tableMotoristas.getSelectionModel().setSelectionInterval(0, 0);
        }
    }
    
    public void createMotoristaForm(Motorista motorista) {
        DialogMotoristaForm dialogForm = new DialogMotoristaForm(FrameLoader.getFrameMain(), true, motorista);
        dialogForm.subscribeListener(this);
        dialogForm.setVisible(true);
    }
    
    @Override
    public void onDataChanged() {
        updateTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        buttonNovo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMotoristas = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1200, 768));
        setLayout(new java.awt.BorderLayout());

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setPreferredSize(new java.awt.Dimension(1216, 80));

        buttonNovo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-novo-24x24.png"))); // NOI18N
        buttonNovo.setText("Novo");
        buttonNovo.setBorderPainted(false);
        buttonNovo.setFocusPainted(false);
        buttonNovo.setFocusable(false);
        buttonNovo.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovoActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-editar-24x24.png"))); // NOI18N
        jButton2.setText("Editar");
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setPreferredSize(new java.awt.Dimension(95, 35));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-excluir-24x24.png"))); // NOI18N
        jButton3.setText("Excluir");
        jButton3.setBorderPainted(false);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setPreferredSize(new java.awt.Dimension(95, 35));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png"))); // NOI18N
        jButton4.setText("CNH");
        jButton4.setBorderPainted(false);
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.setPreferredSize(new java.awt.Dimension(95, 35));

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(792, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(panelHeader, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setFocusable(false);

        tableMotoristas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "CNH", "Categoria CNH", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMotoristas.setFocusable(false);
        jScrollPane1.setViewportView(tableMotoristas);
        if (tableMotoristas.getColumnModel().getColumnCount() > 0) {
            tableMotoristas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableMotoristas.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableMotoristas.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableMotoristas.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableMotoristas.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableMotoristas.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        createMotoristaForm(new Motorista());
    }//GEN-LAST:event_buttonNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tableMotoristas;
    // End of variables declaration//GEN-END:variables
}
