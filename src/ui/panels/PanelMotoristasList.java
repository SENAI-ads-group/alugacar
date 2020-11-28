package ui.panels;

import model.entidades.Motorista;
import model.services.persistence.MotoristaPersistenceService;
import model.services.persistence.PersistenceFactory;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ui.FrameLoader;
import ui.dialogs.DialogMotoristaForm;
import ui.listeners.DataChangeListener;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelMotoristasList extends javax.swing.JPanel implements DataChangeListener {

    private final MotoristaPersistenceService persistenceService = PersistenceFactory.createMotoristaService();

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
                motorista.getPessoa().getEmail(),
                motorista.getPessoa().getTelefone(),
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
            buttonExcluir.setEnabled(true);
            buttonEditar.setEnabled(true);
        } else {
            buttonExcluir.setEnabled(false);
            buttonEditar.setEnabled(false);
        }
    }

    public void createMotoristaForm(Motorista motorista) {
        DialogMotoristaForm dialogForm = new DialogMotoristaForm(FrameLoader.getFrameMain(), true, motorista);
        dialogForm.subscribeListener(this);
        dialogForm.updateFormData();
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
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
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

        buttonEditar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-editar-24x24.png"))); // NOI18N
        buttonEditar.setText("Editar");
        buttonEditar.setBorderPainted(false);
        buttonEditar.setFocusPainted(false);
        buttonEditar.setFocusable(false);
        buttonEditar.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonExcluir.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-excluir-24x24.png"))); // NOI18N
        buttonExcluir.setText("Excluir");
        buttonExcluir.setBorderPainted(false);
        buttonExcluir.setFocusPainted(false);
        buttonExcluir.setFocusable(false);
        buttonExcluir.setPreferredSize(new java.awt.Dimension(95, 35));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Motoristas");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 798, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        add(panelHeader, java.awt.BorderLayout.PAGE_START);

        scrollPane.setFocusable(false);

        tableMotoristas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableMotoristas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Email", "Telefone", "CPF", "RG", "CNH", "Categoria CNH", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMotoristas.setFocusable(false);
        tableMotoristas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableMotoristas);
        if (tableMotoristas.getColumnModel().getColumnCount() > 0) {
            tableMotoristas.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableMotoristas.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableMotoristas.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableMotoristas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableMotoristas.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableMotoristas.getColumnModel().getColumn(5).setPreferredWidth(80);
            tableMotoristas.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableMotoristas.getColumnModel().getColumn(7).setPreferredWidth(50);
            tableMotoristas.getColumnModel().getColumn(8).setPreferredWidth(20);
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        createMotoristaForm(new Motorista());
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(tableMotoristas.getValueAt(tableMotoristas.getSelectedRow(), 0).toString());
        createMotoristaForm(persistenceService.buscar(idSelecionado));
    }//GEN-LAST:event_buttonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableMotoristas;
    // End of variables declaration//GEN-END:variables
}
