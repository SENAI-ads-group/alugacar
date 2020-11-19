package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class DateUtilities {

    public static String formatData(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(data);
    }
}
