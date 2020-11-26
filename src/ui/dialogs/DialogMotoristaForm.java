package ui.dialogs;

import model.entidades.CNH;
import model.entidades.Endereco;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.enums.CategoriaCNH;
import model.entidades.services.persistence.MotoristaPersistenceService;
import model.entidades.services.persistence.PersistenceFactory;
import model.entidades.services.persistence.exceptions.PersistenceException;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.exceptions.ValidationException;
import ui.listeners.DataChangeListener;
import ui.panels.PanelFormEndereco;
import ui.panels.PanelFormPessoaFisica;
import ui.panels.PanelMotoristasList;
import util.DateUtilities;
import util.PanelUtilities;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogMotoristaForm extends javax.swing.JDialog {

    private Motorista motorista;
    private MotoristaPersistenceService MotoristaPersistenceService = PersistenceFactory.createMotoristaService();

    private PanelFormEndereco panelFormEndereco;
    private PanelFormPessoaFisica panelFormPessoaFisica;
    private PanelMotoristasList panelMotoristasList;
    private File fileFotoMotorista;

    private final List<DataChangeListener> listeners;

    public DialogMotoristaForm(java.awt.Frame parent, boolean modal, Motorista motorista) {
        super(parent, modal);
        this.motorista = motorista;
        initComponents();
        initCombobox();
        loadPanels();

        listeners = new ArrayList<>();
    }

    private void loadPanels() {
        panelFormEndereco = new PanelFormEndereco(motorista.getPessoa().getEndereco());
        panelFormPessoaFisica = new PanelFormPessoaFisica(motorista.getPessoa());

        PanelUtilities.loadPanelToPanel(panelFormEndereco, panelEndereco);
        PanelUtilities.loadPanelToPanel(panelFormPessoaFisica, panelInfoPessoais);
    }

    private void initCombobox() {
        comboBoxCategoriaCNH.setModel(new DefaultComboBoxModel(CategoriaCNH.values()));
    }

    private void persistEntity() throws PersistenceException, ValidationException {
        motorista = getFormData();
        if (motorista.getId() == null) {
            MotoristaPersistenceService.inserir(motorista);
        } else {
            MotoristaPersistenceService.atualizar(motorista);
        }
    }

    public Motorista getFormData() throws ValidationException {
        Endereco endereco = panelFormEndereco.getFormData();
        PessoaFisica pessoa = panelFormPessoaFisica.getFormData();
        if (DateUtilities.getAge(pessoa.getDataNascimento()) < Motorista.IDADE_MINIMA) {
            ValidationException exception = new ValidationException("Exceção motorista fora da idade mínima");
            exception.addError("dataNascimento", "Idade mínima " + Motorista.IDADE_MINIMA + " anos");
            throw exception;
        }
        pessoa.setEndereco(endereco);
        motorista.setPessoa(pessoa);
        Integer numeroRegistroCNH = Utilities.tryParseToInteger(textFieldNumeroRegistro.getText());
        Date dataValidadeCNH = dateChooserValidadeCNH.getDate();
        CategoriaCNH categoriaCNH = CategoriaCNH.valueOf(comboBoxCategoriaCNH.getSelectedItem().toString());
        CNH cnh = new CNH(numeroRegistroCNH, categoriaCNH, dataValidadeCNH);
        motorista.setCnh(cnh);
        motorista.setFoto(fileFotoMotorista);

        return motorista;
    }

    public void subscribeListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        if (listeners.size() > 0) {
            for (DataChangeListener listener : listeners) {
                listener.onDataChanged();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        panelInfoPessoais = new javax.swing.JPanel();
        panelCNH = new javax.swing.JPanel();
        textFieldNumeroRegistro = new javax.swing.JTextField();
        labelNome1 = new javax.swing.JLabel();
        labelCPF1 = new javax.swing.JLabel();
        comboBoxCategoriaCNH = new javax.swing.JComboBox<>();
        labelUF1 = new javax.swing.JLabel();
        textFieldFoto = new javax.swing.JTextField();
        labelFoto = new javax.swing.JLabel();
        buttonBuscarFoto = new javax.swing.JButton();
        dateChooserValidadeCNH = new com.toedter.calendar.JDateChooser();
        labelShowFoto = new javax.swing.JLabel();
        labelErroFoto = new javax.swing.JLabel();
        labelErroNumRegistros = new javax.swing.JLabel();
        labelErroCategoriaCNH = new javax.swing.JLabel();
        labelErroDataValidadeCNH = new javax.swing.JLabel();
        panelEndereco = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        buttonConfirmar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de motorista");
        setMaximumSize(new java.awt.Dimension(400, 350));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(400, 270));
        tabbedPane.setMinimumSize(new java.awt.Dimension(400, 270));
        tabbedPane.setPreferredSize(new java.awt.Dimension(400, 270));

        panelInfoPessoais.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoPessoais.setMaximumSize(new java.awt.Dimension(400, 250));
        panelInfoPessoais.setMinimumSize(new java.awt.Dimension(400, 250));
        panelInfoPessoais.setPreferredSize(new java.awt.Dimension(400, 250));
        tabbedPane.addTab("Informações pessoais", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")), panelInfoPessoais, "Informações pessoais do motorista"); // NOI18N

        panelCNH.setBackground(new java.awt.Color(255, 255, 255));
        panelCNH.setMaximumSize(new java.awt.Dimension(400, 250));
        panelCNH.setMinimumSize(new java.awt.Dimension(400, 250));
        panelCNH.setPreferredSize(new java.awt.Dimension(400, 250));
        panelCNH.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNumeroRegistro.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(textFieldNumeroRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        labelNome1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNome1.setText("Número de registro");
        panelCNH.add(labelNome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        labelCPF1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCPF1.setText("Data validade");
        panelCNH.add(labelCPF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, -1, -1));

        comboBoxCategoriaCNH.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(comboBoxCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelUF1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelUF1.setText("Categoria");
        panelCNH.add(labelUF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldFoto.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setPreferredSize(new java.awt.Dimension(140, 25));
        panelCNH.add(textFieldFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 95, -1, -1));

        labelFoto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelFoto.setText("Foto");
        panelCNH.add(labelFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 75, 110, -1));

        buttonBuscarFoto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonBuscarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pasta-24x24.png"))); // NOI18N
        buttonBuscarFoto.setBorderPainted(false);
        buttonBuscarFoto.setFocusPainted(false);
        buttonBuscarFoto.setFocusable(false);
        buttonBuscarFoto.setPreferredSize(new java.awt.Dimension(30, 25));
        buttonBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarFotoActionPerformed(evt);
            }
        });
        panelCNH.add(buttonBuscarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 95, -1, -1));

        dateChooserValidadeCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(dateChooserValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 95, -1, -1));

        labelShowFoto.setMaximumSize(new java.awt.Dimension(170, 90));
        labelShowFoto.setMinimumSize(new java.awt.Dimension(170, 90));
        labelShowFoto.setPreferredSize(new java.awt.Dimension(170, 90));
        panelCNH.add(labelShowFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 135, -1, -1));

        labelErroFoto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroFoto.setForeground(java.awt.Color.red);
        labelErroFoto.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        labelErroNumRegistros.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNumRegistros.setForeground(java.awt.Color.red);
        labelErroNumRegistros.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistros.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistros.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroNumRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 55, -1, -1));

        labelErroCategoriaCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoriaCNH.setForeground(java.awt.Color.red);
        labelErroCategoriaCNH.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, -1, -1));

        labelErroDataValidadeCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataValidadeCNH.setForeground(java.awt.Color.red);
        labelErroDataValidadeCNH.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataValidadeCNH.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataValidadeCNH.setPreferredSize(new java.awt.Dimension(150, 15));
        panelCNH.add(labelErroDataValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

        tabbedPane.addTab("CNH", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png")), panelCNH); // NOI18N

        panelEndereco.setBackground(new java.awt.Color(255, 255, 255));
        panelEndereco.setMaximumSize(new java.awt.Dimension(400, 300));
        panelEndereco.setMinimumSize(new java.awt.Dimension(400, 250));
        panelEndereco.setPreferredSize(new java.awt.Dimension(400, 250));
        tabbedPane.addTab("Endereço", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")), panelEndereco, "Dados do endereço do motorista"); // NOI18N

        getContentPane().add(tabbedPane, java.awt.BorderLayout.PAGE_START);

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(400, 70));
        panelButtons.setMinimumSize(new java.awt.Dimension(400, 10));
        panelButtons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        panelButtons.add(buttonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 5, 115, -1));

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
        panelButtons.add(buttonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 5, -1, -1));

        getContentPane().add(panelButtons, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {
            persistEntity();
            this.dispose();
            notifyListeners();
        } catch (ValidationException ex) {
            panelFormPessoaFisica.setErrorsMessages(ex.getErrors());
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao persistir motorista", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarFotoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("imagem", "jpg", "jpeg", "png"));

        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            fileFotoMotorista = fileChooser.getSelectedFile();
            textFieldFoto.setText(fileFotoMotorista.getPath());
            ImageIcon imageIcon = new ImageIcon(fileFotoMotorista.getPath());
            Image image = imageIcon.getImage().getScaledInstance(labelShowFoto.getWidth(), labelShowFoto.getHeight(), Image.SCALE_DEFAULT);
            labelShowFoto.setIcon(new ImageIcon(image));
            labelShowFoto.repaint();
        }
    }//GEN-LAST:event_buttonBuscarFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscarFoto;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JComboBox<String> comboBoxCategoriaCNH;
    private com.toedter.calendar.JDateChooser dateChooserValidadeCNH;
    private javax.swing.JLabel labelCPF1;
    private javax.swing.JLabel labelErroCategoriaCNH;
    private javax.swing.JLabel labelErroDataValidadeCNH;
    private javax.swing.JLabel labelErroFoto;
    private javax.swing.JLabel labelErroNumRegistros;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelShowFoto;
    private javax.swing.JLabel labelUF1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCNH;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelInfoPessoais;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField textFieldFoto;
    private javax.swing.JTextField textFieldNumeroRegistro;
    // End of variables declaration//GEN-END:variables
}
