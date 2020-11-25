package instanciacao;

import entidades.Cliente;
import entidades.Endereco;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import entidades.enums.TipoCliente;
import entidades.enums.UF;
import java.util.Date;

/**
 *
 * @author Patrick-Ribeiro
 */
public class TesteInstanciacaoCliente {

    public static void main(String[] args) {
        Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
        // PessoaJuridica pow = new PessoaJuridica(nome, telefone, email, endereco, cnpj, razaoSocial, nomeFantasia, Integer.MIN_VALUE);
        PessoaJuridica pessoa = new PessoaJuridica("Empresa", "(62)9.9999-9999", "email", endereco, "cnpj", "socialRazao", 123456789);
        //PessoaJurifica jfj = new PessoaJuridica

        Endereco enderecopf = new Endereco("logradouro", 132, "complemento", "bairro", "cidade", UF.GO, "2323233223");
        PessoaFisica pf = new PessoaFisica("alex", "23233444", "alex@gmail.com", enderecopf, "266.444.444-23", 323344, new Date());
        //PessoaFisica dd = new PessoaFisica(nome, telefone, email, endereco, cpf, Integer.SIZE, dataNascimento);

        Cliente cliente = new Cliente(TipoCliente.PESSOA_JURIDICA);
        cliente.setPessoa(pessoa);
        cliente.setAtivo(true);
        //System.out.println(cliente.toCSV());

        Cliente cliente1 = new Cliente(TipoCliente.PESSOA_FISICA);
        cliente1.setPessoa(pf);

        //Cliente cliente2 = new Cliente(cliente.toCSV().split(";"));
        System.out.println(cliente.toCSV());
        System.out.println(cliente1.toCSV());

    }
}
