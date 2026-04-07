package com.logica.financeira.logica.financeira.Services;

import com.logica.financeira.logica.financeira.Repositories.UsuarioRepository;
import com.logica.financeira.logica.financeira.dtos.mapper.UsuarioDTOMapper;
import com.logica.financeira.logica.financeira.dtos.response.UsuarioDTO;
import com.logica.financeira.logica.financeira.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
    public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDTOMapper usuarioDTOMapper;


    public UsuarioDTO gfindByIdDTO(Long id){
        return usuarioRepository.findById(id)
                .map(usuarioDTOMapper)
                .orElseThrow(()->new RuntimeException("Usuario não encontrado" + id));
    }


    public List<UsuarioDTO> findAllDTO(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioDTOMapper)
                .collect(Collectors.toList());
    }


    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

        public List<Usuario> findAll () {
            return usuarioRepository.findAll();

        }

        public Usuario findById (Long id){
            return usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not finded"));
        }
        public Usuario update (Long id, Usuario usuarioDetails){
            Usuario usuario = findById((id));
            usuario.setNome(usuarioDetails.getNome());
            usuario.setSaldoAtual(usuarioDetails.getSaldoAtual());

            return usuarioRepository.save(usuario);
        }
        public void delete (Long id){
            usuarioRepository.deleteById(id);
        }

    }
