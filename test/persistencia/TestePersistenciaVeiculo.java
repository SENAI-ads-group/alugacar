package persistencia;

import model.entidades.Categoria;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Veiculo;
import model.entidades.enums.Combustivel;
import model.servicos.persistencia.implementacaoCSV.VeiculoCSV;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.VeiculoDAO;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaVeiculo {

    public static void main(String[] args) {

        VeiculoDAO persistenceService = new VeiculoCSV();

        try {
            System.out.println("INSERIR");

            Marca marca = new Marca(1, "descricao");
            Categoria categoria = new Categoria(1, "descricao", 1500.0, 100.0);
            Modelo modelo = new Modelo(1, "codigoFipe", "descricao", marca, categoria, Combustivel.DIESEL, 2020);
            Veiculo veiculo = new Veiculo(1, "placa", "renavam", 25000.0, modelo, 2020, 0.0, 5.0);

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
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }

}
