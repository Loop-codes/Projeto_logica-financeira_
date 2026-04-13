package com.logica.financeira.logica.financeira.FechamentosMensais;


import com.logica.financeira.logica.financeira.entities.TopDespesas;
import com.logica.financeira.logica.financeira.entities.Transacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FechamentoMensal {
        public void main(String [] args) {

            List<Transacao> fatura = new ArrayList<>();

            // TODO: Injetar TransacaoRepository fatura.addAll();



            BigDecimal total = service.calcularTotal(fatura);

            for (Transacao item : fatura) {
                total = total.add(item.getValorGasto());
            }
            System.out.println("FECHAMENTO MENSAL: R$ " + totalGeral);

            // 2. TOP DESPESAS (RANKING)
            System.out.println("--- TOP DESPESAS ---");
            List<Transacao> tops = service.buscarTopDespesas(fatura, 3); // Top 3

            for (Transacao t : tops) {
                System.out.println(t.getDescricao() + ": R$ " + t.getValor());
            }

           /* public static class Transacao {
                private BigDecimal valorGasto;

                public Transacao(BigDecimal valorGasto) {
                    this.valorGasto = valorGasto;
                }

                public BigDecimal getValorGasto() {
                    return valorGasto;
                }


            */

            }
                    }







