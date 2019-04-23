package com.github.alicefng.inf.cs.aula1;

public class CPF {

    private String cpf;

    public CPF(String cpf) {
        validarCPF(cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public static void validarCPF(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("argumento é null");
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos: " + cpf);
        }

        if (!sequenciaContemApenasDigitos(cpf)) {
            throw new IllegalArgumentException("CPF deve conter somente dígitos (0 a 9): " + cpf);
        }

        if (!validarDigitosCPF2(cpf)) {
            throw new IllegalArgumentException("dígitos verificadores do CPF incorretos: " + cpf);
        }
    }

    private static boolean sequenciaContemApenasDigitos(final String cpf) {
        return cpf.chars().allMatch(c -> Character.isDigit(c));
    }

    public static int[] converteCaracteresEmInteiros(String cpf) {
        return cpf.chars().map(c -> Character.getNumericValue(c)).toArray();
    }

    public static boolean validarDigitosCPF(String cpf) {
        int[] cpfVetor = converteCaracteresEmInteiros(cpf);
        int j = cpfVetor[0];
        int k = cpfVetor[1];
        for (int i = 1; i < 9; i++) {
            j += cpfVetor[i] * (i + 1);
        }
        for (int i = 2; i < 10; i++) {
            k += cpfVetor[i] * i;
        }
        int dj = (j % 11) % 10;
        int dk = (k % 11) % 10;
        return dj == cpfVetor[9] & dk == cpfVetor[10];
    }

    public static boolean validarDigitosCPF2(String cpf) {
        int[] cpfVetor = converteCaracteresEmInteiros(cpf);
        int p = cpfVetor[8];
        int s = p;
        for (int c = 7; c >= 0; c--) {
            p += cpfVetor[c];
            s += p;
        }
        int j = (s % 11) % 10;
        int k = ((s - p + 9 * cpfVetor[9]) % 11) % 10;
        return (j == cpfVetor[9] & k == cpfVetor[10]);
    }

}