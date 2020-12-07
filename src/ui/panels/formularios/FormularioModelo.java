package ui.panels.formularios;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import model.entidades.Categoria;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.enums.Combustivel;
import model.exceptions.ValidacaoException;
import model.servicos.persistencia.DAOFactory;
import util.FieldUtilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class FormularioModelo extends javax.swing.JPanel {

    private Modelo modelo;

    public FormularioModelo(Modelo modelo) {
        initComponents();
        this.modelo = modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void atualizarFormulario() {
        if (modelo == null) {
            throw new IllegalStateException("O modelo está nulo");
        }
        comboBoxCategoria.setSelectedItem(modelo.getCategoria());
        comboBoxCombustivel.setSelectedItem(modelo.getCombustivel());
        comboBoxMarca.setSelectedItem(modelo.getMarca());
        textFieldCodigoFipe.setText(modelo.getCodigoFipe());
        textFieldDescricao.setText(modelo.getDescricao());
        if (modelo.getAno() != null) {
            yearChooserModelo.setYear(modelo.getAno());
        }
        if (modelo.getCodigoFipe() != null) {
            textFieldCodigoFipe.setEditable(false);
            textFieldCodigoFipe.setEnabled(false);
        } else {
            textFieldCodigoFipe.setEditable(true);
            textFieldCodigoFipe.setEnabled(true);
        }
    }

    public Modelo getDadosFormulario() throws ValidacaoException {
        if (modelo == null) {
            modelo = new Modelo();
        }
        limparErros();
        validarCampos();
        if (!FieldUtilities.textFieldIsEmpty(textFieldCodigoFipe)) {
            modelo.setCodigoFipe(textFieldCodigoFipe.getText());
        }
        modelo.setDescricao(textFieldDescricao.getText());
        Marca marca = comboBoxMarca.getItemAt(comboBoxMarca.getSelectedIndex());
        modelo.setMarca(marca);
        modelo.setAno(yearChooserModelo.getYear());
        modelo.setCategoria(comboBoxCategoria.getItemAt(comboBoxCategoria.getSelectedIndex()));
        modelo.setCombustivel(comboBoxCombustivel.getItemAt(comboBoxCombustivel.getSelectedIndex()));
        return modelo;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException(getClass().getSimpleName());
        if (FieldUtilities.textFieldIsEmpty(textFieldDescricao)) {
            exception.addError("descricao", "Descrição não informada");
        }
        if (comboBoxCombustivel.getSelectedItem() == null) {
            exception.addError("combustivel", "Combustível não selecionado");
        }
        if (comboBoxCategoria.getSelectedItem() == null) {
            exception.addError("categoria", "Categoria não selecionada");
        }
        if (comboBoxMarca.getSelectedItem() == null) {
            exception.addError("marca", "Marca não selecionada");
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
        if (fields.contains("combustivel")) {
            labelErroCombustivel.setText(erros.get("combustivel"));
        }
        if (fields.contains("categoria")) {
            labelErroCategoria.setText(erros.get("categoria"));
        }
        if (fields.contains("marca")) {
            labelErroMarca.setText(erros.get("categoria"));
        }
    }

    private void limparErros() {
        labelErroAnoModelo.setText("");
        labelErroCombustivel.setText("");
        labelErroMarca.setText("");
        labelErroPlaca.setText("");
        labelErroAnoModelo.setText("");
        labelErroDescricao.setText("");
        labelErroCategoria.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldCodigoFipe = new javax.swing.JTextField();
        labelCodigoFipe = new javax.swing.JLabel();
        textFieldDescricao = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        labelAnoModelo = new javax.swing.JLabel();
        labelErroPlaca = new javax.swing.JLabel();
        labelErroMarca = new javax.swing.JLabel();
        labelErroDescricao = new javax.swing.JLabel();
        labelErroCombustivel = new javax.swing.JLabel();
        yearChooserModelo = new com.toedter.calendar.JYearChooser();
        comboBoxMarca = new javax.swing.JComboBox<>();
        labelMarca = new javax.swing.JLabel();
        labelErroAnoModelo = new javax.swing.JLabel();
        labelErroCategoria = new javax.swing.JLabel();
        comboBoxCategoria = new javax.swing.JComboBox<>();
        labelCategoria = new javax.swing.JLabel();
        labelCombustivel = new javax.swing.JLabel();
        comboBoxCombustivel = new javax.swing.JComboBox<>();
        labelErroStatus1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldCodigoFipe.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldCodigoFipe.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldCodigoFipe.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCodigoFipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelCodigoFipe.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCodigoFipe.setText("Código FIPE");
        add(labelCodigoFipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldDescricao.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldDescricao.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldDescricao.setName(""); // NOI18N
        textFieldDescricao.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDescricao.setText("Descrição");
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        labelAnoModelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelAnoModelo.setText("Ano do modelo");
        add(labelAnoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        labelErroPlaca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroPlaca.setForeground(java.awt.Color.red);
        labelErroPlaca.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroPlaca.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroPlaca.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        labelErroMarca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroMarca.setForeground(java.awt.Color.red);
        labelErroMarca.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroMarca.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroMarca.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 170, -1));

        labelErroDescricao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDescricao.setForeground(java.awt.Color.red);
        labelErroDescricao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        labelErroCombustivel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCombustivel.setForeground(java.awt.Color.red);
        labelErroCombustivel.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCombustivel.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCombustivel.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroCombustivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 170, -1));

        yearChooserModelo.setEndYear(Calendar.getInstance().get(Calendar.YEAR) + 1);
        yearChooserModelo.setFocusable(false);
        yearChooserModelo.setPreferredSize(new java.awt.Dimension(170, 25));
        yearChooserModelo.setStartYear(1900);
        add(yearChooserModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        comboBoxMarca.setModel(new DefaultComboBoxModel(DAOFactory.createMarcaDAO().buscarTodos().toArray()));
        comboBoxMarca.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxMarca.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        labelMarca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelMarca.setText("Marca");
        add(labelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        labelErroAnoModelo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroAnoModelo.setForeground(java.awt.Color.red);
        labelErroAnoModelo.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroAnoModelo.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroAnoModelo.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroAnoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, -1, -1));

        labelErroCategoria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoria.setForeground(java.awt.Color.red);
        labelErroCategoria.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 185, 170, -1));

        comboBoxCategoria.setModel(new DefaultComboBoxModel(DAOFactory.createCategoriaDAO().buscarTodos().toArray()));
        comboBoxCategoria.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCategoria.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCategoria.setText("Categoria");
        add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        labelCombustivel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCombustivel.setText("Combustível");
        add(labelCombustivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        comboBoxCombustivel.setModel(new DefaultComboBoxModel<>(Combustivel.values()));
        comboBoxCombustivel.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCombustivel.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxCombustivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        labelErroStatus1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroStatus1.setForeground(java.awt.Color.red);
        labelErroStatus1.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroStatus1.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroStatus1.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroStatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 170, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Categoria> comboBoxCategoria;
    private javax.swing.JComboBox<Combustivel> comboBoxCombustivel;
    private javax.swing.JComboBox<Marca> comboBoxMarca;
    private javax.swing.JLabel labelAnoModelo;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelCodigoFipe;
    private javax.swing.JLabel labelCombustivel;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelErroAnoModelo;
    private javax.swing.JLabel labelErroCategoria;
    private javax.swing.JLabel labelErroCombustivel;
    private javax.swing.JLabel labelErroDescricao;
    private javax.swing.JLabel labelErroMarca;
    private javax.swing.JLabel labelErroPlaca;
    private javax.swing.JLabel labelErroStatus1;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JTextField textFieldCodigoFipe;
    private javax.swing.JTextField textFieldDescricao;
    private com.toedter.calendar.JYearChooser yearChooserModelo;
    // End of variables declaration//GEN-END:variables
}
