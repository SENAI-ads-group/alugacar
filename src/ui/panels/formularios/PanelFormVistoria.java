package ui.panels.formularios;

import java.awt.Component;
import java.util.Map;
import java.util.Set;
import model.entidades.ItemVistoria;
import model.entidades.Vistoria;
import model.exceptions.ValidacaoException;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public final class PanelFormVistoria extends javax.swing.JPanel {

    private Vistoria vistoria;

    public PanelFormVistoria(Vistoria vistoria) {
        initComponents();
        this.vistoria = vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public void updateListItens() {
        panelCenter.removeAll();
        panelCenter.revalidate();
        panelCenter.repaint();
        for (ItemVistoria item : vistoria.getItens()) {
            System.out.println(item.toCSV());
            PanelItemVistoria panelItem = new PanelItemVistoria(item);
            panelItem.updateItemForm();
            panelCenter.add(panelItem);

            panelCenter.revalidate();
            panelCenter.repaint();
        }
    }

    private void getItensForm() {
        for (Component component : panelCenter.getComponents()) {
            PanelItemVistoria panelItem = (PanelItemVistoria) component;
            ItemVistoria item = panelItem.getItem();
            Integer idItem = vistoria.getItens().indexOf(item);
            vistoria.getItens().get(idItem).setAdequado(item.isAdequado());
        }
    }

    public void updateFormData() {
        if (vistoria == null) {
            throw new IllegalStateException("A vistoria está nulo");
        }
        textFieldKM.setText("" + vistoria.getKmVeiculo());
        updateListItens();
    }

    public Vistoria getFormData() throws ValidacaoException {
        if (vistoria == null) {
            vistoria = new Vistoria();
        }
        clearErrors();
        validarCampos();
        getItensForm();
        vistoria.setKmVeiculo(Utilities.tryParseToDouble(textFieldKM.getText()));
        return vistoria;
    }

    public void clearErrors() {
        labelErroLocacao.setText("");
        labelErroKM.setText("");

    }

    public void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException(getClass().getSimpleName());

    }

    public void setErrorsMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("descricao")) {
            labelErroKM.setText(errors.get("descricao"));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelErroKM = new javax.swing.JLabel();
        labelErroLocacao = new javax.swing.JLabel();
        labelLocacao = new javax.swing.JLabel();
        labelKM = new javax.swing.JLabel();
        textFieldLocacao = new javax.swing.JTextField();
        textFieldKM = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        panelRight = new javax.swing.JPanel();
        panelCenter = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelErroKM.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroKM.setForeground(java.awt.Color.red);
        labelErroKM.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroKM.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroKM.setPreferredSize(new java.awt.Dimension(200, 15));
        add(labelErroKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 45, -1, -1));

        labelErroLocacao.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroLocacao.setForeground(java.awt.Color.red);
        labelErroLocacao.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroLocacao.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroLocacao.setPreferredSize(new java.awt.Dimension(200, 15));
        add(labelErroLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelLocacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelLocacao.setText("Locação");
        add(labelLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelKM.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelKM.setText("KM atual do veículo");
        add(labelKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        textFieldLocacao.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldLocacao.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldLocacao.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldLocacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, -1));

        textFieldKM.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldKM.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldKM.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldKM.setName(""); // NOI18N
        textFieldKM.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 200, -1));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setPreferredSize(new java.awt.Dimension(420, 230));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setPreferredSize(new java.awt.Dimension(410, 220));
        panelItens.setLayout(new java.awt.BorderLayout());

        panelLeft.setBackground(new java.awt.Color(255, 255, 255));
        panelLeft.setMinimumSize(new java.awt.Dimension(20, 10));
        panelLeft.setPreferredSize(new java.awt.Dimension(20, 10));
        panelItens.add(panelLeft, java.awt.BorderLayout.WEST);

        panelRight.setBackground(new java.awt.Color(255, 255, 255));
        panelRight.setMinimumSize(new java.awt.Dimension(20, 10));
        panelRight.setPreferredSize(new java.awt.Dimension(20, 10));
        panelItens.add(panelRight, java.awt.BorderLayout.EAST);

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter.setLayout(new javax.swing.BoxLayout(panelCenter, javax.swing.BoxLayout.Y_AXIS));
        panelItens.add(panelCenter, java.awt.BorderLayout.CENTER);

        scrollPane.setViewportView(panelItens);

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelErroKM;
    private javax.swing.JLabel labelErroLocacao;
    private javax.swing.JLabel labelKM;
    private javax.swing.JLabel labelLocacao;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelRight;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField textFieldKM;
    private javax.swing.JTextField textFieldLocacao;
    // End of variables declaration//GEN-END:variables
}
