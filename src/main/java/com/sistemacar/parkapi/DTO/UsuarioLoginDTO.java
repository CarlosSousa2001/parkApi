package com.sistemacar.parkapi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDTO {

    //@NotEmpty // se estiver vazio ou estiver null;
    @NotBlank // verifica se é null, se esta vazio, se possui ao menos 1 caractere
    @Email(message = "Formato do e-mail está inválido")
    @Size(max = 60)
    private String username;
    @NotBlank
    @Size(min = 6, max = 80)
    private String password;

    public UsuarioLoginDTO(){}

    public UsuarioLoginDTO(String username, String password) {
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
