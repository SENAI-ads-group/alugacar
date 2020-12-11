package ui.dialogs;

import aplicacao.Configuracoes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entidades.Desconto;
import model.entidades.Locacao;
import model.entidades.Taxa;
import model.entidades.enums.StatusLocacao;
import model.entidades.enums.TipoLocacao;
import model.servicos.pdf.GeradorPDF;
import util.DateUtilities;

/**
 *
 * @author patrick-ribeiro
 */
public class DialogRelatorioLocacao extends javax.swing.JDialog {

    private Locacao locacao;

    public DialogRelatorioLocacao(java.awt.Frame parent, boolean modal, Locacao locacao) {
        super(parent, modal);
        this.locacao = locacao;
        initComponents();
    }

    public void atualizarInformacoes() {
        textFieldCliente.setText(locacao.getCliente().toString());
        textFieldDataEntrega.setText(DateUtilities.formatData(locacao.getDataEntrega()));
        textFieldDataDevolucao.setText(DateUtilities.formatData(locacao.getDataDevolucao()));
        TipoLocacao tipo = locacao.getTipo();
        if (tipo == TipoLocacao.DIARIA) {
            textFieldModalidade.setText(tipo.toString() + " - R$ " + locacao.getVeiculo().getModelo().getCategoria().getValorDiaria());
        } else {
            textFieldModalidade.setText(tipo.toString() + " - R$ " + locacao.getVeiculo().getModelo().getCategoria().getValorKM());
        }
        textFieldMotorista.setText(locacao.getMotorista().toString());
        if (locacao.getTipo() == TipoLocacao.KM && locacao.getStatus() != StatusLocacao.FINALIZADA) {
            JOptionPane.showMessageDialog(this, "Não é possível calcular o valor bruto e o valor total da locação para a modalidade " + locacao.getTipo().toString()
                    + " no status " + locacao.getStatus().toString(), "Cálculo do valor bruto e valor total", JOptionPane.WARNING_MESSAGE);
            buttonPDF.setEnabled(false);
        } else {
            textFieldValorBruto.setText("R$ " + locacao.getValorBruto());
            textFieldValorTotal.setText("R$ " + locacao.getValorTotal());
            buttonPDF.setEnabled(true);
        }
        textFieldValorDescontos.setText("R$ " + locacao.getValorDescontos());
        textFieldValorTaxas.setText("R$ " + locacao.getValorTaxas());
        textFieldVeiculo.setText(locacao.getVeiculo().toString());
        atualizarListagemTaxas();
        atualizarListagemDescontos();
    }

    private void atualizarListagemTaxas() {
        DefaultTableModel tableModel = (DefaultTableModel) tableTaxas.getModel();
        tableModel.setNumRows(0);
        for (Taxa taxa : locacao.getTaxas()) {
            Object[] row = {
                taxa.getDescricao(),
                "R$ " + taxa.getValor()
            };

            tableModel.addRow(row);
        }
        tableTaxas.setModel(tableModel);
    }

