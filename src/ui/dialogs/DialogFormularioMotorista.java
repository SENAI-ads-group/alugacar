package ui.dialogs;

import model.entidades.Endereco;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.entidades.CNH;
import model.exceptions.ValidacaoException;
import ui.listeners.DataChangeListener;
import ui.panels.formularios.FormularioEndereco;
import ui.panels.formularios.FormularioPessoaFisica;
import util.DateUtilities;
import util.PanelUtilities;
import model.servicos.persistencia.MotoristaDAO;
import ui.panels.formularios.FormularioCNH;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogFormularioMotorista extends javax.swing.JDialog {

    private Motorista entidade;
    private final MotoristaDAO DAO = DAOFactory.createMotoristaDAO();
    private FormularioEndereco formularioEndereco;
    private FormularioPessoaFisica formularioPessoaFisica;
    private FormularioCNH formularioCNH;
    private final List<DataChangeListener> listeners = new ArrayList<>();

    public DialogFormularioMotorista(java.awt.Frame parent, boolean modal, Motorista entidade) {
        super(parent, modal);
        this.entidade = entidade;
        initComponents();
        carregarFormularios();
    }

    private void carregarFormularios() {
        formularioEndereco = new FormularioEndereco(entidade.getPessoa().getEndereco());
        formularioPessoaFisica = new FormularioPessoaFisica(entidade.getPessoa());
        formularioCNH = new FormularioCNH(entidade.getCnh());

        PanelUtilities.loadPanelToPanel(formularioPessoaFisica, panelCenterTab1);
        PanelUtilities.loadPanelToPanel(formularioCNH, panelCenterTab2);
        PanelUtilities.loadPanelToPanel(formularioEndereco, panelCenterTab3);
    }

    private void persistirEntidade() throws DBException, ValidacaoException {
        entidade = getEntidade();
        if (entidade.getId() == null) {
            DAO.inserir(entidade);
        } else {
            DAO.atualizar(entidade);
        }
    }

    public Motorista getEntidade() throws ValidacaoException {
        PessoaFisica pessoa = formularioPessoaFisica.getDadosFormulario();
        if (DateUtilities.getAge(pessoa.getDataNascimento()) < Motorista.IDADE_MINIMA) {
            ValidacaoException exception = new ValidacaoException("PanelFormPessoaFisica");
            exception.addError("dataNascimento", "Idade mínima " + Motorista.IDADE_MINIMA + " anos");
            throw exception;
        }
        CNH cnh = formularioCNH.getDadosFormulario();
        Endereco endereco = formularioEndereco.getDadosFormulario();

        pessoa.setEndereco(endereco);
        entidade.setPessoa(pessoa);
        entidade.setCnh(cnh);
        return entidade;
    }

    public void atualizarFormulario() {
        formularioCNH.atualizarFormulario();
        formularioEndereco.atualizarFormulario();
        formularioPessoaFisica.atualizarFormulario();
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
        panelTab2 = new javax.swing.JPanel();
        panelTopTab2 = new javax.swing.JPanel();
        panelBorderLeftTab2 = new javax.swing.JPanel();
        panelCenterTab2 = new javax.swing.JPanel();
        panelBorderRightTab2 = new javax.swing.JPanel();
        panelTab3 = new javax.swing.JPanel();
        panelTopTab3 = new javax.swing.JPanel();
        panelBorderLeftTab3 = new javax.swing.JPanel();
        panelCenterTab3 = new javax.swing.JPanel();
        panelBorderRightTab4 = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de motorista");
        setMinimumSize(new java.awt.Dimension(470, 445));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setSize(new java.awt.Dimension(470, 445));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(430, 325));
        tabbedPane.setMinimumSize(new java.awt.Dimension(430, 325));
        tabbedPane.setPreferredSize(new java.awt.Dimension(430, 325));
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
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderLeftTab1, java.awt.BorderLayout.LINE_START);

        panelCenterTab1.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab1.setPreferredSize(new java.awt.Dimension(400, 300));
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
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderRightTab1, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Informações Pessoais", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")), panelTab1, "Informações pessoais básicas do motorista"); // NOI18N

        panelTab2.setBackground(new java.awt.Color(153, 153, 153));
        panelTab2.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab2.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab2.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab2.setLayout(new java.awt.BorderLayout());

        panelTopTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab2.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab2.add(panelTopTab2, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab2.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab2.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab2Layout = new javax.swing.GroupLayout(panelBorderLeftTab2);
        panelBorderLeftTab2.setLayout(panelBorderLeftTab2Layout);
        panelBorderLeftTab2Layout.setHorizontalGroup(
            panelBorderLeftTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab2Layout.setVerticalGroup(
            panelBorderLeftTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderLeftTab2, java.awt.BorderLayout.LINE_START);

        panelCenterTab2.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab2.setPreferredSize(new java.awt.Dimension(400, 290));
        panelCenterTab2.setLayout(new javax.swing.BoxLayout(panelCenterTab2, javax.swing.BoxLayout.LINE_AXIS));
        panelTab2.add(panelCenterTab2, java.awt.BorderLayout.CENTER);

        panelBorderRightTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab2.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab2.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab2Layout = new javax.swing.GroupLayout(panelBorderRightTab2);
        panelBorderRightTab2.setLayout(panelBorderRightTab2Layout);
        panelBorderRightTab2Layout.setHorizontalGroup(
            panelBorderRightTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab2Layout.setVerticalGroup(
            panelBorderRightTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderRightTab2, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("CNH", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png")), panelTab2, "Informaçõoes sobre o motorista e sua CNH"); // NOI18N

        panelTab3.setBackground(new java.awt.Color(153, 153, 153));
        panelTab3.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab3.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab3.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab3.setLayout(new java.awt.BorderLayout());

        panelTopTab3.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab3.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab3.add(panelTopTab3, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab3.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab3.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab3.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab3Layout = new javax.swing.GroupLayout(panelBorderLeftTab3);
        panelBorderLeftTab3.setLayout(panelBorderLeftTab3Layout);
        panelBorderLeftTab3Layout.setHorizontalGroup(
            panelBorderLeftTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab3Layout.setVerticalGroup(
            panelBorderLeftTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderLeftTab3, java.awt.BorderLayout.LINE_START);

        panelCenterTab3.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab3.setPreferredSize(new java.awt.Dimension(400, 300));
        panelCenterTab3.setLayout(new javax.swing.BoxLayout(panelCenterTab3, javax.swing.BoxLayout.LINE_AXIS));
        panelTab3.add(panelCenterTab3, java.awt.BorderLayout.CENTER);

        panelBorderRightTab4.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab4.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab4.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab4Layout = new javax.swing.GroupLayout(panelBorderRightTab4);
        panelBorderRightTab4.setLayout(panelBorderRightTab4Layout);
        panelBorderRightTab4Layout.setHorizontalGroup(
            panelBorderRightTab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab4Layout.setVerticalGroup(
            panelBorderRightTab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderRightTab4, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Endereço", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")), panelTab3, "Informações sobre o endereço do motorista"); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(430, 50));
        panelButtons.setMinimumSize(new java.awt.Dimension(430, 50));
        panelButtons.setPreferredSize(new java.awt.Dimension(430, 50));
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

        getContentPane().add(panelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 325, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        tabbedPane.setIconAt(0, new ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")));
        tabbedPane.setIconAt(1, new ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png")));
        tabbedPane.setIconAt(2, new ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")));
        try {
            persistirEntidade();
            this.dispose();
            notifyListeners();
        } catch (ValidacaoException ex) {
            Icon iconError = new ImageIcon(getClass().getResource("/ui/media/icons/icon-erro-24x24.png"));
            if (ex.getMessage().equals("PessoaFisica")) {
                tabbedPane.setIconAt(0, iconError);
                formularioPessoaFisica.exibirMensagensErro(ex.getErrors());
            }
            if (ex.getMessage().equals("CNH")) {
                tabbedPane.setIconAt(1, iconError);
                formularioCNH.exibirMensagensErro(ex.getErrors());
            }
            if (ex.getMessage().equals("Endereco")) {
                tabbedPane.setIconAt(2, iconError);
                formularioEndereco.exibirMensagensErro(ex.getErrors());
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
    private javax.swing.JPanel panelBorderLeftTab2;
    private javax.swing.JPanel panelBorderLeftTab3;
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelBorderRightTab2;
    private javax.swing.JPanel panelBorderRightTab4;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelCenterTab2;
    private javax.swing.JPanel panelCenterTab3;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTab2;
    private javax.swing.JPanel panelTab3;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JPanel panelTopTab2;
    private javax.swing.JPanel panelTopTab3;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
