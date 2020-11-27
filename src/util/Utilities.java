package util;

import javax.swing.JTextField;

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

    public static boolean textFieldIsEmpty(JTextField textField) {
        return textField.getText() == null || textField.getText().trim().equals("");
    }
}
