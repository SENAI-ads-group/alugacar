
package persistencia;

import entidades.Categoria;
import entidades.Marca;
import entidades.Modelo;
import entidades.Veiculo;
import entidades.services.persistence.CategoriaPersistenceService;
import entidades.services.persistence.VeiculoPersistenceService;
import entidades.services.persistence.csv.CategoriaPersistenceServiceCSV;
import entidades.services.persistence.csv.ModeloPersistenceServiceCSV;
import entidades.services.persistence.csv.VeiculoPersistenceServiceCSV;
import entidades.services.persistence.exceptions.DBConnectionException;
import entidades.services.persistence.exceptions.PersistenceException;
import java.util.Date;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaVeiculo {
    
    public static void main(String[] args) {

        VeiculoPersistenceService persistenceService = new VeiculoPersistenceServiceCSV();

        try {
            System.out.println("INSERIR");
            
            Categoria cat = new CategoriaPersistenceServiceCSV().buscar(10);
            Marca marc = new Marca(4332, "gol");
            Modelo mod = new ModeloPersistenceServiceCSV().buscar(1);
            Veiculo veiculo = new Veiculo("KFCS-765", "renavam", cat, mod, new Date(), new Date(), 32.43);
            
            persistenceService.inserir(veiculo);

            System.out.println("BUSCAR");
            System.err.println("veiculo buscado");
            Veiculo veiculoBuscado = persistenceService.buscar("KFCS-765");
            if (veiculoBuscado != null) {
                System.out.println(veiculoBuscado.toCSV());

                System.out.println("ALTERAR");
                veiculoBuscado.setPrecoCompra(23.500);
                persistenceService.atualizar(veiculoBuscado);
            }

            System.out.println("LISTAR");
            for (Veiculo v : persistenceService.buscarTodos()) {
                System.out.println(v.toCSV());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
    
}
