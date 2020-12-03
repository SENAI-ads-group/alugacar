package util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import model.entidades.CNH;
import model.entidades.Categoria;
import model.entidades.Cliente;
import model.entidades.Endereco;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Motorista;
import model.entidades.Pessoa;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.entidades.Veiculo;
import model.entidades.enums.CategoriaCNH;
import model.entidades.enums.Combustivel;
import model.entidades.enums.UF;

/**
 *
 * @author Alexsander
 */
public class GeradorPdfPF {

    public static void main(String[] args) {

        gerarContratoPDF();

    }
    public static final Font BOLD_UNDERLINED = new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC);

    public static final Font SUB_TITLE = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

    public static final Font NORMAL = new Font(FontFamily.COURIER, 12);

    public static void gerarContratoPDF() {

        Document documentoPDF = new Document();

        Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
        PessoaFisica pessoa = new PessoaFisica("Carlos", "(62)9.92245404", "carlos@carlos", endereco, "701.701.701-45", "313131", new Date());
        PessoaJuridica pj = new PessoaJuridica("PESSOA JURIFICA", "39980231", "pessoajuridica@gmail.com", endereco, "02.323.233/0001-34", "PESSOA JURIDICA SOCIEDADE", "131313");
        Pessoa people = pj;
        Cliente cliente = new Cliente(3131, pessoa, true);
        Marca marca = new Marca(1, "descricao");
        Categoria categoria = new Categoria(233, "descricao", 345.45, 31.44, 23.4);
        Modelo modelo = new Modelo(1, "10101010", "descricao", marca, categoria, Combustivel.DIESEL, 2020);
        Veiculo veiculo = new Veiculo(212, "placa", "renavam", 45.00, modelo, 2013, 23.543);

        CNH cnh = new CNH(1, CategoriaCNH.AB, new Date());
        Motorista motorista = new Motorista(1, pessoa, cnh, true);

        try {

            PdfWriter.getInstance(documentoPDF, new FileOutputStream("C:\\Users\\Alexsander\\Desktop\\contratoDiaria.pdf"));
            documentoPDF.open();

            //setar tamanho da pagina
            documentoPDF.setPageSize(PageSize.A4);

            //**********GERANDO A PRIMEIRA TABELA COM OS DADOS DO CLIENTE*******//
            PdfPTable tabela1 = new PdfPTable(2);

            PdfPCell emBranco = new PdfPCell(new Paragraph("   "));
            emBranco.setColspan(2);
            tabela1.addCell(emBranco);

            PdfPCell cabecalhoTabela1 = new PdfPCell(new Paragraph("DADOS DO CLIENTE", NORMAL));

            cabecalhoTabela1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalhoTabela1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cabecalhoTabela1.setBorder(PdfPTable.ALIGN_MIDDLE);
            cabecalhoTabela1.setColspan(2);

            tabela1.addCell(cabecalhoTabela1);
            tabela1.addCell(emBranco);

            if (people instanceof PessoaFisica) {
                tabela1.addCell("Nome : " + ((PessoaFisica) people).getNome());
            } else {
                tabela1.addCell("Razão Social : " + ((PessoaJuridica) people).getRazaoSocial());

            }

            if (people instanceof PessoaFisica) {

                tabela1.addCell("CPF : " + ((PessoaFisica) people).getCpf());
            } else {
                tabela1.addCell("CNPJ : " + ((PessoaJuridica) people).getCnpj());
            }

            tabela1.addCell("Telefone : " + pessoa.getTelefone());
            tabela1.addCell("E-mail : " + cliente.getPessoa().getEmail());
            tabela1.addCell("ID : " + cliente.getId());
            tabela1.addCell("Tipo : " + cliente.getTipoCliente().toString());

            //tabela.setSpacingBefore(17);
            tabela1.addCell(emBranco);

            //*************CAMPO APENAS COM OS DADOS DO MOTORISTA*************//
            PdfPTable tabela2 = new PdfPTable(2);
            PdfPCell cabecalhoTabela2 = new PdfPCell(new Paragraph("DADOS DO MOTORISTA", NORMAL));

            cabecalhoTabela2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalhoTabela2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cabecalhoTabela2.setBorder(PdfPTable.ALIGN_MIDDLE);
            cabecalhoTabela2.setColspan(2);

            tabela2.addCell(cabecalhoTabela2);
            tabela2.addCell(emBranco);

            tabela2.addCell("Nome : " + motorista.getPessoa().getNome());
            tabela2.addCell("CPF : " + motorista.getPessoa().getCpf());
            tabela2.addCell("CNH : " + motorista.getCnh().getCategoria());
            tabela2.addCell("RG :  " + motorista.getPessoa().getRegistroGeral());
            tabela2.addCell("ID : " + motorista.getId());
            tabela2.addCell("Telefone : " + motorista.getPessoa().getTelefone());
            tabela2.addCell("E-mail : " + motorista.getPessoa().getEmail());
            tabela2.addCell("Data de Nascimento : " + DateUtilities.formatData(motorista.getPessoa().getDataNascimento()));

            tabela2.addCell(emBranco);

            //*************CAMPO APENAS COM OS DADOS DO VEÍCULO ALUGADO*************//
            PdfPTable tabela3 = new PdfPTable(2);
            PdfPCell cabecalhoTabela3 = new PdfPCell(new Paragraph("DADOS DO VEICULO", NORMAL));

            cabecalhoTabela3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalhoTabela3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cabecalhoTabela3.setBorder(PdfPTable.ALIGN_MIDDLE);
            cabecalhoTabela3.setColspan(2);

            tabela3.addCell(cabecalhoTabela3);
            tabela3.addCell(emBranco);

            tabela3.addCell("Marca : " + modelo.getMarca());
            tabela3.addCell("Categoria : " + modelo.getDescricao());

            tabela3.addCell("Código FIPE :  " + modelo.getCodigoFipe());
            tabela3.addCell("Placa : " + veiculo.getPlaca());
            tabela3.addCell("Quilometragem : " + veiculo.getKMRodado() + " KM");
            tabela3.addCell("Ano de Fabricação : " + veiculo.getAnoFabricacao());
            tabela3.addCell("Renavam : " + veiculo.getRenavam());
            tabela3.addCell(emBranco);
            tabela3.addCell(emBranco);

            //*************CAMPO APENAS COM OS DADOS DO MOTORISTA*************//
            //*************CAMPO APENAS COM OS DADOS DA LOCAÇÃO*************//
            PdfPTable tabela4 = new PdfPTable(2);
            PdfPCell cabecalhoTabela4 = new PdfPCell(new Paragraph("DADOS DA LOCAÇÃO", NORMAL));

            cabecalhoTabela4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalhoTabela4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cabecalhoTabela4.setBorder(PdfPTable.ALIGN_MIDDLE);
            cabecalhoTabela4.setColspan(2);

            tabela4.addCell(cabecalhoTabela4);
            tabela4.addCell(emBranco);

            tabela4.addCell("Data da retirada : ");
            tabela4.addCell("Data da devolução : ");
            tabela4.addCell("Modalidade :");
            tabela4.addCell("Status da Locação : ");
            tabela4.addCell(emBranco);

            //*************CAMPO APENAS COM OS DADOS DA VISTORIA*************//
            PdfPTable tabela5 = new PdfPTable(2);
            PdfPCell cabecalhoTabela5 = new PdfPCell(new Paragraph("DADOS DA VISTORIA", NORMAL));

            cabecalhoTabela5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalhoTabela5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cabecalhoTabela5.setBorder(PdfPTable.ALIGN_MIDDLE);
            cabecalhoTabela5.setColspan(2);

            tabela5.addCell(cabecalhoTabela5);
            tabela5.addCell(emBranco);

            tabela5.addCell("Tipo de Taxa");
            tabela5.addCell("Valor");


            /*Image imagem = Image.getInstance("C:\\Users\\Alexsander\\Desktop\\teste.jpg");
            imagem.scalePercent(15);*/
            documentoPDF.add(tabela1);
            documentoPDF.add(tabela2);
            documentoPDF.add(tabela3);
            documentoPDF.add(tabela4);
            documentoPDF.add(tabela5);

            Desktop.getDesktop().open(new File("C:\\Users\\Alexsander\\Desktop\\contratoDiaria.pdf"));

            //adicionando imagem
            //Image imagens = Image.getInstance("C:\\Users\\Alexsander\\Desktop\\teste.jpg");
            //documentoPDF.add(imagens);
        } catch (DocumentException de) {
            JOptionPane.showMessageDialog(null, "ERRO" + de.getMessage());
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERRO" + ioe.getMessage());
        } finally {
            documentoPDF.close();
        }

    }

}
