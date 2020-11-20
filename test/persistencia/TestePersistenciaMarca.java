package persistencia;

import entidades.Marca;
import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;
import entidades.services.persistence.MarcaPersistenceService;
import entidades.services.persistence.exceptions.DBConnectionException;
import entidades.services.persistence.exceptions.PersistenceException;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaMarca {

    public static void main(String[] args) {

        MarcaPersistenceService persistenceService = new MarcaPersistenceServiceCSV();

        try {
            System.out.println("INSERIR");
            Marca marca = new Marca(5, "marca1");
            persistenceService.inserir(marca);

            System.out.println("BUSCAR");
            Marca marcaBuscada = persistenceService.buscar(1);
            if (marcaBuscada != null) {
                System.out.println(marcaBuscada.toCSV());

                System.out.println("ALTERAR");
                marcaBuscada.setDescricao("marca-3");
                persistenceService.atualizar(marcaBuscada);
            }

            System.out.println("LISTAR");
            for (Marca m : persistenceService.buscarTodos()) {
                System.out.println(m.toCSV());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
}