    private void atualizarListagemDescontos() {
        DefaultTableModel tableModel = (DefaultTableModel) tableDescontos.getModel();
        tableModel.setNumRows(0);
        for (Desconto desconto : locacao.getDescontos()) {
            Object[] row = {
                desconto.getDescricao(),
                "R$ " + desconto.getValor()
            };

            tableModel.addRow(row);
        }
        tableDescontos.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        panelTab1 = new javax.swing.JPanel();
        panelTopTab1 = new javax.swing.JPanel();
        panelBorderLeftTab1 = new javax.swing.JPanel();
        panelCenterTab1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelMarca = new javax.swing.JLabel();
        labelCategoria = new javax.swing.JLabel();
        labelDataEntrega = new javax.swing.JLabel();
        labelErroDataEntrega1 = new javax.swing.JLabel();
        labelDataEntrega1 = new javax.swing.JLabel();
        labelMotorista = new javax.swing.JLabel();
        labelCategoria2 = new javax.swing.JLabel();
        textFieldModalidade = new javax.swing.JTextField();
        textFieldVeiculo = new javax.swing.JTextField();
        textFieldDataEntrega = new javax.swing.JTextField();
        textFieldDataDevolucao = new javax.swing.JTextField();
        textFieldCliente = new javax.swing.JTextField();
        textFieldMotorista = new javax.swing.JTextField();
        labelValorTaxas = new javax.swing.JLabel();
        textFieldValorTaxas = new javax.swing.JTextField();
        textFieldValorDescontos = new javax.swing.JTextField();
        labelValorDescontos = new javax.swing.JLabel();
        textFieldValorBruto = new javax.swing.JTextField();
        labelValorBruto = new javax.swing.JLabel();
        textFieldValorTotal = new javax.swing.JTextField();
        labelValorTotal = new javax.swing.JLabel();
        panelBorderRightTab1 = new javax.swing.JPanel();
        panelTab2 = new javax.swing.JPanel();
        panelTopTab2 = new javax.swing.JPanel();
        panelBorderLeftTab2 = new javax.swing.JPanel();
        panelCenterTab2 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        tableTaxas = new javax.swing.JTable();
        panelBorderRightTab2 = new javax.swing.JPanel();
        panelTab3 = new javax.swing.JPanel();
        panelTopTab3 = new javax.swing.JPanel();
        panelBorderLeftTab3 = new javax.swing.JPanel();
        panelCenterTab3 = new javax.swing.JPanel();
        scrollPane1 = new javax.swing.JScrollPane();
        tableDescontos = new javax.swing.JTable();
        panelBorderRightTab3 = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonPDF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualização de locação");
        setMinimumSize(new java.awt.Dimension(500, 450));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabbedPane.setMaximumSize(new java.awt.Dimension(1000, 500));
        tabbedPane.setMinimumSize(new java.awt.Dimension(460, 300));
        tabbedPane.setPreferredSize(new java.awt.Dimension(460, 300));
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
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab1.add(panelBorderLeftTab1, java.awt.BorderLayout.LINE_START);

        panelCenterTab1.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab1.setPreferredSize(new java.awt.Dimension(420, 300));
        panelCenterTab1.setLayout(new javax.swing.BoxLayout(panelCenterTab1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMarca.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelMarca.setText("Veículo");
        labelMarca.setToolTipText("Veículo a ser locado");
        labelMarca.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-info-12x12.png"))); // NOI18N
        labelCategoria.setText("Modalidade de precificação");
        labelCategoria.setToolTipText("<html>Modalidade com qual será calculado os custos da locação<br>\n<span>Diária:</span> custos calculados de acordo com a quantidade de dias de locação;<br>\n<span>KM rodado:</span> custos calculados de acordo com a quantidade de quilômetros percorridos pelo veículo no prazo da locação</html>");
        labelCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 120, -1, -1));

        labelDataEntrega.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDataEntrega.setText("Data de entrega");
        labelDataEntrega.setToolTipText("Data de previsão de entrega do veículo");
        labelDataEntrega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelDataEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelErroDataEntrega1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        labelErroDataEntrega1.setForeground(java.awt.Color.red);
        labelErroDataEntrega1.setMaximumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega1.setMinimumSize(new java.awt.Dimension(150, 15));
        labelErroDataEntrega1.setPreferredSize(new java.awt.Dimension(150, 15));
        jPanel2.add(labelErroDataEntrega1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 170, -1));

