package com.logica.financeira.logica.financeira.dtos.mapper;


import com.logica.financeira.logica.financeira.dtos.response.ParcelamentoDTO;
import com.logica.financeira.logica.financeira.entities.Parcelamento;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

@Service
public class ParcelamentoDTOMapper implements Function<Parcelamento, ParcelamentoDTO> {

    @Override
    public ParcelamentoDTO apply(Parcelamento parcelamento) {
        BigDecimal valorDaParcela = parcelamento.getValorTotal()
                .divide(new BigDecimal(parcelamento.getQuantidadeParcelas()), 2, RoundingMode.HALF_UP);
        return new ParcelamentoDTO(
                parcelamento.getValorTotal(),
                parcelamento.getQuantidadeParcelas(),
                valorDaParcela);
    }
}