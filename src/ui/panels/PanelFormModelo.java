package ui.panels;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import model.entidades.Modelo;
import model.entidades.Veiculo;
import model.entidades.enums.StatusVeiculo;
import model.exceptions.ValidationException;
import model.services.persistence.PersistenceFactory;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelFormModelo extends javax.swing.JPanel {

    private Veiculo veiculo;

    public PanelFormModelo(Veiculo veiculo) {
        initComponents();
        this.veiculo = veiculo;
        initCombobox();
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void initCombobox() {
        Object[] itemsModelo = PersistenceFactory.createModeloService().buscarTodos().toArray();
        comboBoxModelo.setModel(new DefaultComboBoxModel(itemsModelo));
        comboBoxStatus.setModel(new DefaultComboBoxModel(StatusVeiculo.values()));
    }

    public void updateFormData() {
        textFieldPlaca.setText(veiculo.getPlaca());
        textFieldRenavam.setText(veiculo.getRenavam());
        if (veiculo.getQuilometragemRodada() != null) {
            textFieldKMAtual.setText("" + veiculo.getQuilometragemRodada());
        }
        if (veiculo.getPrecoCompra() != null) {
            textFieldPrecoCompra.setText("" + veiculo.getPrecoCompra());
        }
        comboBoxModelo.setSelectedItem(veiculo.getModelo());
        comboBoxStatus.setSelectedItem(veiculo.getStatusVeiculo());
        if (veiculo.getAnoModelo() != null) {
            yearChooserFabricacao.setYear(veiculo.getAnoFabricacao());
        }
        if (veiculo.getAnoFabricacao() != null) {
            yearChooserModelo.setYear(veiculo.getAnoModelo());
        }
    }

    public Veiculo getFormData() throws ValidationException {
        ValidationException exception = new ValidationException(getClass().getSimpleName());
        if (Utilities.textFieldIsEmpty(textFieldPlaca)) {
            exception.addError("placa", "Placa não informada");
        }
        if (Utilities.textFieldIsEmpty(textFieldRenavam)) {
            exception.addError("renavam", "Renavam não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldPrecoCompra)) {
            exception.addError("precoCompra", "Preço de compra não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldKMAtual)) {
            exception.addError("KM", "KM não informado");
        }
        if (String.valueOf(yearChooserFabricacao.getYear()).length() > 4) {
            exception.addError("anoModelo", "Ano inválido");
        }
        if (String.valueOf(yearChooserFabricacao.getYear()).length() > 4) {
            exception.addError("anoFabricacao", "Ano inválido");
        }
        veiculo.setPlaca(textFieldPlaca.getText());
        veiculo.setRenavam(textFieldRenavam.getText());
        veiculo.setPrecoCompra(Utilities.tryParseToDouble(textFieldPrecoCompra.getText()));
        veiculo.setAnoFabricacao(yearChooserFabricacao.getYear());
        veiculo.setAnoModelo(yearChooserModelo.getYear());
        veiculo.setStatusVeiculo((StatusVeiculo) comboBoxStatus.getSelectedItem());
        veiculo.setModelo((Modelo) comboBoxModelo.getSelectedItem());
        clearErrors();
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
        return veiculo;
    }

    public void clearErrors() {
        labelErroAnoFabricacao.setText("");
        labelErroAnoModelo.setText("");
        labelErroKMAtual.setText("");
        labelErroModelo.setText("");
        labelErroPlaca.setText("");
        labelErroPrecoCompra.setText("");
        labelErroRenavam.setText("");
        labelErroStatus.setText("");
    }

    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("placa")) {
            labelErroPlaca.setText(errors.get("placa"));
        }
        if (fields.contains("renavam")) {
            labelErroRenavam.setText(errors.get("renavam"));
        }
        if (fields.contains("precoCompra")) {
            labelErroPrecoCompra.setText(errors.get("precoCompra"));
        }
        if (fields.contains("KM")) {
            labelErroKMAtual.setText(errors.get("KM"));
        }
        if (fields.contains("anoModelo")) {
            labelErroAnoModelo.setText(errors.get("anoModelo"));
        }
        if (fields.contains("anoFabricacao")) {
            labelErroAnoFabricacao.setText(errors.get("anoFabricacao"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldPlaca = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        textFieldRenavam = new javax.swing.JTextField();
        labelRenavam = new javax.swing.JLabel();
        labelAnoModelo = new javax.swing.JLabel();
        textFieldKMAtual = new javax.swing.JTextField();
        labelRG = new javax.swing.JLabel();
        labelErroAnoModelo = new javax.swing.JLabel();
        labelErroPlaca = new javax.swing.JLabel();
        labelErroModelo = new javax.swing.JLabel();
        labelErroRenavam = new javax.swing.JLabel();
        labelErroKMAtual = new javax.swing.JLabel();
        yearChooserModelo = new com.toedter.calendar.JYearChooser();
        labelAnoFabricacao = new javax.swing.JLabel();
        yearChooserFabricacao = new com.toedter.calendar.JYearChooser();
        labelErroAnoFabricacao = new javax.swing.JLabel();
        comboBoxModelo = new javax.swing.JComboBox<>();
        labelModelo = new javax.swing.JLabel();
        textFieldPrecoCompra = new javax.swing.JTextField();
        labelPrecoCompra = new javax.swing.JLabel();
        labelErroPrecoCompra = new javax.swing.JLabel();
        labelErroStatus = new javax.swing.JLabel();
        comboBoxStatus = new javax.swing.JComboBox<>();
        labelModelo1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldPlaca.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldPlaca.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldPlaca.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNome.setText("Placa");
        add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldRenavam.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldRenavam.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldRenavam.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldRenavam.setName(""); // NOI18N
        textFieldRenavam.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldRenavam, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelRenavam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelRenavam.setText("Renavam");
        add(labelRenavam, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        labelAnoModelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelAnoModelo.setText("Ano do modelo");
        add(labelAnoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        textFieldKMAtual.setToolTipText("Apenas números (ex: 123456-7)");
        textFieldKMAtual.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldKMAtual.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldKMAtual.setName(""); // NOI18N
        textFieldKMAtual.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldKMAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        labelRG.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelRG.setText("Quilometragem atual");
        add(labelRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        labelErroAnoModelo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroAnoModelo.setForeground(java.awt.Color.red);
        labelErroAnoModelo.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroAnoModelo.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroAnoModelo.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroAnoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 255, -1, -1));

        labelErroPlaca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroPlaca.setForeground(java.awt.Color.red);
        labelErroPlaca.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroPlaca.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroPlaca.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        labelErroModelo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroModelo.setForeground(java.awt.Color.red);
        labelErroModelo.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroModelo.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroModelo.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 170, -1));

        labelErroRenavam.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroRenavam.setForeground(java.awt.Color.red);
        labelErroRenavam.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroRenavam.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroRenavam.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroRenavam, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        labelErroKMAtual.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroKMAtual.setForeground(java.awt.Color.red);
        labelErroKMAtual.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroKMAtual.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroKMAtual.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroKMAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 170, -1));

        yearChooserModelo.setEndYear(Calendar.getInstance().get(Calendar.YEAR) + 1);
        yearChooserModelo.setFocusable(false);
        yearChooserModelo.setPreferredSize(new java.awt.Dimension(170, 25));
        yearChooserModelo.setStartYear(1900);
        add(yearChooserModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        labelAnoFabricacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelAnoFabricacao.setText("Ano de fabricação");
        add(labelAnoFabricacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        yearChooserFabricacao.setEndYear(Calendar.getInstance().get(Calendar.YEAR) + 1);
        yearChooserFabricacao.setFocusable(false);
        yearChooserFabricacao.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        yearChooserFabricacao.setPreferredSize(new java.awt.Dimension(170, 25));
        yearChooserFabricacao.setStartYear(1900);
        add(yearChooserFabricacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

        labelErroAnoFabricacao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroAnoFabricacao.setForeground(java.awt.Color.red);
        labelErroAnoFabricacao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroAnoFabricacao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroAnoFabricacao.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroAnoFabricacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 255, -1, -1));

        comboBoxModelo.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxModelo.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        labelModelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelModelo.setText("Modelo");
        add(labelModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        textFieldPrecoCompra.setToolTipText("Apenas números (ex: 123456-7)");
        textFieldPrecoCompra.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldPrecoCompra.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldPrecoCompra.setName(""); // NOI18N
        textFieldPrecoCompra.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldPrecoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        labelPrecoCompra.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelPrecoCompra.setText("Preço de compra");
        add(labelPrecoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        labelErroPrecoCompra.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroPrecoCompra.setForeground(java.awt.Color.red);
        labelErroPrecoCompra.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroPrecoCompra.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroPrecoCompra.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroPrecoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, -1, -1));

        labelErroStatus.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroStatus.setForeground(java.awt.Color.red);
        labelErroStatus.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroStatus.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroStatus.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 185, 170, -1));

        comboBoxStatus.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxStatus.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        labelModelo1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelModelo1.setText("Status");
        add(labelModelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Modelo> comboBoxModelo;
    private javax.swing.JComboBox<StatusVeiculo> comboBoxStatus;
    private javax.swing.JLabel labelAnoFabricacao;
    private javax.swing.JLabel labelAnoModelo;
    private javax.swing.JLabel labelErroAnoFabricacao;
    private javax.swing.JLabel labelErroAnoModelo;
    private javax.swing.JLabel labelErroKMAtual;
    private javax.swing.JLabel labelErroModelo;
    private javax.swing.JLabel labelErroPlaca;
    private javax.swing.JLabel labelErroPrecoCompra;
    private javax.swing.JLabel labelErroRenavam;
    private javax.swing.JLabel labelErroStatus;
    private javax.swing.JLabel labelModelo;
    private javax.swing.JLabel labelModelo1;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPrecoCompra;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelRenavam;
    private javax.swing.JTextField textFieldKMAtual;
    private javax.swing.JTextField textFieldPlaca;
    private javax.swing.JTextField textFieldPrecoCompra;
    private javax.swing.JTextField textFieldRenavam;
    private com.toedter.calendar.JYearChooser yearChooserFabricacao;
    private com.toedter.calendar.JYearChooser yearChooserModelo;
    // End of variables declaration//GEN-END:variables
}
