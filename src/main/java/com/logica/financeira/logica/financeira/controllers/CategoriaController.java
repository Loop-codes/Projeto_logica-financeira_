package com.logica.financeira.logica.financeira.controllers;

import com.logica.financeira.logica.financeira.Services.CategoriaService;
import com.logica.financeira.logica.financeira.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tb_categoria")

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria){return categoriaService.create(categoria);}


    @GetMapping
    public List<Categoria> getAllCategoria(){return categoriaService.findAll();}

    @GetMapping("/{id}")
    public Categoria getCategoriabyid(@PathVariable Long id){
        return categoriaService.findById(id);
    }
    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Long id,@RequestBody Categoria categoriaDetails){
        return categoriaService.update(id,categoriaDetails);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id){categoriaService.delete(id);
    }



}
