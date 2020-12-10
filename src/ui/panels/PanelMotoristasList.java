package ui.panels;

import model.entidades.Motorista;
import model.servicos.persistencia.DAOFactory;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.exceptions.DBException;
import ui.FrameLoader;
import ui.dialogs.DialogFormularioMotorista;
import ui.listeners.DataChangeListener;
import util.Utilities;
import model.servicos.persistencia.MotoristaDAO;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelMotoristasList extends javax.swing.JPanel implements DataChangeListener {

    private final MotoristaDAO DAO = DAOFactory.createMotoristaDAO();

    public PanelMotoristasList() {
        initComponents();
        updateTable();
    }

    public void updateTable() {
        List<Motorista> motoristas = DAO.buscarTodos();

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
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
        table.setModel(tableModel);
        if (tableModel.getRowCount() > 0) {
            table.getSelectionModel().setSelectionInterval(0, 0);
            buttonExcluir.setEnabled(true);
            buttonEditar.setEnabled(true);
        } else {
            buttonExcluir.setEnabled(false);
            buttonEditar.setEnabled(false);
        }
    }

    public void createMotoristaForm(Motorista motorista) {
        DialogFormularioMotorista dialogForm = new DialogFormularioMotorista(FrameLoader.getFrameMain(), true, motorista);
        dialogForm.subscribeListener(this);
        dialogForm.atualizarFormulario();
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
        labelTitleList = new javax.swing.JLabel();
        buttonAtivarDesativar = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

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
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        labelTitleList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-motorista-28x28.png"))); // NOI18N
        labelTitleList.setText("Motoristas");

        buttonAtivarDesativar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonAtivarDesativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-ativarDesativar-24x24.png"))); // NOI18N
        buttonAtivarDesativar.setText("Ativar / Desativar");
        buttonAtivarDesativar.setBorderPainted(false);
        buttonAtivarDesativar.setFocusPainted(false);
        buttonAtivarDesativar.setFocusable(false);
        buttonAtivarDesativar.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonAtivarDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtivarDesativarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 588, Short.MAX_VALUE)
                .addComponent(buttonAtivarDesativar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTitleList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAtivarDesativar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        add(panelHeader, java.awt.BorderLayout.PAGE_START);

        scrollPane.setFocusable(false);

        table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 13));
        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.setFocusable(false);
        table.setRowHeight(25);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(80);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(80);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);
            table.getColumnModel().getColumn(8).setPreferredWidth(20);
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        createMotoristaForm(new Motorista());
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        createMotoristaForm(DAO.buscar(idSelecionado));
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        try {
            int option = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do motorista selecionado?", "Exclusão de motorista", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                DAO.excluir(idSelecionado);
                updateTable();
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao excluir o motorista", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonAtivarDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtivarDesativarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        try {
            Motorista motoristaSelecionado = DAO.buscar(idSelecionado);
            motoristaSelecionado.setAtivo(!motoristaSelecionado.isAtivo());
            DAO.atualizar(motoristaSelecionado);
            updateTable();
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao ativar ou desativar motorista", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonAtivarDesativarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtivarDesativar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JLabel labelTitleList;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
