package ui.panels;

import entidades.PessoaFisica;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class PanelFormPessoaFisica extends javax.swing.JPanel {

    private PessoaFisica pessoa;

    public PanelFormPessoaFisica(PessoaFisica pessoa) {
        initComponents();
        this.pessoa = pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public void updateFormData() {
        if (pessoa == null) {
            throw new IllegalStateException("pessoaFísica está nulo");
        }
        textFieldNome.setText(pessoa.getNome());
        textFieldEmail.setText(pessoa.getEmail());
        textFieldCPF.setText(pessoa.getCpf());
        textFieldRG.setText("" + pessoa.getRegistroGeral());
        textFieldTelefone.setText(pessoa.getTelefone());
        dateChooserNascimento.setDate(pessoa.getDataNascimento());
    }

    public PessoaFisica getFormData() {
        if (pessoa == null) {
            pessoa = new PessoaFisica();
        }
        pessoa.setNome(textFieldNome.getText());
        pessoa.setCpf(textFieldCPF.getText());
        pessoa.setTelefone(textFieldTelefone.getText());
        pessoa.setRegistroGeral(Utilities.tryParseToInteger(textFieldRG.getText()));
        pessoa.setEmail(textFieldEmail.getText());
        pessoa.setDataNascimento(dateChooserNascimento.getDate());
        return pessoa;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 240));
        setMinimumSize(new java.awt.Dimension(400, 240));
        setPreferredSize(new java.awt.Dimension(400, 240));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNome.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNome.setText("Nome");
        add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        textFieldTelefone.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setName(""); // NOI18N
        textFieldTelefone.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelTelefone.setText("Telefone");
        add(labelTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        labelEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelEmail.setText("Email");
        add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        textFieldCPF.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        labelCPF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCPF.setText("CPF");
        add(labelCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataNascimento.setText("Data de nascimento");
        add(labelDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        textFieldRG.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setName(""); // NOI18N
        textFieldRG.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        labelRG.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelRG.setText("RG");
        add(labelRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        dateChooserNascimento.setPreferredSize(new java.awt.Dimension(170, 25));
        add(dateChooserNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooserNascimento;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JTextField textFieldCPF;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldRG;
    private javax.swing.JTextField textFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
