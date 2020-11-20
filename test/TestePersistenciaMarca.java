
import entidades.Marca;
import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;
import entidades.services.persistence.MarcaPersistenceService;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaMarca {

    public static void main(String[] args) {

        Marca marca = new Marca(1, "marca teste");
        MarcaPersistenceService marcaDAO = new MarcaPersistenceServiceCSV();
        marcaDAO.inserir(marca);

        /*Marca marcaPesquisa = marcaDAO.buscar(3);
        System.out.println(marcaPesquisa.toCSV());
        
        marcaPesquisa.setDescricao("Nova descrição");
        marcaDAO.atualizar(marcaPesquisa);
        
        System.out.println(marcaDAO.buscar(3).toCSV());*/
    }
}
