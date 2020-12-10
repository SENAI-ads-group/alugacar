package model.servicos.pdf;

import com.itextpdf.text.BadElementException;
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
import com.itextpdf.text.Image;
import model.entidades.Cliente;
import model.entidades.Desconto;
import model.entidades.ItemVistoria;
import model.entidades.Locacao;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.entidades.Taxa;
import model.entidades.Veiculo;
import model.entidades.Vistoria;
import model.entidades.enums.StatusLocacao;
import model.entidades.enums.TipoCliente;
import model.entidades.enums.TipoLocacao;
import util.DateUtilities;

/**
 *
 * @author Alexsander
 */
public class GeradorPDF {

    public static final Font FONTE_TITULO = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    public static final Font FONTE_SUBTITULO = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    public static final Font FONTE_TEXTO = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

    public static void gerarContratoPDF(Locacao locacao, String localDestino) {
        Document documentoPDF = new Document();
        try {
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(localDestino + "/contrato.pdf"));
            documentoPDF.open();

            documentoPDF.setPageSize(PageSize.A4);

            documentoPDF.add(getTabelaLocacao(locacao));
            documentoPDF.add(new Paragraph(" "));
            documentoPDF.add(getTabelaCliente(locacao.getCliente()));
            documentoPDF.add(new Paragraph(" "));
            documentoPDF.add(getTabelaMotorista(locacao.getMotorista()));
            documentoPDF.add(new Paragraph(" "));
            documentoPDF.add(getTabelaVeiculo(locacao.getVeiculo()));
            if (locacao.getStatus() != StatusLocacao.PENDENTE) {
                documentoPDF.newPage();
                documentoPDF.add(getTabelaVistoria(locacao.getVistoriaEntrega(), "Vistoria de Entrega"));
            }
            if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
                documentoPDF.newPage();
                documentoPDF.add(getTabelaVistoria(locacao.getVistoriaDevolucao(), "Vistoria de Devolução"));
            }
            documentoPDF.newPage();
            if (locacao.getCliente().getPessoa() instanceof PessoaFisica) {
                documentoPDF.add(getParagrafoPessoaFisica(locacao));
            } else {
                documentoPDF.add(getParagrafoPessoaJuridica(locacao));
            }
            Desktop.getDesktop().open(new File(localDestino + "/contrato.pdf"));
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        } finally {
            documentoPDF.close();
        }
    }

    private static PdfPTable getTabelaCliente(Cliente cliente) {
        PdfPTable tabela = new PdfPTable(2);

        adicionarCabecalho(tabela, new Paragraph("Informações do Cliente", FONTE_TITULO));

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

        return tabela;
    }

    private static PdfPTable getTabelaMotorista(Motorista motorista) {
        PdfPTable tabela = new PdfPTable(2);

        adicionarCabecalho(tabela, new Paragraph("Informações do Motorista", FONTE_TITULO));

        tabela.addCell("Nome : " + motorista.getPessoa().getNome());
        tabela.addCell("CPF : " + motorista.getPessoa().getCpf());
        tabela.addCell("CNH : " + motorista.getCnh().getCategoria());
        tabela.addCell("RG :  " + motorista.getPessoa().getRegistroGeral());
        tabela.addCell("ID : " + motorista.getId());
        tabela.addCell("Telefone : " + motorista.getPessoa().getTelefone());
        tabela.addCell("E-mail : " + motorista.getPessoa().getEmail());
        tabela.addCell("Data de Nascimento : " + DateUtilities.formatData(motorista.getPessoa().getDataNascimento()));

        return tabela;
    }

    private static PdfPTable getTabelaVeiculo(Veiculo veiculo) {
        PdfPTable tabela = new PdfPTable(2);

        adicionarCabecalho(tabela, new Paragraph("Veículo Locado", FONTE_TITULO));

        tabela.addCell("Marca : " + veiculo.getModelo().getMarca());
        tabela.addCell("Categoria : " + veiculo.getModelo().getDescricao());
        tabela.addCell("Código FIPE :  " + veiculo.getModelo().getCodigoFipe());
        tabela.addCell("Placa : " + veiculo.getPlaca());
        tabela.addCell("Ano de Fabricação : " + veiculo.getAnoFabricacao());
        tabela.addCell("Renavam : " + veiculo.getRenavam());

        return tabela;
    }

    private static PdfPTable getTabelaLocacao(Locacao locacao) {
        PdfPTable tabela = new PdfPTable(2);

        adicionarCabecalho(tabela, new Paragraph("Informações da Locação", FONTE_TITULO));

        tabela.addCell("Data de entrega : " + DateUtilities.formatDataDia(locacao.getDataEntrega()));
        tabela.addCell("Data da devolução : " + DateUtilities.formatDataDia(locacao.getDataDevolucao()));
        tabela.addCell("Status atual : " + locacao.getStatus().toString());

        tabela.addCell("Modalidade de precificação : " + locacao.getTipo());
        if (locacao.getTipo() == TipoLocacao.DIARIA) {
            Paragraph paragraph = new Paragraph("Valor da diária : R$ "
                    + locacao.getVeiculo().getModelo().getCategoria().getValorDiaria());
            adicionarCelulaLinha(tabela, paragraph);
        } else if (locacao.getTipo() == locacao.getTipo().KM) {
            Paragraph paragraph = new Paragraph("Preço do KM rodado : R$ "
                    + locacao.getVeiculo().getModelo().getCategoria().getValorKM());
            adicionarCelulaLinha(tabela, paragraph);
        }
        adicionarCabecalho(tabela, new Paragraph("Valores", FONTE_SUBTITULO));
        for (Taxa taxa : locacao.getTaxas()) {
            PdfPCell cellTaxa = new PdfPCell(new Paragraph("(+) " + taxa.getDescricao()
                    + " R$" + taxa.getValor()));
            cellTaxa.setColspan(2);
            cellTaxa.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabela.addCell(cellTaxa);
        }
        for (Desconto desconto : locacao.getDescontos()) {
            PdfPCell cellDesconto = new PdfPCell(new Paragraph("(-) " + desconto.getDescricao()
                    + " R$" + desconto.getValor()));
            cellDesconto.setColspan(2);
            cellDesconto.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabela.addCell(cellDesconto);
        }
        PdfPCell cellValorBruto = new PdfPCell(new Paragraph("Valor bruto R$" + locacao.getValorBruto()));
        cellValorBruto.setColspan(2);
        cellValorBruto.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cellValorBruto);

        PdfPCell cellValorTotal = new PdfPCell(new Paragraph("Valor total R$" + locacao.getValorTotal()));
        cellValorTotal.setColspan(2);
        cellValorTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cellValorTotal);

        return tabela;
    }

    private static PdfPTable getTabelaVistoria(Vistoria vistoria, String titulo) throws IOException, BadElementException {
        PdfPTable tabela = new PdfPTable(2);

        adicionarCabecalho(tabela, new Paragraph(titulo, FONTE_TITULO));

        // <editor-fold defaultstate="collapsed" desc="Quilometragem"> 
        PdfPCell cellQuilometragem = new PdfPCell(new Paragraph("Quilometragem do veículo: " + vistoria.getKmVeiculo()));
        cellQuilometragem.setColspan(2);
        tabela.addCell(cellQuilometragem);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Checklist"> 
        adicionarCabecalho(tabela, new Paragraph("Checklist", FONTE_SUBTITULO));
        for (ItemVistoria item : vistoria.getItens()) {
            PdfPCell cellItem = new PdfPCell(new Paragraph(item.toString(), FONTE_TEXTO));
            cellItem.setColspan(2);
            tabela.addCell(cellItem);
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Imagens"> 
        adicionarCabecalho(tabela, new Paragraph("Imagens", FONTE_SUBTITULO));
        for (File imagemFile : vistoria.getImagens()) {
            com.itextpdf.text.Image imagem = Image.getInstance(imagemFile.getAbsolutePath());
            tabela.addCell(imagem);
        }
        // </editor-fold>

        return tabela;
    }

    public static Paragraph getParagrafoPessoaFisica(Locacao locacao) {
        PessoaFisica pf = (PessoaFisica) locacao.getCliente().getPessoa();

        String conteudoArquivo
                = "                                                           CONTRATO DE LOCAÇÃO DE VEÍCULO\n"
                + " \n"
                + "      \n"
                + "      \n"
                + "IDENTIFICAÇÃO DAS PARTES CONTRATANTES\n"
                + "      \n"
                + "      \n"
                + "LOCADOR: LOCA CAR AUTOMOBILISTC "
                + "Inscrição Estadual: 873.727.877.126   "
                + "C.N.P.J nº 26.897.399/0001-49, "
                + "residente e domiciliado no endereço Rua Capitão Salomão, nº 459, bairro Jardim Buriti Sereno, Cep nº 74944-320, Cidade Aparecida de Goiânia, no Estado de Goiás.\n"
                + "             \n"
                + "LOCATÁRIO: " + locacao.getCliente().getPessoa().getNome() + ", brasileiro, Solteiro(a), Carteira de Identidade nº " + pf.getRegistroGeral() + ", C.P.F. n " + pf.getCpf() + ", residente e domiciliado no endereço " + pf.getEndereco().getLogradouro() + ", nº " + pf.getEndereco().getNumero() + ", bairro " + pf.getEndereco().getBairro() + ", Cep nº" + pf.getEndereco().getCep() + ", Cidade " + pf.getEndereco().getCidade() + ", no Estado de Goiás.\n"
                + "             \n"
                + "      \n"
                + "As partes acima identificadas têm, entre si, justo e acertado o presente Contrato de Locação de Automóvel, que se regerá pelas cláusulas seguintes e pelas condições de preço, forma e termo de pagamento descritas no presente.      \n"
                + "                       \n"
                + "1. CLÁUSULA PRIMEIRA – DO OBJETO, PRAZO E USO\n"
                + " \n"
                + "             \n"
                + "             \n"
                + "1.1. O LOCADOR declara ser o legítimo possuidor e/ou proprietário do veículo de modelo " + locacao.getVeiculo().getModelo() + ", marca " + locacao.getVeiculo().getModelo().getMarca() + ", ano " + locacao.getVeiculo().getModelo().getAno() + ", placa " + locacao.getVeiculo().getPlaca() + ", código fipe " + locacao.getVeiculo().getModelo().getCodigoFipe() + ", em perfeito estado e que resolveu dá-lo em locação ao LOCATÁRIO, do dia " + DateUtilities.formatDataDia(locacao.getDataEntrega()) + " ao dia " + DateUtilities.formatDataDia(locacao.getDataDevolucao()) + ", contados a partir da assinatura do presente contrato.\n"
                + "                      \n"
                + "1.1.1. Findo prazo acima estipulado, o mesmo poderá ser renovado através de aditivo ou outro instrumento contratual ou o veículo deverá ser devolvido ao LOCADOR nas mesmas condições em que estava quando o recebeu, ou seja, em perfeitas condições de uso, respondendo pelos danos ou prejuízos causados.             \n"
                + "                      \n"
                + "1.1.3. Caso o LOCATÁRIO não restituir o automóvel na data estipulada, deverá pagar, enquanto detiver em seu poder, o valor da locação que o LOCADOR arbitrar, e responderá pelo dano que o automóvel venha a sofrer mesmo se proveniente de caso fortuito.\n"
                + "                      \n"
                + "1.1.3. O bem locado somente será destinado a uso exclusivo no Sistema de Transporte Intermunicipal de Passageiros do Estado de Alagoas.\n"
                + "                      \n"
                + "1.1.4. O bem locado apenas poderá ser dirigido pelo LOCATÁRIO ou por seu motorista substituto cadastrado na ARSAL.\n"
                + "                      \n"
                + "2. CLAÚSULA SEGUNDA – DO VALOR\n"
                + " \n"
                + "                \n"
                + "2.1. O LOCATÁRIO pagará ao LOCADOR a título de locação o valor mensal de R$: REFATORAR ESSA PARTE DO CÓDIGO !!!!\n" // esta parte ainda está sendo trabalhada
                + "        \n"
                + "2.1.1. O atraso no pagamento do acordo da cláusula acima enseja multa previamente informadas.\n"
                + "                      \n"
                + "3. CLAÚSULA TERCEIRA – DAS OBRIGAÇÕES\n"
                + " \n"
                + "      \n"
                + "             \n"
                + "3.1. O LOCATÁRIO deverá manter o veículo em perfeito estado de conservação, de ordem mecânica, tapeçaria, funilaria e pneus, devendo entregar, com o término do contrato, o veículo e sua documentação ao LOCADOR nas mesmas condições em que recebeu.\n"
                + "        \n"
                + "3.2. É de inteira responsabilidade do LOCATÁRIO os débitos de qualquer natureza, com data posterior a assinatura do presente contrato e até a data da rescisão, sejam de multas de trânsito, taxa de licenciamento, seguro obrigatório, seguro do veículo, IPVA e outros relacionados ao veículo.\n"
                + "                      \n"
                + "4. CLÁUSULA QUARTA - DA AUTORIZAÇÃO\n"
                + " \n"
                + "      \n"
                + "             \n"
                + "4.1. O LOCADOR, desde já autoriza o LOCATÁRIO, à implantação da identificação visual e do sistema de monitoramento instituído pela NOME DA NOSSA EMPRESA.\n"
                + "        \n"
                + "          \n"
                + "5. CLÁUSULA QUINTA – DA RESCISÃO \n"
                + " \n"
                + "      \n"
                + "             \n"
                + "5.1. A rescisão, antes do vencimento contratual, por iniciativa de qualquer das partes deverá ser precedida de notificação expressa com antecedência mínima de 60 (sessenta) dias a outra parte.\n"
                + "             \n"
                + "5.2. O LOCATÁRIO, permissionário/autorizado, fica obrigado a comunicar à NOME DA NOSSA EMPRESA sobre a intenção de rescisão do presente contrato manifestada por qualquer das partes, com antecedência mínima de 15 (quinze) dias.\n"
                + "             \n"
                + "5.3. O descumprimento de qualquer das cláusulas por parte dos contratantes ensejará a rescisão deste instrumento e o devido pagamento de multa, pela parte inadimplente no valor de 3% (três por cento) do valor contratual.\n"
                + "                      \n"
                + "6. CLÁUSULA SEXTA – DAS DISPOSIÇÕES GERAIS\n"
                + "   \n"
                + "      \n"
                + "      \n"
                + "6.1. O LOCADOR e LOCATÁRIO atestam que o veículo está sendo entregue em perfeitas condições de uso, na data da assinatura do presente instrumento, mediante vistoria.\n"
                + "        \n"
                + "6.2. As partes contratantes elegem o foro de Maceió-AL, para dirimir qualquer ação oriunda deste contrato.\n"
                + "        \n"
                + "E por estarem justo e contratados, assinam o presente instrumento em 02 (duas) vias de igual teor e forma, na presença de 02 (duas) testemunhas abaixo assinadas.\n"
                + "        \n"
                + "        \n"
                + "        \n"
                + "Goiânia, ____ de _______________ de 2020.\n"
                + "        \n"
                + "        \n"
                + "     __________________________             _____________________________\n"
                + "                      LOCADOR                                                LOCATÁRIO\n"
                + "        \n"
                + "TESTEMUNHAS:\n"
                + "        \n"
                + "Nome: ______________________________	   Nome:_______________________________\n"
                + "CPF: ______________________________	   CPF:_______________________________\n";

        return new Paragraph(conteudoArquivo);
    }

    public static Paragraph getParagrafoPessoaJuridica(Locacao locacao) {

        PessoaJuridica pj = (PessoaJuridica) locacao.getCliente().getPessoa();

        String conteudoArquivo
                = "                                                           CONTRATO DE LOCAÇÃO DE VEÍCULO\n"
                + " \n"
                + "      \n"
                + "      \n"
                + "IDENTIFICAÇÃO DAS PARTES CONTRATANTES\n"
                + "      \n"
                + "      \n"
                + "LOCADOR: LOCA CAR AUTOMOBILISTC "
                + "Inscrição Estadual: 873.727.877.126   "
                + "C.N.P.J nº 26.897.399/0001-49, "
                + "residente e domiciliado no endereço Rua Capitão Salomão, nº 459, bairro Jardim Buriti Sereno, Cep nº 74944-320, Cidade Aparecida de Goiânia, no Estado de Goiás.\n"
                + "             \n"
                + "LOCATÁRIO: " + locacao.getCliente().getPessoa().getNome() + " Inscrição Estadual nº " + pj.getInscricaoEstadual() + ", C.N.P.J n " + pj.getCnpj() + ", residente e domiciliado no endereço " + pj.getEndereco().getLogradouro() + ", nº " + pj.getEndereco().getNumero() + ", bairro " + pj.getEndereco().getBairro() + ", Cep nº" + pj.getEndereco().getCep() + ", Cidade " + pj.getEndereco().getCidade() + ", no Estado de Goiás.\n"
                + "             \n"
                + "      \n"
                + "As partes acima identificadas têm, entre si, justo e acertado o presente Contrato de Locação de Automóvel, que se regerá pelas cláusulas seguintes e pelas condições de preço, forma e termo de pagamento descritas no presente.      \n"
                + "                       \n"
                + "1. CLÁUSULA PRIMEIRA – DO OBJETO, PRAZO E USO\n"
                + " \n"
                + "             \n"
                + "             \n"
                + "1.1. O LOCADOR declara ser o legítimo possuidor e/ou proprietário do veículo de modelo " + locacao.getVeiculo().getModelo() + ", marca " + locacao.getVeiculo().getModelo().getMarca() + ", ano " + locacao.getVeiculo().getModelo().getAno() + ", placa " + locacao.getVeiculo().getPlaca() + ", código fipe " + locacao.getVeiculo().getModelo().getCodigoFipe() + ", em perfeito estado e que resolveu dá-lo em locação ao LOCATÁRIO, do dia " + DateUtilities.formatDataDia(locacao.getDataEntrega()) + " ao dia " + DateUtilities.formatDataDia(locacao.getDataDevolucao()) + ", contados a partir da assinatura do presente contrato.\n"
                + "                      \n"
                + "1.1.1. Findo prazo acima estipulado, o mesmo poderá ser renovado através de aditivo ou outro instrumento contratual ou o veículo deverá ser devolvido ao LOCADOR nas mesmas condições em que estava quando o recebeu, ou seja, em perfeitas condições de uso, respondendo pelos danos ou prejuízos causados.             \n"
                + "                      \n"
                + "1.1.3. Caso o LOCATÁRIO não restituir o automóvel na data estipulada, deverá pagar, enquanto detiver em seu poder, o valor da locação que o LOCADOR arbitrar, e responderá pelo dano que o automóvel venha a sofrer mesmo se proveniente de caso fortuito.\n"
                + "                      \n"
                + "1.1.3. O bem locado somente será destinado a uso exclusivo no Sistema de Transporte Intermunicipal de Passageiros do Estado de Alagoas.\n"
                + "                      \n"
                + "1.1.4. O bem locado apenas poderá ser dirigido pelo LOCATÁRIO ou por seu motorista substituto cadastrado na ARSAL.\n"
                + "                      \n"
                + "2. CLAÚSULA SEGUNDA – DO VALOR\n"
                + " \n"
                + "                \n"
                + "2.1. O LOCATÁRIO pagará ao LOCADOR a título de locação o valor mensal de R$: REFATORAR ESSA PARTE DO CÓDIGO !!!!\n"
                + "        \n"
                + "2.1.1. O atraso no pagamento do acordo da cláusula acima enseja multa de 5 % (cinco por cento) e juros de 1% (um por cento) ao dia.\n"
                + "                      \n"
                + "3. CLAÚSULA TERCEIRA – DAS OBRIGAÇÕES\n"
                + " \n"
                + "      \n"
                + "             \n"
                + "3.1. O LOCATÁRIO deverá manter o veículo em perfeito estado de conservação, de ordem mecânica, tapeçaria, funilaria e pneus, devendo entregar, com o término do contrato, o veículo e sua documentação ao LOCADOR nas mesmas condições em que recebeu.\n"
                + "        \n"
                + "3.2. É de inteira responsabilidade do LOCATÁRIO os débitos de qualquer natureza, com data posterior a assinatura do presente contrato e até a data da rescisão, sejam de multas de trânsito, taxa de licenciamento, seguro obrigatório, seguro do veículo, IPVA e outros relacionados ao veículo.\n"
                + "                      \n"
                + "4. CLÁUSULA QUARTA - DA AUTORIZAÇÃO\n"
                + " \n"
                + "      \n"
                + "             \n"
                + "4.1. O LOCADOR, desde já autoriza o LOCATÁRIO, à implantação da identificação visual e do sistema de monitoramento instituído pela NOME DA NOSSA EMPRESA.\n"
                + "        \n"
                + "          \n"
                + "5. CLÁUSULA QUINTA – DA RESCISÃO \n"
                + " \n"
                + "      \n"
                + "             \n"
                + "5.1. A rescisão, antes do vencimento contratual, por iniciativa de qualquer das partes deverá ser precedida de notificação expressa com antecedência mínima de 60 (sessenta) dias a outra parte.\n"
                + "             \n"
                + "5.2. O LOCATÁRIO, permissionário/autorizado, fica obrigado a comunicar à NOME DA NOSSA EMPRESA sobre a intenção de rescisão do presente contrato manifestada por qualquer das partes, com antecedência mínima de 15 (quinze) dias.\n"
                + "             \n"
                + "5.3. O descumprimento de qualquer das cláusulas por parte dos contratantes ensejará a rescisão deste instrumento e o devido pagamento de multa, pela parte inadimplente no valor de 3% (três por cento) do valor contratual.\n"
                + "                      \n"
                + "6. CLÁUSULA SEXTA – DAS DISPOSIÇÕES GERAIS\n"
                + "   \n"
                + "      \n"
                + "      \n"
                + "6.1. O LOCADOR e LOCATÁRIO atestam que o veículo está sendo entregue em perfeitas condições de uso, na data da assinatura do presente instrumento, mediante vistoria.\n"
                + "        \n"
                + "6.2. As partes contratantes elegem o foro de Maceió-AL, para dirimir qualquer ação oriunda deste contrato.\n"
                + "        \n"
                + "E por estarem justo e contratados, assinam o presente instrumento em 02 (duas) vias de igual teor e forma, na presença de 02 (duas) testemunhas abaixo assinadas.\n"
                + "        \n"
                + "        \n"
                + "        \n"
                + "Goiânia, ____ de _______________ de 2020.\n"
                + "        \n"
                + "        \n"
                + "     __________________________             _____________________________\n"
                + "                      LOCADOR                                                LOCATÁRIO\n"
                + "        \n"
                + "TESTEMUNHAS:\n"
                + "        \n"
                + "Nome: ______________________________	   Nome:_______________________________\n"
                + "CPF: ______________________________	   CPF:_______________________________\n";

        return new Paragraph(conteudoArquivo);
    }

    private static void adicionarCabecalho(PdfPTable tabela, Paragraph paragrafoTitulo) {
        PdfPCell cellCabecalho = new PdfPCell(paragrafoTitulo);
        cellCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setVerticalAlignment(Element.ALIGN_CENTER);
        cellCabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellCabecalho.setBorder(PdfPTable.ALIGN_MIDDLE);
        cellCabecalho.setColspan(tabela.getNumberOfColumns());
        tabela.addCell(cellCabecalho);
    }

    private static void adicionarCelulaLinha(PdfPTable tabela, Paragraph paragraphLinha) {
        PdfPCell cell = new PdfPCell(paragraphLinha);
        cell.setColspan(2);
        tabela.addCell(cell);
    }

}
