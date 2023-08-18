package com.sistemacar.parkapi.service;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String createUser(Usuario usuario){
        if(usuario != null) {
            usuarioRepository.save(usuario);
            return "Usuário criado com sucesso";
        }
        return "Erro ao criar usuário";
    }
}
