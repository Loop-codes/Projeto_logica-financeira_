package com.logica.financeira.logica.financeira.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;


    @Builder
    @JsonPropertyOrder({"descricao","valor","data","tipo"})
    public record TransacaoDTO(
            String descricao,
            BigDecimal valor,
            LocalDate data,
            com.logica.financeira.logica.financeira.entities.enums.TipoTransacao tipo
            )

     {
    }
