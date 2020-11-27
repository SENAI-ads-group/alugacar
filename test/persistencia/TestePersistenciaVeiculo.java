package persistencia;

import java.util.Date;
import model.entidades.Categoria;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Veiculo;
import model.services.persistence.VeiculoPersistenceService;
import model.services.persistence.csv.CategoriaPersistenceServiceCSV;
import model.services.persistence.csv.ModeloPersistenceServiceCSV;
import model.services.persistence.csv.VeiculoPersistenceServiceCSV;
import model.services.persistence.exceptions.DBConnectionException;
import model.services.persistence.exceptions.PersistenceException;

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
