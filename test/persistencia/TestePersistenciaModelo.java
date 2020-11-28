package persistencia;

import model.entidades.Categoria;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.enums.Combustivel;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.implementacaoCSV.MarcaCSV;
import model.servicos.persistencia.implementacaoCSV.ModeloCSV;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.ModeloDAO;
import model.servicos.persistencia.MarcaDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class TestePersistenciaModelo {

    public static void main(String[] args) {
        MarcaDAO marcaPersistenceService = new MarcaCSV();
        ModeloDAO modeloPersistenceService = DAOFactory.createModeloService();

        try {
            System.out.println("INSERIR");
            Marca marcaBuscada = marcaPersistenceService.buscar(5);
            System.out.println(marcaBuscada.toCSV());
            Categoria categoria = new Categoria(1, "descricao", 1500.0, 100.0);
            Modelo modelo = new Modelo(1, "codigoFipe", "descricao", marcaBuscada, categoria, Combustivel.DIESEL, 2020);
            modeloPersistenceService.inserir(modelo);
            System.out.println(modelo.toCSV());

            System.out.println("BUSCAR");

            System.out.println("LISTAR");

            for (Modelo m : new ModeloCSV().buscarTodos()) {
                System.out.println(m.toCSV());
            }
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }

    }
}
