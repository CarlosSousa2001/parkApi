package com.sistemacar.parkapi.controller;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Usuario usuario){
        String result = usuarioService.createUser(usuario);

        if (result.startsWith("Usuário criado")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }

    }
}
