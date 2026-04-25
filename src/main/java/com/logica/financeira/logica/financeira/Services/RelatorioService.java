package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final TransacaoRepository transacaoRepository;

    public RelatorioService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    // --- DTOs (Records) para transferência de dados ---
    public record GastosPorCategoria(String categoria, BigDecimal total) {}
    public record TopDespesa(String descricao, BigDecimal valor, String categoria) {}
    public record ResumoCompleto(
            BigDecimal gastoTotal,
            BigDecimal mediaSemanal,
            List<GastosPorCategoria> porCategoria,
            List<TopDespesa> topDespesas
    ) {}

    // 1. Gasto Total por Mês
    public BigDecimal calcularTotalMensal(Long usuarioId, YearMonth anoMes) {
        BigDecimal total = transacaoRepository.somarGastosPorPeriodo(
                usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth());
        return total != null ? total : BigDecimal.ZERO;
    }

    // 2. Média Semanal
    public BigDecimal calcularMediaSemanal(Long usuarioId, YearMonth anoMes) {
        BigDecimal totalMensal = calcularTotalMensal(usuarioId, anoMes);
        return totalMensal.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP);
    }

    // 3. Gastos por Categoria
    public List<GastosPorCategoria> listarGastosPorCategoria(Long usuarioId, YearMonth anoMes) {
        List<Object[]> dados = transacaoRepository.somarGastosPorCategoria(
                usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth());

        return dados.stream()
                .map(d -> new GastosPorCategoria((String) d[0], (BigDecimal) d[1]))
                .collect(Collectors.toList());
    }

    // 4. Top Despesas do Mês
    public List<TopDespesa> listarTopDespesas(Long usuarioId, YearMonth anoMes) {
        return transacaoRepository.buscarTopDespesas(
                usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth(), PageRequest.of(0, 5));
    }

    // Método que agrega tudo em um único objeto (Útil para Dashboards)
    public ResumoCompleto gerarResumoCompleto(Long usuarioId, YearMonth anoMes) {
        return new ResumoCompleto(
                calcularTotalMensal(usuarioId, anoMes),
                calcularMediaSemanal(usuarioId, anoMes),
                listarGastosPorCategoria(usuarioId, anoMes),
                listarTopDespesas(usuarioId, anoMes)
        );
    }
}