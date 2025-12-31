package controller;
import model.Usuario;
import javax.swing.JOptionPane;
import view.TelaUsuario;
public class Gerenciador {

    public boolean realizarCadastro(String nome, int senha){
        return Usuario.cadastrarUsuario(nome, senha);
    }

    }



