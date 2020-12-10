package ui.panels.formularios;

import application.Configuracoes;
import java.util.Properties;

/**
 *
 * @author patrick-ribeiro
 */
public final class FormularioConfiguracaoGeral extends javax.swing.JPanel {

    public FormularioConfiguracaoGeral() {
        initComponents();
    }

    public void gravarConfiguracoes() {
        Properties properties = Configuracoes.getProperties();
        properties.setProperty("servidor", textFieldServidor.getText());
        properties.setProperty("database", textFieldDatabase.getText());
        properties.setProperty("unidade", textFieldUnidade.getText());
        Configuracoes.setProperties(properties);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldServidor = new javax.swing.JTextField();
        labelCodigoFipe = new javax.swing.JLabel();
        textFieldUnidade = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        labelErroServidor = new javax.swing.JLabel();
        labelErroDatabase = new javax.swing.JLabel();
        labelErroUnidade = new javax.swing.JLabel();
        labelCombustivel = new javax.swing.JLabel();
        textFieldDatabase = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldServidor.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldServidor.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldServidor.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelCodigoFipe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelCodigoFipe.setText("Servidor");
        add(labelCodigoFipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        textFieldUnidade.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldUnidade.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldUnidade.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldUnidade.setName(""); // NOI18N
        textFieldUnidade.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelDescricao.setText("Database");
        add(labelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        labelErroServidor.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroServidor.setForeground(java.awt.Color.red);
        labelErroServidor.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroServidor.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroServidor.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 170, -1));

        labelErroDatabase.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDatabase.setForeground(java.awt.Color.red);
        labelErroDatabase.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDatabase.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDatabase.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 170, -1));

        labelErroUnidade.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroUnidade.setForeground(java.awt.Color.red);
        labelErroUnidade.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroUnidade.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroUnidade.setPreferredSize(new java.awt.Dimension(150, 15));
        add(labelErroUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 170, -1));

        labelCombustivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelCombustivel.setText("Unidade");
        add(labelCombustivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        textFieldDatabase.setToolTipText("Apenas números (ex: (62)9.8765-4321)");
        textFieldDatabase.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldDatabase.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldDatabase.setName(""); // NOI18N
        textFieldDatabase.setPreferredSize(new java.awt.Dimension(170, 25));
        add(textFieldDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelCodigoFipe;
    private javax.swing.JLabel labelCombustivel;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelErroDatabase;
    private javax.swing.JLabel labelErroServidor;
    private javax.swing.JLabel labelErroUnidade;
    private javax.swing.JTextField textFieldDatabase;
    private javax.swing.JTextField textFieldServidor;
    private javax.swing.JTextField textFieldUnidade;
    // End of variables declaration//GEN-END:variables
}
