package br.com.sevencomm.ranking.application.config.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtLoginInput {

    private String username;
    private String senha;

    public JwtLoginInput(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
