package ui.dialogs;

import model.entidades.CNH;
import model.entidades.Endereco;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.enums.CategoriaCNH;
import model.entidades.services.persistence.MotoristaPersistenceService;
import model.entidades.services.persistence.PersistenceFactory;
import model.entidades.services.persistence.exceptions.PersistenceException;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.exceptions.ValidationException;
import ui.listeners.DataChangeListener;
import ui.panels.PanelFormEndereco;
import ui.panels.PanelFormPessoaFisica;
import ui.panels.PanelMotoristasList;
import util.DateUtilities;
import util.PanelUtilities;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogMotoristaForm extends javax.swing.JDialog {

    private Motorista motorista;
    private final MotoristaPersistenceService MotoristaPersistenceService = PersistenceFactory.createMotoristaService();

    private PanelFormEndereco panelFormEndereco;
    private PanelFormPessoaFisica panelFormPessoaFisica;
    private PanelMotoristasList panelMotoristasList;
    private File fileFotoMotorista;

    private final List<DataChangeListener> listeners;

    public DialogMotoristaForm(java.awt.Frame parent, boolean modal, Motorista motorista) {
        super(parent, modal);
        this.motorista = motorista;
        initComponents();
        initCombobox();
        loadPanels();

        listeners = new ArrayList<>();
    }

    private void loadPanels() {
        panelFormEndereco = new PanelFormEndereco(motorista.getPessoa().getEndereco());
        panelFormPessoaFisica = new PanelFormPessoaFisica(motorista.getPessoa());

        PanelUtilities.loadPanelToPanel(panelFormPessoaFisica, panelCenterTab1);
        PanelUtilities.loadPanelToPanel(panelFormEndereco, panelCenterTab3);
    }

    private void initCombobox() {
        comboBoxCategoriaCNH.setModel(new DefaultComboBoxModel(CategoriaCNH.values()));
    }

    private void persistEntity() throws PersistenceException, ValidationException {
        motorista = getFormData();
        if (motorista.getId() == null) {
            MotoristaPersistenceService.inserir(motorista);
        } else {
            MotoristaPersistenceService.atualizar(motorista);
        }
    }

    public Motorista getFormData() throws ValidationException {
        PessoaFisica pessoa = panelFormPessoaFisica.getFormData();
        if (DateUtilities.getAge(pessoa.getDataNascimento()) < Motorista.IDADE_MINIMA) {
            ValidationException exception = new ValidationException("PanelFormPessoaFisica");
            exception.addError("dataNascimento", "Idade mínima " + Motorista.IDADE_MINIMA + " anos");
            throw exception;
        }
        ValidationException exceptionCNH = new ValidationException("CNH");
        if (Utilities.textFieldIsEmpty(textFieldNumeroRegistro)) {
            exceptionCNH.addError("numeroRegistro", "Registro não informado");
        }
        if (Utilities.textFieldIsEmpty(textFieldFoto)) {
            exceptionCNH.addError("foto", "Foto não selecionada");
        }
        if (dateChooserValidadeCNH.getDate() == null) {
            exceptionCNH.addError("validadeCNH", "Data não selecionada");
        }
        clearErrors();
        if (exceptionCNH.getErrors().size() > 0) {
            throw exceptionCNH;
        }
        Endereco endereco = panelFormEndereco.getFormData();
        pessoa.setEndereco(endereco);
        motorista.setPessoa(pessoa);
        Integer numeroRegistroCNH = Utilities.tryParseToInteger(textFieldNumeroRegistro.getText());
        Date dataValidadeCNH = dateChooserValidadeCNH.getDate();
        CategoriaCNH categoriaCNH = CategoriaCNH.valueOf(comboBoxCategoriaCNH.getSelectedItem().toString());
        CNH cnh = new CNH(numeroRegistroCNH, categoriaCNH, dataValidadeCNH);
        motorista.setCnh(cnh);
        motorista.setFoto(fileFotoMotorista);

        return motorista;
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

    public void setErrors(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("numeroRegistro")) {
            labelErroNumRegistro.setText(errors.get("numeroRegistro"));
        }
        if (fields.contains("foto")) {
            labelErroFoto.setText(errors.get("foto"));
        }
        if (fields.contains("validadeCNH")) {
            labelErroValidadeCNH.setText(errors.get("validadeCNH"));
        }
    }

    public void clearErrors() {
        labelErroNumRegistro.setText("");
        labelErroFoto.setText("");
        labelErroValidadeCNH.setText("");
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
        panelCNH = new javax.swing.JPanel();
        textFieldNumeroRegistro = new javax.swing.JTextField();
        labelNumRegistro = new javax.swing.JLabel();
        labelDataValidade = new javax.swing.JLabel();
        comboBoxCategoriaCNH = new javax.swing.JComboBox<>();
        labelCategoria = new javax.swing.JLabel();
        textFieldFoto = new javax.swing.JTextField();
        labelFoto = new javax.swing.JLabel();
        buttonBuscarFoto = new javax.swing.JButton();
        dateChooserValidadeCNH = new com.toedter.calendar.JDateChooser();
        labelShowFoto = new javax.swing.JLabel();
        labelErroNumRegistro = new javax.swing.JLabel();
        labelErroFoto = new javax.swing.JLabel();
        labelErroCategoriaCNH = new javax.swing.JLabel();
        labelErroValidadeCNH = new javax.swing.JLabel();
        panelBorderRightTab2 = new javax.swing.JPanel();
        panelTab3 = new javax.swing.JPanel();
        panelTopTab3 = new javax.swing.JPanel();
        panelBorderLeftTab3 = new javax.swing.JPanel();
        panelCenterTab3 = new javax.swing.JPanel();
        panelBorderRightTab4 = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de motorista");
        setMaximumSize(new java.awt.Dimension(440, 420));
        setMinimumSize(new java.awt.Dimension(440, 420));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(440, 420));
        setResizable(false);
        setSize(new java.awt.Dimension(440, 420));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(400, 300));
        tabbedPane.setMinimumSize(new java.awt.Dimension(400, 300));
        tabbedPane.setPreferredSize(new java.awt.Dimension(400, 300));
        tabbedPane.setRequestFocusEnabled(false);

        panelTab1.setBackground(new java.awt.Color(153, 153, 153));
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
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderLeftTab1, java.awt.BorderLayout.LINE_START);

        panelCenterTab1.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab1.setPreferredSize(new java.awt.Dimension(400, 300));
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
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderRightTab1, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Informações Pessoais", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")), panelTab1); // NOI18N

        panelTab2.setBackground(new java.awt.Color(153, 153, 153));
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
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderLeftTab2, java.awt.BorderLayout.LINE_START);

        panelCenterTab2.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab2.setPreferredSize(new java.awt.Dimension(400, 300));
        panelCenterTab2.setLayout(new javax.swing.BoxLayout(panelCenterTab2, javax.swing.BoxLayout.LINE_AXIS));

        panelCNH.setBackground(new java.awt.Color(255, 255, 255));
        panelCNH.setMaximumSize(new java.awt.Dimension(400, 280));
        panelCNH.setMinimumSize(new java.awt.Dimension(400, 280));
        panelCNH.setPreferredSize(new java.awt.Dimension(400, 280));
        panelCNH.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textFieldNumeroRegistro.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldNumeroRegistro.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(textFieldNumeroRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        labelNumRegistro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelNumRegistro.setText("Número de registro");
        panelCNH.add(labelNumRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelDataValidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelDataValidade.setText("Data validade");
        panelCNH.add(labelDataValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        comboBoxCategoriaCNH.setMinimumSize(new java.awt.Dimension(150, 25));
        comboBoxCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(comboBoxCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelCategoria.setText("Categoria");
        panelCNH.add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        textFieldFoto.setMaximumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setMinimumSize(new java.awt.Dimension(170, 25));
        textFieldFoto.setPreferredSize(new java.awt.Dimension(140, 25));
        panelCNH.add(textFieldFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        labelFoto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelFoto.setText("Foto");
        panelCNH.add(labelFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 110, -1));

        buttonBuscarFoto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonBuscarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pasta-24x24.png"))); // NOI18N
        buttonBuscarFoto.setBorderPainted(false);
        buttonBuscarFoto.setFocusPainted(false);
        buttonBuscarFoto.setFocusable(false);
        buttonBuscarFoto.setPreferredSize(new java.awt.Dimension(30, 25));
        buttonBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarFotoActionPerformed(evt);
            }
        });
        panelCNH.add(buttonBuscarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        dateChooserValidadeCNH.setPreferredSize(new java.awt.Dimension(170, 25));
        panelCNH.add(dateChooserValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        labelShowFoto.setMaximumSize(new java.awt.Dimension(170, 90));
        labelShowFoto.setMinimumSize(new java.awt.Dimension(170, 90));
        labelShowFoto.setPreferredSize(new java.awt.Dimension(170, 90));
        panelCNH.add(labelShowFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, 120));

        labelErroNumRegistro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroNumRegistro.setForeground(java.awt.Color.red);
        labelErroNumRegistro.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistro.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroNumRegistro.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroNumRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, -1, -1));

        labelErroFoto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroFoto.setForeground(java.awt.Color.red);
        labelErroFoto.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroFoto.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 105, -1, -1));

        labelErroCategoriaCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroCategoriaCNH.setForeground(java.awt.Color.red);
        labelErroCategoriaCNH.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroCategoriaCNH.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroCategoriaCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, -1, -1));

        labelErroValidadeCNH.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroValidadeCNH.setForeground(java.awt.Color.red);
        labelErroValidadeCNH.setMaximumSize(new java.awt.Dimension(170, 15));
        labelErroValidadeCNH.setMinimumSize(new java.awt.Dimension(170, 15));
        labelErroValidadeCNH.setPreferredSize(new java.awt.Dimension(170, 15));
        panelCNH.add(labelErroValidadeCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 105, -1, -1));

        panelCenterTab2.add(panelCNH);

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
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderRightTab2, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("CNH", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png")), panelTab2); // NOI18N

        panelTab3.setBackground(new java.awt.Color(153, 153, 153));
        panelTab3.setMaximumSize(new java.awt.Dimension(400, 280));
        panelTab3.setMinimumSize(new java.awt.Dimension(400, 280));
        panelTab3.setPreferredSize(new java.awt.Dimension(400, 280));
        panelTab3.setLayout(new java.awt.BorderLayout());

        panelTopTab3.setBackground(new java.awt.Color(255, 255, 255));
        panelTopTab3.setPreferredSize(new java.awt.Dimension(400, 10));
        panelTab3.add(panelTopTab3, java.awt.BorderLayout.PAGE_START);

        panelBorderLeftTab3.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderLeftTab3.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderLeftTab3.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderLeftTab3Layout = new javax.swing.GroupLayout(panelBorderLeftTab3);
        panelBorderLeftTab3.setLayout(panelBorderLeftTab3Layout);
        panelBorderLeftTab3Layout.setHorizontalGroup(
            panelBorderLeftTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderLeftTab3Layout.setVerticalGroup(
            panelBorderLeftTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderLeftTab3, java.awt.BorderLayout.LINE_START);

        panelCenterTab3.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab3.setPreferredSize(new java.awt.Dimension(400, 300));
        panelCenterTab3.setLayout(new javax.swing.BoxLayout(panelCenterTab3, javax.swing.BoxLayout.LINE_AXIS));
        panelTab3.add(panelCenterTab3, java.awt.BorderLayout.CENTER);

        panelBorderRightTab4.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab4.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab4.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab4Layout = new javax.swing.GroupLayout(panelBorderRightTab4);
        panelBorderRightTab4.setLayout(panelBorderRightTab4Layout);
        panelBorderRightTab4Layout.setHorizontalGroup(
            panelBorderRightTab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab4Layout.setVerticalGroup(
            panelBorderRightTab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderRightTab4, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Endereço", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")), panelTab3); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        getContentPane().add(panelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        tabbedPane.setIconAt(0, new ImageIcon(getClass().getResource("/ui/media/icons/icon-pessoafisica-24x24.png")));
        tabbedPane.setIconAt(1, new ImageIcon(getClass().getResource("/ui/media/icons/icon-cnh-24x24.png")));
        tabbedPane.setIconAt(2, new ImageIcon(getClass().getResource("/ui/media/icons/icon-endereco-24x24.png")));
        try {
            persistEntity();
            this.dispose();
            notifyListeners();
        } catch (ValidationException ex) {
            Icon iconError = new ImageIcon(getClass().getResource("/ui/media/icons/icon-erro-24x24.png"));
            if (ex.getMessage().equals("PanelFormPessoaFisica")) {
                tabbedPane.setIconAt(0, iconError);
                panelFormPessoaFisica.setErrorsMessages(ex.getErrors());
            }
            if (ex.getMessage().equals("CNH")) {
                tabbedPane.setIconAt(1, iconError);
                setErrors(ex.getErrors());
            }
            if (ex.getMessage().equals("PanelFormEndereco")) {
                tabbedPane.setIconAt(2, iconError);
                panelFormEndereco.setErrorsMessages(ex.getErrors());
            }
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao persistir motorista", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarFotoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("imagem", "jpg", "jpeg", "png"));

        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            fileFotoMotorista = fileChooser.getSelectedFile();
            textFieldFoto.setText(fileFotoMotorista.getPath());
            ImageIcon imageIcon = new ImageIcon(fileFotoMotorista.getPath());
            Image image = imageIcon.getImage().getScaledInstance(labelShowFoto.getWidth(), labelShowFoto.getHeight(), Image.SCALE_DEFAULT);
            labelShowFoto.setIcon(new ImageIcon(image));
            labelShowFoto.repaint();
        }
    }//GEN-LAST:event_buttonBuscarFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscarFoto;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JComboBox<String> comboBoxCategoriaCNH;
    private com.toedter.calendar.JDateChooser dateChooserValidadeCNH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelDataValidade;
    private javax.swing.JLabel labelErroCategoriaCNH;
    private javax.swing.JLabel labelErroFoto;
    private javax.swing.JLabel labelErroNumRegistro;
    private javax.swing.JLabel labelErroValidadeCNH;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelNumRegistro;
    private javax.swing.JLabel labelShowFoto;
    private javax.swing.JPanel panelBorderLeftTab1;
    private javax.swing.JPanel panelBorderLeftTab2;
    private javax.swing.JPanel panelBorderLeftTab3;
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelBorderRightTab2;
    private javax.swing.JPanel panelBorderRightTab4;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCNH;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelCenterTab2;
    private javax.swing.JPanel panelCenterTab3;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTab2;
    private javax.swing.JPanel panelTab3;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JPanel panelTopTab2;
    private javax.swing.JPanel panelTopTab3;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField textFieldFoto;
    private javax.swing.JTextField textFieldNumeroRegistro;
    // End of variables declaration//GEN-END:variables
}
