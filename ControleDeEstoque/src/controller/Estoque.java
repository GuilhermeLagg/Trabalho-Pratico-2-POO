package controller;

import model.*;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
    //Vamos criar uma lista de produtos em estoque (produtos esses que não vao mudar. São os produtos disponíveis pra compra).
    //essa lista tem que ser iniciada carregando o nosso estoque está no arquivo estoque.txt;

   private static List<Produto> produtos = GerenciadorArquivos.carregarEstoque();
    //Essa outra lista é o nosso carrinho. Vamos add os produtos ao carrinho pra que possamos comprá-los.
    private static List<ItemCarrinho> carrinho = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Variável responsável por armazenar o valor total dos produtos no carrinho (N sei se seria a melhor forma de fazer isso)
    private static double totalCarrinho;
    public static String visualizarEstoque(){
        StringBuilder sb = new StringBuilder();
        if (produtos.isEmpty()){
            sb.append("Estoque vazio ou arquivo não encontrado");

        }
        else {
            //mostrando os produtos em estoque
            sb.append("Produtos em Estoque");
            for (Produto p : produtos) {
                sb.append("**************************");
                sb.append(p);
                sb.append("**************************");
            }
        }
        return sb.toString();
    }

    //metodo para adicionar os produtos no carrinho através do id
    public static String adicionarAoCarrinho(int id, int qtde) {
        try {
            Produto produtoEstoque = null;
            for (Produto p : produtos) {
                if (p.getId() == id) {
                    produtoEstoque = p;
                    break;
                }
            }

            if (produtoEstoque == null) {
                return "Produto não encontrado!";
            }

            int qtdeNoCarrinho = 0;
            ItemCarrinho itemExistente = null;
            for (ItemCarrinho item : carrinho) {
                if (item.getProduto().getId() == id) {
                    qtdeNoCarrinho = item.getQuantidadeCarrinho();
                    itemExistente = item;
                    break;
                }
            }

            if ((qtdeNoCarrinho + qtde) > produtoEstoque.getQuantidadeProduto()) {
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente! Você já tem " + qtdeNoCarrinho +
                                " no carrinho e o estoque total é " + produtoEstoque.getQuantidadeProduto()
                );
            }

            if (itemExistente != null) {
                itemExistente.adicionarProduto(qtde);
            } else {
                carrinho.add(new ItemCarrinho(produtoEstoque, qtde));
            }

            totalCarrinho += produtoEstoque.getPreco() * qtde;
            return qtde + " unidades de " + produtoEstoque.getNome() + " adicionadas ao carrinho!";
        } catch (EstoqueInsuficienteException e) {
            return "Erro no pedido! " + e.getMessage();
        }
    }

    public static String removerDoCarrinho(int id, int qtde) {
        // procurar item no carrinho
        ItemCarrinho itemCarrinho = null;
        for (ItemCarrinho item : carrinho) {
            if (item.getProduto().getId() == id) {
                itemCarrinho = item;
                break;
            }
        }

        // se não encontrou
        if (itemCarrinho == null) {
            return "Produto não encontrado no carrinho";
        }

        // lógica de remoção
        if (itemCarrinho.getQuantidadeCarrinho() == qtde) {
            carrinho.remove(itemCarrinho);
            totalCarrinho -= itemCarrinho.produtoValorCarrinho();
            return "Produto removido com sucesso";
        } else if (itemCarrinho.getQuantidadeCarrinho() > qtde) {
            itemCarrinho.setQuantidadeCarrinho(itemCarrinho.getQuantidadeCarrinho() - qtde);
            totalCarrinho -= itemCarrinho.getProduto().getPreco() * qtde;
            return "Quantidade removida com sucesso";
        } else {
            return "Você não possui essa quantidade no carrinho.";
        }
    }

    public static String imprimeCarrinho() {
        StringBuilder sb = new StringBuilder();
        // formata
        if (Estoque.carrinhoVazio()) {
            sb.append("O carrinho está vazio! Adicione itens ao carrinho para poder visualizar.\n");

        } else {
            sb.append("PRODUTOS NO CARRINHO \n");
            for (ItemCarrinho item : carrinho) {
                sb.append("Produto: ")
                        .append(item.getProduto().getNome())
                        .append(" | Quantidade: ")
                        .append(item.getQuantidadeCarrinho())
                        .append(" | ID: ")
                        .append(item.getProduto().getId())
                        .append("\n\n");
            }
            sb.append(String.format("Subtotal no carrinho: R$%.2f%n", totalCarrinho));

        }

        return sb.toString(); // retorna a String completa
    }


    public static String pesquisarProduto(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return "Produto encontrado:\n" +
                        "Nome: " + p.getNome() +
                        " | Quantidade: " + p.getQuantidadeProduto() +
                        " | ID: " + p.getId();
            }
        }
        return "Produto com ID " + id + " não localizado.";
    }


    public static String finalizarCompra(String nome, int senha) {
        if (carrinhoVazio()) {
            return "Seu carrinho está vazio!";
        }

        // autenticação
        if (!Usuario.confirmarUsuario(nome,senha)) {
            return "Usuário ou senha incorretos. Compra não autorizada.";
        }

        // atualizar estoque
        for (ItemCarrinho item : carrinho) {
            Produto p = item.getProduto();
            p.setQuantidadeProduto(p.getQuantidadeProduto() - item.getQuantidadeCarrinho());
        }

        GerenciadorArquivos.salvarEstoque(produtos);
        GerenciadorArquivos.gerarRelatorioCompra(carrinho, totalCarrinho);

        carrinho.clear();
        totalCarrinho = 0;

        return "Compra finalizada com sucesso!\nRecibo gerado em relatorio_compra.txt";
    }

    public static boolean carrinhoVazio(){
        if (carrinho.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean confirmaUsuario(String confirmNome, int confirmSenha) {
        return Usuario.confirmarUsuario(confirmNome, confirmSenha);
    }
    public boolean realizarCadastro(String nome, int senha) {
        return Usuario.cadastrarUsuario(nome, senha);
    }
}
