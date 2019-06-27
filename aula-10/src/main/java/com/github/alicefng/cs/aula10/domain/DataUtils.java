package com.github.alicefng.cs.aula10.domain;

import java.util.Arrays;

/**
 * Classe para calcular dia da semana de interesse e validar datas.
 */
public final class DataUtils {

    /**
     * Número de dígitos necessários para uma data válida no formato String
     * (aaaammdd).
     */
    private static final int NUMERO_DIGITOS = 8;

    /**
     * Inteiro correspondente ao mês de janeiro.
     */
    private static final int JANEIRO = 1;

    /**
     * Inteiro correspondente ao mês de fevereiro.
     */
    private static final int FEVEREIRO = 2;

    /**
     * Inteiro correspondente ao mês de março.
     */
    private static final int MARCO = 3;

    /**
     * Inteiro correspondente ao mês abril.
     */
    private static final int ABRIL = 4;

    /**
     * Inteiro correspondente ao mês de maio.
     */
    private static final int MAIO = 5;

    /**
     * Inteiro correspondente ao mês de junho.
     */
    private static final int JUNHO = 6;

    /**
     * Inteiro correspondente ao mês de julho.
     */
    private static final int JULHO = 7;

    /**
     * Inteiro correspondente ao mês de agosto.
     */
    private static final int AGOSTO = 8;

    /**
     * Inteiro correspondente ao mês de setembro.
     */
    private static final int SETEMBRO = 9;

    /**
     * Inteiro correspondente ao mês de outubro.
     */
    private static final int OUTUBRO = 10;

    /**
     * Inteiro correspondente ao mês de novembro.
     */
    private static final int NOVEMBRO = 11;

    /**
     * Inteiro correspondente ao mês dezembro.
     */
    private static final int DEZEMBRO = 12;

    /**
     * Primeiro dia de todos os meses.
     */
    private static final int PRIMEIRO_DIA_MES = 1;

    /**
     * Ultimo dia dos meses mais longos (31 dias).
     */
    private static final int ULTIMO_DIA_MES_LONGO = 31;

    /**
     * Ultimo dia dos meses com 30 dias.
     */
    private static final int ULTIMO_DIA_MES_NORMAL = 30;

    /**
     * Último dia de fevereiro em ano comum.
     */
    private static final int ULTIMO_DIA_FEV = 28;

    /**
     * Último dia de fevereiro em ano bissexto.
     */
    private static final int ULTIMO_DIA_FEV_BISSEXTO = 29;

    /**
     * Inteiro correspondente à segunda-feira.
     */
    private static final int SEGUNDA = 0;

    /**
     * Inteiro correspondente ao domingo.
     */
    private static final int DOMINGO = 6;

    /**
     * Menor ano bissexto considerado válido de acordo com os requisitos.
     */
    private static final int MENOR_ANO_BISSEXTO = 1;

    /**
     * Construtor privado para evitar instaciação da classe utilitária.
     */
    private DataUtils() {

    }

    /**
     * Avalia se a quantidade de dígitos de uma data está incorreta.
     *
     * @param data Data a ser avaliada
     * @throws DataInvalidaException Se a quantidade de dígitos não for
     *                               formada exatamente po 8 dígitos
     * @implNote Para atender o requisito R13a
     */
    public static void evalQtdDigitosData(final String data) {
        if (data.length() != NUMERO_DIGITOS) {
            throw new DataInvalidaException();
        }
    }

    /**
     * Avalia se o primeiro dígito da data está incorreto.
     *
     * @param data Data a ser avaliada
     * @throws DataInvalidaException Se o primeiro dígito da data for 0
     * @implNote Para atender o requisito R13b
     */
    public static void evalRangeAno(final String data) {
        final int primeiroDigitoData =
                Integer.parseInt(data.substring(0, 1));
        if (primeiroDigitoData == 0) {
            throw new DataInvalidaException();
        }
    }

