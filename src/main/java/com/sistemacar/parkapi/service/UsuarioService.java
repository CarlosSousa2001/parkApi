package com.sistemacar.parkapi.service;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.exception.EntityExistsException;
import com.sistemacar.parkapi.exception.UserNameUniqueViolationException;
import com.sistemacar.parkapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional(readOnly = true) // apenas metodo de consulta
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario;
        }
        throw new EntityExistsException("Usuário não encontrado");
    }

    @Transactional
    public Usuario createUser(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new UserNameUniqueViolationException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional
    public Usuario updatePassword(UUID id, String senhaAtual, String novaSenha, String confirmarSenha) {
        if (!novaSenha.equals(confirmarSenha)) {
            throw new RuntimeException("A senha precisa ser igual");
        }
        Usuario user = findById(id);
        if (!senhaAtual.equals(user.getPassword())) {
            throw new RuntimeException("A senha incorreta");
        }
        user.setPassword(novaSenha);
        return user;
    }
}
