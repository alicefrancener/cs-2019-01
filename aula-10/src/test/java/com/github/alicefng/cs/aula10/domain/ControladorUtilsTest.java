package com.github.alicefng.cs.aula10.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControladorUtilsTest {

    @Test
    public void dataDeInteresseInvalida() {
        String[] entrada = {"2010101", "1", "23450101", "1"};
        String[] entrada2 = {"100000101", "1", "23450101", "1"};
        String[] entrada3 = {"20161301", "1", "23450101", "1"};
        String[] entrada4 = {"20160001", "1", "23450101", "1"};
        String[] entrada5 = {"20160931", "1", "23450101", "1"};
        String[] entrada6 = {"20160900", "1", "23450101", "1"};
        String[] entrada7 = {"01110101", "1", "23450101", "1"};

        assertEquals(-1, ControladorUtils.executar(entrada));
        assertEquals(-1, ControladorUtils.executar(entrada2));
        assertEquals(-1, ControladorUtils.executar(entrada3));
        assertEquals(-1, ControladorUtils.executar(entrada4));
        assertEquals(-1, ControladorUtils.executar(entrada5));
        assertEquals(-1, ControladorUtils.executar(entrada6));
        assertEquals(-1, ControladorUtils.executar(entrada7));
    }

    @Test
    public void dataDeReferenciaInvalida(){
        String[] entrada = {"20190626", "1", "20190230", "1"};
        String[] entrada2 = {"20190626", "1", "20190132", "1"};
        String[] entrada3 = {"20190626", "1", "20191131", "1"};

        assertEquals(-1, ControladorUtils.executar(entrada));
        assertEquals(-1, ControladorUtils.executar(entrada2));
        assertEquals(-1, ControladorUtils.executar(entrada3));
    }

    @Test
    public void anoBissextoInvalido() {
        String[] entrada1 = {"20190626", "0", "23450101", "1"};
        String[] entrada2 = {"20190626", "-100", "23450101", "1"};
        assertEquals(-1, ControladorUtils.executar(entrada1));
        assertEquals(-1, ControladorUtils.executar(entrada2));
    }

}
