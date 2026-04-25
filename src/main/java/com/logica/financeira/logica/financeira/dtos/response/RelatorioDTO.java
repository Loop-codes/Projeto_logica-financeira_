package com.logica.financeira.logica.financeira.dtos.response;
import java.math.BigDecimal;
import java.util.List;
public class RelatorioDTO{

        public record GastosPorCategoria(String categoria, BigDecimal total) {}
        public record TopDespesa(String descricao, BigDecimal valor, String categoria) {}
        public record ResumoCompleto(
                BigDecimal gastoTotal,
                BigDecimal mediaSemanal,
                List<GastosPorCategoria> porCategoria,
                List<TopDespesa> topDespesas
        ) {}
    }

