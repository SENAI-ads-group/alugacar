package model.servicos.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.itextpdf.text.Font.FontFamily;
import model.entidades.Cliente;
import model.entidades.Locacao;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.entidades.Veiculo;
import model.entidades.Vistoria;
import model.entidades.enums.TipoCliente;
import util.DateUtilities;

/**
 *
 * @author Alexsander
 */
public class GeradorPDF {

    public static final Font BOLD_UNDERLINED = new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC);
    public static final Font SUB_TITLE = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    public static final Font NORMAL = new Font(FontFamily.COURIER, 12);

    public static void gerarContratoPDF(Locacao locacao, String localDestino) {
        Document documentoPDF = new Document();
        try {
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(localDestino + "\\contratoDiaria.pdf"));
            documentoPDF.open();

            documentoPDF.setPageSize(PageSize.A4);

            documentoPDF.add(getTabelaCliente(locacao.getCliente()));
            documentoPDF.add(getTabelaMotorista(locacao.getMotorista()));
            documentoPDF.add(getTabelaVeiculo(locacao.getVeiculo()));
            documentoPDF.add(getTabelaLocacao(locacao));
            /*Verificar o status da locação antes de inserir as vistorias no PDF
            Se o status for PENDENTE deve gerar apenas os dados do veículo, cliente, motorista,
            cliente, data de registro, data de entrega (representa a previsão de entrega),
            data de devolução (representa a previsão de devolução)
            
            Se for INICIADA não deve ser gerado além das informações principais, as taxas, descontos e vistoria de entrada
            
            Se for FINALIZADA dedve ser gerado todas as informações
            
            Consulte a entidade de Vistoria, Locacao e o serviço de contrato
             */
            documentoPDF.add(getTabelaVistoria(locacao.getVistoriaEntrega()));

            Desktop.getDesktop().open(new File(localDestino + "\\contratoDiaria.pdf"));
        } catch (DocumentException de) {
            JOptionPane.showMessageDialog(null, "ERRO DocumentException: " + de.getMessage());
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERRO IOException: " + ioe.getMessage());
        } finally {
            documentoPDF.close();
        }
    }

    private static PdfPTable getTabelaCliente(Cliente cliente) {
        PdfPTable tabela = new PdfPTable(2);
        tabela.addCell(getCellEspacamento());

        PdfPCell cellCabecalho = new PdfPCell(new Paragraph("DADOS DO CLIENTE", NORMAL));
        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(2);

        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        tabela.addCell("ID : " + cliente.getId());
        tabela.addCell("Tipo : " + cliente.getTipoCliente().toString());
        if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
            PessoaFisica pessoaFisica = (PessoaFisica) cliente.getPessoa();
            tabela.addCell("Nome : " + pessoaFisica.getNome());
            tabela.addCell("CPF : " + pessoaFisica.getCpf());
        } else {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente.getPessoa();
            tabela.addCell("Razão Social : " + pessoaJuridica.getRazaoSocial());
            tabela.addCell("CNPJ : " + pessoaJuridica.getCnpj());
        }
        tabela.addCell("Telefone : " + cliente.getPessoa().getTelefone());
        tabela.addCell("E-mail : " + cliente.getPessoa().getEmail());

        //tabela.setSpacingBefore(17);
        tabela.addCell(getCellEspacamento());
        return tabela;
    }

    private static PdfPTable getTabelaMotorista(Motorista motorista) {
        PdfPTable tabela = new PdfPTable(2);
        PdfPCell cellCabecalho = new PdfPCell(new Paragraph("DADOS DO MOTORISTA", NORMAL));

        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(2);

        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        tabela.addCell("Nome : " + motorista.getPessoa().getNome());
        tabela.addCell("CPF : " + motorista.getPessoa().getCpf());
        tabela.addCell("CNH : " + motorista.getCnh().getCategoria());
        tabela.addCell("RG :  " + motorista.getPessoa().getRegistroGeral());
        tabela.addCell("ID : " + motorista.getId());
        tabela.addCell("Telefone : " + motorista.getPessoa().getTelefone());
        tabela.addCell("E-mail : " + motorista.getPessoa().getEmail());
        tabela.addCell("Data de Nascimento : " + DateUtilities.formatData(motorista.getPessoa().getDataNascimento()));

        tabela.addCell(getCellEspacamento());
        return tabela;
    }

    private static PdfPTable getTabelaVeiculo(Veiculo veiculo) {
        PdfPTable tabela = new PdfPTable(2);

        PdfPCell cellCabecalho = new PdfPCell(new Paragraph("DADOS DO VEICULO", NORMAL));
        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(2);

        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        tabela.addCell("Marca : " + veiculo.getModelo().getMarca());
        tabela.addCell("Categoria : " + veiculo.getModelo().getDescricao());
        tabela.addCell("Código FIPE :  " + veiculo.getModelo().getCodigoFipe());
        tabela.addCell("Placa : " + veiculo.getPlaca());
        tabela.addCell("Ano de Fabricação : " + veiculo.getAnoFabricacao());
        tabela.addCell("Renavam : " + veiculo.getRenavam());

        tabela.addCell(getCellEspacamento());
        tabela.addCell(getCellEspacamento());
        return tabela;
    }

    private static PdfPTable getTabelaLocacao(Locacao locacao) {
        PdfPTable tabela = new PdfPTable(2);

        PdfPCell cellCabecalho = new PdfPCell(new Paragraph("DADOS DA LOCAÇÃO", NORMAL));
        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(2);

        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        //Implementar estrutura repetitiva para cada taxa e desconto
        tabela.addCell("Data da retirada : ");
        tabela.addCell("Data da devolução : ");
        tabela.addCell("Modalidade :");
        tabela.addCell("Status da Locação : ");
        tabela.addCell(getCellEspacamento());
        return tabela;
    }

    private static PdfPTable getTabelaVistoria(Vistoria vistoria) {
        PdfPTable tabela = new PdfPTable(2);

        PdfPCell cellCabecalho = new PdfPCell(new Paragraph("DADOS DA VISTORIA", NORMAL));
        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(2);

        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        tabela.addCell("Tipo de Taxa");
        tabela.addCell("Valor");
        /*Image imagem = Image.getInstance("C:\\Users\\Alexsander\\Desktop\\teste.jpg");
            imagem.scalePercent(15);*/
        //adicionando imagem
        //Image imagens = Image.getInstance("C:\\Users\\Alexsander\\Desktop\\teste.jpg");
        //documentoPDF.add(imagens);
        return tabela;
    }

    private static PdfPCell getCellEspacamento() {
        PdfPCell cellEspacamento = new PdfPCell(new Paragraph("   "));
        cellEspacamento.setColspan(2);
        return cellEspacamento;
    }
}
