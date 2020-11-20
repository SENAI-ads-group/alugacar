package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class DateUtilities {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String formatData(Date data) {
        return simpleDateFormat.format(data);
    }

    public static Date toDate(String dataFormatada) {
        try {
            return simpleDateFormat.parse(dataFormatada);
        } catch (ParseException ex) {
            return null;
        }
    }
}
