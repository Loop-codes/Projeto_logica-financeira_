package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // Busca todas as transações de um usuário em um período específico
    List<Transacao> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);
}