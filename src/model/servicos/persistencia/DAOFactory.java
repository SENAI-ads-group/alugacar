package model.servicos.persistencia;

import model.servicos.persistencia.implementacaoCSV.CnhCSV;
import model.servicos.persistencia.implementacaoCSV.CategoriaCSV;
import model.servicos.persistencia.implementacaoCSV.ClienteCSV;
import model.servicos.persistencia.implementacaoCSV.MarcaCSV;
import model.servicos.persistencia.implementacaoCSV.ModeloCSV;
import model.servicos.persistencia.implementacaoCSV.MotoristaCSV;
import model.servicos.persistencia.implementacaoCSV.UsuarioCSV;
import model.servicos.persistencia.implementacaoCSV.VeiculoCSV;

/**
 *
 * @author patrick-ribeiro
 */
public class DAOFactory {

    public static MarcaDAO createMarcaService() {
        return new MarcaCSV();
    }

    public static ModeloDAO createModeloService() {
        return new ModeloCSV();
    }

    public static CategoriaDAO createCategoriaService() {
        return new CategoriaCSV();
    }

    public static UsuarioDAO createUsuarioService() {
        return new UsuarioCSV();
    }

    public static MotoristaDAO createMotoristaService() {
        return new MotoristaCSV();
    }

    public static ClienteDAO createClienteService() {
        return new ClienteCSV();
    }

    public static CnhDAO createCNHService() {
        return new CnhCSV();
    }

    /*public static EnderecoPersistenseService createEnderecoService() {
        return new EnderecoPersistenceServiceCSV();
    }*/
    
    public static VeiculoDAO createVeiculoService() {
        return new VeiculoCSV();
    }
}
