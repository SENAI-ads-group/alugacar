package persistencia;

import entidades.Categoria;
import entidades.services.persistence.CategoriaPersistenceService;
import entidades.services.persistence.PersistenceFactory;
import entidades.services.persistence.exceptions.DBConnectionException;
import entidades.services.persistence.exceptions.PersistenceException;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaCategoria {

    public static void main(String[] args) {
        CategoriaPersistenceService persistenceService = PersistenceFactory.createCategoriaService();
        try {
            System.out.println("INSERIR");
            Categoria categoria = new Categoria("teste", 56.87);
            persistenceService.inserir(categoria);

            System.out.println("BUSCAR");
            Categoria categoriaBuscada = persistenceService.buscar(2);
            if (categoriaBuscada != null) {
                System.out.println(categoriaBuscada.toCSV());

                System.out.println("ALTERAR");
                categoriaBuscada.setDescricao("FAMÍLIA");
                persistenceService.atualizar(categoriaBuscada);
            }

            System.out.println("LISTAR");

            for (Categoria ctg : persistenceService.buscarTodos()) {
                System.out.println(ctg.toCSV());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }

}
