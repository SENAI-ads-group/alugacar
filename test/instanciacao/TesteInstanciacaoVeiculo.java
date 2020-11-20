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

        Marca marca = new Marca(1, "marca1");
        Modelo modelo = new Modelo(1, marca, "modelo1");
        Categoria categoria = new Categoria(1, "categoria1", 100.0);

        Date dataAtual = new Date();

        Veiculo veiculo = new Veiculo(1, "KKK-3333", "123456789", categoria, modelo,
                dataAtual, dataAtual, 15000.0);

        System.out.println("MARCA -> " + marca.toCSV());
        System.out.println();
        System.out.println("MODELO -> " + modelo.toCSV());
        System.out.println();
        System.out.println("CATEGORIA -> " + categoria.toCSV());
        System.out.println();
        System.out.println("VEICULO -> " + veiculo.toCSV());
    }
}
