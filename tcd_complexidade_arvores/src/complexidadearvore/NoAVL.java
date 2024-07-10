package complexidadearvore;

public class NoAVL {
    int chave;
    NoAVL esquerda, direita;
    int altura;

    public NoAVL(int item) {
        chave = item;
        esquerda = direita = null;
        altura = 1;
    }
}