        labelDataEntrega1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelDataEntrega1.setText("Data de devolução");
        labelDataEntrega1.setToolTipText("Data de previsão de devolução do veículo");
        labelDataEntrega1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelDataEntrega1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, -1, -1));

        labelMotorista.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelMotorista.setText("Motorista");
        labelMotorista.setToolTipText("Motorista responsável pelo veículo a ser locado");
        labelMotorista.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        labelCategoria2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCategoria2.setText("Cliente");
        labelCategoria2.setToolTipText("Cliente responsável pela locação");
        labelCategoria2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelCategoria2, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 60, -1, -1));

        textFieldModalidade.setEditable(false);
        textFieldModalidade.setFocusable(false);
        textFieldModalidade.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldModalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 140, 200, -1));

        textFieldVeiculo.setEditable(false);
        textFieldVeiculo.setFocusable(false);
        textFieldVeiculo.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, -1));

        textFieldDataEntrega.setEditable(false);
        textFieldDataEntrega.setFocusable(false);
        textFieldDataEntrega.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldDataEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, -1));

        textFieldDataDevolucao.setEditable(false);
        textFieldDataDevolucao.setFocusable(false);
        textFieldDataDevolucao.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldDataDevolucao, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 20, 200, -1));

        textFieldCliente.setEditable(false);
        textFieldCliente.setFocusable(false);
        textFieldCliente.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 80, 200, -1));

        textFieldMotorista.setEditable(false);
        textFieldMotorista.setFocusable(false);
        textFieldMotorista.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 200, -1));

        labelValorTaxas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorTaxas.setText("Valor de taxas");
        labelValorTaxas.setToolTipText("Motorista responsável pelo veículo a ser locado");
        labelValorTaxas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelValorTaxas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        textFieldValorTaxas.setEditable(false);
        textFieldValorTaxas.setFocusable(false);
        textFieldValorTaxas.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldValorTaxas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 200, -1));

        textFieldValorDescontos.setEditable(false);
        textFieldValorDescontos.setFocusable(false);
        textFieldValorDescontos.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldValorDescontos, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 200, 200, -1));

        labelValorDescontos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorDescontos.setText("Valor de descontos");
        labelValorDescontos.setToolTipText("Motorista responsável pelo veículo a ser locado");
        labelValorDescontos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelValorDescontos, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 180, -1, -1));

        textFieldValorBruto.setEditable(false);
        textFieldValorBruto.setFocusable(false);
        textFieldValorBruto.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldValorBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, -1));

        labelValorBruto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorBruto.setText("Valor bruto");
        labelValorBruto.setToolTipText("Motorista responsável pelo veículo a ser locado");
        labelValorBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelValorBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        textFieldValorTotal.setEditable(false);
        textFieldValorTotal.setFocusable(false);
        textFieldValorTotal.setPreferredSize(new java.awt.Dimension(190, 25));
        jPanel2.add(textFieldValorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 260, 200, -1));

        labelValorTotal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelValorTotal.setText("Valor total");
        labelValorTotal.setToolTipText("Motorista responsável pelo veículo a ser locado");
        labelValorTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(labelValorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 240, -1, -1));

        panelCenterTab1.add(jPanel2);

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

        tabbedPane.addTab("Locação", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-locacao-24x24.png")), panelTab1); // NOI18N

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
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab2.add(panelBorderLeftTab2, java.awt.BorderLayout.LINE_START);

        panelCenterTab2.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab2.setPreferredSize(new java.awt.Dimension(420, 300));
        panelCenterTab2.setLayout(new javax.swing.BoxLayout(panelCenterTab2, javax.swing.BoxLayout.LINE_AXIS));

        tableTaxas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableTaxas.setFocusable(false);
        scrollPane.setViewportView(tableTaxas);

        panelCenterTab2.add(scrollPane);

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

        tabbedPane.addTab("Taxas", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-taxa-24x24.png")), panelTab2); // NOI18N

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
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderLeftTab3, java.awt.BorderLayout.LINE_START);

        panelCenterTab3.setBackground(new java.awt.Color(250, 250, 250));
        panelCenterTab3.setPreferredSize(new java.awt.Dimension(420, 300));
        panelCenterTab3.setLayout(new javax.swing.BoxLayout(panelCenterTab3, javax.swing.BoxLayout.LINE_AXIS));

        tableDescontos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableDescontos.setFocusable(false);
        scrollPane1.setViewportView(tableDescontos);

        panelCenterTab3.add(scrollPane1);

        panelTab3.add(panelCenterTab3, java.awt.BorderLayout.CENTER);

        panelBorderRightTab3.setBackground(new java.awt.Color(255, 255, 255));
        panelBorderRightTab3.setMaximumSize(new java.awt.Dimension(20, 272));
        panelBorderRightTab3.setMinimumSize(new java.awt.Dimension(20, 272));

        javax.swing.GroupLayout panelBorderRightTab3Layout = new javax.swing.GroupLayout(panelBorderRightTab3);
        panelBorderRightTab3.setLayout(panelBorderRightTab3Layout);
        panelBorderRightTab3Layout.setHorizontalGroup(
            panelBorderRightTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelBorderRightTab3Layout.setVerticalGroup(
            panelBorderRightTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        panelTab3.add(panelBorderRightTab3, java.awt.BorderLayout.LINE_END);

        tabbedPane.addTab("Descontos", new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-desconto-24x24.png")), panelTab3); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 350));

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setMaximumSize(new java.awt.Dimension(400, 50));
        panelButtons.setMinimumSize(new java.awt.Dimension(400, 50));
        panelButtons.setPreferredSize(new java.awt.Dimension(400, 50));
        panelButtons.setVerifyInputWhenFocusTarget(false);
        panelButtons.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonPDF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        buttonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-pdf-24x24.png"))); // NOI18N
        buttonPDF.setText("PDF");
        buttonPDF.setBorderPainted(false);
        buttonPDF.setFocusPainted(false);
        buttonPDF.setFocusable(false);
        buttonPDF.setPreferredSize(new java.awt.Dimension(115, 35));
        buttonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPDFActionPerformed(evt);
            }
        });
        jPanel1.add(buttonPDF);

        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(15, 5));
        jPanel1.add(jLabel1);

        panelButtons.add(jPanel1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 460, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPDFActionPerformed
        GeradorPDF.gerarContratoPDF(locacao, Configuracoes.getProperties().getProperty("pasta-raiz"));
    }//GEN-LAST:event_buttonPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelCategoria2;
    private javax.swing.JLabel labelDataEntrega;
    private javax.swing.JLabel labelDataEntrega1;
    private javax.swing.JLabel labelErroDataEntrega1;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelMotorista;
    private javax.swing.JLabel labelValorBruto;
    private javax.swing.JLabel labelValorDescontos;
    private javax.swing.JLabel labelValorTaxas;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JPanel panelBorderLeftTab1;
    private javax.swing.JPanel panelBorderLeftTab2;
    private javax.swing.JPanel panelBorderLeftTab3;
    private javax.swing.JPanel panelBorderRightTab1;
    private javax.swing.JPanel panelBorderRightTab2;
    private javax.swing.JPanel panelBorderRightTab3;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenterTab1;
    private javax.swing.JPanel panelCenterTab2;
    private javax.swing.JPanel panelCenterTab3;
    private javax.swing.JPanel panelTab1;
    private javax.swing.JPanel panelTab2;
    private javax.swing.JPanel panelTab3;
    private javax.swing.JPanel panelTopTab1;
    private javax.swing.JPanel panelTopTab2;
    private javax.swing.JPanel panelTopTab3;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableDescontos;
    private javax.swing.JTable tableTaxas;
    private javax.swing.JTextField textFieldCliente;
    private javax.swing.JTextField textFieldDataDevolucao;
    private javax.swing.JTextField textFieldDataEntrega;
    private javax.swing.JTextField textFieldModalidade;
    private javax.swing.JTextField textFieldMotorista;
    private javax.swing.JTextField textFieldValorBruto;
    private javax.swing.JTextField textFieldValorDescontos;
    private javax.swing.JTextField textFieldValorTaxas;
    private javax.swing.JTextField textFieldValorTotal;
    private javax.swing.JTextField textFieldVeiculo;
    // End of variables declaration//GEN-END:variables
}
