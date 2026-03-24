package com.logica.financeira.logica.financeira.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_parcelamento")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoCompromisso;

    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcela;

    @OneToMany
    List<Transacao> parcelas;
}