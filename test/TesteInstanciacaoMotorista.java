
import entidades.CNH;
import entidades.Endereco;
import entidades.Motorista;
import entidades.PessoaFisica;
import entidades.enums.CategoriaCNH;
import entidades.enums.UF;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Patrick-Ribeiro
 */
public class TesteInstanciacaoMotorista {

    public static void main(String[] args) throws IOException {

        Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
        CNH cnh = new CNH(1, CategoriaCNH.AB, new Date());
        PessoaFisica pessoa = new PessoaFisica("Carlos", "(62)9.92245404", "carlos@carlos", endereco);
        Motorista motorista = new Motorista(1, pessoa, cnh, true);
        motorista.setFoto(new File("/home/usuario/Imagens/SENAI-logo.png"));

        System.out.println(motorista.toCSV());
    }
}
