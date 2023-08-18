package com.sistemacar.parkapi.controller;

import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ResponseEntity<List<Usuario>> findAll(){
        List list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
