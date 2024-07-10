package complexidadearvore;

public class ArvoreBinariaDeBusca {
    No raiz;

    ArvoreBinariaDeBusca() {
        raiz = null;
    }

    public void inserir(int chave) {
        raiz = inserirRecursivo(raiz, chave);
    }

    No inserirRecursivo(No raiz, int chave) {
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }

        if (chave < raiz.chave)
            raiz.esquerda = inserirRecursivo(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = inserirRecursivo(raiz.direita, chave);

        return raiz;
    }

    public void remover(int chave) {
        raiz = removerRecursivo(raiz, chave);
    }

    No removerRecursivo(No raiz, int chave) {
        if (raiz == null)
            return raiz;

        if (chave < raiz.chave)
            raiz.esquerda = removerRecursivo(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = removerRecursivo(raiz.direita, chave);
        else {
            if (raiz.esquerda == null)
                return raiz.direita;
            else if (raiz.direita == null)
                return raiz.esquerda;

            raiz.chave = valorMinimo(raiz.direita);
            raiz.direita = removerRecursivo(raiz.direita, raiz.chave);
        }

        return raiz;
    }

    int valorMinimo(No raiz) {
        int minimo = raiz.chave;
        while (raiz.esquerda != null) {
            minimo = raiz.esquerda.chave;
            raiz = raiz.esquerda;
        }
        return minimo;
    }

    public boolean buscar(int chave) {
        return buscarRecursivo(raiz, chave);
    }

    boolean buscarRecursivo(No raiz, int chave) {
        if (raiz == null)
            return false;

        if (raiz.chave == chave)
            return true;

        return chave < raiz.chave
                ? buscarRecursivo(raiz.esquerda, chave)
                : buscarRecursivo(raiz.direita, chave);
    }
}
