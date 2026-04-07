package com.logica.financeira.logica.financeira.Controllers;

import com.logica.financeira.logica.financeira.Services.ParcelamentoService;
import com.logica.financeira.logica.financeira.Services.UsuarioService;
import com.logica.financeira.logica.financeira.entities.Parcelamento;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tb_parcelamento")
public class ParcelamentoController {

        @Autowired
        private ParcelamentoService parcelamentoService;


        @PostMapping
        public Parcelamento createParcelamento(@RequestBody Parcelamento parcelamento){return parcelamentoService.create(parcelamento);}


        @GetMapping
        public List<Parcelamento> getAllParcelamento(){return parcelamentoService.findAll();}

        @GetMapping("/{id}")
        public Parcelamento getUsuariobyid(@PathVariable Long id){
            return parcelamentoService.findById(id);
        }
        @PutMapping("/{id}")
        public Parcelamento updateParcelamento(@PathVariable Long id,@RequestBody Parcelamento parcelamentoDetails){
            return parcelamentoService.update(id,parcelamentoDetails);
        }
        @DeleteMapping("/{id}")
        public void deleteParcelamento(@PathVariable Long id){parcelamentoService.delete(id);
        }



    }


