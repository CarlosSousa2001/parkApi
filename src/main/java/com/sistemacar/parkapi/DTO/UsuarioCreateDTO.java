package com.sistemacar.parkapi.DTO;

import jakarta.persistence.Column;

import java.util.UUID;

public class UsuarioCreateDTO {

    private String username;
    private String password;

    public UsuarioCreateDTO(){}

    public UsuarioCreateDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
