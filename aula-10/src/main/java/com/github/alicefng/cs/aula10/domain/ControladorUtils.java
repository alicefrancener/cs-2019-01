package com.github.alicefng.cs.aula10.domain;

public final class ControladorUtils {

    /**
     * Construtor privado para evitar instaciação da classe utilitária.
     */
    private ControladorUtils() {

    }

    public static int executar(String[] argumentos) {
        try {
            InputUtils.qtdArgumentos(argumentos);
            InputUtils.argumentoIsNatural(argumentos);
            DataUtils.qtdDigitosData(argumentos[0]);
            DataUtils.qtdDigitosData(argumentos[2]);
        } catch (IllegalArgumentException | DataInvalidaException ex) {
            return -1;
        }
        return 0;
    }
}
