package com.logica.financeira.logica.financeira.services;

import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.TipoTransacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import com.logica.financeira.logica.financeira.repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Usuario usuario = usuarioRepository.findById(transacao.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (transacao.getTipo() == TipoTransacao.RECEITA) {
            usuario.setSaldoAtual(usuario.getSaldoAtual().add(transacao.getValor()));

        } else if (transacao.getTipo() == TipoTransacao.DESPESA) {
            usuario.setSaldoAtual(usuario.getSaldoAtual().subtract(transacao.getValor()));
        }

        usuarioRepository.save(usuario);

        return transacaoRepository.save(transacao);
    }
}
