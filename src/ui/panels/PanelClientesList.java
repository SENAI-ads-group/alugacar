package ui.panels;

import model.servicos.persistencia.DAOFactory;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entidades.Cliente;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.entidades.enums.TipoCliente;
import ui.FrameLoader;
import ui.dialogs.DialogClienteForm;
import ui.listeners.DataChangeListener;
import util.Utilities;
import model.servicos.persistencia.ClienteDAO;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelClientesList extends javax.swing.JPanel implements DataChangeListener {

    private final ClienteDAO persistenceService = DAOFactory.createClienteService();

    public PanelClientesList() {
        initComponents();
        updateTable();
    }

    public void updateTable() {
        List<Cliente> clientes = persistenceService.buscarTodos();

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);

        clientes.stream().map((cliente) -> {
            String cpfCnpj = null;
            String rgInscricaoEstadual = null;
            if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                PessoaFisica pf = (PessoaFisica) cliente.getPessoa();
                cpfCnpj = pf.getCpf();
                rgInscricaoEstadual = pf.getRegistroGeral();
            } else if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                PessoaJuridica pj = (PessoaJuridica) cliente.getPessoa();
                cpfCnpj = pj.getCnpj();
                rgInscricaoEstadual = pj.getInscricaoEstadual();
            }
            Object[] row = {
                cliente.getId(),
                cliente.getPessoa().getNome(),
                cliente.getPessoa().getEmail(),
                cliente.getPessoa().getTelefone(),
                cliente.getTipoCliente().toString(),
                cpfCnpj,
                rgInscricaoEstadual,
                cliente.isAtivo()
            };
            return row;
        }).forEachOrdered((row) -> {
            tableModel.addRow(row);
        });
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

    public void createClienteForm(Cliente cliente) {
        DialogClienteForm dialogForm = new DialogClienteForm(FrameLoader.getFrameMain(), true, cliente);
        dialogForm.updateFormData();
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
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        labelTitleList = new javax.swing.JLabel();
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

        labelTitleList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-clientes-28x28.png"))); // NOI18N
        labelTitleList.setText("Clientes");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 766, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTitleList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Id", "Nome", "Email", "Telefone", "Tipo", "CPF / CNPJ", "RG / Inscrição Estadual", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(2).setPreferredWidth(180);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(80);
            table.getColumnModel().getColumn(7).setPreferredWidth(20);
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        TipoCliente[] itens = TipoCliente.values();
        TipoCliente selectedValue = (TipoCliente) JOptionPane.showInputDialog(FrameLoader.getFrameMain(), "Escolha o tipo de cliente", "Tipo de cliente",
                JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
        if (selectedValue != null) {
            createClienteForm(new Cliente(selectedValue));
        }
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        createClienteForm(persistenceService.buscar(idSelecionado));
    }//GEN-LAST:event_buttonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JLabel labelTitleList;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
