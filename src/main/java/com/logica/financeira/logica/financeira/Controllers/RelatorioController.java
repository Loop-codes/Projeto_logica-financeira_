package com.logica.financeira.logica.financeira.Controllers;

import com.logica.financeira.logica.financeira.Services.RelatorioService;
import com.logica.financeira.logica.financeira.Services.RelatorioService.ResumoCompleto;
import com.logica.financeira.logica.financeira.Services.RelatorioService.GastosPorCategoria;
import com.logica.financeira.logica.financeira.Services.RelatorioService.TopDespesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    // Endpoint para o resumo completo (Dashboard)
    @GetMapping("/mensal/{usuarioId}")
    public ResponseEntity<ResumoCompleto> getResumoMensal(
            @PathVariable Long usuarioId,
            @RequestParam int ano,
            @RequestParam int mes) {

        YearMonth anoMes = YearMonth.of(ano, mes);
        ResumoCompleto resumo = relatorioService.gerarResumoCompleto(usuarioId, anoMes);
        return ResponseEntity.ok(resumo);
    }

    // Endpoint específico apenas para gastos por categoria
    @GetMapping("/categorias/{usuarioId}")
    public ResponseEntity<List<GastosPorCategoria>> getGastosPorCategoria(
            @PathVariable Long usuarioId,
            @RequestParam int ano,
            @RequestParam int mes) {

        YearMonth anoMes = YearMonth.of(ano, mes);
        List<GastosPorCategoria> gastos = relatorioService.listarGastosPorCategoria(usuarioId, anoMes);
        return ResponseEntity.ok(gastos);
    }

    // Endpoint específico apenas para as top despesas
    @GetMapping("/top-despesas/{usuarioId}")
    public ResponseEntity<List<TopDespesa>> getTopDespesas(
            @PathVariable Long usuarioId,
            @RequestParam int ano,
            @RequestParam int mes) {

        YearMonth anoMes = YearMonth.of(ano, mes);
        List<TopDespesa> tops = relatorioService.listarTopDespesas(usuarioId, anoMes);
        return ResponseEntity.ok(tops);
    }

    // Endpoint para saber apenas o total gasto no mês
    @GetMapping("/total-gasto/{usuarioId}")
    public ResponseEntity<BigDecimal> getTotalGasto(
            @PathVariable Long usuarioId,
            @RequestParam int ano,
            @RequestParam int mes) {

        YearMonth anoMes = YearMonth.of(ano, mes);
        BigDecimal total = relatorioService.calcularTotalMensal(usuarioId, anoMes);
        return ResponseEntity.ok(total);
    }
}