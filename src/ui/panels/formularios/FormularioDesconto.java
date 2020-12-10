package ui.panels.formularios;

import java.util.Map;
import java.util.Set;
import model.entidades.Desconto;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class FormularioDesconto extends javax.swing.JPanel {

    private Desconto desconto;

    public FormularioDesconto(Desconto desconto) {
        initComponents();
        this.desconto = desconto;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }

    public void atualizarFormulario() {
        if (desconto != null) {
            textFieldDescricao.setText(desconto.getDescricao());
            textFieldValor.setText("" + desconto.getValor());
        }
    }

    public Desconto getDadosFormulario() throws ValidacaoException {
        if (desconto == null) {
            desconto = new Desconto();
        }
        limparErros();
        validarCampos();
        desconto.setDescricao(textFieldDescricao.getText());
        desconto.setValor(Utilities.tryParseToDouble(textFieldValor.getText()));
        return desconto;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException("Desconto");
        if (FieldUtilities.textFieldIsEmpty(textFieldDescricao)) {
            exception.addError("descricao", "Descrição não informada");
        }
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void exibirMensagensErro(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("descricao")) {
            labelErroDescricao.setText(errors.get("descricao"));
        }
        if (fields.contains("valor")) {
            labelErroDescricao.setText(errors.get("valor"));
        }
    }

    public void limparErros() {
        labelErroDescricao.setText("");
        labelErroValor.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldDescricao = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        labelErroDescricao = new javax.swing.JLabel();
        textFieldValor = new javax.swing.JTextField();
        labelValor = new javax.swing.JLabel();
        labelErroValor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(390, 280));
        setMinimumSize(new java.awt.Dimension(390, 280));
        setPreferredSize(new java.awt.Dimension(390, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldDescricao.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        FieldUtilities.setFieldOnlyText(textFieldDescricao, 25);

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDescricao.setText("Descrição");
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelErroDescricao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDescricao.setForeground(java.awt.Color.red);
        labelErroDescricao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        textFieldValor.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));
        FieldUtilities.setFieldOnlyText(textFieldDescricao, 25);

        labelValor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValor.setText("Valor (R$)");
        add(labelValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        labelErroValor.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValor.setForeground(java.awt.Color.red);
        labelErroValor.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroValor.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroValor.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelErroDescricao;
    private javax.swing.JLabel labelErroValor;
    private javax.swing.JLabel labelValor;
    private javax.swing.JTextField textFieldDescricao;
    private javax.swing.JTextField textFieldValor;
    // End of variables declaration//GEN-END:variables
}
