package com.logica.financeira.logica.financeira.Services;


import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class RelatorioService {

        private final TransacaoRepository transacaoRepository;

        public RelatorioService(TransacaoRepository transacaoRepository) {
            this.transacaoRepository = transacaoRepository;
        }

        public record GastosPorCategoria(String categoria, BigDecimal total) {}
        public record TopDespesa(String descricao, BigDecimal valor) {}
        public record ResumoCompleto(
                BigDecimal gastoTotal,
                BigDecimal mediaSemanal,
                List<GastosPorCategoria> porCategoria,
                List<TopDespesa> topDespesas
        ) {}

        public BigDecimal calcularTotalMensal(Long usuarioId, YearMonth anoMes) {
            BigDecimal total = transacaoRepository.somarGastosPorPeriodo(
                    usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth());
            return total != null ? total : BigDecimal.ZERO;
        }

        public BigDecimal calcularMediaSemanal(Long usuarioId, YearMonth anoMes) {
            BigDecimal totalMensal = calcularTotalMensal(usuarioId, anoMes);
            long semanas = ChronoUnit.WEEKS.between(anoMes.atDay(1), anoMes.atEndOfMonth()) + 1;
            return totalMensal.divide(new BigDecimal(semanas), 2, RoundingMode.HALF_UP);
        }

        public List<GastosPorCategoria> listarGastosPorCategoria(Long usuarioId, YearMonth anoMes) {
            List<Object[]> dados = transacaoRepository.somarGastosPorCategoria(
                    usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth());
            return dados.stream()
                    .map(d -> new GastosPorCategoria((String) d[0], (BigDecimal) d[1]))
                    .collect(Collectors.toList());
        }

        public List<TopDespesa> listarTopDespesas(Long usuarioId, YearMonth anoMes) {
            return transacaoRepository.buscarTopDespesas(
                            usuarioId, TipoTransacao.DESPESA, anoMes.atDay(1), anoMes.atEndOfMonth(),
                            (Pageable) PageRequest.of(0, 5))
                    .stream()
                    .map(t -> new TopDespesa(t.getDescricao(), t.getValor()))
                    .collect(Collectors.toList());
        }

        public ResumoCompleto gerarResumoCompleto(Long usuarioId, YearMonth anoMes) {
            return new ResumoCompleto(
                    calcularTotalMensal(usuarioId, anoMes),
                    calcularMediaSemanal(usuarioId, anoMes),
                    listarGastosPorCategoria(usuarioId, anoMes),
                    listarTopDespesas(usuarioId, anoMes)
            );
        }
    }
