package com.logica.financeira.logica.financeira.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
@JsonPropertyOrder({"valorTotal","quantidadeParcelas","valorParcela"})

public record ParcelamentoDTO(
        BigDecimal valorTotal,
        Integer quantidadeParcelas,
        java.util.List<com.logica.financeira.logica.financeira.entities.Transacao> valorParcela)
{

}
