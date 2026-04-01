package com.logica.financeira.logica.financeira.dtos.mapper;

import com.logica.financeira.logica.financeira.dtos.response.UsuarioDTO;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.stereotype.Service;


import java.util.function.Function;

@Service
public class UsuarioDTOMapper implements Function<Usuario, UsuarioDTO> {
    @Override
    public UsuarioDTO apply(Usuario  usuario){
        return new UsuarioDTO(usuario.getNome(),
                usuario.getSaldoAtual());
    }
}
