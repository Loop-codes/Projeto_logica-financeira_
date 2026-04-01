package com.logica.financeira.logica.financeira.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
    @JsonPropertyOrder({"nome","saldoAtual"})
    public record UsuarioDTO(
            String nome,
            BigDecimal saldoAtual

    ){

    }

