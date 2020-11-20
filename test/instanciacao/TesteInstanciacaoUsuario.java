package instanciacao;


import entidades.Endereco;
import entidades.PessoaFisica;
import entidades.Usuario;
import entidades.enums.CategoriaUsuario;
import entidades.enums.UF;

/**
 *
 * @author Patrick-Ribeiro
 */
public class TesteInstanciacaoUsuario {

    public static void main(String[] args) {

        Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
        PessoaFisica pessoa = new PessoaFisica("Carlos", "(62)9.92245404", "carlos@carlos", endereco);
        Usuario usuario = new Usuario(1, pessoa, "123456", CategoriaUsuario.ADMINISTRADOR, true);

        System.out.println(usuario.toCSV());
    }
}
