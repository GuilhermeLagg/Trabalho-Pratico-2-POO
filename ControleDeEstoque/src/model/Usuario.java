package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario implements UsuarioAutenticavel{
    //criando a classe usuario pra poder cadastrar na hora do inicio do programa
    private String nome;
    private int senha;
    private static Scanner sc = new Scanner(System.in);

    //criando uma lista de usuarios
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nome, int senha){
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome(){
        return nome;
    }

    //cadastrando os usuarios somente com nome e senha
    public static void cadastrarUsuario(){
        System.out.print("Informe o usuário: ");
        String nome = sc.nextLine();
        System.out.print("Informe a senha: ");
        int sen = sc.nextInt();

        //vamos verificar se já existe um usuario com o nome que voce esta tentando usar
        if (usuarioJaExiste(nome)){
            System.out.println("Usuário já existe!");
            return;
        }
        //se nao existir, adiciona a lista de usuarios
        usuarios.add(new Usuario(nome, sen));
    }

    //aqui é o metodo para fazer a validação de usuarios
    public static boolean usuarioJaExiste(String nome){
        for (Usuario u : usuarios){
            if (u.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

}
