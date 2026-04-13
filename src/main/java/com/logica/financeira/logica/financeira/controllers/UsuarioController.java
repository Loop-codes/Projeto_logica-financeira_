package com.logica.financeira.logica.financeira.controllers;


import com.logica.financeira.logica.financeira.Services.UsuarioService;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tb_usuario")


public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){return usuarioService.create(usuario);}


    @GetMapping
    public List<Usuario> getAllUsuario(){return usuarioService.findAll();}

    @GetMapping("/{id}")
    public Usuario getUsuariobyid(@PathVariable Long id){
        return usuarioService.findById(id);
    }
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id,@RequestBody Usuario usuarioDetails){
        return usuarioService.update(id,usuarioDetails);
    }
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){usuarioService.delete(id);
    }



}