    /**
     * Avalia se amplitude do dia da semana está correta.
     *
     * @param diaDaSemana Data a ser avaliada
     * @throws IllegalArgumentException Se o dia da semana for maior que 6
     * @implNote Para atender o requisito R10
     */
    public static void evalRangeDiaDaSemana(final String diaDaSemana) {
        final int diaDaSemanaAux = Integer.parseInt(diaDaSemana);
        if (diaDaSemanaAux > DOMINGO) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Avalia se amplitude do ano bissexto está correta.
     *
     * @param anoBissexto O ano bissexto a ser avaliado
     * @throws IllegalArgumentException Se o ano bissexto for menor que 1
     * @implNote Para atender o requisito R11
     */
    public static void evalRangeAnoBissexto(final String anoBissexto) {
        final int anoBissextoAux = Integer.parseInt(anoBissexto);
        if (anoBissextoAux < MENOR_ANO_BISSEXTO) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Obtém o ano como inteiro a partir de uma data no formato "aaaammdd".
     *
     * @param data A data da qual se quer obter o ano
     * @return O ano como inteiro
     */
    public static int getAnoAsInt(final String data) {
        return Integer.parseInt(data.substring(0, 4));
    }

    /**
     * Obtém o mês como inteiro a partir de uma data no formato "aaaammdd".
     *
     * @param data A data da qual se quer obter o mês
     * @return O mes como inteiro
     * @throws DataInvalidaException Se o mes for zero ou maior que 12
     *                               (equivalente ao mês DEZEMBRO)
     * @implNote Para atender o requisito R13c
     */
    public static int getMesAsInt(final String data) {
        final int mes = Integer.parseInt(data.substring(4, 6));
        if (mes == 0 | mes > DEZEMBRO) {
            throw new DataInvalidaException();
        }

        return mes;
    }

    /**
     * Obtém o dia como inteiro a partir de uma data no formato "aaaammdd".
     *
     * @param data        A data da qual se quer obter o dia
     * @param anoBissexto O ano bissexto de referência
     * @return O dia como inteiro
     * @throws DataInvalidaException Se o dia for zero ou maior que 31;
     *                               Se o dia não for compatível com o mês
     * @implNote Para atender os requisitos R13d e R13e
     */
    public static int getDiaAsInt(final String data, final String anoBissexto) {
        final int dia = Integer.parseInt(data.substring(6, 8));
        final int mes = getMesAsInt(data);

        final int[] mesesTrintaEUmDias = {JANEIRO, MARCO, MAIO, JULHO, AGOSTO,
                OUTUBRO, DEZEMBRO};

        if (dia == 0 | dia > ULTIMO_DIA_MES_LONGO) {
            throw new DataInvalidaException();
        }
        
        if (dia == ULTIMO_DIA_MES_LONGO
                & Arrays.stream(mesesTrintaEUmDias).noneMatch(m -> m == mes)) {
            throw new DataInvalidaException();
        }

        if (dia > ULTIMO_DIA_FEV & mes == FEVEREIRO) {

            final int ano = getAnoAsInt(data);
            if (isBissexto(anoBissexto, ano)) {
                if (dia > ULTIMO_DIA_FEV_BISSEXTO) {
                    throw new DataInvalidaException();
                }
            } else {
                throw new DataInvalidaException();
            }
        }

        return dia;
    }

    /**
     * Compara duas datas em relação à ordem temporal.
     *
     * @param data             A data a ser comparada
     * @param dataDeReferencia A data de referência
     * @param anoBissexto      O ano bissexto de referência
     * @return 0, se data e dataDeReferencia forem iguais;
     * -1, se data for anterior à dataDeReferencia;
     * 1, se data for posterior à dataDeReferencia
     */
    public static int comparaDatas(final String data,
                                   final String dataDeReferencia,
                                   final String anoBissexto) {
        final int ano = getAnoAsInt(data);
        final int mes = getMesAsInt(data);
        final int dia = getDiaAsInt(data, anoBissexto);
        final int anoRef = getAnoAsInt(dataDeReferencia);
        final int mesRef = getMesAsInt(dataDeReferencia);
        final int diaRef = getDiaAsInt(dataDeReferencia, anoBissexto);

        if (ano < anoRef) {
            return -1;
        }
        if (ano > anoRef) {
            return 1;
        } else {
            if (mes < mesRef) {
                return -1;
            }
            if (mes > mesRef) {
                return 1;
            } else {
                if (dia < diaRef) {
                    return -1;
                }
                if (dia > diaRef) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * Obtém o dia da semana de uma data de interesse com base em uma data de
     * referência e o dia da semana dessa data, além do ano bissexto de
     * referência.
     *
     * @param dataDeInteresse  A data da qual quer se obter o dia da semana
     * @param anoBissexto      O ano bissexto de referência
     * @param dataDeReferencia A data de referência
     * @param diaDaSemana      O dia da semana da data de referência
     * @return O dia da semana da data de interesse (0 a 6). Com 0
     * correspondente à segunda-feira, 1 à terça-feira, e assim
     * sucessivamente
     * @implNote Para atender requisitos R17 e R18
     */
    public static int getDiaDaSemana(final String dataDeInteresse,
                                     final String anoBissexto,
                                     final String dataDeReferencia,
                                     final String diaDaSemana) {

        String dataDeReferenciaAux = dataDeReferencia;
        int diaDaSemanaAux = Integer.parseInt(diaDaSemana);

        final int comparacao = comparaDatas(dataDeInteresse, dataDeReferencia,
                anoBissexto);
        final int dataDeReferenciaPosterior = -1;
        final int dataDeReferenciaAnterior = 1;

        if (comparacao == dataDeReferenciaPosterior) {
            while (!dataDeInteresse.equals(dataDeReferenciaAux)) {
                dataDeReferenciaAux = subtraiDia(dataDeReferenciaAux,
                        anoBissexto);
                diaDaSemanaAux = subtraiDiaDaSemana(diaDaSemanaAux);
            }
        }

        if (comparacao == dataDeReferenciaAnterior) {
            while (!dataDeInteresse.equals(dataDeReferenciaAux)) {
                dataDeReferenciaAux = adicionaDia(dataDeReferenciaAux,
                        anoBissexto);
                diaDaSemanaAux = adicionaDiaDaSemana(diaDaSemanaAux);
            }
        }

        return diaDaSemanaAux;
    }

    /**
     * Adiciona um dia à data passada como argumento.
     *
     * @param data        A data a ser adicionada
     * @param anoBissexto O ano bissexto de referência
     * @return A data posterior à data passada como argumento
     */
    public static String adicionaDia(final String data,
                                     final String anoBissexto) {
        int ano = getAnoAsInt(data);
        int mes = getMesAsInt(data);
        int dia = getDiaAsInt(data, anoBissexto);

        switch (mes) {
            case ABRIL:
            case JUNHO:
            case SETEMBRO:
            case NOVEMBRO:
                if (dia == ULTIMO_DIA_MES_NORMAL) {
                    dia = PRIMEIRO_DIA_MES;
                    mes = adicionaMes(mes);
                } else {
                    dia++;
                }
                break;
            case JANEIRO:
            case MARCO:
            case MAIO:
            case JULHO:
            case AGOSTO:
            case OUTUBRO:
                if (dia == ULTIMO_DIA_MES_LONGO) {
                    dia = PRIMEIRO_DIA_MES;
                    mes = adicionaMes(mes);
                } else {
                    dia++;
                }
                break;
            case DEZEMBRO:
                if (dia == ULTIMO_DIA_MES_LONGO) {
                    dia = PRIMEIRO_DIA_MES;
                    mes = adicionaMes(mes);
                    ano++;
                } else {
                    dia++;
                }
                break;
            case FEVEREIRO:
                if (isBissexto(anoBissexto, ano)) {
                    if (dia == ULTIMO_DIA_FEV_BISSEXTO) {
                        dia = PRIMEIRO_DIA_MES;
                        mes = adicionaMes(mes);
                    } else {
                        dia++;
                    }
                } else if (dia == ULTIMO_DIA_FEV) {
                    dia = PRIMEIRO_DIA_MES;
                    mes = adicionaMes(mes);
                } else {
                    dia++;
                }
            default:
                break;
        }

        return String.format("%04d%02d%02d", ano, mes, dia);
    }

    /**
     * Subtrai um dia à data passada como argumento.
     *
     * @param data        A data a ser subtraída
     * @param anoBissexto O ano bissexto de referência
     * @return A data anterior à data passada como argumento
     */
    private static String subtraiDia(final String data,
                                     final String anoBissexto) {
        int ano = getAnoAsInt(data);
        int mes = getMesAsInt(data);
        int dia = getDiaAsInt(data, anoBissexto);

        switch (mes) {

            case FEVEREIRO:
            case ABRIL:
            case JUNHO:
            case AGOSTO:
            case SETEMBRO:
            case NOVEMBRO:
                if (dia == PRIMEIRO_DIA_MES) {
                    dia = ULTIMO_DIA_MES_LONGO;
                    mes = subtraiMes(mes);
                } else {
                    dia--;
                }
                break;

            case MAIO:
            case JULHO:
            case OUTUBRO:
            case DEZEMBRO:
                if (dia == PRIMEIRO_DIA_MES) {
                    dia = ULTIMO_DIA_MES_NORMAL;
                    mes = subtraiMes(mes);
                } else {
                    dia--;
                }
                break;

            case JANEIRO:
                if (dia == PRIMEIRO_DIA_MES) {
                    dia = ULTIMO_DIA_MES_LONGO;
                    mes = subtraiMes(mes);
                    ano--;
                } else {
                    dia--;
                }
                break;

            case MARCO:
                if (dia == PRIMEIRO_DIA_MES) {
                    dia = isBissexto(anoBissexto, ano)
                            ? ULTIMO_DIA_FEV_BISSEXTO
                            : ULTIMO_DIA_FEV;
                    mes = subtraiMes(mes);
                } else {
                    dia--;
                }
            default:
                break;
        }

        return String.format("%04d%02d%02d", ano, mes, dia);
    }

    /**
     * Adiciona um mês ao mês passado como argumento.
     *
     * @param mes O mês a ser adicionado
     * @return O mes posterior ao mês passado como argumento
     */
    private static int adicionaMes(final int mes) {
        int mesAux = mes;
        if (mesAux == DEZEMBRO) {
            return JANEIRO;
        }

        return ++mesAux;
    }

    /**
     * Subtrai um mês ao mês passado como argumento.
     *
     * @param mes O mês a ser subtraído
     * @return O mes anterior ao mes passado como argumento
     */
    private static int subtraiMes(final int mes) {
        int mesAux = mes;
        if (mesAux == JANEIRO) {
            return DEZEMBRO;
        }

        return --mesAux;
    }

    /**
     * Adiciona um dia ao dia da semana passado como argumento.
     *
     * @param diaDaSemana O dia da semana a ser adicionado
     * @return O diaDaSemana posterior ao dia da semana passado como argumento
     */
    private static int adicionaDiaDaSemana(final int diaDaSemana) {
        int diaDaSemanaAux = diaDaSemana;
        if (diaDaSemanaAux == DOMINGO) {
            return SEGUNDA;
        }

        return ++diaDaSemanaAux;
    }

    /**
     * Subtrai um dia do dia da semana passado como argumento.
     *
     * @param diaDaSemana O dia da semana a ser subtraído
     * @return O diaDaSemana anterior ao dia da semana passado
     */
    private static int subtraiDiaDaSemana(final int diaDaSemana) {
        int diaDaSemanaAux = diaDaSemana;
        if (diaDaSemanaAux == SEGUNDA) {
            return DOMINGO;
        }
        return --diaDaSemanaAux;
    }

    /**
     * Avalia se um ano é bissexto dado um ano bissexto de referência.
     *
     * @param anoBissexto Ano bissexto de referência
     * @param anoAvaliado Ano a ser avaliado
     * @return true, se o ano avaliado é bissexto; false, caso não seja
     * @implNote Para atender os requisitos R15, R16
     */
    public static boolean isBissexto(final String anoBissexto,
                                     final int anoAvaliado) {
        final int anoBissextoInt = Integer.parseInt(anoBissexto);
        final int rangeAnoBissexto = 4;
        final int cem = 100;
        final int quatrocentos = 400;

        if ((anoBissextoInt - anoAvaliado) % rangeAnoBissexto == 0) {
            return !isMultiplo(anoAvaliado, cem)
                    || isMultiplo(anoAvaliado, quatrocentos);
        }

        return false;
    }

    /**
     * Avalia se dois números são múltiplos entre si.
     *
     * @param num1 O número1 a ser avaliado
     * @param num2 O número2 a ser avaliado
     * @return true, se os números são múltiplos; false, se os números
     * não são múltiplos
     */
    public static boolean isMultiplo(final int num1, final int num2) {
        return num1 % num2 == 0;
    }

}
