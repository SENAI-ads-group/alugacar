package persistencia;

import entidades.Marca;
import entidades.Modelo;
import entidades.services.persistence.MarcaPersistenceService;
import entidades.services.persistence.ModeloPersistenceService;
import entidades.services.persistence.PersistenceFactory;
import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;
import entidades.services.persistence.csv.ModeloPersistenceServiceCSV;
import entidades.services.persistence.exceptions.DBConnectionException;
import entidades.services.persistence.exceptions.PersistenceException;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaModelo {

    public static void main(String[] args) {
        MarcaPersistenceService marcaPersistenceService = new MarcaPersistenceServiceCSV();
        ModeloPersistenceService modeloPersistenceService = PersistenceFactory.createModeloService();

        try {
            System.out.println("INSERIR");
            Marca marcaBuscada = marcaPersistenceService.buscar(5);
            System.out.println(marcaBuscada.toCSV());
            Modelo modelo = new Modelo(1, marcaBuscada, "descr");
            modeloPersistenceService.inserir(modelo);
            System.out.println(modelo.toCSV());

            System.out.println("BUSCAR");

            System.out.println("LISTAR");
            new ModeloPersistenceServiceCSV().getUltimoRegistro();
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }

    }
}
