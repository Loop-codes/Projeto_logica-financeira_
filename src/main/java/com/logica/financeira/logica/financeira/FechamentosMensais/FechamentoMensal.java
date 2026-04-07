package com.logica.financeira.logica.financeira.FechamentosMensais;


import com.logica.financeira.logica.financeira.entities.Transacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FechamentoMensal {
        public void main(String [] args) {

            List<Transacao> fatura = new ArrayList<>();

            fatura.addAll();/*<---- Adicionar o caminho do banco aqui*/



            BigDecimal total = BigDecimal.ZERO;

            for (Transacao item : fatura) {
                total = total.add(item.getValorGasto());
            }
                System.out.println(total);
            }

            public static class Transacao {
                private BigDecimal valorGasto;

                public Transacao(BigDecimal valorGasto) {
                    this.valorGasto = valorGasto;
                }

                public BigDecimal getValorGasto() {
                    return valorGasto;
                }


            }


        }
