package ui.paineis.formularios;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.entidades.CNH;
import model.entidades.enums.CategoriaCNH;
import model.exceptions.ValidacaoException;
import util.FieldUtilities;

/**
 *
 * @author PatrickRibeiro
 *
 */
public class FormularioCNH extends javax.swing.JPanel {

    private CNH cnh;

    public FormularioCNH(CNH cnh) {
        initComponents();
        this.cnh = cnh;
    }

    public void setCnh(CNH cnh) {
        this.cnh = cnh;
    }

    public void atualizarFormulario() {
        if (cnh == null) {
            cnh = new CNH();
        } else {
            textFieldNumeroRegistro.setText(cnh.getNumeroRegistro());
            if (cnh.getNumeroRegistro() != null || cnh.getNumeroRegistro().trim().length() <= 0) {
                textFieldNumeroRegistro.setEditable(false);
            } else {
                textFieldNumeroRegistro.setEditable(true);
            }
            comboBoxCategoriaCNH.setSelectedIndex(cnh.getCategoria().ordinal());
            dateChooserValidadeCNH.setDate(cnh.getDataValidade());
            exibirFoto(cnh.getFotoFrente(), labelFoto1);
            exibirFoto(cnh.getFotoVerso(), labelFoto2);
        }
    }

    public CNH getDadosFormulario() throws ValidacaoException {
        if (cnh == null) {
            cnh = new CNH();
        }
        validarCampos();
        cnh.setNumeroRegistro(textFieldNumeroRegistro.getText().trim().replaceAll("[^0-9]", ""));
        cnh.setDataValidade(dateChooserValidadeCNH.getDate());
        cnh.setCategoria(comboBoxCategoriaCNH.getItemAt(comboBoxCategoriaCNH.getSelectedIndex()));
        return cnh;
    }

    private void validarCampos() throws ValidacaoException {
        ValidacaoException exception = new ValidacaoException(CNH.class.getSimpleName());
        if (FieldUtilities.textFieldIsEmpty(textFieldNumeroRegistro)) {
            exception.addError("numeroRegistro", "Registro não informado");
        }
        if (cnh.getFotoVerso() == null) {
            exception.addError("foto", "Foto do verso da CNH não selecionada");
        }
        if (cnh.getFotoFrente() == null) {
            exception.addError("foto", "Foto da frente da CNH não selecionada");
        }
        if (dateChooserValidadeCNH.getDate() == null) {
            exception.addError("validade", "Validade não selecionada");
        } else if (dateChooserValidadeCNH.getDate().before(new Date())) {
            exception.addError("validade", "Validade inválida");
        }

        limparErros();
        if (exception.getErrors().size() > 0) {
            throw exception;
        }
    }

    public void exibirMensagensErro(Map<String, String> erros) {
        Set<String> fields = erros.keySet();

        if (fields.contains("numeroRegistro")) {
            labelErroNumRegistro.setText(erros.get("numeroRegistro"));
        }
        if (fields.contains("foto")) {
            labelErroFoto.setText(erros.get("foto"));
        }
        if (fields.contains("validade")) {
            labelErroValidadeCNH.setText(erros.get("validade"));
        }
    }

    public void limparErros() {
        labelErroNumRegistro.setText("");
        labelErroValidadeCNH.setText("");
    }

