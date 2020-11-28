package persistencia;

import model.entidades.Categoria;
import model.servicos.persistencia.DAOFactory;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.CategoriaDAO;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaCategoria {

    public static void main(String[] args) {
        CategoriaDAO persistenceService = DAOFactory.createCategoriaService();
        try {
            System.out.println("INSERIR");
            Categoria categoria = new Categoria(1, "descricao", 1500.0, 100.0);
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
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }

}
