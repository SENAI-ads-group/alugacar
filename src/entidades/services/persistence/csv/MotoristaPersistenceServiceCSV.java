package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Endereco;
import entidades.Motorista;
import entidades.PessoaFisica;
import entidades.enums.UF;
import entidades.services.persistence.MotoristaPersistenceService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.DateUtilities;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class MotoristaPersistenceServiceCSV implements MotoristaPersistenceService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public MotoristaPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.motorista");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Motorista motorista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Motorista motorista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Motorista buscar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Motorista> buscarTodos() {
        connection.open(arquivoDB);

        List<Motorista> motoristasEncontrados = new ArrayList<>();
        String linha = connection.reader().readLine();

        while (linha != null) {
            String[] csv = linha.split(";");
            PessoaFisica pessoa = instanciarPessoa(csv);
            Motorista motoristaEncontrado = new Motorista(csv, pessoa);
            motoristasEncontrados.add(motoristaEncontrado);
            linha = connection.reader().readLine();
        }

        connection.close();
        return motoristasEncontrados;
    }

    private PessoaFisica instanciarPessoa(String[] csv) {
        String nome = csv[4];
        String telefone = csv[5];
        String email = csv[6];
        String logradouro = csv[7];
        Integer numero = Utilities.tryParseToInteger(csv[8]);
        String complemento = csv[9];
        String bairro = csv[10];
        String cidade = csv[11];
        UF uf = UF.valueOf(csv[12]);
        String cep = csv[13];
        String cpf = csv[14];
        Date dataNascimento = DateUtilities.tryParseToDate(csv[15]);
        Integer registroGeral = Utilities.tryParseToInteger(csv[16]);

        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
        return new PessoaFisica(nome, telefone, email, endereco, cpf, registroGeral, dataNascimento);
    }

}
