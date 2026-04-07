package com.logica.financeira.logica.financeira.Controllers;

import com.logica.financeira.logica.financeira.Services.CategoriaService;
import com.logica.financeira.logica.financeira.Services.ParcelamentoService;
import com.logica.financeira.logica.financeira.Services.TransacaoService;
import com.logica.financeira.logica.financeira.entities.Categoria;
import com.logica.financeira.logica.financeira.entities.Parcelamento;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tb_transacao")
public class TransacaoController {

        @Autowired
        private TransacaoService transacaoService;


        @PostMapping
        public Transacao createTransacao(@RequestBody Transacao transacao){return transacaoService.create(transacao);}


        @GetMapping
        public List<Transacao> getAllTransacao(){return transacaoService.findAll();}

        @GetMapping("/{id}")
        public Transacao getTransacaobyid(@PathVariable Long id){
            return transacaoService.findById(id);
        }
        @PutMapping("/{id}")
        public Transacao updateTransacao(@PathVariable Long id,@RequestBody Transacao transacaoDetails){
            return transacaoService.update(id,transacaoDetails);
        }
        @DeleteMapping("/{id}")
        public void deleteTransao(@PathVariable Long id){transacaoService.delete(id);
        }



    }

