package complexidadearvore;

public class No {
    int chave;
    No esquerda, direita;

    public No(int item) {
        chave = item;
        esquerda = direita = null;
    }
}
