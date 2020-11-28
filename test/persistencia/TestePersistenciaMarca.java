package persistencia;

import model.entidades.Marca;
import model.servicos.persistencia.implementacaoCSV.MarcaCSV;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.MarcaDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaMarca {

    public static void main(String[] args) {

        MarcaDAO persistenceService = new MarcaCSV();

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
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
}
