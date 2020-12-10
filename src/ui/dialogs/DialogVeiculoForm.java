package ui.dialogs;

import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.entidades.Veiculo;
import model.exceptions.ValidacaoException;
import ui.listeners.DataChangeListener;
import ui.panels.formularios.FormularioVeiculo;
import util.PanelUtilities;
import model.servicos.persistencia.VeiculoDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogVeiculoForm extends javax.swing.JDialog {

    private Veiculo veiculo;
    private final VeiculoDAO persistenceService = DAOFactory.createVeiculoDAO();

    private FormularioVeiculo formVeiculo;

    private final List<DataChangeListener> listeners;

    public DialogVeiculoForm(java.awt.Frame parent, boolean modal, Veiculo veiculo) {
        super(parent, modal);
        this.veiculo = veiculo;
        initComponents();
        loadPanels();

        listeners = new ArrayList<>();
    }

    private void loadPanels() {
        formVeiculo = new FormularioVeiculo(veiculo);
        PanelUtilities.loadPanelToPanel(formVeiculo, panelCenterTab1);
    }

    private void persistEntity() throws DBException, ValidacaoException {
        getFormData();
        if (veiculo.getId() == null) {
            persistenceService.inserir(veiculo);
        } else {
            persistenceService.atualizar(veiculo);
        }
    }

    public Veiculo getFormData() throws ValidacaoException {
        veiculo = formVeiculo.getFormData();
        return veiculo;
    }

    public void updateFormData() {
        formVeiculo.updateFormData();
    }

    public void subscribeListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        if (listeners.size() > 0) {
            listeners.forEach((listener) -> {
                listener.onDataChanged();
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        panelTab1 = new javax.swing.JPanel();
        panelTopTab1 = new javax.swing.JPanel();
        panelBorderLeftTab1 = new javax.swing.JPanel();
        panelCenterTab1 = new javax.swing.JPanel();
        panelBorderRightTab1 = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de veículo");
        setMinimumSize(new java.awt.Dimension(440, 440));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(440, 440));
        setResizable(false);
        setSize(new java.awt.Dimension(440, 440));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(400, 320));
        tabbedPane.setMinimumSize(new java.awt.Dimension(400, 320));
        tabbedPane.setPreferredSize(new java.awt.Dimension(400, 320));
        tabbedPane.setRequestFocusEnabled(false);

        panelTab1.setBackground(new java.awt.Color(153, 153, 153));
        panelTab1.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab1.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab1.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab1.setLayout(new java.awt.BorderLayout());

        panelTopTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab1.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab1.add(panelTopTab1, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab1.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab1.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab1Layout = new javax.swing.GroupLayout(panelBorderLeftTab1);
        panelBorderLeftTab1.setLayout(panelBorderLeftTab1Layout);
        panelBorderLeftTab1Layout.setHorizontalGroup(
            panelBorderLeftTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab1Layout.setVerticalGroup(
            panelBorderLeftTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderLeftTab1, java.awt.BorderLayout.LINE_START);

        panelCenterTab1.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab1.setPreferredSize(new java.awt.Dimension(400, 350));
        panelCenterTab1.setLayout(new javax.swing.BoxLayout(panelCenterTab1, javax.swing.BoxLayout.LINE_AXIS));
        panelTab1.add(panelCenterTab1, java.awt.BorderLayout.CENTER);

        panelBorderRightTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab1.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab1.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab1Layout = new javax.swing.GroupLayout(panelBorderRightTab1);
        panelBorderRightTab1.setLayout(panelBorderRightTab1Layout);
        panelBorderRightTab1Layout.setHorizontalGroup(
            panelBorderRightTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab1Layout.setVerticalGroup(
            panelBorderRightTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderRightTab1, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Veículo", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-veiculo-28x28.png")), panelTab1, "Informações pessoais básicas do motorista"); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(400, 50));
        panelButtons.setMinimumSize(new java.awt.Dimension(400, 50));
        panelButtons.setPreferredSize(new java.awt.Dimension(400, 50));
        panelButtons.setVerifyInputWhenFocusTarget(false);
        panelButtons.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cancelar-24x24.png"))); // NOI18N
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setBorderPainted(false);
        buttonCancelar.setFocusPainted(false);
        buttonCancelar.setFocusable(false);
        buttonCancelar.setPreferredSize(new java.awt.Dimension(115, 35));
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonCancelar);

        buttonConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-confirmar-24x24.png"))); // NOI18N
        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.setBorderPainted(false);
        buttonConfirmar.setFocusPainted(false);
        buttonConfirmar.setFocusable(false);
        buttonConfirmar.setPreferredSize(new java.awt.Dimension(115, 35));
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonConfirmar);

        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1.add(jLabel1);

        panelButtons.add(jPanel1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {
            persistEntity();
            this.dispose();
            notifyListeners();
        } catch (ValidacaoException ex) {
            Icon iconError = new ImageIcon(getClass().getResource("/ui/media/icons/icon-erro-24x24.png"));
            if (ex.getMessage().equals("PanelFormVeiculo")) {
                tabbedPane.setIconAt(0, iconError);
                formVeiculo.setErrorsMessages(ex.getErrors());
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao persistir motorista", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBorderLeftTab1;
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
