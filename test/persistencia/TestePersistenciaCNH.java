package persistencia;

import model.entidades.CNH;
import model.entidades.enums.CategoriaCNH;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import java.util.Date;
import model.servicos.persistencia.CnhDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaCNH {

    public static void main(String[] args) {

        CnhDAO persistenceService = DAOFactory.createCNHService();
        try {
            System.out.println("INSERIR");
            CNH cnh = new CNH(1234567, CategoriaCNH.A, new Date());
            persistenceService.inserir(cnh);

            System.out.println("BUSCAR");
            CNH cnhBuscada = persistenceService.buscar(1234567);
            if (cnhBuscada != null) {
                System.out.println(cnhBuscada.toCSV());

                System.out.println("ALTERAR");
                cnhBuscada.setCategoria(CategoriaCNH.E);
                persistenceService.atualizar(cnhBuscada);
            }

            System.out.println("LISTAR");

            for (CNH cn : persistenceService.buscarTodos()) {
                System.out.println(cn.toCSV());
            }
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
}
