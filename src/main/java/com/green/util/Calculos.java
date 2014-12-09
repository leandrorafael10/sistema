/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leandro.silva
 */
public class Calculos {

    /*
     * calculo de prorata informando o inicio e o fim do periodo  mais o valor que sera calculado
     * retorna um map com a chave que e o valor que e o valor da parcela do mes referente
     * lembrando que este metodo esta quando nao informado o dia do vencimento ira fixar o vencimento
     * sempre para todo dia 10 de cada mês
     * 
     */
    @SuppressWarnings({ "rawtypes", "deprecation" })
	public static Map<Date, BigDecimal> calculaProRata(Date inicio, Date fim, BigDecimal valor, Integer dia) {
        Map<Date, BigDecimal> map = new HashMap<>();
        Calendar dataInicio = Calendar.getInstance();
        Calendar dataFim = Calendar.getInstance();

        dataInicio.setTime(inicio);
        dataFim.setTime(fim);

        int ultimoDiaMesInicio = dataInicio.getActualMaximum(Calendar.DAY_OF_MONTH);//ultimo dia do mes de inicio
        int ultimoDiaMesFim = dataFim.getActualMaximum(Calendar.DAY_OF_MONTH); //ultimo dia do mes de termino
        int prorataInicio = (ultimoDiaMesInicio - dataInicio.get(Calendar.DAY_OF_MONTH)) + 1;// quantos dias serão cobrados do mês de inicio
        int prorataFim = dataFim.get(Calendar.DAY_OF_MONTH);//quantos dias seão cobrados do mês de termino
        //int ultimoMes = dataFim.get(Calendar.MONTH) + 1;
        if (dia != null) {
            dataInicio.set(Calendar.DAY_OF_MONTH, dia);
            dataFim.set(Calendar.DAY_OF_MONTH, dia);
        }

        long result;
        result = fim.getTime() - inicio.getTime();
        int numDias = (int) ((((result / 1000) / 60) / 60) / 24) + 1; //calcular numero total de dias do periodo

        if (numDias <= 31) { //menos de 31 dias pegara o valor e dividira se for em dois meses  e se o periodo for dentro do mesmo mês ira gerar apenas uma parcela com o valor integral
            if (dataInicio.get(Calendar.MONTH) == dataFim.get(Calendar.MONTH) && dataInicio.get(Calendar.YEAR) == dataInicio.get(Calendar.YEAR)) {
                dataInicio.add(Calendar.MONTH, +1);
                map.put(dataInicio.getTime(), valor);
                return map;
            } else {
                BigDecimal valorDia = valor.divide(new BigDecimal(numDias), 2, RoundingMode.UP);
                BigDecimal valorPrimeira = valorDia.multiply(new BigDecimal(prorataInicio));
                BigDecimal valorSegunda = valor.subtract(valorPrimeira);
                dataInicio.add(Calendar.MONTH, +1);
                map.put(dataInicio.getTime(), valorPrimeira);
                dataInicio.add(Calendar.MONTH, +1);
                Date dFim = dataInicio.getTime();
                map.put(dFim, valorSegunda);
                return map;

            }
        } else {
            BigDecimal primeiraParcela = valor.divide(new BigDecimal(ultimoDiaMesInicio), 2, RoundingMode.UP);
            primeiraParcela = primeiraParcela.multiply(new BigDecimal(prorataInicio));//calculo do valor da pro-rata da primeira parcela

            dataInicio.add(Calendar.MONTH, +1);
            map.put(dataInicio.getTime(), primeiraParcela);//salvando mês evalor da primeira parcela
            // dataFim.roll(Calendar.MONTH, false);
            while (dataInicio.before(dataFim)) {//laço para gera as parcelas intermediarias ao periodo.
                if (dataInicio.get(Calendar.MONTH) == dataFim.get(Calendar.MONTH) && dataInicio.get(Calendar.YEAR) == dataFim.get(Calendar.YEAR)) {
                    break;
                } else {
                    Date dfim = new Date();
                    dataInicio.add(Calendar.MONTH, +1);
                    dfim = dataInicio.getTime();
                    map.put(dfim, valor);
                }

            }
            BigDecimal ultimaParcela = valor.divide(new BigDecimal(ultimoDiaMesFim), 2, RoundingMode.UP).multiply(new BigDecimal(prorataFim));
            dataInicio.add(Calendar.MONTH, +1);
            Date d = new Date();
            d = dataInicio.getTime();
            map.put(d, ultimaParcela);//calculo e guardando mês e valor pro-rata da ultima parcela 
            for (Map.Entry entry : map.entrySet()) {
                Date dt = (Date) entry.getKey();
                System.out.println("Data = " + dt.toGMTString() + "Valor =" + entry.getValue());
            }
            return map;

        }

    }
}
