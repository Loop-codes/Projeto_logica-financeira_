package com.logica.financeira.logica.financeira.dtos.mapper;


import com.logica.financeira.logica.financeira.dtos.response.ParcelamentoDTO;
import com.logica.financeira.logica.financeira.entities.Parcelamento;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ParcelamentoDTOMapper {
    @Override
    public ParcelamentoDTO apply(Parcelamento parcelamento) {
        return new ParcelamentoDTO(parcelamento.getValorTotal(),
                parcelamento.getQuantidadeParcelas(),
                parcelamento.getParcelas());

    }
}