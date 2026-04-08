package com.logica.financeira.logica.financeira.repository;

import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo AND t.data >= :inicio AND t.data <= :fim")
    BigDecimal somarGastosPorPeriodo(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}