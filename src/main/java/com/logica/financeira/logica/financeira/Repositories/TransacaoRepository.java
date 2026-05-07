package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);

    @Query("SELECT SUM(t.valor) FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = :tipo " +
            "AND t.data >= :inicio AND t.data <= :fim")
    BigDecimal somarGastosPorPeriodo(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    @Query("SELECT t.categoria.nome, SUM(t.valor) FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = :tipo " +
            "AND t.data >= :inicio AND t.data <= :fim " +
            "GROUP BY t.categoria.nome")
    List<Object[]> somarGastosPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    @Query("SELECT t FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = :tipo " +
            "AND t.data >= :inicio AND t.data <= :fim " +
            "ORDER BY t.valor DESC")
    List<Transacao> buscarTopDespesas(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim,
            Pageable pageable
    );
}