package com.logica.financeira.logica.financeira.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
@JsonPropertyOrder({"nome","TipoTransacao"})
public record CategoriaDTO(
        String nome,
        TipoTransacao tipo ) {

    }

