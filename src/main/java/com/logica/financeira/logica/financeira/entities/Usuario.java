package com.logica.financeira.logica.financeira.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private BigDecimal saldoAtual;

    public Usuario(Long id, String nome, BigDecimal saldoAtual) {
        this.id = id;
        this.nome = nome;
        this.saldoAtual = saldoAtual;
    }
}
