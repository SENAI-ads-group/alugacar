package instanciacao;

import model.entidades.Endereco;
import model.entidades.PessoaFisica;
import model.entidades.Usuario;
import model.entidades.enums.CategoriaUsuario;
import model.entidades.enums.UF;

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

        Usuario usuario1 = new Usuario(usuario.toCSV().split(";"));
        System.out.println(usuario1.toCSV());
    }
}
