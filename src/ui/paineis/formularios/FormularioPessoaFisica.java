package ui.paineis.formularios;

import java.util.Map;
import java.util.Set;
import model.entidades.PessoaFisica;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class FormularioPessoaFisica extends javax.swing.JPanel {

    private PessoaFisica pessoa;

    public FormularioPessoaFisica(PessoaFisica pessoa) {
        initComponents();
        this.pessoa = pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public void atualizarFormulario() {
        if (pessoa != null) {
            textFieldNome.setText(pessoa.getNome());
            textFieldEmail.setText(pessoa.getEmail());
            formattedTextFieldCPF.setText(pessoa.getCpf());
            formattedTextFieldRG.setText(pessoa.getRegistroGeral());
            formattedTextFieldTelefone.setText(pessoa.getTelefone());
            dateChooserNascimento.setDate(pessoa.getDataNascimento());
        }
    }

    public PessoaFisica getDadosFormulario() throws ValidacaoException {
        if (pessoa == null) {
            pessoa = new PessoaFisica();
        }
        validarCampos();
        limparErros();
        pessoa.setNome(textFieldNome.getText());
        pessoa.setCpf(formattedTextFieldCPF.getText());
        pessoa.setTelefone(formattedTextFieldTelefone.getText());
        pessoa.setRegistroGeral(formattedTextFieldRG.getText());
        pessoa.setEmail(textFieldEmail.getText());
        pessoa.setDataNascimento(dateChooserNascimento.getDate());

        return pessoa;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException("PessoaFisica");
        if (FieldUtilities.textFieldIsEmpty(textFieldNome)) {
            exception.addError("nome", "Nome não informado");
        }
        if (FieldUtilities.formattedTextFieldIsEmpty(formattedTextFieldCPF)) {
            exception.addError("CPF", "CPF não informado");
        } else if (!FieldUtilities.formattedTextFieldIsValid(formattedTextFieldCPF)) {
            exception.addError("CPF", "CPF inválido");
        }
        if (FieldUtilities.formattedTextFieldIsEmpty(formattedTextFieldRG)) {
            exception.addError("RG", "RG não informado");
        } else if (!FieldUtilities.formattedTextFieldIsValid(formattedTextFieldRG)) {
            exception.addError("RG", "RG inválido");
        }
        if (FieldUtilities.formattedTextFieldIsEmpty(formattedTextFieldTelefone)) {
            exception.addError("telefone", "Telefone não informado");
        } else if (!FieldUtilities.formattedTextFieldIsValid(formattedTextFieldTelefone)) {
            exception.addError("telefone", "Telefone inválido");
        }
        if (dateChooserNascimento.getDate() == null) {
            exception.addError("dataNascimento", "Data não informada");
        }

        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void exibirMensagensErro(Map<String, String> erros) {
        Set<String> fields = erros.keySet();

        if (fields.contains("nome")) {
            labelErroNome.setText(erros.get("nome"));
        }
        if (fields.contains("CPF")) {
            labelErroCPF.setText(erros.get("CPF"));
        }
        if (fields.contains("RG")) {
            labelErroRG.setText(erros.get("RG"));
        }
        if (fields.contains("telefone")) {
            labelErroTelefone.setText(erros.get("telefone"));
        }
        if (fields.contains("dataNascimento")) {
            labelErroDataNascimento.setText(erros.get("dataNascimento"));
        }
    }

    private void limparErros() {
        labelErroNome.setText("");
        labelErroCPF.setText("");
        labelErroRG.setText("");
        labelErroTelefone.setText("");
        labelErroDataNascimento.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNome = new javax.swing.JLabel();
        labelTelefone = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelCPF = new javax.swing.JLabel();
        labelDataNascimento = new javax.swing.JLabel();
        labelRG = new javax.swing.JLabel();
        dateChooserNascimento = new com.toedter.calendar.JDateChooser();
        labelErroDataNascimento = new javax.swing.JLabel();
        labelErroNome = new javax.swing.JLabel();
        labelErroCPF = new javax.swing.JLabel();
        labelErroTelefone = new javax.swing.JLabel();
        labelErroRG = new javax.swing.JLabel();
        labelErroEmail = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        formattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        formattedTextFieldRG = new javax.swing.JFormattedTextField();
        formattedTextFieldCPF = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(390, 280));
        setMinimumSize(new java.awt.Dimension(390, 280));
        setPreferredSize(new java.awt.Dimension(390, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelNome.setText("Nome");
        add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelTelefone.setText("Telefone");
        add(labelTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldEmail.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

        labelEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelEmail.setText("Email");
        add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        labelCPF.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCPF.setText("CPF");
        add(labelCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, -1, -1));

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDataNascimento.setText("Data de nascimento");
        add(labelDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        labelRG.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelRG.setText("RG");
        add(labelRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 65, -1, -1));

        dateChooserNascimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        dateChooserNascimento.setPreferredSize(new java.awt.Dimension(190, 25));
        add(dateChooserNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        labelErroDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataNascimento.setForeground(java.awt.Color.red);
        labelErroDataNascimento.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataNascimento.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataNascimento.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 175, -1, -1));

        labelErroNome.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNome.setForeground(java.awt.Color.red);
        labelErroNome.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroCPF.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCPF.setForeground(java.awt.Color.red);
        labelErroCPF.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCPF.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCPF.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));

        labelErroTelefone.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroTelefone.setForeground(java.awt.Color.red);
        labelErroTelefone.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroTelefone.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroTelefone.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, -1, -1));

        labelErroRG.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroRG.setForeground(java.awt.Color.red);
        labelErroRG.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroRG.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroRG.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        labelErroEmail.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroEmail.setForeground(java.awt.Color.red);
        labelErroEmail.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroEmail.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroEmail.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, -1, -1));

        textFieldNome.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNome.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        util.FieldUtilities.setFieldOnlyText(textFieldNome, 25);

        formattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        formattedTextFieldTelefone.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));
        util.FieldUtilities.setFieldTelefone(formattedTextFieldTelefone);

        formattedTextFieldRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        formattedTextFieldRG.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 85, -1, -1));
        util.FieldUtilities.setFieldRG(formattedTextFieldRG);

        formattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        formattedTextFieldCPF.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, -1, -1));
        util.FieldUtilities.setFieldCPF(formattedTextFieldCPF);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooserNascimento;
    private javax.swing.JFormattedTextField formattedTextFieldCPF;
    private javax.swing.JFormattedTextField formattedTextFieldRG;
    private javax.swing.JFormattedTextField formattedTextFieldTelefone;
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
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldNome;
    // End of variables declaration//GEN-END:variables
}