    private File importarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("imagem", "jpg", "jpeg", "png"));

        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private void exibirFoto(BufferedImage imagem, JLabel label) {
        ImageIcon imageIcon = new ImageIcon(imagem.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
        label.repaint();
    }

    private void exibirFoto(File fileImagem, JLabel label) {
        if (fileImagem == null) {
            throw new IllegalStateException("fileImagem está nulo");
        }
        try {
            Image image = ImageIO.read(fileImagem).getScaledInstance(190, 150, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(image);
            label.setIcon(imageIcon);
            label.repaint();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao importar imagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldNumeroRegistro = new javax.swing.JTextField();
        labelNumRegistro = new javax.swing.JLabel();
        labelDataValidade = new javax.swing.JLabel();
        comboBoxCategoriaCNH = new javax.swing.JComboBox<>();
        labelCategoria = new javax.swing.JLabel();
        dateChooserValidadeCNH = new com.toedter.calendar.JDateChooser();
        labelErroNumRegistro = new javax.swing.JLabel();
        labelErroFoto = new javax.swing.JLabel();
        labelErroCategoriaCNH = new javax.swing.JLabel();
        labelErroValidadeCNH = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelFotos = new javax.swing.JPanel();
        panelFoto1 = new javax.swing.JPanel();
        labelTituloFoto1 = new javax.swing.JLabel();
        labelFoto1 = new javax.swing.JLabel();
        panelFoto2 = new javax.swing.JPanel();
        labelTituloFoto2 = new javax.swing.JLabel();
        labelFoto2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(390, 285));
        setMinimumSize(new java.awt.Dimension(390, 285));
        setPreferredSize(new java.awt.Dimension(390, 285));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNumeroRegistro.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setPreferredSize(new java.awt.Dimension(190, 25));
        add(textFieldNumeroRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        FieldUtilities.setFieldOnlyInteger(textFieldNumeroRegistro, 11);

        labelNumRegistro.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelNumRegistro.setText("Número de registro");
        add(labelNumRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelDataValidade.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDataValidade.setText("Data validade");
        add(labelDataValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        comboBoxCategoriaCNH.setModel(new DefaultComboBoxModel(CategoriaCNH.values()));
        comboBoxCategoriaCNH.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCategoriaCNH.setPreferredSize(new java.awt.Dimension(190, 25));
        add(comboBoxCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCategoria.setText("Categoria");
        add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        dateChooserValidadeCNH.setMaxSelectableDate(new java.util.Date(253370779295000L));
        dateChooserValidadeCNH.setMinSelectableDate(new Date());
        dateChooserValidadeCNH.setPreferredSize(new java.awt.Dimension(190, 25));
        add(dateChooserValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        labelErroNumRegistro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNumRegistro.setForeground(java.awt.Color.red);
        labelErroNumRegistro.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistro.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistro.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroNumRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroFoto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroFoto.setForeground(java.awt.Color.red);
        labelErroFoto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelErroFoto.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 125, 340, -1));

        labelErroCategoriaCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoriaCNH.setForeground(java.awt.Color.red);
        labelErroCategoriaCNH.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, -1, -1));

        labelErroValidadeCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValidadeCNH.setForeground(java.awt.Color.red);
        labelErroValidadeCNH.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroValidadeCNH.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroValidadeCNH.setPreferredSize(new java.awt.Dimension(170, 15));
        add(labelErroValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 105, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Fotos");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        panelFotos.setBackground(new java.awt.Color(255, 255, 255));
        panelFotos.setPreferredSize(new java.awt.Dimension(390, 135));
        panelFotos.setLayout(new javax.swing.BoxLayout(panelFotos, javax.swing.BoxLayout.X_AXIS));

        panelFoto1.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto1.setPreferredSize(new java.awt.Dimension(190, 170));
        panelFoto1.setLayout(new java.awt.BorderLayout());

        labelTituloFoto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto1.setText("Frente");
        labelTituloFoto1.setPreferredSize(new java.awt.Dimension(190, 20));
        panelFoto1.add(labelTituloFoto1, java.awt.BorderLayout.PAGE_START);

        labelFoto1.setBackground(new java.awt.Color(255, 255, 255));
        labelFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/CNH-frente-190x150.jpg"))); // NOI18N
        labelFoto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto1MousePressed(evt);
            }
        });
        panelFoto1.add(labelFoto1, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto1);

        panelFoto2.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto2.setPreferredSize(new java.awt.Dimension(190, 170));
        panelFoto2.setLayout(new java.awt.BorderLayout());

        labelTituloFoto2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto2.setText("Verso");
        panelFoto2.add(labelTituloFoto2, java.awt.BorderLayout.PAGE_START);

        labelFoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/CNH-verso-190x150.jpg"))); // NOI18N
        labelFoto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto2MousePressed(evt);
            }
        });
        panelFoto2.add(labelFoto2, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto2);

        add(panelFotos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void labelFoto1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto1MousePressed
        File fileSelected = importarFoto();
        if (fileSelected != null) {
            cnh.setFotoFrente(fileSelected);
            exibirFoto(cnh.getFotoFrente(), labelFoto1);
        }
    }//GEN-LAST:event_labelFoto1MousePressed

    private void labelFoto2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto2MousePressed
        File fileSelected = importarFoto();
        if (fileSelected != null) {
            cnh.setFotoVerso(fileSelected);
            exibirFoto(cnh.getFotoVerso(), labelFoto2);
        }
    }//GEN-LAST:event_labelFoto2MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<CategoriaCNH> comboBoxCategoriaCNH;
    private com.toedter.calendar.JDateChooser dateChooserValidadeCNH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelDataValidade;
    private javax.swing.JLabel labelErroCategoriaCNH;
    private javax.swing.JLabel labelErroFoto;
    private javax.swing.JLabel labelErroNumRegistro;
    private javax.swing.JLabel labelErroValidadeCNH;
    private javax.swing.JLabel labelFoto1;
    private javax.swing.JLabel labelFoto2;
    private javax.swing.JLabel labelNumRegistro;
    private javax.swing.JLabel labelTituloFoto1;
    private javax.swing.JLabel labelTituloFoto2;
    private javax.swing.JPanel panelFoto1;
    private javax.swing.JPanel panelFoto2;
    private javax.swing.JPanel panelFotos;
    private javax.swing.JTextField textFieldNumeroRegistro;
    // End of variables declaration//GEN-END:variables
}
