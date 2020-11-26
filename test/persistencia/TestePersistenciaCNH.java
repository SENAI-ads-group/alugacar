package persistencia;

import model.entidades.CNH;
import model.entidades.enums.CategoriaCNH;
import model.entidades.services.persistence.CNHPersistenseService;
import model.entidades.services.persistence.PersistenceFactory;
import model.entidades.services.persistence.exceptions.DBConnectionException;
import model.entidades.services.persistence.exceptions.PersistenceException;
import java.util.Date;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaCNH {

    public static void main(String[] args) {

        CNHPersistenseService persistenceService = PersistenceFactory.createCNHService();
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
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
}
