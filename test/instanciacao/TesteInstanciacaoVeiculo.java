package instanciacao;

import model.entidades.Categoria;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Veiculo;
import model.entidades.enums.Combustivel;

/**
 *
 * @author usuario
 */
public class TesteInstanciacaoVeiculo {

    public static void main(String[] args) {

        Marca marca = new Marca(1, "descricao");
        Categoria categoria = new Categoria(1, "descricao", 1500.0, 100.0);
        Modelo modelo = new Modelo(1, "codigoFipe", "descricao", marca, categoria, Combustivel.DIESEL, 2020);
        Veiculo veiculo = new Veiculo(1, "placa", "renavam", 25000.0, modelo, 2020, 0.0, 5.0);
        System.out.println(veiculo.toCSV());

        Veiculo veiculo1 = new Veiculo(veiculo.toCSV().split(";"));
        System.out.println(veiculo1.toCSV());

        System.out.println();
        System.out.println(categoria.toCSV());

        Categoria categoria1 = new Categoria(categoria.toCSV().split(";"));
        System.out.println(categoria1.toCSV());

        System.out.println();
        System.out.println(marca.toCSV());

        Modelo modelo1 = new Modelo(modelo.toCSV().split(";"));
        System.out.println(modelo1.toCSV());

        System.out.println();
        System.out.println(marca.toCSV());

        Marca marca1 = new Marca(marca.toCSV().split(";"));
        System.out.println(marca1.toCSV());
    }
}
