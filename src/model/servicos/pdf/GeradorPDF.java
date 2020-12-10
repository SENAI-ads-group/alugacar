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
import model.entidades.enums.TipoLocacao;
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
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(localDestino + "/contrato.pdf"));
            documentoPDF.open();

            documentoPDF.setPageSize(PageSize.A4);

            documentoPDF.add(getTabelaCliente(locacao.getCliente()));
            documentoPDF.add(getTabelaMotorista(locacao.getMotorista()));
            documentoPDF.add(getTabelaVeiculo(locacao.getVeiculo()));
            documentoPDF.add(getTabelaLocacao(locacao));
            documentoPDF.add(getTabelaVistoria(locacao.getVistoriaEntrega()));
            documentoPDF.newPage();
            if (locacao.getCliente().getPessoa() instanceof PessoaFisica) {
                //documentoPDF.add(getCriarContratoPF(locacao,locacao.getCliente().getPessoa()pessoa);  
                documentoPDF.add(getCriarContratoPF(locacao));
            } else {
                documentoPDF.add(getCriarContratoPJ(locacao));
            }


            /*Verificar o status da locação antes de inserir as vistorias no PDF
            Se o status for PENDENTE deve gerar apenas os dados do veículo, cliente, motorista,
            cliente, data de registro, data de entrega (representa a previsão de entrega),
            data de devolução (representa a previsão de devolução)
            
            Se for INICIADA não deve ser gerado além das informações principais, as taxas, descontos e vistoria de entrada
            
            Se for FINALIZADA dedve ser gerado todas as informações
            
            Consulte a entidade de Vistoria, Locacao e o serviço de contrato
             */
            Desktop.getDesktop().open(new File(localDestino + "\\contrato.pdf"));
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
        tabela.addCell("Data da retirada : " + locacao.getDataEntrega());
        tabela.addCell("Data da devolução : " + locacao.getDataDevolucao());
        tabela.addCell("Modalidade : " + locacao.getTipo());
        if (locacao.getTipo() == TipoLocacao.DIARIA) {
            tabela.addCell("Valor da diária : ");
        } else if (locacao.getTipo() == locacao.getTipo().KM) {
            tabela.addCell("Preço do KM rodado : ");
        }
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

        tabela.addCell(getCellEspacamento());
        tabela.addCell(cellCabecalho);
        tabela.addCell(getCellEspacamento());

        tabela.addCell("Tipo de Taxa");
        tabela.addCell("Valor");

        tabela.addCell(getCellEspacamento());
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

    public static Paragraph getCriarContratoPF(Locacao locacao) {

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
                + "1.1. O LOCADOR declara ser o legítimo possuidor e/ou proprietário do veículo de modelo " + locacao.getVeiculo().getModelo() + ", marca " + locacao.getVeiculo().getModelo().getMarca() + ", ano " + locacao.getVeiculo().getModelo().getAno() + ", placa " + locacao.getVeiculo().getPlaca() + ", código fipe " + locacao.getVeiculo().getModelo().getCodigoFipe() + ", em perfeito estado e que resolveu dá-lo em locação ao LOCATÁRIO, do dia " + locacao.getDataEntrega() + " ao dia " + locacao.getDataDevolucao() + ", contados a partir da assinatura do presente contrato.\n"
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

    public static Paragraph getCriarContratoPJ(Locacao locacao) {

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
                + "1.1. O LOCADOR declara ser o legítimo possuidor e/ou proprietário do veículo de modelo " + locacao.getVeiculo().getModelo() + ", marca " + locacao.getVeiculo().getModelo().getMarca() + ", ano " + locacao.getVeiculo().getModelo().getAno() + ", placa " + locacao.getVeiculo().getPlaca() + ", código fipe " + locacao.getVeiculo().getModelo().getCodigoFipe() + ", em perfeito estado e que resolveu dá-lo em locação ao LOCATÁRIO, do dia " + locacao.getDataEntrega() + " ao dia " + locacao.getDataDevolucao() + ", contados a partir da assinatura do presente contrato.\n"
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

}
