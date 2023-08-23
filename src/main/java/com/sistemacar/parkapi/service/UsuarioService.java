package com.sistemacar.parkapi.service;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.enums.Role;
import com.sistemacar.parkapi.exception.EntityExistsException;
import com.sistemacar.parkapi.exception.UserNameUniqueViolationException;
import com.sistemacar.parkapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
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
        if (!passwordEncoder.matches(senhaAtual, user.getPassword())) {
            throw new RuntimeException("A senha incorreta");
        }
        user.setPassword(passwordEncoder.encode(novaSenha));
        return user;
    }
    @Transactional(readOnly = true) // apenas metodo de consulta
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário não encontrado"))
        );
    }

    public Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
