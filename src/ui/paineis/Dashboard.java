package ui.paineis;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import model.entidades.Locacao;
import model.entidades.enums.StatusLocacao;
import model.servicos.persistencia.DAOFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author patrick-ribeiro
 */
public final class Dashboard extends javax.swing.JPanel {

    public Dashboard() {
        initComponents();
        criarGraficoLocacoes();
    }

    public void criarGraficoLocacoes() {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        List<Locacao> locacoes = DAOFactory.createLocacaoDAO().buscarTodos();

        int qtdePendente = 0;
        int qtdeIniciada = 0;
        int qtdeFinalizada = 0;
        int qtdeAtrasada = 0;
        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == StatusLocacao.PENDENTE) {
                qtdePendente++;
            } else if (locacao.getStatus() == StatusLocacao.INICIADA && locacao.getDataDevolucao().before(new Date())) {
                qtdeAtrasada++;
            } else if (locacao.getStatus() == StatusLocacao.INICIADA) {
                qtdeIniciada++;
            } else if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
                qtdeFinalizada++;
            }
        }
        categoryDataset.addValue(qtdePendente, "Pendentes", "");
        categoryDataset.addValue(qtdeIniciada, "Em aberto", "");
        categoryDataset.addValue(qtdeAtrasada, "Atrasadas", "");
        categoryDataset.addValue(qtdeFinalizada, "Finalizadas", "");
        JFreeChart chart = ChartFactory.createBarChart("Status de locações", "Quantidade", "", categoryDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(240, 240, 240));
        //painelGrafico.add(chartPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        labelTitleList = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1200, 768));
        setLayout(new java.awt.BorderLayout());

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setPreferredSize(new java.awt.Dimension(1216, 80));

        labelTitleList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitleList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/icons/icon-vistoria-28x28.png"))); // NOI18N
        labelTitleList.setText("Dashboard");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleList)
                .addContainerGap(1044, Short.MAX_VALUE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(labelTitleList, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(panelHeader, java.awt.BorderLayout.PAGE_START);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/media/images/logomarca.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelTitleList;
    private javax.swing.JPanel panelHeader;
    // End of variables declaration//GEN-END:variables
}
