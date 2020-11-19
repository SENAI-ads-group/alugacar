
import entidades.Cliente;
import entidades.Endereco;
import entidades.PessoaJuridica;
import entidades.enums.UF;

/**
 *
 * @author Patrick-Ribeiro
 */
public class TesteInstanciacaoCliente {

    public static void main(String[] args) {
        Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
        PessoaJuridica pessoa = new PessoaJuridica("Empresa", "(62)9.9999-9999", "email", endereco, "cnpj",
                "razaoSocial", "nomeFantasia", 123456789);
        Cliente cliente = new Cliente(1, pessoa, true);
        
        System.out.println(cliente.toCSV());
    }
}
