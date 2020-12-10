package ui.panels;

import model.servicos.persistencia.DAOFactory;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entidades.Desconto;
import model.entidades.Locacao;
import model.entidades.Taxa;
import model.entidades.Vistoria;
import model.entidades.enums.StatusLocacao;
import model.servicos.persistencia.LocacaoDAO;
import model.servicos.persistencia.TaxaLocacaoDAO;
import model.servicos.persistencia.implementacaoCSV.DescontoLocacaoCSV;
import ui.FrameLoader;
import ui.dialogs.DialogLocacaoForm;
import ui.dialogs.DialogVistoriaForm;
import ui.dialogs.DialogVisualizacaoLocacao;
import ui.listeners.DataChangeListener;
import util.DateUtilities;
import util.Utilities;
import model.servicos.persistencia.implementacaoCSV.TaxaLocacaoCSV;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelLocacoesList extends javax.swing.JPanel implements DataChangeListener {

    private final LocacaoDAO DAO = DAOFactory.createLocacaoDAO();

    public PanelLocacoesList() {
        initComponents();
        updateTable();
    }

    public void updateTable() {
        List<Locacao> locacoes = DAO.buscarTodos();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setNumRows(0);
        for (Locacao locacao : locacoes) {
            Object[] row = {
                locacao.getId(),
                DateUtilities.formatData(locacao.getDataRegistro()),
                DateUtilities.formatData(locacao.getDataEntrega()),
                DateUtilities.formatData(locacao.getDataDevolucao()),
                locacao.getVeiculo(),
                locacao.getCliente(),
                locacao.getMotorista(),
                locacao.getTipo().toString(),
                locacao.getStatus()
            };
            tableModel.addRow(row);
        }
        table.setModel(tableModel);
        if (tableModel.getRowCount() > 0) {
            table.getSelectionModel().setSelectionInterval(0, 0);
            buttonVer.setEnabled(true);
            buttonEntregarDevolver.setEnabled(true);
        } else {
            buttonVer.setEnabled(false);
            buttonEntregarDevolver.setEnabled(false);
        }
    }

    public void createLocacaoForm(Locacao locacao) {
        DialogLocacaoForm dialogForm = new DialogLocacaoForm(FrameLoader.getFrameMain(), true, locacao);
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
        buttonVer = new javax.swing.JButton();
        labelTitleList = new javax.swing.JLabel();
        buttonEntregarDevolver = new javax.swing.JButton();
        buttonAdicionarTaxa = new javax.swing.JButton();
        buttonAdicionarDesconto = new javax.swing.JButton();
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

        buttonVer.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-visualizar-24x24.png"))); // NOI18N
        buttonVer.setText("Visualizar");
        buttonVer.setBorderPainted(false);
        buttonVer.setFocusPainted(false);
        buttonVer.setFocusable(false);
        buttonVer.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerActionPerformed(evt);
            }
        });

        labelTitleList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-locacao-28x28.png"))); // NOI18N
        labelTitleList.setText("Locações");

        buttonEntregarDevolver.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonEntregarDevolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-entregarDevolver-24x24.png"))); // NOI18N
        buttonEntregarDevolver.setText("Entregar / Devolver");
        buttonEntregarDevolver.setBorderPainted(false);
        buttonEntregarDevolver.setFocusPainted(false);
        buttonEntregarDevolver.setFocusable(false);
        buttonEntregarDevolver.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonEntregarDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntregarDevolverActionPerformed(evt);
            }
        });

        buttonAdicionarTaxa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonAdicionarTaxa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-taxa-24x24.png"))); // NOI18N
        buttonAdicionarTaxa.setText("Taxa");
        buttonAdicionarTaxa.setBorderPainted(false);
        buttonAdicionarTaxa.setFocusPainted(false);
        buttonAdicionarTaxa.setFocusable(false);
        buttonAdicionarTaxa.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonAdicionarTaxa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarTaxaActionPerformed(evt);
            }
        });

        buttonAdicionarDesconto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonAdicionarDesconto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-desconto-24x24.png"))); // NOI18N
        buttonAdicionarDesconto.setText("Desconto");
        buttonAdicionarDesconto.setBorderPainted(false);
        buttonAdicionarDesconto.setFocusPainted(false);
        buttonAdicionarDesconto.setFocusable(false);
        buttonAdicionarDesconto.setPreferredSize(new java.awt.Dimension(95, 35));
        buttonAdicionarDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarDescontoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
                .addComponent(buttonAdicionarDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdicionarTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEntregarDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonVer, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(buttonVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEntregarDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAdicionarTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAdicionarDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                "Id", "Data de registro", "Data de entrega", "Data de devolução", "Veículo", "Cliente", "Motorista", "Modalidade", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        createLocacaoForm(new Locacao());
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        Locacao locacao = DAO.buscar(idSelecionado);
        DialogVisualizacaoLocacao dialog = new DialogVisualizacaoLocacao(FrameLoader.getFrameMain(), true, locacao);
        dialog.atualizarInformacoes();
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonVerActionPerformed

    private void buttonEntregarDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntregarDevolverActionPerformed
        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        Locacao locacao = DAO.buscar(idSelecionado);
        if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
            JOptionPane.showMessageDialog(this, "Esta operação é inválida, pois veículo desta locação já foi devolvido", "Operação inválida", JOptionPane.ERROR_MESSAGE);
        } else {
            DialogVistoriaForm dialogForm = new DialogVistoriaForm(FrameLoader.getFrameMain(), true, locacao, new Vistoria());
            dialogForm.subscribeListener(this);
            dialogForm.updateFormData();
            dialogForm.setVisible(true);
        }
    }//GEN-LAST:event_buttonEntregarDevolverActionPerformed

    private void buttonAdicionarTaxaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarTaxaActionPerformed
        Object[] itens = DAOFactory.createTaxaDAO().buscarTodos().toArray();
        Taxa selectedValue = (Taxa) JOptionPane.showInputDialog(FrameLoader.getFrameMain(), "Escolha a taxa", "Adição de taxa",
                JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        Locacao locacao = DAO.buscar(idSelecionado);
        TaxaLocacaoDAO daoTaxa = new TaxaLocacaoCSV();
        daoTaxa.exportar(locacao, selectedValue);
        daoTaxa.importar(locacao);
    }//GEN-LAST:event_buttonAdicionarTaxaActionPerformed

    private void buttonAdicionarDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarDescontoActionPerformed
        Object[] itens = DAOFactory.createDescontoDAO().buscarTodos().toArray();
        Desconto selectedValue = (Desconto) JOptionPane.showInputDialog(FrameLoader.getFrameMain(), "Escolha o desconto", "Adição de desconto",
                JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

        Integer idSelecionado = Utilities.tryParseToInteger(table.getValueAt(table.getSelectedRow(), 0).toString());
        Locacao locacao = DAO.buscar(idSelecionado);
        DescontoLocacaoCSV daoDesconto = new DescontoLocacaoCSV();
        daoDesconto.exportar(locacao, selectedValue);
        daoDesconto.importar(locacao);
    }//GEN-LAST:event_buttonAdicionarDescontoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarDesconto;
    private javax.swing.JButton buttonAdicionarTaxa;
    private javax.swing.JButton buttonEntregarDevolver;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonVer;
    private javax.swing.JLabel labelTitleList;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
