package com.logica.financeira.logica.financeira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.Repositories.UsuarioRepository;
import com.logica.financeira.logica.financeira.Services.TransacaoService;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    private Usuario usuario;
    private Transacao transacaoReceita;
    private Transacao transacaoDespesa;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setSaldoAtual(new BigDecimal("100.00"));

        transacaoReceita = new Transacao();
        transacaoReceita.setValor(new BigDecimal("50.00"));
        transacaoReceita.setTipo(TipoTransacao.RECEITA);
        transacaoReceita.setUsuario(usuario);

        transacaoDespesa = new Transacao();
        transacaoDespesa.setValor(new BigDecimal("30.00"));
        transacaoDespesa.setTipo(TipoTransacao.DESPESA);
        transacaoDespesa.setUsuario(usuario);
    }

    @Test
    void deveAumentarOSaldoQuandoATransacaoForReceita() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacaoReceita);

        transacaoService.registrarTransacao(transacaoReceita);

        assertEquals(new BigDecimal("150.00"), usuario.getSaldoAtual());
    }

    @Test
    void deveDiminuirOSaldoQuandoATransacaoForDespesa() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacaoDespesa);

        transacaoService.registrarTransacao(transacaoDespesa);

        assertEquals(new BigDecimal("70.00"), usuario.getSaldoAtual());
    }
}