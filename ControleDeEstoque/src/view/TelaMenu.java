package view;
import controller.Estoque;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends javax.swing.JFrame {
    private JPanel painel;
    private JButton sairButton;
    private JButton visualizarEstoqueButton;
    private JButton visualizarCarrinhoButton;
    private JButton pesquisarProdutoButton;
    private JButton button5;
    private JButton adicionarProdutoAoCarrinhoButton;
    private JButton retirarProdutoDoCarrinhoButton;

    private Estoque estoque;

    public TelaMenu(Estoque estoque) {
        this.estoque = estoque;
        setContentPane(painel); // coloca o painel do form dentro do JFrame
        setTitle("Cadastro ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // ajusta ao conteúdo
        setLocationRelativeTo(null);

        visualizarCarrinhoButton.addActionListener(e -> {
            String carrinhoInfo = Estoque.imprimeCarrinho();

            JOptionPane.showMessageDialog(
                    TelaMenu.this,
                    carrinhoInfo,
                    "Itens no carrinho",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        visualizarEstoqueButton.addActionListener(e -> {
            String estoqueInfo = Estoque.visualizarEstoque();

            JOptionPane.showMessageDialog(
                    TelaMenu.this,
                    estoqueInfo,
                    "Itens no estoque",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        adicionarProdutoAoCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Estoque est = new Estoque();
        SwingUtilities.invokeLater(() -> new TelaMenu(est).setVisible(true));
    }
}
