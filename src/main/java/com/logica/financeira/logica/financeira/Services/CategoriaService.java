package com.logica.financeira.logica.financeira.Services; // Ajustado para minúsculo

import com.logica.financeira.logica.financeira.dtos.mapper.CategoriaDTOMapper;
import com.logica.financeira.logica.financeira.dtos.response.CategoriaDTO;
import com.logica.financeira.logica.financeira.entities.Categoria;
import com.logica.financeira.logica.financeira.Repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaDTOMapper categoriaDTOMapper;

    // Construtor para injeção (melhor prática)
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaDTOMapper categoriaDTOMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaDTOMapper = categoriaDTOMapper;
    }

    // 1. Métodos de DTO (Para a API)

    public CategoriaDTO findByIdDTO(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaDTOMapper)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }

    public List<CategoriaDTO> findAllDTO() {
        // Correção: O findAll deve ser chamado no Repository, não no Mapper
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaDTOMapper)
                .collect(Collectors.toList());
    }

    // 2. Métodos de Entidade (Para uso interno/outros services)

    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }

    public Categoria update(Long id, Categoria categoriaDetails) {
        Categoria categoria = findById(id);
        categoria.setNome(categoriaDetails.getNome());
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        // Verificação de segurança antes de deletar
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Categoria inexistente.");
        }
        categoriaRepository.deleteById(id);
    }
}