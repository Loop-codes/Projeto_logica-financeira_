package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
