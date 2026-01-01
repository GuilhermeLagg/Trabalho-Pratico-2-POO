package view;
import controller.Estoque;
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
    private Estoque estoque ;
    public TelaUsuario(Estoque estoque) {
        this.estoque = estoque;
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
                boolean sucesso = estoque.realizarCadastro(nomeUsuario, senhaInt);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                    TelaMenu telaMenu = new TelaMenu(estoque);
                    telaMenu.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário já existe!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Senha deve ser numérica!");
            }
        });
    }

    public static void main(String[] args) {
       Estoque est = new Estoque();
        SwingUtilities.invokeLater(() -> new TelaUsuario(est).setVisible(true));
    }
}