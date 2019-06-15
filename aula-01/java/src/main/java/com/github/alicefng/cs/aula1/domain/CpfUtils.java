/*
 * Copyright (c) 2019.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.alicefng.cs.aula1.domain;

import java.util.stream.IntStream;

/**
 * Implementação de algoritomos para validar CPF.
 */
public final class CpfUtils {

    /**
     * Valor correspondente ao tamanho (quantidade de dígitos) de um CPF.
     */
    public static final int TAMANHO_CPF = 11;

    /**
     * // TODO Não é o valor, mas o índice de
     * Índice correspondente ao 1º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_1 = 0;

    /**
     * Valor correspondente ao 2º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_2 = 1;

    /**
     * Valor correspondente ao 3º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_3 = 2;


    /**
     * Valor correspondente ao 8º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_8 = 7;

    /**
     * Valor correspondente ao 9º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_9 = 8;

    /**
     * Valor correspondente ao 10º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_10 = 9;

    /**
     * Valor correspondente ao 11º dígitos de um CPF em um vetor.
     */
    public static final int DIGITO_11 = 10;

    /**
     * Previne a classe utilitária de ser instanciada.
     */
    private CpfUtils() {
    }

    /**
     * Avalia se uma String contém apenas dígitos.
     *
     * @param cpf A string contendo o CPF a ser avaliado
     * @return verdadeiro, se a string contém apenos dígitos
     * falso, se a string contém outros caracteres
     */
    private static boolean sequenciaContemApenasDigitos(final String cpf) {
        return cpf.chars().allMatch(c -> Character.isDigit(c));
    }

    /**
     * Transforma caracteres numéricos em inteiros.
     *
     * @param cpf String que contém CPF
     * @return Vetor contendo em cada posição um dígito do CPF como inteiro
     */
    private static int[] converteCaracteresEmInteiros(final String cpf) {
        return cpf.chars().map(c -> Character.getNumericValue(c)).toArray();
    }

    /**
     * Avalia se CPF fornecido é válido dado seus dígitos verificadores
     * (dois últimos dígitos) - algoritmo 1.
     *
     * @param cpf String que contém CPF
     * @return verdadeiro, se dígitos verificadores do CPF estiverem corretos,
     * falso, se não
     * @throws IllegalArgumentException Se argumento é null
     * @throws IllegalArgumentException Se argumento não contém 11 dígitos
     * @throws IllegalArgumentException Se argumento contém algo diferente
     *                                  de dígitos
     */
    public static boolean validarDigitosCPF(final String cpf) {
        final int[] digitos = cpfStringParaDigitos(cpf);

        final int primeiro = primeiroDigitoVerificador(digitos);
        final int segundo = segundoDigitoVerificador(digitos);

        return confereDigitosVerificadores(digitos, primeiro, segundo);
    }

    private static boolean confereDigitosVerificadores(
            final int[] digitos, final int primeiro, final long segundo) {
        return primeiro == digitos[DIGITO_10] & segundo == digitos[DIGITO_11];
    }

    private static int segundoDigitoVerificador(final int[] digitosCpf) {
        IntStream indices = IntStream.range(DIGITO_3, DIGITO_11);
        final int parcelas = (int) indices
                .mapToLong(i -> digitosCpf[i] * i).sum();

        return ((parcelas + digitosCpf[DIGITO_2]) % 11) % 10;
    }

    private static int primeiroDigitoVerificador(final int[] digitosCpf) {
        int calculoDigito10 = digitosCpf[DIGITO_1];
        for (int i = DIGITO_2; i < DIGITO_10; i++) {
            calculoDigito10 = calculoDigito10 + digitosCpf[i] * (i + 1);
        }

        return (calculoDigito10 % 11) % 10;
    }

    /**
     * Avalia se CPF fornecido é válido dado seus dígitos verificadores
     * (dois últimos dígitos) - algoritmo 2.
     *
     * @param cpf String que contém CPF
     * @return verdadeiro, se dígitos verificadores do CPF estiverem corretos,
     * falso, se não
     * @throws IllegalArgumentException Se argumento é null
     * @throws IllegalArgumentException Se argumento não contém 11 dígitos
     * @throws IllegalArgumentException Se argumento contém algo diferente
     *                                  de dígitos (0 a 9)
     */
    public static boolean validarDigitosCPF2(final String cpf) {
        final int[] digitosCpf = cpfStringParaDigitos(cpf);
        int calculoDigito11 = digitosCpf[DIGITO_9];
        int calculoDigito10 = digitosCpf[DIGITO_9];

        for (int i = DIGITO_8; i >= DIGITO_1; i--) {
            calculoDigito11 = calculoDigito11 + digitosCpf[i];
            calculoDigito10 = calculoDigito10 + calculoDigito11;
        }
        calculoDigito10 = (calculoDigito10 % 11) % 10;
        calculoDigito11 = ((calculoDigito10 - calculoDigito11
                + 9 * digitosCpf[DIGITO_10]) % 11) % 10;

        return calculoDigito10 == digitosCpf[DIGITO_10]
                & calculoDigito11 == digitosCpf[DIGITO_11];
    }

    private static int[] cpfStringParaDigitos(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (cpf.length() != TAMANHO_CPF) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos: "
                    + cpf);
        }
        if (!sequenciaContemApenasDigitos(cpf)) {
            throw new IllegalArgumentException("CPF deve conter somente "
                    + "dígitos (0 a 9): " + cpf);
        }

        return converteCaracteresEmInteiros(cpf);
    }

}
