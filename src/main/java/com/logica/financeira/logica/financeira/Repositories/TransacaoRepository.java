package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long > {
}
