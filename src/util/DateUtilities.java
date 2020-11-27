package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author usuario
 */
public class DateUtilities {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String formatData(Date data) {
        if (data == null) {
            return null;
        }
        return simpleDateFormat.format(data);
    }

    public static Date tryParseToDate(String dataFormatada) {
        try {
            return simpleDateFormat.parse(dataFormatada);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static int getAge(Date dateOfBirth) {
        Calendar birth = new GregorianCalendar();
        birth.setTime(dateOfBirth);
        Calendar now = Calendar.getInstance();

        int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (now.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            age--;
        } else if (now.get(Calendar.MONTH) == birth.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }
}
