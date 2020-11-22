package util;

/**
 *
 * @author patrick-ribeiro
 */
public class Utilities {

    public static Integer tryParseToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Double tryParseToDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
