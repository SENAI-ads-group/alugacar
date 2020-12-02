package ui.panels;

import java.awt.event.KeyEvent;
import model.servicos.persistencia.DAOFactory;
import java.util.List;
import javafx.scene.input.KeyCode;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.scripts.JO;
import model.entidades.Veiculo;
import model.entidades.enums.StatusVeiculo;
import ui.FrameLoader;
import ui.dialogs.DialogVeiculoForm;
import ui.listeners.DataChangeListener;
import util.Utilities;
import model.servicos.persistencia.VeiculoDAO;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelVeiculosList extends javax.swing.JPanel implements DataChangeListener {

    private final VeiculoDAO DAO = DAOFactory.createVeiculoDAO();

    public PanelVeiculosList() {
        initComponents();
        updateTable();
    }

    public void updateTable() {
        updateTable(DAO.buscarTodos());
    }

    public void updateTable(List<Veiculo> veiculos) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);
        for (Veiculo veiculo : veiculos) {
            Object[] row = {
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getRenavam(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getModelo().getAno(),
                veiculo.getKMRodado(),
                veiculo.getStatusVeiculo()
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

    public void createVeiculoForm(Veiculo veiculo) {
        DialogVeiculoForm dialogForm = new DialogVeiculoForm(FrameLoader.getFrameMain(), true, veiculo);
        dialogForm.subscribeListener(this);
        dialogForm.updateFormData();
        dialogForm.setVisible(true);
    }

    private void pesquisar(String filtro) {
        if (filtro != null && filtro.trim().length() > 0) {
            updateTable(DAO.buscar(textFieldPesquisa.getText()));
        } else {
            textFieldPesquisa.setText("");
            updateTable();
        }
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
        buttonDisponibilizarIndisponibilizar = new javax.swing.JButton();
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

        labelTitleList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-veiculo-28x28.png"))); // NOI18N
        labelTitleList.setText("Veículos");

        buttonDisponibilizarIndisponibilizar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonDisponibilizarIndisponibilizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-ativarDesativar-24x24.png"))); // NOI18N
        buttonDisponibilizarIndisponibilizar.setText("Disponibilizar / Indisponibilizar");
        buttonDisponibilizarIndisponibilizar.setBorderPainted(false);
        buttonDisponibilizarIndisponibilizar.setFocusPainted(false);
        buttonDisponibilizarIndisponibilizar.setFocusable(false);
        buttonDisponibilizarIndisponibilizar.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonDisponibilizarIndisponibilizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDisponibilizarIndisponibilizarActionPerformed(evt);
            }
        });

        panelPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        panelPesquisa.setPreferredSize(new java.awt.Dimension(160, 25));
        panelPesquisa.setLayout(new java.awt.BorderLayout());

        buttonPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pesquisa-24x24.png"))); // NOI18N
        buttonPesquisar.setBorderPainted(false);
        buttonPesquisar.setFocusPainted(false);
        buttonPesquisar.setFocusable(false);
        buttonPesquisar.setMaximumSize(new java.awt.Dimension(40, 35));
        buttonPesquisar.setMinimumSize(new java.awt.Dimension(40, 35));
        buttonPesquisar.setPreferredSize(new java.awt.Dimension(40, 35));
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });
        panelPesquisa.add(buttonPesquisar, java.awt.BorderLayout.EAST);

        textFieldPesquisa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldPesquisa.setPreferredSize(new java.awt.Dimension(250, 35));
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
                .addComponent(panelPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(buttonDisponibilizarIndisponibilizar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTitleList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonDisponibilizarIndisponibilizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Id", "Placa", "Renavam", "Modelo", "Ano fabricação", "Ano modelo", "KM atual", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
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
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        createVeiculoForm(new Veiculo());
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        int selectedRow = table.getSelectedRow();
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(selectedRow, 0).toString());
        createVeiculoForm(DAO.buscar(idSelecionado));
        table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonDisponibilizarIndisponibilizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDisponibilizarIndisponibilizarActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        Veiculo veiculoSelecionado = DAO.buscar(idSelecionado);
        StatusVeiculo status = veiculoSelecionado.getStatusVeiculo();

        if (status == StatusVeiculo.DISPONIVEL) {
            veiculoSelecionado.setStatusVeiculo(StatusVeiculo.INDISPONIVEL);
            DAO.atualizar(veiculoSelecionado);
            updateTable();
            JOptionPane.showMessageDialog(this, "O veículo " + veiculoSelecionado.toString()
                    + "  foi indisponibilizado para a locação", "Indisponibilização de veículo", JOptionPane.WARNING_MESSAGE);
        } else if (status == StatusVeiculo.INDISPONIVEL) {
            veiculoSelecionado.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
            DAO.atualizar(veiculoSelecionado);
            updateTable();
            JOptionPane.showMessageDialog(this, "O veículo " + veiculoSelecionado.toString()
                    + "  foi disponibilizado para a locação", "Disponibilização de veículo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonDisponibilizarIndisponibilizarActionPerformed

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        pesquisar(textFieldPesquisa.getText());
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    private void textFieldPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPesquisaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pesquisar(textFieldPesquisa.getText());
        }
    }//GEN-LAST:event_textFieldPesquisaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDisponibilizarIndisponibilizar;
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
