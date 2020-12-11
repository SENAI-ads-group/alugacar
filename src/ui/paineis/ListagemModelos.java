package ui.paineis;

import java.awt.event.KeyEvent;
import model.servicos.persistencia.DAOFactory;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entidades.Modelo;
import model.exceptions.DBException;
import model.servicos.persistencia.interfaces.ModeloDAO;
import ui.FrameLoader;
import ui.dialogs.DialogModelo;
import ui.listeners.DataChangeListener;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class ListagemModelos extends javax.swing.JPanel implements DataChangeListener {

    private final ModeloDAO DAO = DAOFactory.createModeloDAO();

    public ListagemModelos() {
        initComponents();
    }

    public void atualizarListagem() {
        atualizarListagem(DAO.buscarTodos());
    }

    public void atualizarListagem(List<Modelo> modelos) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);
        for (Modelo modelo : modelos) {
            Object[] row = {
                modelo.getId(),
                modelo.getCodigoFipe(),
                modelo.getDescricao(),
                modelo.getMarca().toString(),
                modelo.getAno(),
                modelo.getCategoria().toString(),
                modelo.getCombustivel().toString()
            };
            tableModel.addRow(row);
        }
        table.setModel(tableModel);

        if (tableModel.getRowCount()
                > 0) {
            table.getSelectionModel().setSelectionInterval(0, 0);
            buttonExcluir.setEnabled(true);
            buttonEditar.setEnabled(true);
        } else {
            buttonExcluir.setEnabled(false);
            buttonEditar.setEnabled(false);
        }
    }

    public void exibirFormulario(Modelo modelo) {
        DialogModelo dialogForm = new DialogModelo(FrameLoader.getFrameMain(), true, modelo);
        dialogForm.subscribeListener(this);
        dialogForm.atualizarFormulario();
        dialogForm.setVisible(true);
    }

    @Override
    public void onDataChanged() {
        atualizarListagem();
    }

    private void pesquisar(String filtro) {
        if (filtro != null && filtro.trim().length() > 0) {
            atualizarListagem(DAO.buscar(textFieldPesquisa.getText()));
        } else {
            textFieldPesquisa.setText("");
            atualizarListagem(DAO.buscarTodos());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        buttonNovo = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        labelTitleList = new javax.swing.JLabel();
        panelPesquisa = new javax.swing.JPanel();
        buttonPesquisar = new javax.swing.JButton();
        textFieldPesquisa = new javax.swing.JTextField();
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
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-modelos-28x28.png"))); // NOI18N
        labelTitleList.setText("Modelos de veículos");

        panelPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        panelPesquisa.setPreferredSize(new java.awt.Dimension(100, 25));
        panelPesquisa.setLayout(new java.awt.BorderLayout());

        buttonPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pesquisa-24x24.png"))); // NOI18N
        buttonPesquisar.setBorderPainted(false);
        buttonPesquisar.setFocusPainted(false);
        buttonPesquisar.setFocusable(false);
        buttonPesquisar.setMaximumSize(new java.awt.Dimension(40, 30));
        buttonPesquisar.setMinimumSize(new java.awt.Dimension(40, 30));
        buttonPesquisar.setPreferredSize(new java.awt.Dimension(40, 30));
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });
        panelPesquisa.add(buttonPesquisar, java.awt.BorderLayout.EAST);

        textFieldPesquisa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldPesquisa.setPreferredSize(new java.awt.Dimension(200, 25));
        textFieldPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldPesquisaKeyReleased(evt);
            }
        });
        panelPesquisa.add(textFieldPesquisa, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(labelTitleList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
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
                "Id", "Código FIPE", "Descrição", "Marca", "Ano", "Categoria", "Combustível"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        exibirFormulario(new Modelo());
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        exibirFormulario(DAO.buscar(idSelecionado));
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        try {
            int option = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do modelo selecionado?", "Exclusão de modelo", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                DAO.excluir(idSelecionado);
                atualizarListagem();
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao excluir o modelo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        pesquisar(textFieldPesquisa.getText());
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    private void textFieldPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPesquisaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pesquisar(textFieldPesquisa.getText());
        }
    }//GEN-LAST:event_textFieldPesquisaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JLabel labelTitleList;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelPesquisa;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField textFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
