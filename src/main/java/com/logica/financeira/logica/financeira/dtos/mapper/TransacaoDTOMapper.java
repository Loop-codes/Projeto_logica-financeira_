package com.logica.financeira.logica.financeira.dtos.mapper;

import com.logica.financeira.logica.financeira.dtos.response.TransacaoDTO;
import com.logica.financeira.logica.financeira.dtos.response.UsuarioDTO;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TransacaoDTOMapper implements Function<Transacao,TransacaoDTO> {
        @Override
        public TransacaoDTO apply(Transacao transacao){
            return new TransacaoDTO(transacao.getDescricao(),
                    transacao.getValor(),
                    transacao.getData(),
                    transacao.getTipo());
        }
    }
