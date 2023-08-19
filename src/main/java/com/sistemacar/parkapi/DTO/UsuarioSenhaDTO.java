package com.sistemacar.parkapi.DTO;

public class UsuarioSenhaDTO {

    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;

    public  UsuarioSenhaDTO(){}

    public UsuarioSenhaDTO(String senhaAtual, String novaSenha, String confirmarSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmarSenha = confirmarSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
