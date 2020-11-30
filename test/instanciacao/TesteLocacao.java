package instanciacao;

import java.util.Date;
import model.entidades.CNH;
import model.entidades.Categoria;
import model.entidades.Cliente;
import model.entidades.Locacao;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.Veiculo;
import model.entidades.Vistoria;
import model.entidades.enums.CategoriaCNH;
import model.entidades.enums.Combustivel;
import model.entidades.enums.TipoCliente;
import model.entidades.enums.TipoLocacao;

/**
 *
 * @author Alexsander
 */
public class TesteLocacao {
    
    public static void main(String[] args) {
        
        Marca marca = new Marca();
        marca.setDescricao("Chevrolet");
        
        Categoria categoria = new Categoria();
        categoria.setDescricao("Econômico");
        categoria.setValorDiaria(100.00);
        categoria.setValorMinimoLocacao(100.00);
        categoria.setValorKM(50.00);
        
        Modelo modelo = new Modelo();
        modelo.setCategoria(categoria);
        modelo.setCombustivel(Combustivel.GASOLINA);
        modelo.setDescricao("Camaro");
        modelo.setMarca(marca);
        
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setKMRodado(0.0);
        veiculo.setPlaca("ABC-1234");
        veiculo.setPrecoCompra(150.00);
        veiculo.setRenavam("123456");
        veiculo.setAnoFabricacao(2020);
        
        CNH cnh = new CNH(12345, CategoriaCNH.A, new Date());
        PessoaFisica pessoa = new PessoaFisica("Carlos");
        Motorista motorista = new Motorista();
        motorista.setCnh(cnh);
        motorista.setPessoa(pessoa);
        
        Cliente cliente = new Cliente(TipoCliente.PESSOA_FISICA);
        cliente.setPessoa(pessoa);
        
        Vistoria vistoriaEntrega = new Vistoria(veiculo.getKMRodado(), true);
        
        Locacao locacao = new Locacao(TipoLocacao.DIARIA, new Date(), new Date(2020, 11, 14), veiculo, cliente, motorista);
        locacao.iniciar(vistoriaEntrega);
        System.out.println(locacao.toCSV());
        
        Vistoria vistoriaDevolucao = new Vistoria(50.0, false);
        locacao.finalizar(vistoriaDevolucao);
        System.out.println(locacao.toCSV());
        
    }
}
