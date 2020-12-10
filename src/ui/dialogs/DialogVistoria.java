package ui.dialogs;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.entidades.Locacao;
import model.entidades.Vistoria;
import model.entidades.enums.StatusLocacao;
import model.exceptions.ValidacaoException;
import model.servicos.persistencia.interfaces.LocacaoDAO;
import model.servicos.persistencia.interfaces.VistoriaDAO;
import ui.listeners.DataChangeListener;
import ui.paineis.formularios.FormularioVistoria;
import util.PanelUtilities;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogVistoria extends javax.swing.JDialog {

    private Vistoria vistoria;
    private Locacao locacao;
    private final VistoriaDAO DAO_VISTORIA = DAOFactory.createVistoriaDAO();
    private final LocacaoDAO DAO_LOCACAO = DAOFactory.createLocacaoDAO();
    private FormularioVistoria formVistoria;
    private final List<DataChangeListener> listeners = new ArrayList<>();

    public DialogVistoria(java.awt.Frame parent, boolean modal, Locacao locacao, Vistoria vistoria) {
        super(parent, modal);
        this.vistoria = vistoria;
        this.locacao = locacao;

        initComponents();
        loadPanels();
    }

    private void loadPanels() {
        formVistoria = new FormularioVistoria(vistoria, locacao);
        PanelUtilities.loadPanelToPanel(formVistoria, panelCenterTab1);
    }

    private void persistEntity() throws DBException, ValidacaoException {
        getFormData();
        if (vistoria.getId() == null) {
            DAO_VISTORIA.inserir(vistoria);
        }
        if (locacao.getStatus() == StatusLocacao.INICIADA) {
            DAO_LOCACAO.devolverVeiculo(locacao, vistoria);
        } else if (locacao.getStatus() == StatusLocacao.PENDENTE) {
            DAO_LOCACAO.entregarVeiculo(locacao, vistoria);
        }
    }

    public Vistoria getFormData() throws ValidacaoException {
        vistoria = formVistoria.getFormData();

        ValidacaoException ex = new ValidacaoException("Fotos");
        for (File file : vistoria.getImagens()) {
            if (file == null) {
                ex.addError("foto", "É necessário importar todas as fotos solicitadas na aba de fotos");
                throw ex;
            }
        }
        return vistoria;
    }

    public void updateFormData() {
        formVistoria.updateFormData();
    }

    public void subscribeListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        if (listeners.size() > 0) {
            listeners.forEach((listener) -> {
                listener.onDataChanged();
            });
        }
    }

    public void showImagem(Image imagem, JLabel label) {
        ImageIcon imageIcon = new ImageIcon(imagem);
        Image image = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(image));
        label.repaint();
    }

    public File showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("imagem", "jpg", "jpeg", "png"));

        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        panelTab1 = new javax.swing.JPanel();
        panelTopTab1 = new javax.swing.JPanel();
        panelBorderLeftTab1 = new javax.swing.JPanel();
        panelCenterTab1 = new javax.swing.JPanel();
        panelBorderRightTab1 = new javax.swing.JPanel();
        panelTab2 = new javax.swing.JPanel();
        panelTopTab2 = new javax.swing.JPanel();
        panelBorderLeftTab2 = new javax.swing.JPanel();
        panelCenterTab2 = new javax.swing.JPanel();
        scrollPaneFotos = new javax.swing.JScrollPane();
        panelFotos = new javax.swing.JPanel();
        panelFoto1 = new javax.swing.JPanel();
        labelTituloFoto1 = new javax.swing.JLabel();
        labelFoto1 = new javax.swing.JLabel();
        panelFoto2 = new javax.swing.JPanel();
        labelTituloFoto2 = new javax.swing.JLabel();
        labelFoto2 = new javax.swing.JLabel();
        panelFoto3 = new javax.swing.JPanel();
        labelTituloFoto3 = new javax.swing.JLabel();
        labelFoto3 = new javax.swing.JLabel();
        panelFoto4 = new javax.swing.JPanel();
        labelTituloFoto4 = new javax.swing.JLabel();
        labelFoto4 = new javax.swing.JLabel();
        panelFoto5 = new javax.swing.JPanel();
        labelTituloFoto5 = new javax.swing.JLabel();
        labelFoto5 = new javax.swing.JLabel();
        panelFoto6 = new javax.swing.JPanel();
        labelTituloFoto6 = new javax.swing.JLabel();
        labelFoto6 = new javax.swing.JLabel();
        panelBorderRightTab2 = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de vistoria");
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(500, 450));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(1000, 500));
        tabbedPane.setMinimumSize(new java.awt.Dimension(460, 300));
        tabbedPane.setPreferredSize(new java.awt.Dimension(460, 300));
        tabbedPane.setRequestFocusEnabled(false);

        panelTab1.setBackground(new java.awt.Color(153, 153, 153));
        panelTab1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTab1.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab1.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab1.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab1.setLayout(new java.awt.BorderLayout());

        panelTopTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab1.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab1.add(panelTopTab1, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab1.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab1.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab1Layout = new javax.swing.GroupLayout(panelBorderLeftTab1);
        panelBorderLeftTab1.setLayout(panelBorderLeftTab1Layout);
        panelBorderLeftTab1Layout.setHorizontalGroup(
            panelBorderLeftTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab1Layout.setVerticalGroup(
            panelBorderLeftTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderLeftTab1, java.awt.BorderLayout.LINE_START);

        panelCenterTab1.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab1.setPreferredSize(new java.awt.Dimension(420, 300));
        panelCenterTab1.setLayout(new javax.swing.BoxLayout(panelCenterTab1, javax.swing.BoxLayout.LINE_AXIS));
        panelTab1.add(panelCenterTab1, java.awt.BorderLayout.CENTER);

        panelBorderRightTab1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab1.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab1.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab1Layout = new javax.swing.GroupLayout(panelBorderRightTab1);
        panelBorderRightTab1.setLayout(panelBorderRightTab1Layout);
        panelBorderRightTab1Layout.setHorizontalGroup(
            panelBorderRightTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab1Layout.setVerticalGroup(
            panelBorderRightTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderRightTab1, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Vistoria", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-vistoria-24x24.png")), panelTab1, "Informações pessoais básicas do motorista"); // NOI18N

        panelTab2.setBackground(new java.awt.Color(153, 153, 153));
        panelTab2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelTab2.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab2.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab2.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab2.setLayout(new java.awt.BorderLayout());

        panelTopTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab2.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab2.add(panelTopTab2, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab2.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab2.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab2Layout = new javax.swing.GroupLayout(panelBorderLeftTab2);
        panelBorderLeftTab2.setLayout(panelBorderLeftTab2Layout);
        panelBorderLeftTab2Layout.setHorizontalGroup(
            panelBorderLeftTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab2Layout.setVerticalGroup(
            panelBorderLeftTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderLeftTab2, java.awt.BorderLayout.LINE_START);

        panelCenterTab2.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab2.setPreferredSize(new java.awt.Dimension(420, 300));
        panelCenterTab2.setLayout(new javax.swing.BoxLayout(panelCenterTab2, javax.swing.BoxLayout.LINE_AXIS));

        scrollPaneFotos.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneFotos.setPreferredSize(new java.awt.Dimension(410, 290));

        panelFotos.setBackground(new java.awt.Color(255, 255, 255));
        panelFotos.setPreferredSize(new java.awt.Dimension(390, 450));
        panelFotos.setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        panelFoto1.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto1.setLayout(new java.awt.BorderLayout());

        labelTituloFoto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto1.setText("Diagonal frontal esquerda");
        panelFoto1.add(labelTituloFoto1, java.awt.BorderLayout.PAGE_START);

        labelFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-diagonal-frontal-esquerda-195x150.jpg"))); // NOI18N
        labelFoto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto1MousePressed(evt);
            }
        });
        panelFoto1.add(labelFoto1, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto1);

        panelFoto2.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto2.setLayout(new java.awt.BorderLayout());

        labelTituloFoto2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto2.setText("Diagonal frontal direita");
        panelFoto2.add(labelTituloFoto2, java.awt.BorderLayout.PAGE_START);

        labelFoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-diagonal-frontal-direita-195x150.jpg"))); // NOI18N
        labelFoto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto2MousePressed(evt);
            }
        });
        panelFoto2.add(labelFoto2, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto2);

        panelFoto3.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto3.setLayout(new java.awt.BorderLayout());

        labelTituloFoto3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto3.setText("Diagonal traseira esquerda");
        panelFoto3.add(labelTituloFoto3, java.awt.BorderLayout.PAGE_START);

        labelFoto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-diagonal-traseira-esquerda-195x150.jpg"))); // NOI18N
        labelFoto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto3MousePressed(evt);
            }
        });
        panelFoto3.add(labelFoto3, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto3);

        panelFoto4.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto4.setLayout(new java.awt.BorderLayout());

        labelTituloFoto4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto4.setText("Diagonal traseira direita");
        panelFoto4.add(labelTituloFoto4, java.awt.BorderLayout.PAGE_START);

        labelFoto4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-diagonal-traseira-direita-195x150.jpg"))); // NOI18N
        labelFoto4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto4MousePressed(evt);
            }
        });
        panelFoto4.add(labelFoto4, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto4);

        panelFoto5.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto5.setLayout(new java.awt.BorderLayout());

        labelTituloFoto5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto5.setText("Traseira");
        panelFoto5.add(labelTituloFoto5, java.awt.BorderLayout.PAGE_START);

        labelFoto5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-traseira-195x150.jpg"))); // NOI18N
        labelFoto5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto5MousePressed(evt);
            }
        });
        panelFoto5.add(labelFoto5, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto5);

        panelFoto6.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        panelFoto6.setLayout(new java.awt.BorderLayout());

        labelTituloFoto6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloFoto6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloFoto6.setText("Dianteira");
        panelFoto6.add(labelTituloFoto6, java.awt.BorderLayout.PAGE_START);

        labelFoto6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/vistoria-dianteira-195x150.jpg"))); // NOI18N
        labelFoto6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelFoto6MousePressed(evt);
            }
        });
        panelFoto6.add(labelFoto6, java.awt.BorderLayout.CENTER);

        panelFotos.add(panelFoto6);

        scrollPaneFotos.setViewportView(panelFotos);

        panelCenterTab2.add(scrollPaneFotos);

        panelTab2.add(panelCenterTab2, java.awt.BorderLayout.CENTER);

        panelBorderRightTab2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab2.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab2.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab2Layout = new javax.swing.GroupLayout(panelBorderRightTab2);
        panelBorderRightTab2.setLayout(panelBorderRightTab2Layout);
        panelBorderRightTab2Layout.setHorizontalGroup(
            panelBorderRightTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab2Layout.setVerticalGroup(
            panelBorderRightTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderRightTab2, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Fotos", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-fotos-24x24.png")), panelTab2, "Informações pessoais básicas do motorista"); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 350));

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(400, 50));
        panelButtons.setMinimumSize(new java.awt.Dimension(400, 50));
        panelButtons.setPreferredSize(new java.awt.Dimension(400, 50));
        panelButtons.setVerifyInputWhenFocusTarget(false);
        panelButtons.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cancelar-24x24.png"))); // NOI18N
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setBorderPainted(false);
        buttonCancelar.setFocusPainted(false);
        buttonCancelar.setFocusable(false);
        buttonCancelar.setPreferredSize(new java.awt.Dimension(115, 35));
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonCancelar);

        buttonConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-confirmar-24x24.png"))); // NOI18N
        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.setBorderPainted(false);
        buttonConfirmar.setFocusPainted(false);
        buttonConfirmar.setFocusable(false);
        buttonConfirmar.setPreferredSize(new java.awt.Dimension(115, 35));
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonConfirmar);

        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1.add(jLabel1);

        panelButtons.add(jPanel1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 460, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {
            persistEntity();
            this.dispose();
            notifyListeners();
        } catch (ValidacaoException ex) {
            Icon iconError = new ImageIcon(getClass().getResource("/ui/media/icons/icon-erro-24x24.png"));
            if (ex.getMessage().equals("Vistoria")) {
                tabbedPane.setIconAt(0, iconError);
                formVistoria.setErrorsMessages(ex.getErrors());
            }
            if (ex.getMessage().equals("Fotos")) {
                JOptionPane.showMessageDialog(rootPane, ex.getErrors().get("foto"), "Importação necessária", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao persistir vistoria", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void labelFoto1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto1MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto1);
                vistoria.setImagem(0, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto1MousePressed

    private void labelFoto2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto2MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto2);
                vistoria.setImagem(1, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto2MousePressed

    private void labelFoto3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto3MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto3);
                vistoria.setImagem(2, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto3MousePressed

    private void labelFoto4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto4MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto4);
                vistoria.setImagem(3, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto4MousePressed

    private void labelFoto5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto5MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto5);
                vistoria.setImagem(4, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto5MousePressed

    private void labelFoto6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFoto6MousePressed
        File fileSelected = showFileChooser();
        if (fileSelected != null) {
            try {
                BufferedImage image = ImageIO.read(fileSelected);
                showImagem(image, labelFoto6);
                vistoria.setImagem(5, fileSelected);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao selecionar foto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_labelFoto6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelFoto1;
    private javax.swing.JLabel labelFoto2;
    private javax.swing.JLabel labelFoto3;
    private javax.swing.JLabel labelFoto4;
    private javax.swing.JLabel labelFoto5;
    private javax.swing.JLabel labelFoto6;
    private javax.swing.JLabel labelTituloFoto1;
    private javax.swing.JLabel labelTituloFoto2;
    private javax.swing.JLabel labelTituloFoto3;
    private javax.swing.JLabel labelTituloFoto4;
    private javax.swing.JLabel labelTituloFoto5;
    private javax.swing.JLabel labelTituloFoto6;
    private javax.swing.JPanel panelBorderLeftTab1;
    private javax.swing.JPanel panelBorderLeftTab2;
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelBorderRightTab2;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelCenterTab2;
    private javax.swing.JPanel panelFoto1;
    private javax.swing.JPanel panelFoto2;
    private javax.swing.JPanel panelFoto3;
    private javax.swing.JPanel panelFoto4;
    private javax.swing.JPanel panelFoto5;
    private javax.swing.JPanel panelFoto6;
    private javax.swing.JPanel panelFotos;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTab2;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JPanel panelTopTab2;
    private javax.swing.JScrollPane scrollPaneFotos;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
