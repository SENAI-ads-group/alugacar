package ui.dialogs;

import entidades.Motorista;
import entidades.enums.CategoriaCNH;
import entidades.enums.UF;
import java.awt.Image;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogMotoristaForm extends javax.swing.JDialog {

    private Motorista motorista;

    public DialogMotoristaForm(java.awt.Frame parent, boolean modal, Motorista motorista) {
        super(parent, modal);
        this.motorista = motorista;
        initComponents();
        initCombobox();
    }

    private void initCombobox() {
        comboBoxCategoriaCNH.setModel(new DefaultComboBoxModel(CategoriaCNH.values()));
        comboBoxUF.setModel(new DefaultComboBoxModel(UF.values()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        panelInfoPessoais = new javax.swing.JPanel();
        textFieldNome = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        textFieldTelefone = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        textFieldCPF = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        labelDataNascimento = new javax.swing.JLabel();
        textFieldRG = new javax.swing.JTextField();
        labelRG = new javax.swing.JLabel();
        dateChooserNascimento = new com.toedter.calendar.JDateChooser();
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
        panelEndereco = new javax.swing.JPanel();
        textFieldLogradouro = new javax.swing.JTextField();
        labelLogradouro = new javax.swing.JLabel();
        textFieldNumero = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        textFieldBairro = new javax.swing.JTextField();
        labelUF = new javax.swing.JLabel();
        textFieldComplemento = new javax.swing.JTextField();
        labelComplemento = new javax.swing.JLabel();
        labelCidade = new javax.swing.JLabel();
        textFieldCidade = new javax.swing.JTextField();
        labelCEP = new javax.swing.JLabel();
        textFieldCEP = new javax.swing.JTextField();
        comboBoxUF = new javax.swing.JComboBox<>();
        labelBairro = new javax.swing.JLabel();
        panelButtoons = new javax.swing.JPanel();
        buttonConfirmar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de motorista");
        setMinimumSize(new java.awt.Dimension(400, 300));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(400, 240));
        tabbedPane.setMinimumSize(new java.awt.Dimension(400, 240));
        tabbedPane.setPreferredSize(new java.awt.Dimension(400, 240));

        panelInfoPessoais.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoPessoais.setMaximumSize(new java.awt.Dimension(400, 240));
        panelInfoPessoais.setMinimumSize(new java.awt.Dimension(400, 240));
        panelInfoPessoais.setPreferredSize(new java.awt.Dimension(400, 240));
        panelInfoPessoais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNome.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNome.setText("Nome");
        panelInfoPessoais.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        textFieldTelefone.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setName(""); // NOI18N
        textFieldTelefone.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(textFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelTelefone.setText("Telefone");
        panelInfoPessoais.add(labelTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        labelEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelEmail.setText("Email");
        panelInfoPessoais.add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        textFieldCPF.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(textFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        labelCPF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCPF.setText("CPF");
        panelInfoPessoais.add(labelCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataNascimento.setText("Data de nascimento");
        panelInfoPessoais.add(labelDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        textFieldRG.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setName(""); // NOI18N
        textFieldRG.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(textFieldRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        labelRG.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelRG.setText("RG");
        panelInfoPessoais.add(labelRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        dateChooserNascimento.setPreferredSize(new java.awt.Dimension(170, 25));
        panelInfoPessoais.add(dateChooserNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        tabbedPane.addTab("Informações Pessoais", panelInfoPessoais);

        panelCNH.setBackground(new java.awt.Color(255, 255, 255));
        panelCNH.setMaximumSize(new java.awt.Dimension(400, 240));
        panelCNH.setMinimumSize(new java.awt.Dimension(400, 240));
        panelCNH.setPreferredSize(new java.awt.Dimension(400, 240));
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
        panelCNH.add(labelCPF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        comboBoxCategoriaCNH.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(comboBoxCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelUF1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelUF1.setText("Categoria");
        panelCNH.add(labelUF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldFoto.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setPreferredSize(new java.awt.Dimension(140, 25));
        panelCNH.add(textFieldFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        labelFoto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelFoto.setText("Foto");
        panelCNH.add(labelFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 110, -1));

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
        panelCNH.add(buttonBuscarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        dateChooserValidadeCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(dateChooserValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        labelShowFoto.setMaximumSize(new java.awt.Dimension(360, 50));
        labelShowFoto.setMinimumSize(new java.awt.Dimension(360, 50));
        labelShowFoto.setPreferredSize(new java.awt.Dimension(360, 50));
        panelCNH.add(labelShowFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 170, 90));

        tabbedPane.addTab("CNH", panelCNH);

        panelEndereco.setBackground(new java.awt.Color(255, 255, 255));
        panelEndereco.setMaximumSize(new java.awt.Dimension(400, 240));
        panelEndereco.setMinimumSize(new java.awt.Dimension(400, 240));
        panelEndereco.setPreferredSize(new java.awt.Dimension(400, 240));
        panelEndereco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldLogradouro.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        labelLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelLogradouro.setText("Logradouro");
        panelEndereco.add(labelLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        textFieldNumero.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNumero.setText("Número");
        panelEndereco.add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldBairro.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        labelUF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelUF.setText("UF");
        panelEndereco.add(labelUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        textFieldComplemento.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        labelComplemento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelComplemento.setText("Complemento");
        panelEndereco.add(labelComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelCidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCidade.setText("Cidade");
        panelEndereco.add(labelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        textFieldCidade.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        labelCEP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCEP.setText("CEP");
        panelEndereco.add(labelCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        textFieldCEP.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(textFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        comboBoxUF.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxUF.setPreferredSize(new java.awt.Dimension(170, 25));
        panelEndereco.add(comboBoxUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        labelBairro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelBairro.setText("Bairro");
        panelEndereco.add(labelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        tabbedPane.addTab("Endereço", panelEndereco);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.PAGE_START);

        panelButtoons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtoons.setMaximumSize(new java.awt.Dimension(400, 60));
        panelButtoons.setMinimumSize(new java.awt.Dimension(400, 60));
        panelButtoons.setPreferredSize(new java.awt.Dimension(400, 60));
        panelButtoons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        panelButtoons.add(buttonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 115, -1));

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
        panelButtoons.add(buttonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        getContentPane().add(panelButtoons, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed

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
            File fileSelected = fileChooser.getSelectedFile();
            textFieldFoto.setText(fileSelected.getPath());
            ImageIcon imageIcon = new ImageIcon(fileSelected.getPath());
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
    private javax.swing.JComboBox<String> comboBoxUF;
    private com.toedter.calendar.JDateChooser dateChooserNascimento;
    private com.toedter.calendar.JDateChooser dateChooserValidadeCNH;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelCPF1;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelLogradouro;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelShowFoto;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelUF;
    private javax.swing.JLabel labelUF1;
    private javax.swing.JPanel panelButtoons;
    private javax.swing.JPanel panelCNH;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelInfoPessoais;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField textFieldBairro;
    private javax.swing.JTextField textFieldCEP;
    private javax.swing.JTextField textFieldCPF;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldComplemento;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldFoto;
    private javax.swing.JTextField textFieldLogradouro;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldNumero;
    private javax.swing.JTextField textFieldNumeroRegistro;
    private javax.swing.JTextField textFieldRG;
    private javax.swing.JTextField textFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
