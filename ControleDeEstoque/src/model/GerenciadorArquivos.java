package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String NOME_ARQUIVO = "estoque.txt";

    //metodo que vai salvar a lista de produtos no arquivo
    public static void salvarEstoque(List<Produto> produtos){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter(NOME_ARQUIVO);
            bw = new BufferedWriter((fw));

            for (Produto p: produtos){
                String linha = "";
                //aqui diz, basicamente: se esse produto p for IMPORTADO
                if (p instanceof Importado imp) {
                    linha = "IMPORTADO;" + p.getNome() + ";" +p.getPreco() + ";" + p.getQuantidadeProduto() + ";" + imp.getTaxaImportacao();
                    //se ele for importado essa eh a linha que ele tem que pegar
                } else {
                    linha = "COMUM" + p.getNome() + ";" +p.getPreco() + ";" + p.getQuantidadeProduto();
                    //se ele for comum essa eh a linha que ele tem que pegar
                }
                bw.write(linha); //vamos escrever no arquivo
                bw.newLine(); //quebra de linha
            }
        } catch (IOException e){
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        } finally {
            try {
                //Fechamos sempre do mais externo para o mais interno
                if (bw !=null){
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e){
                System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            }
        }
    }

    //metodo que vai ler a lista de produtos do arquivo
    public static List<Produto> carregarEstoque(){
        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);

        //se o arquivo nao existir, retorne a minha lista de produtos
        if (!arquivo.exists()){
            return produtos;
        }

        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader((NOME_ARQUIVO));
            br = new BufferedReader(fr);
            String linha;

            //enquanto a linha que eu for ler não for nula, continue fazendo:
            while ((linha = br.readLine()) != null){
                String[] partes = linha.split(";"); //o ponto e virgula vai ser o separador dos atributos do meu produto na linha
                String tipo = partes[0]; //o tipo do prod ta na primeira pos... Importado ou Comum
                String nome = partes[1]; //o nome do prod ta na segunda pos
                double preco = Double.parseDouble(partes[2]); //o preco do prod ta na segunda pos... como o arquivo esta lendo string, essa informação precisamos converter de volta pra dado numerico
                int qtd = Integer.parseInt(partes[3]); //a qtde di prod ta na terceira pos... como o arquivo esta lendo string, essa informação precisamos converter de volta pra dado numerico

                //agora vamos mexer com o polimorfismo, precisamos saber se eh importado ou comum, pq o importado tem informacoes adicionais
                switch (tipo){
                    case "COMUM":
                        produtos.add(new Comum(nome, preco, qtd));
                        break;
                    case "IMPORTADO":
                        double taxa = Double.parseDouble(partes[4]);//memo esquema de converter de volta pra numerico
                        produtos.add(new Importado(nome, preco, qtd, taxa));
                }
            }
        } catch (IOException | RuntimeException e){
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } finally {
            try {
                if (br != null){
                    br.close();
                }
                if (fr != null){
                    fr.close();
                }
            } catch (IOException e){
                System.out.println("Erro ao fechar o leitor: " + e.getMessage());
            }
        }
        return produtos;
    }
}
