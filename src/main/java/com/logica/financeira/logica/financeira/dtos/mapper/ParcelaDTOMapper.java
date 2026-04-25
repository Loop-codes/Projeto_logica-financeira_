package com.logica.financeira.logica.financeira.dtos.mapper;

import com.logica.financeira.logica.financeira.dtos.response.ParcelamentoDTO;
import com.logica.financeira.logica.financeira.entities.Parcelamento;

import java.math.BigDecimal;

public class ParcelaDTOMapper {
    public ParcelamentoDTO apply(Parcelamento parcelamento) {
        return new ParcelamentoDTO(parcelamento.getValorTotal(),
                parcelamento.getQuantidadeParcelas(),
                (BigDecimal) parcelamento.getParcelas());

    }
}
