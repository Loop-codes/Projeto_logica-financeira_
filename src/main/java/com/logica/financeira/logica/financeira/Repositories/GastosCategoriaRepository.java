package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface GastosCategoriaRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId " +
            "AND t.tipoTransacao = :tipo AND t.data BETWEEN :inicio AND :fim")
    BigDecimal somarGastosPorPeriodo(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") String tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}