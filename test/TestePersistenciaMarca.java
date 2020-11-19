
import entidades.Marca;
import entidades.dao.MarcaDAO;
import entidades.dao.interfaces.IMarcaDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaMarca {

    public static void main(String[] args) {

        //Marca marca = new Marca(1, "marca1");
        IMarcaDAO marcaDAO = new MarcaDAO();

        Marca marcaPesquisa = marcaDAO.buscar(3);
        System.out.println(marcaPesquisa.toCSV());
        
        marcaPesquisa.setDescricao("Nova descrição");
        marcaDAO.atualizar(marcaPesquisa);
        
        System.out.println(marcaDAO.buscar(3).toCSV());
    }
}
