package model.servicos.persistencia;

import model.servicos.persistencia.interfaces.VeiculoDAO;
import model.servicos.persistencia.interfaces.ClienteDAO;
import model.servicos.persistencia.interfaces.ModeloDAO;
import model.servicos.persistencia.interfaces.ImagemDAO;
import model.servicos.persistencia.interfaces.VistoriaDAO;
import model.servicos.persistencia.interfaces.CnhDAO;
import model.servicos.persistencia.interfaces.MarcaDAO;
import model.servicos.persistencia.interfaces.MotoristaDAO;
import model.servicos.persistencia.interfaces.DescontoDAO;
import model.servicos.persistencia.interfaces.TaxaDAO;
import model.servicos.persistencia.interfaces.CategoriaDAO;
import model.servicos.persistencia.interfaces.ItemVistoriaDAO;
import model.servicos.persistencia.interfaces.LocacaoDAO;
import model.servicos.persistencia.interfaces.UsuarioDAO;
import model.servicos.persistencia.implementacaoCSV.CnhCSV;
import model.servicos.persistencia.implementacaoCSV.CategoriaCSV;
import model.servicos.persistencia.implementacaoCSV.ClienteCSV;
import model.servicos.persistencia.implementacaoCSV.DescontoCSV;
import model.servicos.persistencia.implementacaoCSV.ImagemCSV;
import model.servicos.persistencia.implementacaoCSV.ItemVistoriaCSV;
import model.servicos.persistencia.implementacaoCSV.LocacaoCSV;

import model.servicos.persistencia.implementacaoCSV.MarcaCSV;
import model.servicos.persistencia.implementacaoCSV.ModeloCSV;
import model.servicos.persistencia.implementacaoCSV.MotoristaCSV;
import model.servicos.persistencia.implementacaoCSV.TaxaCSV;
import model.servicos.persistencia.implementacaoCSV.UsuarioCSV;
import model.servicos.persistencia.implementacaoCSV.VeiculoCSV;
import model.servicos.persistencia.implementacaoCSV.VistoriaCSV;

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

    public static VistoriaDAO createVistoriaDAO() {
        return new VistoriaCSV();
    }

    public static ImagemDAO createImagemDAO() {
        return new ImagemCSV();
    }

    public static ItemVistoriaDAO createItemVistoriaDAO() {
        return new ItemVistoriaCSV();
    }

    public static TaxaDAO createTaxaDAO() {
        return new TaxaCSV();
    }

    public static DescontoDAO createDescontoDAO() {
        return new DescontoCSV();
    }

}
