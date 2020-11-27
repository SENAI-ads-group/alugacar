package instanciacao;

import model.entidades.CNH;
import model.entidades.enums.CategoriaCNH;
import java.util.Date;

/**
 *
 * @author patrick-ribeiro
 */
public class TesteInstanciacaoCNH {

    public static void main(String[] args) {
        CNH cnh = new CNH(123456789, CategoriaCNH.A, new Date());
        System.out.println(cnh.toCSV());

        CNH cnh2 = new CNH(cnh.toCSV().split(";"));
        System.out.println(cnh2.toCSV());
    }

}
