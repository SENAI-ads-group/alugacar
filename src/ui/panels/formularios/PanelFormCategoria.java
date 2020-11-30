package ui.panels.formularios;

import java.util.Map;
import java.util.Set;
import model.entidades.Categoria;
import model.exceptions.ValidacaoException;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelFormCategoria extends javax.swing.JPanel {

    private Categoria categoria;

    public PanelFormCategoria(Categoria categoria) {
        initComponents();
        this.categoria = categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void updateFormData() {
        if (categoria == null) {
            throw new IllegalStateException("A categoria está nulo");
        }
        textFieldDescricao.setText(categoria.getDescricao());
        textFieldValorDiaria.setText(String.valueOf(categoria.getValorDiaria()));
        textFieldValorMinimoLocacao.setText(String.valueOf(categoria.getValorMinimoLocacao()));
    }

    public Categoria getFormData() throws ValidacaoException {
        if (categoria == null) {
            categoria = new Categoria();
        }
        clearErrors();
        validarCampos();
        categoria.setDescricao(textFieldDescricao.getText());
        categoria.setValorDiaria(Utilities.tryParseToDouble(textFieldValorDiaria.getText()));
        categoria.setValorMinimoLocacao(Utilities.tryParseToDouble(textFieldValorMinimoLocacao.getText()));
        return categoria;
    }

    public void clearErrors() {
        labelErroDescricao.setText("");
        labelErroValorMinimoLocacao.setText("");
        labelErroValorDiaria.setText("");
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException(getClass().getSimpleName());
        if (Utilities.textFieldIsEmpty(textFieldDescricao)) {
            exception.addError("descricao", "Descrição não informada");
        }
        if (Utilities.textFieldIsEmpty(textFieldValorMinimoLocacao)) {
            exception.addError("valorMinimoLocacao", "Valor mínimo não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldValorDiaria)) {
            exception.addError("valorDiaria", "Valor da diária não informado");
        }

        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("descricao")) {
            labelErroDescricao.setText(errors.get("descricao"));
        }
        if (fields.contains("valorDiaria")) {
            labelErroValorDiaria.setText(errors.get("valorDiaria"));
        }
        if (fields.contains("valorMinimoLocacao")) {
            labelErroValorDiaria.setText(errors.get("valorMinimoLocacao"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldDescricao = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        textFieldValorMinimoLocacao = new javax.swing.JTextField();
        labelValorMinimoLocacao = new javax.swing.JLabel();
        labelErroDescricao = new javax.swing.JLabel();
        labelErroValorMinimoLocacao = new javax.swing.JLabel();
        textFieldValorDiaria = new javax.swing.JTextField();
        labelErroValorDiaria = new javax.swing.JLabel();
        labelValorDiaria = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldDescricao.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDescricao.setText("Descrição");
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldValorMinimoLocacao.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldValorMinimoLocacao.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldValorMinimoLocacao.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldValorMinimoLocacao.setName(""); // NOI18N
        textFieldValorMinimoLocacao.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldValorMinimoLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelValorMinimoLocacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelValorMinimoLocacao.setText("Valor mínimo locação");
        add(labelValorMinimoLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        labelErroDescricao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDescricao.setForeground(java.awt.Color.red);
        labelErroDescricao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        labelErroValorMinimoLocacao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValorMinimoLocacao.setForeground(java.awt.Color.red);
        labelErroValorMinimoLocacao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroValorMinimoLocacao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroValorMinimoLocacao.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroValorMinimoLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        textFieldValorDiaria.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldValorDiaria.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldValorDiaria.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldValorDiaria.setName(""); // NOI18N
        textFieldValorDiaria.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        labelErroValorDiaria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValorDiaria.setForeground(java.awt.Color.red);
        labelErroValorDiaria.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroValorDiaria.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroValorDiaria.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 170, -1));

        labelValorDiaria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelValorDiaria.setText("Valor da diária");
        add(labelValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelErroDescricao;
    private javax.swing.JLabel labelErroValorDiaria;
    private javax.swing.JLabel labelErroValorMinimoLocacao;
    private javax.swing.JLabel labelValorDiaria;
    private javax.swing.JLabel labelValorMinimoLocacao;
    private javax.swing.JTextField textFieldDescricao;
    private javax.swing.JTextField textFieldValorDiaria;
    private javax.swing.JTextField textFieldValorMinimoLocacao;
    // End of variables declaration//GEN-END:variables
}
