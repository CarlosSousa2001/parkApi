package com.sistemacar.parkapi.controller;

import com.sistemacar.parkapi.DTO.UsuarioCreateDTO;
import com.sistemacar.parkapi.DTO.UsuarioResponseDTO;
import com.sistemacar.parkapi.DTO.UsuarioSenhaDTO;
import com.sistemacar.parkapi.DTO.mapper.UsuarioMapper;
import com.sistemacar.parkapi.entity.Usuario;
import com.sistemacar.parkapi.exception.ErrorMessage;
import com.sistemacar.parkapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "usuarios", description = "Contem todas as operções sobre criar buscar e editar usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<Usuario> users = usuarioService.findAll();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
    @Operation(summary = "Buscar usuario por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recuso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable UUID id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok().body(UsuarioMapper.toDto(usuario));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Criar um novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
                    @ApiResponse(responseCode = "409", description = "Usuario já existe",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> createUser(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario user = usuarioService.createUser(UsuarioMapper.toUsuario(usuarioCreateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updatePassword(@Valid @PathVariable UUID id, @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) {
        Usuario user = usuarioService.updatePassword(id, usuarioSenhaDTO.getSenhaAtual(), usuarioSenhaDTO.getNovaSenha(), usuarioSenhaDTO.getConfirmarSenha());
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }
}
