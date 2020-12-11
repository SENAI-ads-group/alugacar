package ui.paineis.formularios;

import java.util.Map;
import java.util.Set;
import model.entidades.Categoria;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class FormularioCategoria extends javax.swing.JPanel {

    private Categoria categoria;

    public FormularioCategoria(Categoria categoria) {
        initComponents();
        this.categoria = categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void atualizarFormulario() {
        if (categoria == null) {
            throw new IllegalStateException("A categoria está nulo");
        }
        textFieldDescricao.setText(categoria.getDescricao());
        formattedTextFieldValorDiaria.setText("R$ " + (String.valueOf(categoria.getValorDiaria())).replace(".", ","));
        formattedTextFieldValorKM.setText("R$ " + (String.valueOf(categoria.getValorKM())).replace(".", ","));
    }

    public Categoria getDadosFormulario() throws ValidacaoException {
        if (categoria == null) {
            categoria = new Categoria();
        }
        limparErros();
        validarCampos();
        categoria.setDescricao(textFieldDescricao.getText());
        categoria.setValorDiaria(Utilities.tryParseToDouble(formattedTextFieldValorDiaria.getText()));
        categoria.setValorKM(Utilities.tryParseToDouble(formattedTextFieldValorKM.getText()));
        return categoria;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException("Categoria");
        if (FieldUtilities.textFieldIsEmpty(textFieldDescricao)) {
            exception.addError("descricao", "Descrição não informada");
        }
        if (FieldUtilities.textFieldIsEmpty(formattedTextFieldValorDiaria)) {
            exception.addError("valorDiaria", "Valor da diária não informado");
        }
        if (FieldUtilities.formattedTextFieldIsEmpty(formattedTextFieldValorKM)) {
            exception.addError("valorKM", "Valor do KM não informado");
        }

        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void exibirMensagensErro(Map<String, String> erros) {
        Set<String> fields = erros.keySet();

        if (fields.contains("descricao")) {
            labelErroDescricao.setText(erros.get("descricao"));
        }
        if (fields.contains("valorDiaria")) {
            labelErroValorDiaria.setText(erros.get("valorDiaria"));
        }
        if (fields.contains("valorKM")) {
            labelErroValorKM.setText(erros.get("valorKM"));
        }
    }

    public void limparErros() {
        labelErroDescricao.setText("");
        labelErroValorDiaria.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldDescricao = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        labelErroDescricao = new javax.swing.JLabel();
        labelErroValorDiaria = new javax.swing.JLabel();
        labelValorDiaria = new javax.swing.JLabel();
        labelValorKM = new javax.swing.JLabel();
        labelErroValorKM = new javax.swing.JLabel();
        formattedTextFieldValorDiaria = new javax.swing.JFormattedTextField();
        formattedTextFieldValorKM = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(390, 260));
        setPreferredSize(new java.awt.Dimension(390, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldDescricao.setToolTipText("");
        textFieldDescricao.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        textFieldDescricao.getAccessibleContext().setAccessibleName("");

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDescricao.setText("Descrição");
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelErroDescricao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDescricao.setForeground(java.awt.Color.red);
        labelErroDescricao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroValorDiaria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValorDiaria.setForeground(java.awt.Color.red);
        labelErroValorDiaria.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroValorDiaria.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroValorDiaria.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 105, -1, -1));

        labelValorDiaria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorDiaria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelValorDiaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-info-12x12.png"))); // NOI18N
        labelValorDiaria.setText("Valor da diária");
        labelValorDiaria.setToolTipText("Valor em reais (R$) para cada dia de locação na modalidade por diária");
        labelValorDiaria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(labelValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        labelValorKM.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-info-12x12.png"))); // NOI18N
        labelValorKM.setText("Valor KM");
        labelValorKM.setToolTipText("Valor em reais (R$) para cada quillômetro percorrido pelo veículo na modalidade de locação por quilômetro");
        labelValorKM.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(labelValorKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        labelErroValorKM.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValorKM.setForeground(java.awt.Color.red);
        labelErroValorKM.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroValorKM.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroValorKM.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroValorKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, -1, -1));

        formattedTextFieldValorDiaria.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        formattedTextFieldValorDiaria.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldValorDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));
        FieldUtilities.setFieldMoeda(formattedTextFieldValorDiaria);

        formattedTextFieldValorKM.setPreferredSize(new java.awt.Dimension(190, 25));
        add(formattedTextFieldValorKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));
        FieldUtilities.setFieldMoeda(formattedTextFieldValorKM);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField formattedTextFieldValorDiaria;
    private javax.swing.JFormattedTextField formattedTextFieldValorKM;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelErroDescricao;
    private javax.swing.JLabel labelErroValorDiaria;
    private javax.swing.JLabel labelErroValorKM;
    private javax.swing.JLabel labelValorDiaria;
    private javax.swing.JLabel labelValorKM;
    private javax.swing.JTextField textFieldDescricao;
    // End of variables declaration//GEN-END:variables
}
