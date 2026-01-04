package model;

public class Administrador implements UsuarioAutenticavel{
    private String login;
    private int senha;

    public Administrador (String login, int senha){
        this.login = login;
        this.senha = senha;
    }


    @Override
    public boolean autenticar(String nome, int senha) {
        return this.login.equalsIgnoreCase(nome) && this.senha == senha;
    }
}
