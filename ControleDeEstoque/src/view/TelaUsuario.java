package view;

import controller.Gerenciador;
import javax.swing.*;
public class TelaUsuario extends javax.swing.JFrame {
    private JLabel tituloCadastro;
    private JLabel informeNome;
    private JPanel painelCadastro;
    private JTextField senha;
    private JTextField nome;
    private JButton cadastrarButton;
    private JLabel informeSenha;

    public static void main(String[] args) {
        Gerenciador ger = new Gerenciador();
        ger.iniciarPrograma();
    }
}