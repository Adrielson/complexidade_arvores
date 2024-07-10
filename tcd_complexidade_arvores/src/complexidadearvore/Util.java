package complexidadearvore;

import java.util.Random;

public class Util {
    public static int[] gerarArrayAleatorio(int tamanho, int limite) {
        Random random = new Random();
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(limite);
        }
        return array;
    }

    public static int[] gerarArrayCrescente(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] gerarArrayDecrescente(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = tamanho - i;
        }
        return array;
    }
}
