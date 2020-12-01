package ui.panels.formularios;

import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import model.entidades.Cliente;
import model.entidades.Locacao;
import model.entidades.Motorista;
import model.entidades.Veiculo;
import model.entidades.enums.TipoLocacao;
import model.exceptions.ValidacaoException;
import model.servicos.persistencia.DAOFactory;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelFormLocacao extends javax.swing.JPanel {
    
    private Locacao locacao;
    
    public PanelFormLocacao(Locacao locacao) {
        initComponents();
        this.locacao = locacao;
        initCombobox();
    }
    
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    
    public void initCombobox() {
        Object[] itensVeiculo = DAOFactory.createVeiculoDAO().buscarTodos().toArray();
        Object[] itensCliente = DAOFactory.createClienteDAO().buscarTodos().toArray();
        Object[] itensMotorista = DAOFactory.createMotoristaDAO().buscarTodos().toArray();
        comboBoxVeiculo.setModel(new DefaultComboBoxModel(itensVeiculo));
        comboBoxMotorista.setModel(new DefaultComboBoxModel(itensMotorista));
        comboBoxCliente.setModel(new DefaultComboBoxModel(itensCliente));
    }
    
    public void updateFormData() {
        if (locacao == null) {
            throw new IllegalStateException("O modelo está nulo");
        }
        comboBoxCliente.setSelectedItem(locacao.getCliente());
        comboBoxMotorista.setSelectedItem(locacao.getMotorista());
        comboBoxVeiculo.setSelectedItem(locacao.getVeiculo());
    }
    
    public Locacao getFormData() throws ValidacaoException {
        if (locacao == null) {
            locacao = new Locacao();
        }
        clearErrors();
        validarCampos();
        locacao.setTipo(TipoLocacao.DIARIA);
        locacao.setDataEntrega(dateChooserEntrega.getDate());
        locacao.setDataDevolucao(dateChooserDevolucao.getDate());
        locacao.setCliente(comboBoxCliente.getItemAt(comboBoxCliente.getSelectedIndex()));
        locacao.setMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()));
        locacao.setVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()));
        return locacao;
    }
    
    public void clearErrors() {
        labelErroDataEntrega.setText("");
        labelErroMarca.setText("");
        labelErroDataDevolucao.setText("");
        labelErroCategoria.setText("");
    }
    
    public void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException(getClass().getSimpleName());
        
    }
    
    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();
        
        if (fields.contains("descricao")) {
            labelErroDataDevolucao.setText(errors.get("descricao"));
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelErroMarca = new javax.swing.JLabel();
        labelErroDataDevolucao = new javax.swing.JLabel();
        labelErroDataEntrega = new javax.swing.JLabel();
        comboBoxVeiculo = new javax.swing.JComboBox<>();
        labelMarca = new javax.swing.JLabel();
        labelErroCategoria = new javax.swing.JLabel();
        comboBoxCliente = new javax.swing.JComboBox<>();
        labelCategoria = new javax.swing.JLabel();
        labelDataEntrega = new javax.swing.JLabel();
        dateChooserEntrega = new com.toedter.calendar.JDateChooser();
        labelErroDataEntrega1 = new javax.swing.JLabel();
        dateChooserDevolucao = new com.toedter.calendar.JDateChooser();
        labelDataEntrega1 = new javax.swing.JLabel();
        labelErroCategoria1 = new javax.swing.JLabel();
        comboBoxMotorista = new javax.swing.JComboBox<>();
        labelCategoria1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelErroMarca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroMarca.setForeground(java.awt.Color.red);
        labelErroMarca.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroMarca.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroMarca.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 170, -1));

        labelErroDataDevolucao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataDevolucao.setForeground(java.awt.Color.red);
        labelErroDataDevolucao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataDevolucao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataDevolucao.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDataDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        labelErroDataEntrega.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataEntrega.setForeground(java.awt.Color.red);
        labelErroDataEntrega.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDataEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        comboBoxVeiculo.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxVeiculo.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        labelMarca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelMarca.setText("Veículo");
        add(labelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        labelErroCategoria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoria.setForeground(java.awt.Color.red);
        labelErroCategoria.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, 170, -1));

        comboBoxCliente.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCliente.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCategoria.setText("Cliente");
        add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        labelDataEntrega.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataEntrega.setText("Data de entrega");
        add(labelDataEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dateChooserEntrega.setPreferredSize(new java.awt.Dimension(170, 25));
        add(dateChooserEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 170, -1));

        labelErroDataEntrega1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataEntrega1.setForeground(java.awt.Color.red);
        labelErroDataEntrega1.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega1.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega1.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDataEntrega1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 170, -1));

        dateChooserDevolucao.setPreferredSize(new java.awt.Dimension(170, 25));
        add(dateChooserDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 170, -1));

        labelDataEntrega1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataEntrega1.setText("Previsão de devolução");
        add(labelDataEntrega1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        labelErroCategoria1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoria1.setForeground(java.awt.Color.red);
        labelErroCategoria1.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria1.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroCategoria1.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 170, -1));

        comboBoxMotorista.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxMotorista.setPreferredSize(new java.awt.Dimension(170, 25));
        add(comboBoxMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        labelCategoria1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCategoria1.setText("Motorista");
        add(labelCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Cliente> comboBoxCliente;
    private javax.swing.JComboBox<Motorista> comboBoxMotorista;
    private javax.swing.JComboBox<Veiculo> comboBoxVeiculo;
    private com.toedter.calendar.JDateChooser dateChooserDevolucao;
    private com.toedter.calendar.JDateChooser dateChooserEntrega;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelCategoria1;
    private javax.swing.JLabel labelDataEntrega;
    private javax.swing.JLabel labelDataEntrega1;
    private javax.swing.JLabel labelErroCategoria;
    private javax.swing.JLabel labelErroCategoria1;
    private javax.swing.JLabel labelErroDataDevolucao;
    private javax.swing.JLabel labelErroDataEntrega;
    private javax.swing.JLabel labelErroDataEntrega1;
    private javax.swing.JLabel labelErroMarca;
    private javax.swing.JLabel labelMarca;
    // End of variables declaration//GEN-END:variables
}
