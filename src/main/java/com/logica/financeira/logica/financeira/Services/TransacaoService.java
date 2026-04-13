package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.Repositories.UsuarioRepository;
import com.logica.financeira.logica.financeira.dtos.response.TopDespesaDTO;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        Usuario usuario = usuarioRepository.findById(transacao.getUsuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Agora reconhece getTipo() e compara com o Enum corretamente
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

        // Agora o Repository possui este método declarado
        return transacaoRepository.somarGastosPorPeriodo(
                usuarioId,
                TipoTransacao.DESPESA,
                inicioDoMes,
                fimDoMes
        );
    }

    public List<TopDespesaDTO> obterTopDespesasDoMes(Long usuarioId, YearMonth anoMes) {
        LocalDate inicio = anoMes.atDay(1);
        LocalDate fim = anoMes.atEndOfMonth();

        // Agora o Repository possui este método declarado
        List<Transacao> todas = transacaoRepository.findByUsuarioIdAndDataBetween(usuarioId, inicio, fim);

        return todas.stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .sorted(Comparator.comparing(Transacao::getValor).reversed())
                .limit(5)
                .map(t -> new TopDespesaDTO(t.getDescricao(), t.getValor()))
                .collect(Collectors.toList());
    }
}