package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.CategoriaRepository;
import com.logica.financeira.logica.financeira.Repositories.ParcelamentoRepository;
import com.logica.financeira.logica.financeira.dtos.mapper.ParcelamentoDTOMapper;
import com.logica.financeira.logica.financeira.entities.Categoria;
import com.logica.financeira.logica.financeira.entities.Parcelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParcelamentoService {


    @Autowired
    private ParcelamentoRepository parcelamentoRepository;
    @Autowired
    private ParcelamentoDTOMapper parcelamentoDTOMapper;

    public Parcelamento create(Parcelamento parcelamento) {
        return parcelamentoRepository.save(parcelamento);
    }

    public List<Parcelamento> findAll () {
        return parcelamentoRepository.findAll();
    }

    public Parcelamento findById (Long id){
        return parcelamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria not finded"));
    }
    public Parcelamento update (Long id, Parcelamento parcelamentoDetails){
        Parcelamento parcelamento = findById((id));
        parcelamento.setValorParcela(parcelamentoDetails.getValorParcela());
        parcelamento.setQuantidadeParcelas(parcelamentoDetails.getQuantidadeParcelas());
        parcelamento.setValorTotal(parcelamentoDetails.getValorTotal());


        return parcelamentoRepository.save(parcelamento);
    }
    public void delete (Long id){
        parcelamentoRepository.deleteById(id);
    }

}



