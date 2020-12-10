package ui.panels.formularios;

import java.util.Map;
import java.util.Set;
import model.entidades.Endereco;
import model.entidades.ItemVistoria;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;

/**
 *
 * @author usuario
 */
public class FormularioItemChecklist extends javax.swing.JPanel {
    
    private ItemVistoria item;
    
    public FormularioItemChecklist(ItemVistoria item) {
        initComponents();
        this.item = item;
    }
    
    public void setEndereco(ItemVistoria item) {
        this.item = item;
    }
    
    public void atualizarFormulario() {
        if (item != null) {
            textFieldNome.setText(item.getNome());
            textAreaDescricao.setText(item.getDescricao());
            checkBoxObrigatoriedade.setSelected(item.isObrigatorio());
        }
    }
    
    public ItemVistoria getDadosFormulario() throws ValidacaoException {
        if (item == null) {
            item = new ItemVistoria();
        }
        limparErros();
        validarCampos();
        item.setNome(textFieldNome.getText());
        item.setDescricao(textAreaDescricao.getText());
        item.setObrigatorio(checkBoxObrigatoriedade.isSelected());
        return item;
    }
    
    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException("ItemChecklist");
        if (FieldUtilities.textFieldIsEmpty(textFieldNome)) {
            exception.addError("nome", "Nome do item não informado");
        }
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }
    
    public void exibirMensagensErro(Map<String, String> errors) {
        Set<String> fields = errors.keySet();
        
        if (fields.contains("nome")) {
            labelErroNome.setText(errors.get("nome"));
        }
    }
    
    public void limparErros() {
        labelErroNome.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldNome = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        labelUF = new javax.swing.JLabel();
        labelErroNome = new javax.swing.JLabel();
        checkBoxObrigatoriedade = new javax.swing.JCheckBox();
        labelDetalhamento = new javax.swing.JLabel();
        labelErroDescricao = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        textAreaDescricao = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(390, 280));
        setMinimumSize(new java.awt.Dimension(390, 280));
        setPreferredSize(new java.awt.Dimension(390, 280));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNome.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        FieldUtilities.setFieldOnlyText(textFieldNome, 25);

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelNumero.setText("Nome");
        add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelUF.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelUF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-info-12x12.png"))); // NOI18N
        labelUF.setText("Obrigatoriedade");
        labelUF.setToolTipText("Define se a validação do item como adequado será obrigatória na vistoria");
        labelUF.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(labelUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        labelErroNome.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNome.setForeground(java.awt.Color.red);
        labelErroNome.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroNome.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        checkBoxObrigatoriedade.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxObrigatoriedade.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        checkBoxObrigatoriedade.setFocusPainted(false);
        checkBoxObrigatoriedade.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkBoxObrigatoriedade.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        checkBoxObrigatoriedade.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkBoxObrigatoriedade.setPreferredSize(new java.awt.Dimension(190, 25));
        add(checkBoxObrigatoriedade, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        labelDetalhamento.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDetalhamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-info-12x12.png"))); // NOI18N
        labelDetalhamento.setText("Descrição");
        labelDetalhamento.setToolTipText("Descrição de ajuda para a validação do item na vistoria");
        labelDetalhamento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(labelDetalhamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        labelErroDescricao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDescricao.setForeground(java.awt.Color.red);
        labelErroDescricao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDescricao.setPreferredSize(new java.awt.Dimension(190, 15));
        add(labelErroDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        scrollPane.setPreferredSize(new java.awt.Dimension(190, 120));

        textAreaDescricao.setColumns(20);
        textAreaDescricao.setLineWrap(true);
        textAreaDescricao.setRows(3);
        textAreaDescricao.setMaximumSize(new java.awt.Dimension(185, 115));
        textAreaDescricao.setMinimumSize(new java.awt.Dimension(185, 115));
        textAreaDescricao.setPreferredSize(new java.awt.Dimension(185, 115));
        scrollPane.setViewportView(textAreaDescricao);

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxObrigatoriedade;
    private javax.swing.JLabel labelDetalhamento;
    private javax.swing.JLabel labelErroDescricao;
    private javax.swing.JLabel labelErroNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelUF;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textAreaDescricao;
    private javax.swing.JTextField textFieldNome;
    // End of variables declaration//GEN-END:variables
}
