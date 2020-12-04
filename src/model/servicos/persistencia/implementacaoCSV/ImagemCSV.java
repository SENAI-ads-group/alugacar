package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import model.entidades.CNH;
import model.entidades.Vistoria;
import model.exceptions.DBException;
import model.servicos.persistencia.ImagemDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author usuario
 */
public class ImagemCSV implements ImagemDAO {

    private final File ARQUIVO_DB_VISTORIA = new File(Programa.getPropriedade("absoluteDatabaseImagePath") + "imagens-vistorias.csv");
    private final File ARQUIVO_DB_CNH = new File(Programa.getPropriedade("absoluteDatabaseImagePath") + "imagens-cnh.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(BufferedImage imagem, Vistoria vistoria) {
        if (vistoria.getId() == null) {
            throw new DBException("O id da vistoria está nulo");
        }
        try {
            CONEXAO.open(ARQUIVO_DB_VISTORIA);
            String encodedString = getEncodedString(imageToByteArray(imagem));
            CONEXAO.writer().write(vistoria.getId() + ";" + encodedString);
            CONEXAO.writer().newLine();
            CONEXAO.close();
        } catch (IOException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public void inserir(BufferedImage imagem, CNH cnh) {
        if (cnh.getNumeroRegistro() == null) {
            throw new DBException("O número de registro da CNH está nulo");
        }
        try {
            CONEXAO.open(ARQUIVO_DB_CNH);
            String encodedString = getEncodedString(imageToByteArray(imagem));
            CONEXAO.writer().write(cnh.getNumeroRegistro() + ";" + encodedString);
            CONEXAO.writer().newLine();
            CONEXAO.close();
        } catch (IOException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public List<BufferedImage> buscar(Vistoria vistoria) {
        if (vistoria.getId() == null) {
            throw new DBException("O id da vistoria está nulo");
        }
        try {
            CONEXAO.open(ARQUIVO_DB_VISTORIA);
            List<BufferedImage> imagemList = new ArrayList<>();
            String linha = CONEXAO.reader().readLine();
            while (linha != null) {
                String[] csv = linha.split(";");
                Integer idVistoria = Utilities.tryParseToInteger(csv[0]);
                BufferedImage imagemEncontrada = createImage(getDecodedBytes(csv[1]));
                if (idVistoria.equals(vistoria.getId())) {
                    imagemList.add(imagemEncontrada);
                }
                linha = CONEXAO.reader().readLine();
            }
            CONEXAO.close();
            return imagemList;
        } catch (IOException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public List<BufferedImage> buscar(CNH cnh) {
        if (cnh.getNumeroRegistro() == null) {
            throw new DBException("O id da vistoria está nulo");
        }
        try {
            CONEXAO.open(ARQUIVO_DB_CNH);
            List<BufferedImage> imagemList = new ArrayList<>();
            String linha = CONEXAO.reader().readLine();
            while (linha != null) {
                String[] csv = linha.split(";");
                Integer idCNH = Utilities.tryParseToInteger(csv[0]);
                BufferedImage imagemEncontrada = createImage(getDecodedBytes(csv[1]));
                if (idCNH.equals(cnh.getNumeroRegistro())) {
                    imagemList.add(imagemEncontrada);
                }
                linha = CONEXAO.reader().readLine();
            }
            CONEXAO.close();
            return imagemList;
        } catch (IOException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    private byte[] imageToByteArray(File file) throws IOException {
        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", byteArrayOut);
        return byteArrayOut.toByteArray();
    }

    private byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOut);
        return byteArrayOut.toByteArray();
    }

    public BufferedImage createImage(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(bytes);
        return ImageIO.read(byteArrayInput);
    }

    private String getEncodedString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] getDecodedBytes(String encodedString) {
        return Base64.getDecoder().decode(encodedString);
    }
}
