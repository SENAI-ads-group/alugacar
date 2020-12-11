package ui.dialogs;

import model.entidades.Endereco;
import model.entidades.PessoaFisica;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.entidades.Cliente;
import model.entidades.Pessoa;
import model.entidades.PessoaJuridica;
import model.entidades.enums.TipoCliente;
import model.exceptions.ValidacaoException;
import ui.listeners.DataChangeListener;
import ui.paineis.formularios.FormularioEndereco;
import ui.paineis.formularios.FormularioPessoaFisica;
import ui.paineis.formularios.FormularioPessoaJuridica;
import util.DateUtilities;
import util.PanelUtilities;
import model.servicos.persistencia.interfaces.ClienteDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogCliente extends javax.swing.JDialog {

    private Cliente cliente;
    private final ClienteDAO DAO = DAOFactory.createClienteDAO();

    private FormularioEndereco formularioEndereco;
    private FormularioPessoaFisica formularioPF;
    private FormularioPessoaJuridica formularioPJ;

    private final List<DataChangeListener> listeners;

    public DialogCliente(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        this.cliente = cliente;
        initComponents();
        carregarPaineis(cliente.getTipoCliente());

        listeners = new ArrayList<>();
    }

    private void carregarPaineis(TipoCliente tipoCliente) {
        formularioEndereco = new FormularioEndereco(cliente.getPessoa().getEndereco());
        if (tipoCliente == TipoCliente.PESSOA_FISICA) {
            formularioPF = new FormularioPessoaFisica((PessoaFisica) cliente.getPessoa());
            PanelUtilities.loadPanelToPanel(formularioPF, panelCenterTab1);
        } else {
            formularioPJ = new FormularioPessoaJuridica((PessoaJuridica) cliente.getPessoa());
            PanelUtilities.loadPanelToPanel(formularioPJ, panelCenterTab1);
        }
        atualizarIconesAbas();
        PanelUtilities.loadPanelToPanel(formularioEndereco, panelCenterTab2);
    }

    private void persistirEntidade() throws DBException, ValidacaoException {
        cliente = getDadosFormulario();
        if (cliente.getId() == null) {
            DAO.inserir(cliente);
        } else {
            DAO.atualizar(cliente);
        }
    }

    public Cliente getDadosFormulario() throws ValidacaoException {
        Pessoa pessoa = null;
        if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
            pessoa = formularioPF.getDadosFormulario();
            if (DateUtilities.getAge(((PessoaFisica) pessoa).getDataNascimento()) < Cliente.IDADE_MINIMA) {
                ValidacaoException exception = new ValidacaoException("PessoaFisica");
                exception.addError("dataNascimento", "Idade mínima " + Cliente.IDADE_MINIMA + " anos");
                throw exception;
            }
        } else {
            pessoa = formularioPJ.getFormData();
        }
        Endereco endereco = formularioEndereco.getDadosFormulario();
        pessoa.setEndereco(endereco);
        cliente.setPessoa(pessoa);

        return cliente;
    }

    public void atualizarFormulario() {
        formularioEndereco.atualizarFormulario();
        if (formularioPF != null) {
            formularioPF.atualizarFormulario();
        }
        if (formularioPJ != null) {
            formularioPJ.updateFormData();
        }
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

    public void atualizarIconesAbas() {
        if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
            tabbedPane.setTitleAt(0, "Informações pessoais");
            tabbedPane.setToolTipTextAt(0, "Informações pessoais básicas sobre o cliente");
            tabbedPane.setIconAt(0, new ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")));
        } else if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
            tabbedPane.setTitleAt(0, "Informações empresariais");
            tabbedPane.setToolTipTextAt(0, "Informações sobre a empresa");
            tabbedPane.setIconAt(0, new ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoaJuridica-24x24.png")));
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
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de cliente");
        setMinimumSize(new java.awt.Dimension(470, 445));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(470, 445));
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

        tabbedPane.addTab("Informações", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")), panelTab1); // NOI18N

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
        panelCenterTab2.setPreferredSize(new java.awt.Dimension(400, 300));
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

        tabbedPane.addTab("Endereço", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")), panelTab2); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(400, 50));
        panelButtons.setMinimumSize(new java.awt.Dimension(400, 50));
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
        atualizarIconesAbas();
        try {
            persistirEntidade();
            notifyListeners();
            this.dispose();
        } catch (ValidacaoException ex) {
            Icon iconError = new ImageIcon(getClass().getResource("/ui/media/icons/icon-erro-24x24.png"));

            if (ex.getMessage().equals("PessoaFisica")) {
                tabbedPane.setIconAt(0, iconError);
                formularioPF.exibirMensagensErro(ex.getErrors());
            }
            if (ex.getMessage().equals("PessoaJuridica")) {
                tabbedPane.setIconAt(0, iconError);
                formularioPJ.setErrorsMessages(ex.getErrors());
            }
            if (ex.getMessage().equals("Endereco")) {
                tabbedPane.setIconAt(1, iconError);
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
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelBorderRightTab2;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelCenterTab2;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTab2;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JPanel panelTopTab2;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
