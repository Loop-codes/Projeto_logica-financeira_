package com.logica.financeira.logica.financeira.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;


    @Builder
    @JsonPropertyOrder({"descricao","valor","data","tipoTransacao"})
    public record TransacaoDTO(
            String descricao,
            BigDecimal valor,
            LocalDate data,
            TipoTransacao tipoTransacao
            )

     {
    }
