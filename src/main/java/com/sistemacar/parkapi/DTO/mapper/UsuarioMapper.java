package com.sistemacar.parkapi.DTO.mapper;

import com.sistemacar.parkapi.DTO.UsuarioCreateDTO;
import com.sistemacar.parkapi.DTO.UsuarioResponseDTO;
import com.sistemacar.parkapi.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO){
        return new ModelMapper().map(usuarioCreateDTO, Usuario.class);
    }
    public static UsuarioResponseDTO toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }
    public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios){
        return usuarios.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());
    }
}
