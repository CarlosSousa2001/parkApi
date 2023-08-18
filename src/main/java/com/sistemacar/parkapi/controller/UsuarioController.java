package com.sistemacar.parkapi.controller;

import com.sistemacar.parkapi.DTO.UsuarioCreateDTO;
import com.sistemacar.parkapi.DTO.UsuarioResponseDTO;
import com.sistemacar.parkapi.DTO.mapper.UsuarioMapper;
import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable UUID id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok().body(UsuarioMapper.toDto(usuario));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> createUser(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario user = usuarioService.createUser(UsuarioMapper.toUsuario(usuarioCreateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable UUID id, @RequestBody Usuario usuario) {
        Usuario user = usuarioService.updatePassword(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }
}
