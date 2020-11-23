package ui.dialogs;

import entidades.Endereco;
import entidades.Motorista;
import entidades.enums.CategoriaCNH;
import entidades.enums.UF;
import java.awt.Image;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import ui.panels.PanelFormEndereco;
import ui.panels.PanelFormPessoaFisica;

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
        loadPanels();

    }

    private void loadPanels() {
        panelEndereco.removeAll();
        panelEndereco.revalidate();
        panelEndereco.repaint();
        panelInfoPessoais.removeAll();
        panelInfoPessoais.revalidate();
        panelInfoPessoais.repaint();

        PanelFormEndereco panelFormEndereco = new PanelFormEndereco(motorista.getPessoa().getEndereco());
        PanelFormPessoaFisica panelFormPessoaFisica = new PanelFormPessoaFisica(motorista.getPessoa());

        panelEndereco.add(panelFormEndereco);
        panelEndereco.revalidate();
        panelEndereco.repaint();
        panelInfoPessoais.add(panelFormPessoaFisica);
        panelInfoPessoais.revalidate();
        panelInfoPessoais.repaint();
    }

    private void initCombobox() {
        comboBoxCategoriaCNH.setModel(new DefaultComboBoxModel(CategoriaCNH.values()));
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
        panelEndereco = new javax.swing.JPanel();
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
    private com.toedter.calendar.JDateChooser dateChooserValidadeCNH;
    private javax.swing.JLabel labelCPF1;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelShowFoto;
    private javax.swing.JLabel labelUF1;
    private javax.swing.JPanel panelButtoons;
    private javax.swing.JPanel panelCNH;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelInfoPessoais;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField textFieldFoto;
    private javax.swing.JTextField textFieldNumeroRegistro;
    // End of variables declaration//GEN-END:variables
}
