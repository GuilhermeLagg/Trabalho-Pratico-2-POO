package view;
import controller.Estoque;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class TelaMenu extends javax.swing.JFrame {
    private JPanel painel;
    private JButton sairButton;
    private JButton visualizarEstoqueButton;
    private JButton visualizarCarrinhoButton;
    private JButton pesquisarProdutoButton;
    private JButton finalizarCompraButton;
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
            // JOptionPane abre uma janela e o método showMessageDialog cria, especificamente, uma janela de mensagem
            JOptionPane.showMessageDialog(
                    TelaMenu.this, carrinhoInfo, "Itens no carrinho", JOptionPane.INFORMATION_MESSAGE
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
        adicionarProdutoAoCarrinhoButton.addActionListener(e -> {
            JTextField campoId = new JTextField();
            JTextField campoQuantidade = new JTextField();

            Object[] mensagem = {
                    "ID do produto:", campoId,
                    "Quantidade:", campoQuantidade
            };

            int op1 = JOptionPane.showConfirmDialog(
                    TelaMenu.this,
                    mensagem,
                    "Adicionar produto ao carrinho",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (op1 == JOptionPane.OK_OPTION) {
                try {
                    int id = Integer.parseInt(campoId.getText());
                    int quantidade = Integer.parseInt(campoQuantidade.getText());

                    String resultado = Estoque.adicionarAoCarrinho(id, quantidade);

                    JOptionPane.showMessageDialog(
                            TelaMenu.this,
                            resultado,
                            "Resultado",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            TelaMenu.this,
                            "ID e quantidade devem ser números!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        retirarProdutoDoCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField campoId = new JTextField();
                JTextField campoQuantidade = new JTextField();

                Object[] mens = {
                        "ID do produto:", campoId,
                        "Quantidade:", campoQuantidade
                };

                int op2 = JOptionPane.showConfirmDialog(
                        TelaMenu.this,
                        mens,
                        "Retirar produto do carrinho",
                        JOptionPane.OK_CANCEL_OPTION

                );

                if (op2 == JOptionPane.OK_OPTION) {
                    try {
                        int id = Integer.parseInt(campoId.getText());
                        int quantidade = Integer.parseInt(campoQuantidade.getText());

                        String resultado = Estoque.removerDoCarrinho(id, quantidade);

                        JOptionPane.showMessageDialog(
                                TelaMenu.this,
                                resultado,
                                "Resultado",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                TelaMenu.this,
                                "ID e quantidade devem ser números!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }

            ;
        });
        pesquisarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField campoId = new JTextField();


                Object[] mens = {
                        "ID do produto:", campoId

                };

                int op2 = JOptionPane.showConfirmDialog(
                        TelaMenu.this,
                        mens,
                        "Pesquisar produto no carrinho",
                        JOptionPane.OK_CANCEL_OPTION

                );
                if (op2 == JOptionPane.OK_OPTION) {
                    try {
                        int id = Integer.parseInt(campoId.getText());

                        String resultado = Estoque.pesquisarProduto(id);

                        JOptionPane.showMessageDialog(
                                TelaMenu.this,
                                resultado,
                                "Resultado",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                TelaMenu.this,
                                "ID deve ser um número!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }

            ;
        });
        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField campoNome = new JTextField();
                JTextField campoSenha = new JTextField();

                Object[] mensagem = {
                        "Nome de usuário:", campoNome,
                        "Senha do usuário:", campoSenha
                };

                int op1 = JOptionPane.showConfirmDialog(
                        TelaMenu.this,
                        mensagem,
                        "Confirma usuário",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (op1 == JOptionPane.OK_OPTION) {
                    try {

                        String nome = campoNome.getText();
                        int senha = Integer.parseInt(campoSenha.getText());
                        String resultado = Estoque.finalizarCompra(nome, senha);
                        JOptionPane.showMessageDialog(null, resultado);
                        String recibo = Estoque.visualizarEstoque();

                        JOptionPane.showMessageDialog(
                                TelaMenu.this,
                                recibo,
                                "Itens no estoque",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    });

                    }
                        catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(TelaMenu.this, "Senha deve ser numérica!");
                        }


            }
        };
    });
    }
        public static void main (String[]args){
            Estoque est = new Estoque();
            SwingUtilities.invokeLater(() -> new TelaMenu(est).setVisible(true));
        }

}