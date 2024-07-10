package complexidadearvore;

public class ArvoreAVL {
    NoAVL raiz;

    int altura(NoAVL N) {
        if (N == null)
            return 0;
        return N.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    int obterBalanceamento(NoAVL N) {
        if (N == null)
            return 0;
        return altura(N.esquerda) - altura(N.direita);
    }

    public void inserir(int chave) {
        raiz = inserirRecursivo(raiz, chave);
    }

    NoAVL inserirRecursivo(NoAVL no, int chave) {
        if (no == null)
            return (new NoAVL(chave));

        if (chave < no.chave)
            no.esquerda = inserirRecursivo(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = inserirRecursivo(no.direita, chave);
        else
            return no;

        no.altura = 1 + max(altura(no.esquerda), altura(no.direita));

        int balanceamento = obterBalanceamento(no);

        if (balanceamento > 1 && chave < no.esquerda.chave)
            return rotacaoDireita(no);

        if (balanceamento < -1 && chave > no.direita.chave)
            return rotacaoEsquerda(no);

        if (balanceamento > 1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void remover(int chave) {
        raiz = removerRecursivo(raiz, chave);
    }

    NoAVL removerRecursivo(NoAVL raiz, int chave) {
        if (raiz == null)
            return raiz;

        if (chave < raiz.chave)
            raiz.esquerda = removerRecursivo(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = removerRecursivo(raiz.direita, chave);
        else {
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                NoAVL temp = null;
                if (temp == raiz.esquerda)
                    temp = raiz.direita;
                else
                    temp = raiz.esquerda;

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;
            } else {
                NoAVL temp = valorMinimo(raiz.direita);
                raiz.chave = temp.chave;
                raiz.direita = removerRecursivo(raiz.direita, temp.chave);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = max(altura(raiz.esquerda), altura(raiz.direita)) + 1;

        int balanceamento = obterBalanceamento(raiz);

        if (balanceamento > 1 && obterBalanceamento(raiz.esquerda) >= 0)
            return rotacaoDireita(raiz);

        if (balanceamento > 1 && obterBalanceamento(raiz.esquerda) < 0) {
            raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
            return rotacaoDireita(raiz);
        }

        if (balanceamento < -1 && obterBalanceamento(raiz.direita) <= 0)
            return rotacaoEsquerda(raiz);

        if (balanceamento < -1 && obterBalanceamento(raiz.direita) > 0) {
            raiz.direita = rotacaoDireita(raiz.direita);
            return rotacaoEsquerda(raiz);
        }

        return raiz;
    }

    NoAVL valorMinimo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }

    public boolean buscar(int chave) {
        return buscarRecursivo(raiz, chave);
    }

    boolean buscarRecursivo(NoAVL raiz, int chave) {
        if (raiz == null)
            return false;

        if (raiz.chave == chave)
            return true;

        return chave < raiz.chave
                ? buscarRecursivo(raiz.esquerda, chave)
                : buscarRecursivo(raiz.direita, chave);
    }
}
