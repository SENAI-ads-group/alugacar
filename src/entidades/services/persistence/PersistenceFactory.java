package entidades.services.persistence;

import entidades.services.persistence.csv.MarcaPersistenceServiceCSV;

/**
 *
 * @author patrick-ribeiro
 */
public class PersistenceFactory {

    public static MarcaPersistenceService createMarcaService() {
        return new MarcaPersistenceServiceCSV();
    }
}
