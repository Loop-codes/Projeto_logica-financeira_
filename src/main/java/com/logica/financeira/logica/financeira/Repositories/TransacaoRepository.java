package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.Usuario;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface TransacaoRepository extends JpaRepository<Transacao,Long > {

    @Query("SELECT t.categoria, SUM(t.valor) FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = :tipo " +
            "AND t.data >= :inicio AND t.data <= :fim " +
            "GROUP BY t.categoria")
    List<Object[]> somarGastosPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );


@Query("SELECT SUM(t.valor) FROM Transacao t " +
        "WHERE t.usuario.id = :usuarioId " +
        "AND t.tipo = :tipo " +
        "AND t.data BETWEEN :inicio AND :fim")
BigDecimal somarGastosPorPeriodo(
        @Param("usuarioId") Long usuarioId,
        @Param("tipo") TipoTransacao tipo,
        @Param("inicio") LocalDate inicio,
        @Param("fim") LocalDate fim
);
    List<Transacao> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);
}