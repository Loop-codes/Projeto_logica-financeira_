package com.logica.financeira.logica.financeira.dtos.mapper;

import com.logica.financeira.logica.financeira.dtos.response.CategoriaDTO;
import com.logica.financeira.logica.financeira.entities.Categoria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategoriaDTOMapper implements Function<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO apply(Categoria categoria) {
        return new CategoriaDTO(categoria.getNome(),
                categoria.getTipo()
            );
    }

}
