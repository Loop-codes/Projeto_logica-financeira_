package com.logica.financeira.logica.financeira.entities;

import com.logica.financeira.logica.financeira.entities.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    public Categoria(Long id, String nome, TipoTransacao tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }
}