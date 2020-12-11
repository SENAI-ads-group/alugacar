package util;

/**
 *
 * @author patrick-ribeiro
 */
public class Validador {

    public static boolean cpfIsValido(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            return false;
        }
        if (digitosIguais(cpf)) {
            return false;
        }
        char digito10 = getPrimeiroDigitoVerificadorCNPJ(cpf);
        char digito11 = getSegundoDigitoVerificadorCPF(cpf);

        return ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10)));
    }

    private static char getPrimeiroDigitoVerificadorCNPJ(String cpf) {
        char digitoVerificado;

        int soma = 0;
        int peso = 10;
        int numero = 0;

        // (48 eh a posicao de '0' na tabela ASCII)
        for (int i = 0; i < 9; i++) {
            numero = (int) (cpf.charAt(i) - 48);
            soma += numero * peso;
            peso--;
        }

        int resultado = 11 - (soma % 11);
        if (resultado == 10 || resultado == 11) {
            digitoVerificado = '0';
        } else {
            digitoVerificado = (char) (resultado + 48); // converte no respectivo caractere numerico
        }
        return digitoVerificado;
    }

    private static char getSegundoDigitoVerificadorCPF(String cpf) {
        char digitoVerificador;
        int soma = 0;
        int peso = 11;
        int numero;

        for (int i = 0; i < 10; i++) {
            numero = (int) (cpf.charAt(i) - 48);
            soma += (numero * peso);
            peso--;
        }

        int resultado = 11 - (soma % 11);
        if ((resultado == 10) || (resultado == 11)) {
            digitoVerificador = '0';
        } else {
            digitoVerificador = (char) (resultado + 48);
        }
        return digitoVerificador;
    }

    private static boolean digitosIguais(String string) {
        return string.equals("00000000000") || string.equals("11111111111")
                || string.equals("22222222222") || string.equals("33333333333")
                || string.equals("44444444444") || string.equals("55555555555")
                || string.equals("66666666666") || string.equals("77777777777")
                || string.equals("88888888888") || string.equals("99999999999");
    }

    public static boolean cnpjIsValido(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if (cnpj.length() != 14) {
            return false;
        }
        if (digitosIguais(cnpj)) {
            return false;
        }
        char dig13, dig14;
        int soma, i, resultado, num, peso;

        // Calculo do primeiro Digito Verificador
        soma = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            num = (int) (cnpj.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }
        resultado = soma % 11;
        if ((resultado == 0) || (resultado == 1)) {
            dig13 = '0';
        } else {
            dig13 = (char) ((11 - resultado) + 48);
        }

        // Calculo do segundo Digito Verificador
        soma = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = (int) (cnpj.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }
        resultado = soma % 11;
        if ((resultado == 0) || (resultado == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - resultado) + 48);
        }

        return ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)));

    }

    public static boolean renavamIsValido(String renavam) {
        // Pegando como exemplo o renavam = 639884962
        // Completa com zeros a esquerda se for no padrao antigo de 9 digitos
        // renavam = 00639884962
        if (renavam.matches("^([0-9]{9})$")) {
            renavam = "00" + renavam;
        }
        // Valida se possui 11 digitos pos formatacao
        if (!renavam.matches("[0-9]{11}")) {
            return false;
        }
        // Remove o digito (11 posicao)
        // renavamSemDigito = 0063988496
        String renavamSemDigito = renavam.substring(0, 10);

        // Inverte os caracteres (reverso)
        // renavamReversoSemDigito = 69488936
        String renavamReversoSemDigito = new StringBuffer(renavamSemDigito).reverse().toString();

        int soma = 0;
        // Multiplica as strings reversas do renavam pelos numeros multiplicadores
        // para apenas os primeiros 8 digitos de um total de 10
        // Exemplo: renavam reverso sem digito = 69488936
        // 6, 9, 4, 8, 8, 9, 3, 6
        // * * * * * * * *
        // 2, 3, 4, 5, 6, 7, 8, 9 (numeros multiplicadores – sempre os mesmos [fixo])
        // 12 + 27 + 16 + 40 + 48 + 63 + 24 + 54
        // soma = 284
        for (int i = 0; i < 8; i++) {
            Integer algarismo = Integer.parseInt(renavamReversoSemDigito.substring(i, i + 1));
            Integer multiplicador = i + 2;
            soma += algarismo * multiplicador;
        }

        // Multiplica os dois ultimos digitos e soma
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(8)) * 2;
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(9)) * 3;

        // mod11 = 284 % 11 = 9 (resto da divisao por 11)
        int mod11 = soma % 11;

        // Faz-se a conta 11 (valor fixo) – mod11 = 11 – 9 = 2
        int ultimoDigitoCalculado = 11 - mod11;

        // ultimoDigito = Caso o valor calculado anteriormente seja 10 ou 11, transformo ele em 0
        // caso contrario, eh o proprio numero
        ultimoDigitoCalculado = (ultimoDigitoCalculado >= 10 ? 0 : ultimoDigitoCalculado);

        // Pego o ultimo digito do renavam original (para confrontar com o calculado)
        int digitoRealInformado = Integer.valueOf(renavam.substring(renavam.length() - 1, renavam.length()));

        // Comparo os digitos calculado e informado
        if (ultimoDigitoCalculado == digitoRealInformado) {
            return true;
        }
        return false;
    }

}
