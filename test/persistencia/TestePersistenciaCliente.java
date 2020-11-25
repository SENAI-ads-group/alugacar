
package persistencia;

import entidades.Cliente;
import entidades.Endereco;
import entidades.PessoaFisica;
import entidades.enums.UF;
import entidades.services.persistence.ClientePersistenceService;
import entidades.services.persistence.csv.ClientePersistenceServiceCSV;
import entidades.services.persistence.exceptions.DBConnectionException;
import entidades.services.persistence.exceptions.PersistenceException;
import java.util.Date;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaCliente {
    
    public static void main(String[] args) {
        
              ClientePersistenceService persistenceService = new ClientePersistenceServiceCSV();

         try {
            System.out.println("INSERIR");
            
             Endereco end = new Endereco("logradouro", 0001, "qd13", "colina azul", "goiania", UF.GO, "763771");
             PessoaFisica pf = new PessoaFisica("alex", "3233-4345", "alexsander@gmail.com", end, "454.213.232-42", 042423, new Date());
            Cliente cliente = new Cliente(Integer.SIZE, pf, true);
            persistenceService.inserir(cliente);

            System.out.println("BUSCAR");
            Cliente clienteBuscado = persistenceService.buscar(1);
            if (clienteBuscado != null) {
                System.out.println(clienteBuscado.toCSV());

                System.out.println("ALTERAR");
                clienteBuscado.setAtivo(false);
                persistenceService.atualizar(clienteBuscado);
            }

            System.out.println("LISTAR");
            for (Cliente c : persistenceService.buscarTodos()) {
                System.out.println(c.toCSV());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBConnectionException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }

    }
    
}
