package model.services.persistence;

import model.services.persistence.csv.CNHPersistenceServiceCSV;
import model.services.persistence.csv.CategoriaPersistenceServiceCSV;
import model.services.persistence.csv.ClientePersistenceServiceCSV;
import model.services.persistence.csv.MarcaPersistenceServiceCSV;
import model.services.persistence.csv.ModeloPersistenceServiceCSV;
import model.services.persistence.csv.MotoristaPersistenceServiceCSV;
import model.services.persistence.csv.UsuarioPersistenceServiceCSV;
import model.services.persistence.csv.VeiculoPersistenceServiceCSV;

/**
 *
 * @author patrick-ribeiro
 */
public class PersistenceFactory {

    public static MarcaPersistenceService createMarcaService() {
        return new MarcaPersistenceServiceCSV();
    }

    public static ModeloPersistenceService createModeloService() {
        return new ModeloPersistenceServiceCSV();
    }

    public static CategoriaPersistenceService createCategoriaService() {
        return new CategoriaPersistenceServiceCSV();
    }

    public static UsuarioPersistenceService createUsuarioService() {
        return new UsuarioPersistenceServiceCSV();
    }

    public static MotoristaPersistenceService createMotoristaService() {
        return new MotoristaPersistenceServiceCSV();
    }

    public static ClientePersistenceService createClienteService() {
        return new ClientePersistenceServiceCSV();
    }

    public static CNHPersistenseService createCNHService() {
        return new CNHPersistenceServiceCSV();
    }

    /*public static EnderecoPersistenseService createEnderecoService() {
        return new EnderecoPersistenceServiceCSV();
    }*/
    
    public static VeiculoPersistenceService createVeiculoService() {
        return new VeiculoPersistenceServiceCSV();
    }
}
