package com.logica.financeira.logica.financeira.Controllers; // 'c' minúsculo

import com.logica.financeira.logica.financeira.Services.TransacaoService;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.dtos.response.TopDespesaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/transacoes") // Nome mais limpo que 'tb_transacao'
public class TransacaoController {

    private final TransacaoService transacaoService;

    // Injeção via construtor (melhor prática que @Autowired)
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    // 1. Criar transação (Usa o método que atualiza saldo no Service)
    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody Transacao transacao) {
        Transacao novaTransacao = transacaoService.registrarTransacao(transacao);
        return ResponseEntity.ok(novaTransacao);
    }

    // 2. Ranking de Despesas do Mês
    @GetMapping("/ranking")
    public ResponseEntity<List<TopDespesaDTO>> getRanking(
            @RequestParam Long usuarioId,
            @RequestParam String mesAno) { // Ex: "2026-04"

        YearMonth ym = YearMonth.parse(mesAno);
        List<TopDespesaDTO> ranking = transacaoService.obterTopDespesasDoMes(usuarioId, ym);
        return ResponseEntity.ok(ranking);
    }

    // 3. Total de Gastos do Mês
    @GetMapping("/total-gastos")
    public ResponseEntity<BigDecimal> getTotalGastos(
            @RequestParam Long usuarioId,
            @RequestParam String mesAno) {

        YearMonth ym = YearMonth.parse(mesAno);
        BigDecimal total = transacaoService.calcularGastosPorMes(usuarioId, ym);
        return ResponseEntity.ok(total);
    }

    /* Nota: Adicione os métodos findAll, findById e delete no seu TransacaoService
       se desejar utilizá-los aqui, pois no Service anterior focamos na lógica de saldo.
    */
}