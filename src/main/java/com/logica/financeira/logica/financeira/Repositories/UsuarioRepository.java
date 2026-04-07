package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long > {

}
