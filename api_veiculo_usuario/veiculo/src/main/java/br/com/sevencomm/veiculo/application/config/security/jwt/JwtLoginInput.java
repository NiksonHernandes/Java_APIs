package br.com.sevencomm.veiculo.application.config.security.jwt;

public class JwtLoginInput {
    private String username;
    private String password;

    public JwtLoginInput(){}

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
