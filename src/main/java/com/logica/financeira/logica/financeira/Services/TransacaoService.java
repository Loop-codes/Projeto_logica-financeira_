package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.CategoriaRepository;
import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.dtos.mapper.TransacaoDTOMapper;
import com.logica.financeira.logica.financeira.dtos.mapper.UsuarioDTOMapper;
import com.logica.financeira.logica.financeira.dtos.response.TransacaoDTO;
import com.logica.financeira.logica.financeira.dtos.response.UsuarioDTO;
import com.logica.financeira.logica.financeira.entities.Categoria;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

        @Autowired
        private TransacaoRepository transacaoRepository;
    @Autowired
    private TransacaoDTOMapper transacaoDTOMapper;


    public TransacaoDTO gfindByIdDTO(Long id){
        return transacaoRepository.findById(id)
                .map(transacaoDTOMapper)
                .orElseThrow(()->new RuntimeException("Usuario não encontrado" + id));
    }


    public List<TransacaoDTO> findAllDTO(){
        return transacaoRepository.findAll()
                .stream()
                .map(transacaoDTOMapper)
                .collect(Collectors.toList());
    }


        public Transacao create(Transacao categoria) {
            return transacaoRepository.save(categoria);
        }

        public List<Transacao> findAll () {
            return transacaoRepository.findAll();
        }

        public Transacao findById (Long id){
            return transacaoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Categoria not finded"));
        }
        public Transacao update (Long id, Transacao transacaoDetails){
            Transacao transacao = findById((id));
            transacao.setDescricao(transacaoDetails.getDescricao());
            transacao.setValor(transacaoDetails.getValor());
            transacao.setData(transacaoDetails.getData());
            transacao.setTipo(transacaoDetails.getTipo());


            return transacaoRepository.save(transacao);
        }
        public void delete (Long id){
            transacaoRepository.deleteById(id);
        }

    }




