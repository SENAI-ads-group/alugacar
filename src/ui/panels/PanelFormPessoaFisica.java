package ui.panels;

import java.util.Map;
import java.util.Set;
import model.entidades.PessoaFisica;
import model.exceptions.ValidacaoException;
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
        if (pessoa != null) {
            textFieldNome.setText(pessoa.getNome());
            textFieldEmail.setText(pessoa.getEmail());
            textFieldCPF.setText(pessoa.getCpf());
            textFieldRG.setText(pessoa.getRegistroGeral());
            textFieldTelefone.setText(pessoa.getTelefone());
            dateChooserNascimento.setDate(pessoa.getDataNascimento());
        }
    }

    public PessoaFisica getFormData() throws ValidacaoException {
        if (pessoa == null) {
            pessoa = new PessoaFisica();
        }
        ValidacaoException exception = new ValidacaoException(getClass().getSimpleName());
        if (Utilities.textFieldIsEmpty(textFieldNome)) {
            exception.addError("nome", "Nome não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldCPF)) {
            exception.addError("CPF", "CPF não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldRG)) {
            exception.addError("RG", "RG não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldTelefone)) {
            exception.addError("telefone", "Telefone não informado");
        }
        if (dateChooserNascimento.getDate() == null) {
            exception.addError("dataNascimento", "Data não informada");
        }
        pessoa.setNome(textFieldNome.getText());
        pessoa.setCpf(textFieldCPF.getText());
        pessoa.setTelefone(textFieldTelefone.getText());
        pessoa.setRegistroGeral(textFieldRG.getText());
        pessoa.setEmail(textFieldEmail.getText());
        pessoa.setDataNascimento(dateChooserNascimento.getDate());

        clearErrors();
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
        return pessoa;
    }

    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("nome")) {
            labelErroNome.setText(errors.get("nome"));
        }
        if (fields.contains("CPF")) {
            labelErroCPF.setText(errors.get("CPF"));
        }
        if (fields.contains("RG")) {
            labelErroRG.setText(errors.get("RG"));
        }
        if (fields.contains("telefone")) {
            labelErroTelefone.setText(errors.get("telefone"));
        }
        if (fields.contains("dataNascimento")) {
            labelErroDataNascimento.setText(errors.get("dataNascimento"));
        }
    }

    private void clearErrors() {
        labelErroNome.setText("");
        labelErroCPF.setText("");
        labelErroRG.setText("");
        labelErroTelefone.setText("");
        labelErroDataNascimento.setText("");
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
        labelErroDataNascimento = new javax.swing.JLabel();
        labelErroNome = new javax.swing.JLabel();
        labelErroCPF = new javax.swing.JLabel();
        labelErroTelefone = new javax.swing.JLabel();
        labelErroRG = new javax.swing.JLabel();
        labelErroEmail = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(360, 280));
        setMinimumSize(new java.awt.Dimension(360, 280));
        setPreferredSize(new java.awt.Dimension(360, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNome.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNome.setText("Nome");
        add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldTelefone.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldTelefone.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldTelefone.setName(""); // NOI18N
        textFieldTelefone.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelTelefone.setText("Telefone");
        add(labelTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

        labelEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelEmail.setText("Email");
        add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        textFieldCPF.setToolTipText("Apenas números (ex: 123.456.789-00)");
        textFieldCPF.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldCPF.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, -1, -1));

        labelCPF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCPF.setText("CPF");
        add(labelCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, -1, -1));

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataNascimento.setText("Data de nascimento");
        add(labelDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        textFieldRG.setToolTipText("Apenas números (ex: 123456-7)");
        textFieldRG.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldRG.setName(""); // NOI18N
        textFieldRG.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 85, -1, -1));

        labelRG.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelRG.setText("RG");
        add(labelRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 65, -1, -1));

        dateChooserNascimento.setPreferredSize(new java.awt.Dimension(170, 25));
        add(dateChooserNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        labelErroDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataNascimento.setForeground(java.awt.Color.red);
        labelErroDataNascimento.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataNascimento.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataNascimento.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 175, -1, -1));

        labelErroNome.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNome.setForeground(java.awt.Color.red);
        labelErroNome.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        labelErroCPF.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCPF.setForeground(java.awt.Color.red);
        labelErroCPF.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCPF.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCPF.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 170, -1));

        labelErroTelefone.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroTelefone.setForeground(java.awt.Color.red);
        labelErroTelefone.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroTelefone.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroTelefone.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        labelErroRG.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroRG.setForeground(java.awt.Color.red);
        labelErroRG.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroRG.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroRG.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, -1));

        labelErroEmail.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroEmail.setForeground(java.awt.Color.red);
        labelErroEmail.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroEmail.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroEmail.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, 170, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooserNascimento;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelErroCPF;
    private javax.swing.JLabel labelErroDataNascimento;
    private javax.swing.JLabel labelErroEmail;
    private javax.swing.JLabel labelErroNome;
    private javax.swing.JLabel labelErroRG;
    private javax.swing.JLabel labelErroTelefone;
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
