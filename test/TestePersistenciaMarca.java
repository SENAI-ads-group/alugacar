
import entidades.Marca;
import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;
import entidades.services.persistence.MarcaPersistenceService;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaMarca {

    public static void main(String[] args) {

        MarcaPersistenceService persistenceService = new MarcaPersistenceServiceCSV();

        System.out.println("INSERIR");
        Marca marca = new Marca(1, "marca1");
        persistenceService.inserir(marca);

        System.out.println("BUSCAR");
        Marca marcaBuscada = persistenceService.buscar(1);
        System.out.println(marcaBuscada.toCSV());

        System.out.println("ALTERAR");
        marcaBuscada.setDescricao("descrição atualizada");
        persistenceService.atualizar(marcaBuscada);

        System.out.println("LISTAR");
        for (Marca m : persistenceService.buscarTodos()) {
            System.out.println(m.toCSV());
        }
    }
}
