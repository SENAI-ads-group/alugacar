package util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author patrick-ribeiro
 */
public class FieldUtilities {

    public static void setFieldOnlyInteger(JTextField textField, int limit) {
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
                str = str.replaceAll("[^0-9]", "");
                if (limit <= 0) {
                    super.insertString(offset, str, attr);
                } else if (getLength() + str.length() <= limit) {
                    super.insertString(offset, str, attr);
                }
            }
        });
    }

    public static void setFieldOnlyText(JTextField textField, int limit) {
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
                str = str.replaceAll("[^a-z|^A-Z|Â|Ã|Ê|Ô|Õ|~|ç|Ç|Á|À|É|ã|â|ê|õ|ô|á|à|é|^ ]", "");
                if (limit <= 0) {
                    super.insertString(offset, str, attr);
                } else if (getLength() + str.length() <= limit) {
                    super.insertString(offset, str, attr);
                }
            }
        });
    }

    public static void setFieldLimit(JTextField textField, int limit) {
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
                if (limit <= 0) {
                    super.insertString(offset, str, attr);
                } else if (getLength() + str.length() <= limit) {
                    super.insertString(offset, str, attr);
                }
            }
        });
    }

    public static void setFormattedTextFieldMask(JFormattedTextField field, String mask) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(mask);
            field.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void setFieldCPF(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "###.###.###-##");
    }

    public static void setFieldCNPJ(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "##.###.###/####-##");
    }

    public static void setFieldRG(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "######-#");
    }

    public static void setFieldTelefone(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "(##)####-####");
    }

    public static void setFieldValor(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "¤ #,##0.00");
    }

    public static void setFieldCEP(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "#####-###");
    }

    public static void setFieldMoeda(JFormattedTextField field) {
        field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        field.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
                str = str.replaceAll("[^0-9,]", "");
                if (offset < 1) {
                    remove(0, getLength());
                    super.insertString(offset, "R$ " + str, attr);
                } else {
                    super.insertString(offset, str, attr);
                }
            }
        });
    }

    public static void setFieldPlaca(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "UUU-####");
    }
    
    public static void setFieldCodigoFipe(JFormattedTextField field) {
        setFormattedTextFieldMask(field, "######-#");
    }

    public static boolean textFieldIsEmpty(JTextField textField) {
        return textField.getText() == null || textField.getText().trim().equals("");
    }

    public static boolean formattedTextFieldIsEmpty(JFormattedTextField field) {
        String text = field.getText().replaceAll("[^0-9]", "");
        return text == null || text.trim().equals("");
    }

    public static boolean formattedTextFieldIsValid(JFormattedTextField field) {
        try {
            field.getFormatter().stringToValue(field.getText());
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

}
