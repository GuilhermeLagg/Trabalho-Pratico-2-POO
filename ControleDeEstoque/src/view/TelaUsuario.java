package view;
import controller.Gerenciador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUsuario extends javax.swing.JFrame {
    private javax.swing.JLabel tituloCadastro;
    private javax.swing.JLabel informeNome;
    private javax.swing.JPanel painelCadastro;
    private javax.swing.JTextField nome;
    private javax.swing.JButton cadastrarButton;
    private javax.swing.JLabel informeSenha;
    private javax.swing.JPasswordField senha;
    private Gerenciador gerenciador;
    public TelaUsuario(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
        setContentPane(painelCadastro); // coloca o painel do form dentro do JFrame
        setTitle("Cadastro ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // ajusta ao conteúdo
        setLocationRelativeTo(null); // centraliza na tela

        cadastrarButton.addActionListener(e -> {
            String nomeUsuario = nome.getText();
            String senhaUsuario = new String(senha.getPassword());

            try {
                int senhaInt = Integer.parseInt(senhaUsuario);
                boolean sucesso = gerenciador.realizarCadastro(nomeUsuario, senhaInt);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário já existe!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Senha deve ser numérica!");
            }
        });
    }

    public static void main(String[] args) {
        Gerenciador ger = new Gerenciador();
        SwingUtilities.invokeLater(() -> new TelaUsuario(ger).setVisible(true));
    }
    }



