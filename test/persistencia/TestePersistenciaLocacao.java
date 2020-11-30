
package persistencia;

import java.util.Date;
import model.entidades.CNH;
import model.entidades.Categoria;
import model.entidades.Cliente;
import model.entidades.Endereco;
import model.entidades.Locacao;
import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.Motorista;
import model.entidades.PessoaFisica;
import model.entidades.Veiculo;
import model.entidades.enums.CategoriaCNH;
import model.entidades.enums.Combustivel;
import model.entidades.enums.TipoLocacao;
import model.entidades.enums.UF;
import model.exceptions.DBException;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.LocacaoDAO;

/**
 *
 * @author Alexsander
 */
public class TestePersistenciaLocacao {
    
        public static void main(String[] args) {     
        try {
            LocacaoDAO persistenceService = DAOFactory.createLocacaoService();
            
            System.out.println("INSERIR");
            Endereco endereco = new Endereco("Rua X", 123, "complemento", "bairro1", "cidade1", UF.CE, "75380082");
            PessoaFisica pessoa = new PessoaFisica("Carlos", "(62)9.92245404", "carlos@carlos", endereco);
            Cliente cliente = new Cliente(3131, pessoa, true);
            Marca marca = new Marca(1, "descricao");
            Categoria categoria = new Categoria(1, "descricao", 1500.0, 100.0);
            Modelo modelo = new Modelo(1, "codigoFipe", "descricao", marca, categoria, Combustivel.DIESEL, 2020);
            Veiculo veiculo = new Veiculo(1, "placa", "renavam", 25000.0, modelo, 2020, 0.0, 5.0);
            CNH cnh = new CNH(1, CategoriaCNH.AB, new Date());
            Motorista motorista = new Motorista(1, pessoa, cnh, true);
            Locacao locacao = new Locacao(156, TipoLocacao.DIARIA, new Date(), veiculo, cliente, motorista);
            locacao.getTipo().getContratoService().setLocacao(locacao);
            persistenceService.registrar(locacao);

            System.out.println("BUSCAR");
            Locacao locacaoBuscada = persistenceService.buscar(156);
            if (locacaoBuscada != null) {
                System.out.println(locacaoBuscada.toCSV());

                System.out.println("ALTERAR");
                locacaoBuscada.setId(155);
                persistenceService.atualizar(locacaoBuscada);
            }

            System.out.println("LISTAR");

            for (Locacao loc : persistenceService.buscarTodos()) {
                System.out.println(loc.toCSV());
            }
        } catch (PersistenciaException ex) {
            System.out.println("Erro de persistencia: " + ex.getMessage());
        } catch (DBException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
    
}
