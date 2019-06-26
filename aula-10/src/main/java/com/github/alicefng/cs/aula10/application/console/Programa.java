package com.github.alicefng.cs.aula10.application.console;

import com.github.alicefng.cs.aula10.domain.ValidaEntradaUtils;

/**
 * Classe para executar o programa pelo terminal.
 */
public final class Programa {

    /**
     * Construtor privado para evitar instanciação.
     */
    private Programa() {
    }

    /**
     * Programa para executar pelo terminal o programa.
     * @param args Os argumentos fornecidos pelo usuário.
     */
    public static void main(final String[] args) {
        System.out.println(ValidaEntradaUtils.qtdArgumentos(args));
        System.out.println(ValidaEntradaUtils.argumentoIsNatural(args));
    }

}