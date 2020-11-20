package entidades.services.persistence;

import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;
import entidades.services.persistence.csv.ModeloPersistenceServiceCSV;

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
}
