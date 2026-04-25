package com.logica.financeira.logica.financeira.Repositories;

import com.logica.financeira.logica.financeira.Services.RelatorioService;
import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import com.logica.financeira.logica.financeira.Services.RelatorioService.TopDespesa; // Import do Record do Service
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // 1. Soma o total geral (Corrigido o nome do parâmetro para 'tipo')
    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo AND t.data BETWEEN :inicio AND :fim")
    BigDecimal somarGastosPorPeriodo(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    // 2. Soma por categoria (Retorna Object[] para o Service mapear)
    @Query("SELECT t.categoria.nome, SUM(t.valor) FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo " +
            "AND t.data BETWEEN :inicio AND :fim " +
            "GROUP BY t.categoria.nome")
    List<Object[]> somarGastosPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    // 3. Busca Top Despesas (Usando o construtor do Record diretamente na Query para evitar erros)
    @Query("SELECT new com.logica.financeira.logica.financeira.Services.RelatorioService$TopDespesa(t.descricao, t.valor, t.categoria.nome) " +
            "FROM Transacao t " +
            "WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo " +
            "AND t.data BETWEEN :inicio AND :fim " +
            "ORDER BY t.valor DESC")
    List<RelatorioService.TopDespesa> buscarTopDespesas(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoTransacao tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim,
            Pageable pageable
    );
}