package com.logica.financeira.logica.financeira.ParcelasCompras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.text.ChoiceFormat.nextDouble;


public class ParcelasEmCompras {
    public void main(String[] args) {
        List<Integer> vezes = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            vezes.add(i);
        }
        Scanner parcela = new Scanner(System.in);
        int opcao = parcela.nextInt() ;
        double valor= parcela.nextDouble();

        if (vezes.contains(opcao)) {

          BigDecimal total = new BigDecimal(String.valueOf(valor));
          BigDecimal divisor = new BigDecimal(String.valueOf(opcao));

          BigDecimal valorParcela = total.divide(divisor,2, RoundingMode.HALF_DOWN);
            System.out.println(valorParcela);

        } else {
            System.out.println("Opção inválida!");

        }

    }
}