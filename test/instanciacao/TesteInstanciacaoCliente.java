package instanciacao;

import java.util.Date;
import model.entidades.Cliente;
import model.entidades.Endereco;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.entidades.enums.TipoCliente;
import model.entidades.enums.UF;

/**
 *
 * @author Patrick-Ribeiro
 */
public class TesteInstanciacaoCliente {

    public static void main(String[] args) {
        System.out.println("INTANCIAÇÃO MANUAL COMO PESSOA JURÍDICA");
        Endereco endereco1 = new Endereco("logradouro", 123, "complemento", "bairro", "cidade", UF.CE, "cep");
        PessoaJuridica pessoaJuridica = new PessoaJuridica("nome (fantasia)", "telefone", "email", endereco1, "cnpj", "razao social", "inscrição estadual");
        Cliente cliente1 = new Cliente(TipoCliente.PESSOA_JURIDICA);
        cliente1.setPessoa(pessoaJuridica);
        cliente1.setAtivo(true);
        System.out.println(cliente1.toCSV());

        System.out.println();

        System.out.println("INSTANCIAÇÃO A PARTIR DE UM CSV COMO PESSOOA JURÍDICA");
        String[] csv1 = cliente1.toCSV().split(";");
        Cliente cliente3 = new Cliente(csv1);
        System.out.println(cliente3.toCSV());

        System.out.println();

        System.out.println("INTANCIAÇÃO MANUAL COMO PESSOA FÍSICA");
        Endereco endereco2 = new Endereco("logradouro", 123, "complemento", "bairro", "cidade", UF.CE, "cep");
        PessoaFisica pessoaFisica = new PessoaFisica("nome", "telefone", "email", endereco2, "cpf", "registroGeral", new Date());
        Cliente cliente2 = new Cliente(TipoCliente.PESSOA_FISICA);
        cliente2.setPessoa(pessoaFisica);
        cliente2.setAtivo(true);
        System.out.println(cliente2.toCSV());

        System.out.println();

        System.out.println("INSTANCIAÇÃO A PARTIR DE UM CSV COMO PESSOOA FÍSICA");
        String[] csv2 = cliente2.toCSV().split(";");
        Cliente cliente4 = new Cliente(csv2);
        System.out.println(cliente4.toCSV());

    }
}
