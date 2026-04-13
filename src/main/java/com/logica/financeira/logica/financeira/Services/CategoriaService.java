package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.CategoriaRepository;
import com.logica.financeira.logica.financeira.dtos.mapper.CategoriaDTOMapper;
import com.logica.financeira.logica.financeira.dtos.response.CategoriaDTO;
import com.logica.financeira.logica.financeira.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
    public class CategoriaService {
        @Autowired
        private CategoriaRepository categoriaRepository;
        @Autowired
        private CategoriaDTOMapper categoriaDTOMapper;

        public CategoriaDTO gfindByIdCategoriaDTO(Long id){ return categoriaRepository.findById(id)
                .map(categoriaDTOMapper)
                .orElseThrow(()->new RuntimeException("Usuario não encontrado" + id));
        }



        public Categoria create(Categoria categoria) {
            return categoriaRepository.save(categoria);
        }


        public List<Categoria> findAll () {
            return categoriaRepository.findAll();
        }

        public Categoria findById (Long id){
            return categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Categoria not finded"));
        }
        public Categoria update (Long id, Categoria categoriaDetails){
            Categoria categoria = findById((id));
            categoria.setNome(categoriaDetails.getNome());


            return categoriaRepository.save(categoria);
        }

        public List<CategoriaDTO> findAllDTO(){
            return categoriaRepository.findAll()
                    .stream()
                    .map(categoriaDTOMapper)
                    .collect(Collectors.toList());
        }

        public void delete (Long id){
            categoriaRepository.deleteById(id);
        }

    }


