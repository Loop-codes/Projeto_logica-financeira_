package com.logica.financeira.logica.financeira.Controllers;

import com.logica.financeira.logica.financeira.Services.RelatorioService;
import com.logica.financeira.logica.financeira.Services.RelatorioService.ResumoCompleto; // Importa o Record interno
import com.logica.financeira.logica.financeira.Services.TransacaoService;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/tb_transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public Transacao createTransacao(@RequestBody Transacao transacao) {
        return transacaoService.create(transacao);
    }

    @GetMapping
    public List<Transacao> getAllTransacao() {
        return transacaoService.findAll();
    }

    @GetMapping("/resumo/{usuarioId}")
    public ResponseEntity<ResumoCompleto> getResumo(
            @PathVariable Long usuarioId,
            @RequestParam int ano,
            @RequestParam int mes) {

        YearMonth anoMes = YearMonth.of(ano, mes);

        // AJUSTADO: O nome do método deve ser gerarResumoCompleto para bater com seu Service
        ResumoCompleto resumo = relatorioService.gerarResumoCompleto(usuarioId, anoMes);

        return ResponseEntity.ok(resumo);
    }
}