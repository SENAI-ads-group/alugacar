package ui.panels;

import entidades.Endereco;
import entidades.enums.UF;
import javax.swing.DefaultComboBoxModel;
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
        endereco.setLogradouro(textFieldLogradouro.getText());
        endereco.setNumero(Utilities.tryParseToInteger(textFieldNumero.getText()));
        endereco.setComplemento(textFieldComplemento.getText());
        endereco.setBairro(textFieldBairro.getText());
        endereco.setCidade(textFieldCidade.getText());
        endereco.setCep(textFieldCEP.getText());
        endereco.setUf(UF.valueOf(comboBoxUF.getSelectedItem().toString()));
        return endereco;
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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 240));
        setMinimumSize(new java.awt.Dimension(400, 240));
        setPreferredSize(new java.awt.Dimension(400, 240));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldLogradouro.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        labelLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelLogradouro.setText("Logradouro");
        add(labelLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        textFieldNumero.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNumero.setText("Número");
        add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        textFieldBairro.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        labelUF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelUF.setText("UF");
        add(labelUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        textFieldComplemento.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        labelComplemento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelComplemento.setText("Complemento");
        add(labelComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        labelCidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCidade.setText("Cidade");
        add(labelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        textFieldCidade.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        labelCEP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCEP.setText("CEP");
        add(labelCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        textFieldCEP.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        comboBoxUF.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxUF.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        labelBairro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelBairro.setText("Bairro");
        add(labelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxUF;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
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
