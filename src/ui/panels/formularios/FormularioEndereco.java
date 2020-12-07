package ui.panels.formularios;

import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import model.entidades.Endereco;
import model.entidades.enums.UF;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;

/**
 *
 * @author usuario
 */
public class FormularioEndereco extends javax.swing.JPanel {

    private Endereco endereco;

    public FormularioEndereco(Endereco endereco) {
        initComponents();
        this.endereco = endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void atualizarFormulario() {
        if (endereco != null) {
            textFieldLogradouro.setText(endereco.getLogradouro());
            textFieldNumero.setText(endereco.getNumero());
            textFieldComplemento.setText(endereco.getComplemento());
            textFieldBairro.setText(endereco.getBairro());
            textFieldCidade.setText(endereco.getCidade());
            formattedTextFieldCEP.setText(endereco.getCep());
            comboBoxUF.setSelectedIndex(endereco.getUf().ordinal());
        }
    }

    public Endereco getDadosFormulario() throws ValidacaoException {
        if (endereco == null) {
            endereco = new Endereco();
        }
        validarCampos();
        endereco.setLogradouro(textFieldLogradouro.getText());
        endereco.setNumero(textFieldNumero.getText());
        endereco.setComplemento(textFieldComplemento.getText());
        endereco.setBairro(textFieldBairro.getText());
        endereco.setCidade(textFieldCidade.getText());
        endereco.setCep(formattedTextFieldCEP.getText());
        endereco.setUf(comboBoxUF.getItemAt(comboBoxUF.getSelectedIndex()));

        return endereco;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException("Endereco");
        if (FieldUtilities.textFieldIsEmpty(textFieldLogradouro)) {
            exception.addError("logradouro", "Logradouro não informado");
        }
        if (FieldUtilities.formattedTextFieldIsEmpty(formattedTextFieldCEP)) {
            exception.addError("CEP", "CEP não informado");
        } else if (!FieldUtilities.formattedTextFieldIsValid(formattedTextFieldCEP)) {
            exception.addError("CEP", "CEP inválido");
        }
        if (FieldUtilities.textFieldIsEmpty(textFieldBairro)) {
            exception.addError("bairro", "Bairro não informado");
        }
        if (FieldUtilities.textFieldIsEmpty(textFieldCidade)) {
            exception.addError("cidade", "Cidade não informada");
        }
        if (FieldUtilities.textFieldIsEmpty(textFieldNumero) && FieldUtilities.textFieldIsEmpty(textFieldComplemento)) {
            exception.addError("numero", "Informe número ou complemento");
            exception.addError("complemento", "Informe número ou complemento");
        }

        limparErros();
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void exibirMensagensErro(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("logradouro")) {
            labelErroLogradouro.setText(errors.get("logradouro"));
        }
        if (fields.contains("CEP")) {
            labelErroCEP.setText(errors.get("CEP"));
        }
        if (fields.contains("bairro")) {
            labelErroBairro.setText(errors.get("bairro"));
        }
        if (fields.contains("cidade")) {
            labelErroCidade.setText(errors.get("cidade"));
        }
        if (fields.contains("numero")) {
            labelErroNumero.setText(errors.get("numero"));
        }
        if (fields.contains("complemento")) {
            labelErroComplemento.setText(errors.get("complemento"));
        }
    }

    public void limparErros() {
        labelErroLogradouro.setText("");
        labelErroNumero.setText("");
        labelErroCEP.setText("");
        labelErroBairro.setText("");
        labelErroCidade.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        comboBoxUF = new javax.swing.JComboBox<>();
        labelBairro = new javax.swing.JLabel();
        labelErroCidade = new javax.swing.JLabel();
        labelErroNumero = new javax.swing.JLabel();
        labelErroCEP = new javax.swing.JLabel();
        labelErroComplemento = new javax.swing.JLabel();
        labelErroLogradouro = new javax.swing.JLabel();
        labelErroBairro = new javax.swing.JLabel();
        formattedTextFieldCEP = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(390, 280));
        setMinimumSize(new java.awt.Dimension(390, 280));
        setPreferredSize(new java.awt.Dimension(390, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldLogradouro.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelLogradouro.setText("Logradouro");
        add(labelLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldNumero.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));
        FieldUtilities.setFieldOnlyInteger(textFieldNumero, 4);

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelNumero.setText("Número");
        add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        textFieldBairro.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        labelUF.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelUF.setText("UF");
        add(labelUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        textFieldComplemento.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        labelComplemento.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelComplemento.setText("Complemento");
        add(labelComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        labelCidade.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCidade.setText("Cidade");
        add(labelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        textFieldCidade.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        labelCEP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCEP.setText("CEP");
        add(labelCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        comboBoxUF.setModel(new DefaultComboBoxModel(UF.values()));
        comboBoxUF.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxUF.setPreferredSize(new java.awt.Dimension(190, 25));
        add(comboBoxUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        labelBairro.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelBairro.setText("Bairro");
        add(labelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        labelErroCidade.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCidade.setForeground(java.awt.Color.red);
        labelErroCidade.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCidade.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCidade.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 165, -1, -1));

        labelErroNumero.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNumero.setForeground(java.awt.Color.red);
        labelErroNumero.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroNumero.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroNumero.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, -1, -1));

        labelErroCEP.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCEP.setForeground(java.awt.Color.red);
        labelErroCEP.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCEP.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCEP.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 105, -1, -1));

        labelErroComplemento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroComplemento.setForeground(java.awt.Color.red);
        labelErroComplemento.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroComplemento.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroComplemento.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 105, -1, -1));

        labelErroLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroLogradouro.setForeground(java.awt.Color.red);
        labelErroLogradouro.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroLogradouro.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroLogradouro.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroBairro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroBairro.setForeground(java.awt.Color.red);
        labelErroBairro.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroBairro.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroBairro.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 165, -1, -1));

        formattedTextFieldCEP.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));
        FieldUtilities.setFieldCEP(formattedTextFieldCEP);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<UF> comboBoxUF;
    private javax.swing.JFormattedTextField formattedTextFieldCEP;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelErroBairro;
    private javax.swing.JLabel labelErroCEP;
    private javax.swing.JLabel labelErroCidade;
    private javax.swing.JLabel labelErroComplemento;
    private javax.swing.JLabel labelErroLogradouro;
    private javax.swing.JLabel labelErroNumero;
    private javax.swing.JLabel labelLogradouro;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelUF;
    private javax.swing.JTextField textFieldBairro;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldComplemento;
    private javax.swing.JTextField textFieldLogradouro;
    private javax.swing.JTextField textFieldNumero;
    // End of variables declaration//GEN-END:variables
}
