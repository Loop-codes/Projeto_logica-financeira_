package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.Repositories.UsuarioRepository;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Transacao registrarTransacao(Transacao transacao) {

        Usuario usuario = usuarioRepository.findById(transacao.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (transacao.getTipo() == TipoTransacao.RECEITA) {
            usuario.setSaldoAtual(usuario.getSaldoAtual().add(transacao.getValor()));

        } else if (transacao.getTipo() == TipoTransacao.DESPESA) {
            usuario.setSaldoAtual(usuario.getSaldoAtual().subtract(transacao.getValor()));
        }

        usuarioRepository.save(usuario);

        return transacaoRepository.save(transacao);
    }

    public BigDecimal calcularGastosPorMes(Long usuarioId, YearMonth anoMes) {
        LocalDate inicioDoMes = anoMes.atDay(1);
        LocalDate fimDoMes = anoMes.atEndOfMonth();

        BigDecimal totalGastos = transacaoRepository.somarGastosPorPeriodo(
                usuarioId,
                TipoTransacao.DESPESA,
                inicioDoMes,
                fimDoMes
        );

        return totalGastos != null ? totalGastos : BigDecimal.ZERO;
    }

    public BigDecimal calcularMediaSemanal(Long usuarioId, YearMonth anoMes) {
        BigDecimal gastosDoMes = calcularGastosPorMes(usuarioId, anoMes);

        if (gastosDoMes.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return gastosDoMes.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP);
    }
}
