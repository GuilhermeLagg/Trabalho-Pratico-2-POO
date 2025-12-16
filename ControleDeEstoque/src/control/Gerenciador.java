package control;

import java.util.Scanner;

public class Gerenciador {

    private static Scanner sc = new Scanner(System.in);

    public void iniciarPrograma() {
        menu();
        do {
            switch (menu()) {
                case 1:
                    if (Estoque.estoqueVazio()) {
                        System.out.println("O estoque está vazio! Adicione itens para poder visualizar.");
                    } else {
                        Estoque.imprimeProduto();
                    }
                    break;
                case 2:
                    Estoque.cadastrarProduto();
                    break;
                case 3:
            }
        } while (menu() != 5);

    }

    public int menu() {
        System.out.println("Bem vindo ao estoque da loja XPTO!");
        System.out.println("Informe a operação que deseja realizar:");
        System.out.println("""
                1 - Visualizar Estoque
                2 - Cadastrar produto
                3 - Comprar produto
                4 - Vender produto
                5 - Pesquisar produto
                6 - Sair
                """);
        int resp = sc.nextInt();
        return resp;
    }


}
