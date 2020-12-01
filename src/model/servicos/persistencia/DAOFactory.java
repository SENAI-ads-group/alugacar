package model.servicos.persistencia;

import model.servicos.persistencia.implementacaoCSV.CnhCSV;
import model.servicos.persistencia.implementacaoCSV.CategoriaCSV;
import model.servicos.persistencia.implementacaoCSV.ClienteCSV;
import model.servicos.persistencia.implementacaoCSV.LocacaoCSV;

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

    public static MarcaDAO createMarcaDAO() {
        return new MarcaCSV();
    }

    public static ModeloDAO createModeloDAO() {
        return new ModeloCSV();
    }

    public static CategoriaDAO createCategoriaDAO() {
        return new CategoriaCSV();
    }

    public static UsuarioDAO createUsuarioDAO() {
        return new UsuarioCSV();
    }

    public static MotoristaDAO createMotoristaDAO() {
        return new MotoristaCSV();
    }

    public static ClienteDAO createClienteDAO() {
        return new ClienteCSV();
    }

    public static CnhDAO createCnhDAO() {
        return new CnhCSV();
    }

    public static VeiculoDAO createVeiculoDAO() {
        return new VeiculoCSV();
    }

    public static LocacaoDAO createLocacaoDAO() {
        return new LocacaoCSV();
    }

}
