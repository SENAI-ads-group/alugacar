package ui.panels;

import java.util.Map;
import java.util.Set;
import model.entidades.Endereco;
import model.entidades.enums.UF;
import javax.swing.DefaultComboBoxModel;
import model.exceptions.ValidationException;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class PanelFormEndereco extends javax.swing.JPanel {

    private Endereco endereco;

    public PanelFormEndereco(Endereco endereco) {
        initComponents();
        comboBoxUF.setModel(new DefaultComboBoxModel(UF.values()));
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void updateFormData() {
        if (endereco == null) {
            throw new IllegalStateException("O endereço está nulo");
        }
        textFieldLogradouro.setText(endereco.getLogradouro());
        textFieldNumero.setText("" + endereco.getNumero());
        textFieldComplemento.setText(endereco.getComplemento());
        textFieldBairro.setText(endereco.getBairro());
        textFieldCidade.setText(endereco.getCidade());
        textFieldCEP.setText(endereco.getCep());
        comboBoxUF.setSelectedItem(endereco.getUf().toString());
    }

    public Endereco getFormData() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        ValidationException exception = new ValidationException(getClass().getSimpleName());
        if (Utilities.textFieldIsEmpty(textFieldLogradouro)) {
            exception.addError("logradouro", "Logradouro não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldNumero)) {
            exception.addError("numero", "Número não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldCEP)) {
            exception.addError("CEP", "CEP não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldBairro)) {
            exception.addError("bairro", "Bairro não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldCidade)) {
            exception.addError("cidade", "Cidade não informada");
        }
        endereco.setLogradouro(textFieldLogradouro.getText());
        endereco.setNumero(Utilities.tryParseToInteger(textFieldNumero.getText()));
        endereco.setComplemento(textFieldComplemento.getText());
        endereco.setBairro(textFieldBairro.getText());
        endereco.setCidade(textFieldCidade.getText());
        endereco.setCep(textFieldCEP.getText());
        endereco.setUf(UF.valueOf(comboBoxUF.getSelectedItem().toString()));

        clearErrors();
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
        return endereco;
    }

    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("logradouro")) {
            labelErroLogradouro.setText(errors.get("logradouro"));
        }
        if (fields.contains("numero")) {
            labelErroNumero.setText(errors.get("numero"));
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
    }

    public void clearErrors() {
        labelLogradouro.setText("");
        labelNumero.setText("");
        labelCEP.setText("");
        labelBairro.setText("");
        labelCidade.setText("");
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
        textFieldCEP = new javax.swing.JTextField();
        comboBoxUF = new javax.swing.JComboBox<>();
        labelBairro = new javax.swing.JLabel();
        labelErroCidade = new javax.swing.JLabel();
        labelErroNumero = new javax.swing.JLabel();
        labelErroCEP = new javax.swing.JLabel();
        labelErroComplemento = new javax.swing.JLabel();
        labelErroLogradouro = new javax.swing.JLabel();
        labelErroBairro = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(360, 280));
        setMinimumSize(new java.awt.Dimension(360, 280));
        setPreferredSize(new java.awt.Dimension(360, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldLogradouro.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelLogradouro.setText("Logradouro");
        add(labelLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldNumero.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNumero.setText("Número");
        add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        textFieldBairro.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

        labelUF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelUF.setText("UF");
        add(labelUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 195, -1, -1));

        textFieldComplemento.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, -1, -1));

        labelComplemento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelComplemento.setText("Complemento");
        add(labelComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, -1, -1));

        labelCidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCidade.setText("Cidade");
        add(labelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        textFieldCidade.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        labelCEP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCEP.setText("CEP");
        add(labelCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 65, -1, -1));

        textFieldCEP.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 85, -1, -1));

        comboBoxUF.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxUF.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 215, -1, -1));

        labelBairro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelBairro.setText("Bairro");
        add(labelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        labelErroCidade.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCidade.setForeground(java.awt.Color.red);
        labelErroCidade.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCidade.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCidade.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 175, -1, -1));

        labelErroNumero.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNumero.setForeground(java.awt.Color.red);
        labelErroNumero.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroNumero.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroNumero.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, -1, -1));

        labelErroCEP.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCEP.setForeground(java.awt.Color.red);
        labelErroCEP.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCEP.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCEP.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        labelErroComplemento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroComplemento.setForeground(java.awt.Color.red);
        labelErroComplemento.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroComplemento.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroComplemento.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));

        labelErroLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroLogradouro.setForeground(java.awt.Color.red);
        labelErroLogradouro.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroLogradouro.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroLogradouro.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroBairro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroBairro.setForeground(java.awt.Color.red);
        labelErroBairro.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroBairro.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroBairro.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxUF;
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
    private javax.swing.JTextField textFieldCEP;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldComplemento;
    private javax.swing.JTextField textFieldLogradouro;
    private javax.swing.JTextField textFieldNumero;
    // End of variables declaration//GEN-END:variables
}
