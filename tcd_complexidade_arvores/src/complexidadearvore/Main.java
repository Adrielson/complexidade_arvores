package complexidadearvore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] tamanhos = {500, 1000, 1500, 2000,  2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000};
        List<String[]> resultados = new ArrayList<>();

        resultados.add(new String[]{"Algoritmo", "Tamanho", "Caso", "Tempo Insercao (ns)", "Tempo Busca (ns)", "Tempo Remocao (ns)"});

        System.out.println("Teste de Inserção, Busca e Remoção (Caso Médio e Pior Caso):");
        for (int tamanho : tamanhos) {
            int[] valoresAleatorios = Util.gerarArrayAleatorio(tamanho, 100000);
            int[] valoresDecrescentes = Util.gerarArrayDecrescente(tamanho);

            // Caso Médio
            System.out.println("\nTamanho: " + tamanho + " (Caso Médio)");

            // Teste com BST (Caso Médio)
            ArvoreBinariaDeBusca bst = new ArvoreBinariaDeBusca();
            long inicioBST = System.nanoTime();
            for (int valor : valoresAleatorios) {
                bst.inserir(valor);
            }
            long fimBST = System.nanoTime();
            long tempoInsercaoBST = fimBST - inicioBST;

            inicioBST = System.nanoTime();
            for (int valor : valoresAleatorios) {
                bst.buscar(valor);
            }
            fimBST = System.nanoTime();
            long tempoBuscaBST = fimBST - inicioBST;

            inicioBST = System.nanoTime();
            for (int valor : valoresAleatorios) {
                bst.remover(valor);
            }
            fimBST = System.nanoTime();
            long tempoRemocaoBST = fimBST - inicioBST;

            resultados.add(new String[]{"BST", String.valueOf(tamanho), "Medio", String.valueOf(tempoInsercaoBST), String.valueOf(tempoBuscaBST), String.valueOf(tempoRemocaoBST)});

            // Teste com AVL (Caso Médio)
            ArvoreAVL avl = new ArvoreAVL();
            long inicioAVL = System.nanoTime();
            for (int valor : valoresAleatorios) {
                avl.inserir(valor);
            }
            long fimAVL = System.nanoTime();
            long tempoInsercaoAVL = fimAVL - inicioAVL;

            inicioAVL = System.nanoTime();
            for (int valor : valoresAleatorios) {
                avl.buscar(valor);
            }
            fimAVL = System.nanoTime();
            long tempoBuscaAVL = fimAVL - inicioAVL;

            inicioAVL = System.nanoTime();
            for (int valor : valoresAleatorios) {
                avl.remover(valor);
            }
            fimAVL = System.nanoTime();
            long tempoRemocaoAVL = fimAVL - inicioAVL;

            resultados.add(new String[]{"AVL", String.valueOf(tamanho), "Medio", String.valueOf(tempoInsercaoAVL), String.valueOf(tempoBuscaAVL), String.valueOf(tempoRemocaoAVL)});

            // Pior Caso - Inserção em ordem decrescente
            System.out.println("\nTamanho: " + tamanho + " (Pior Caso)");

            // Teste com BST (Pior Caso)
            bst = new ArvoreBinariaDeBusca();
            inicioBST = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                bst.inserir(valor);
            }
            fimBST = System.nanoTime();
            tempoInsercaoBST = fimBST - inicioBST;

            inicioBST = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                bst.buscar(valor);
            }
            fimBST = System.nanoTime();
            tempoBuscaBST = fimBST - inicioBST;

            inicioBST = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                bst.remover(valor);
            }
            fimBST = System.nanoTime();
            tempoRemocaoBST = fimBST - inicioBST;

            resultados.add(new String[]{"BST", String.valueOf(tamanho), "Pior", String.valueOf(tempoInsercaoBST), String.valueOf(tempoBuscaBST), String.valueOf(tempoRemocaoBST)});

            // Teste com AVL (Pior Caso)
            avl = new ArvoreAVL();
            inicioAVL = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                avl.inserir(valor);
            }
            fimAVL = System.nanoTime();
            tempoInsercaoAVL = fimAVL - inicioAVL;

            inicioAVL = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                avl.buscar(valor);
            }
            fimAVL = System.nanoTime();
            tempoBuscaAVL = fimAVL - inicioAVL;

            inicioAVL = System.nanoTime();
            for (int valor : valoresDecrescentes) {
                avl.remover(valor);
            }
            fimAVL = System.nanoTime();
            tempoRemocaoAVL = fimAVL - inicioAVL;

            resultados.add(new String[]{"AVL", String.valueOf(tamanho), "Pior", String.valueOf(tempoInsercaoAVL), String.valueOf(tempoBuscaAVL), String.valueOf(tempoRemocaoAVL)});
        }

        try (FileWriter writer = new FileWriter("resultados.csv")) {
            for (String[] resultado : resultados) {
                writer.append(String.join(",", resultado));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
