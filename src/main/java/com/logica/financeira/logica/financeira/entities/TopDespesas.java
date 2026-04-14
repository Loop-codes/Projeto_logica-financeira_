package com.logica.financeira.logica.financeira.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode
@Table(name="tb_despesas")
@Getter
@Setter
@NoArgsConstructor
public class TopDespesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private Long id;
    private String despesa;
    private BigDecimal valor;


    public TopDespesas(Long id,String despesa,BigDecimal valor){
        this.id=id;
        this.despesa=despesa;
        this.valor=valor;

    }

}
