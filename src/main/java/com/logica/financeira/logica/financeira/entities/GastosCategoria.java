package com.logica.financeira.logica.financeira.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "tb_gastoscategoria")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class GastosCategoria {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@EqualsAndHashCode.Include
    private Long id;

    private String categoria;
    private BigDecimal total;

    public GastosCategoria(Long id, String categoria, BigDecimal total) {
        this.id = id;
        this.categoria = categoria;
        this.total = total;
    }
}
