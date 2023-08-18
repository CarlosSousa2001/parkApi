package com.sistemacar.parkapi.service;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario findById(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            return  usuario;
        }
        return null;
    }
    @Transactional(readOnly = true) // apenas metodo de consulta
    public String createUser(Usuario usuario){
        if(usuario != null) {
            usuarioRepository.save(usuario);
            return "Usuário criado com sucesso";
        }
        return "Erro ao criar usuário";
    }
}
