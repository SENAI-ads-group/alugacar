package util;

/**
 *
 * @author patrick-ribeiro
 */
public class Utilities {

    public static Integer tryParseToInteger(String string) {
        try {
            string = string.trim().replaceAll("[^0-9]", "");
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Double tryParseToDouble(String string) {
        try {
            string = string.trim().replaceAll("[^0-9,.]", "");
            string = string.trim().replaceAll(",", ".");
            return Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
