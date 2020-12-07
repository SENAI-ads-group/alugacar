package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import com.alee.utils.FileUtils;
import java.io.File;
import java.util.List;
import model.entidades.CNH;
import model.entidades.Vistoria;
import model.exceptions.DBException;
import model.servicos.persistencia.ImagemDAO;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class ImagemCSV implements ImagemDAO {

    private final String CAMINHO_DB_VISTORIA = Programa.getPropriedade("absoluteDatabaseImagePath") + "vistorias/";
    private final String CAMINHO_DB_CNH = Programa.getPropriedade("absoluteDatabaseImagePath") + "cnh/";

    @Override
    public void exportar(Vistoria vistoria) {
        if (vistoria.getId() == null) {
            throw new DBException("O id da vistoria está nulo");
        }
        List<File> files = vistoria.getImagens();
        for (File file : files) {
            String nomeArquivo = vistoria.getId() + ";" + files.indexOf(file) + ".jpg";
            File destino = new File(CAMINHO_DB_VISTORIA + nomeArquivo);
            FileUtils.copyFile(file, destino);
        }
    }

    @Override
    public void exportar(CNH cnh) {
        if (cnh.getNumeroRegistro() == null) {
            throw new DBException("O número de registro da CNH está nulo");
        }
        String nomeArquivoFrente = cnh.getNumeroRegistro() + ";frente" + ".jpg";
        String nomeArquivoVerso = cnh.getNumeroRegistro() + ";verso" + ".jpg";
        File destinoFrente = new File(CAMINHO_DB_CNH + nomeArquivoFrente);
        File destinoVerso = new File(CAMINHO_DB_CNH + nomeArquivoVerso);
        if (!cnh.getFotoFrente().equals(destinoFrente)) {
            FileUtils.copyFile(cnh.getFotoFrente(), destinoFrente);
        }
        if (!cnh.getFotoVerso().equals(destinoVerso)) {
            FileUtils.copyFile(cnh.getFotoVerso(), destinoVerso);
        }
    }

    @Override
    public void importar(Vistoria vistoria) {
        if (vistoria.getId() == null) {
            throw new DBException("O id da vistoria está nulo");
        }
        File[] arrayFiles = new File(CAMINHO_DB_VISTORIA).listFiles();
        for (int i = 0; i < arrayFiles.length; i++) {
            File file = arrayFiles[i];
            String nomeArquivo = file.getName();
            Integer idFile = Utilities.tryParseToInteger(nomeArquivo.split(";")[0]);
            if (idFile.equals(vistoria.getId())) {
                vistoria.addImagem(i, file);
            }
        }
    }

    @Override
    public void importar(CNH cnh) {
        if (cnh.getNumeroRegistro() == null) {
            throw new DBException("O id da cnh está nulo");
        }
        File[] arrayFiles = new File(CAMINHO_DB_CNH).listFiles();
        for (int i = 0; i < arrayFiles.length; i++) {
            File file = arrayFiles[i];
            String nomeArquivo = file.getName();
            Integer idFile = Utilities.tryParseToInteger(nomeArquivo.split(";")[0]);
            if (idFile.equals(cnh.getNumeroRegistro())) {
                boolean fotoFrente = nomeArquivo.contains("frente");
                if (fotoFrente) {
                    cnh.setFotoFrente(file);
                } else {
                    cnh.setFotoVerso(file);
                }
            }
        }

    }
}
