package instanciacao;

import entidades.Categoria;
import entidades.Marca;
import entidades.Modelo;
import entidades.Veiculo;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class TesteInstanciacaoVeiculo {

    public static void main(String[] args) {

        Marca marca = new Marca("marca1");
        Modelo modelo = new Modelo(marca, "modelo1");
        Categoria categoria = new Categoria(1, "categoria1", 103.0);
        Veiculo veiculo = new Veiculo(1, "KKK-3333", "123456789", categoria, modelo,
                new Date(), new Date(), 15000.0);
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