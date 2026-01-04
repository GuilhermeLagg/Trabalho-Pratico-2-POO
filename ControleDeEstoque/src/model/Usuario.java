package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements UsuarioAutenticavel{
    //criando a classe usuario pra poder cadastrar na hora do inicio do programa
    private String nome;
    private int senha;

    //criando uma lista de usuarios
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nome, int senha){
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome(){
        return nome;
    }

    public int getSenha(){
        return senha;
    }
    //cadastrando os usuarios somente com nome e senha

    public void setNome(String n){
        this.nome = n;
    }

    public void setSenha(int s){
        this.senha = s;
    }
    public static boolean cadastrarUsuario(String nome, int senha){
        //vamos verificar se já existe um usuario com o nome que voce esta tentando usar
        if (nomeJaExiste(nome)){
            System.out.println("Usuário já existe!");
            return false;
        }
        //se nao existir, adiciona a lista de usuarios
        usuarios.add(new Usuario(nome, senha));
        return true;
    }

    public static boolean confirmarUsuario(String confirmNome, int confirmSenha ){
        for(Usuario u : usuarios){
            if(u.autenticar(confirmNome, confirmSenha)){
                return true;
            }
        }
        return false;
    }
    //aqui é o metodo para fazer a validação de usuarios
    public static boolean nomeJaExiste(String nome) {
        for (Usuario u : usuarios) {
            if (u.nome.equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean autenticar(String nome, int senha){
        return this.nome.equalsIgnoreCase(nome) && this.senha == senha;
    }
}
