import java.util.*;

// Classe principal para simulação via terminal
public class SimuladorLRU {

    public static void main(String[] args) {
        // Sequência de páginas que serão acessadas 
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};

        // Capacidade máxima do buffer 
        int capacity = 3;

        // Criando um LinkedHashMap com ordem de acesso 
        // Isso significa que a ordem dos elementos será atualizada a cada acesso
        LinkedHashMap<Integer, Integer> buffer = new LinkedHashMap<>(capacity, 0.75f, true);

        // Contador de falhas de página 
        int faults = 0;

        // Loop para percorrer todas as páginas acessadas
        for (int page : pages) {
            // Verifica se a página NÃO está no buffer 
            if (!buffer.containsKey(page)) {
                // Se o buffer está cheio, remove a página menos recentemente usada
                if (buffer.size() == capacity) {
                    // A chave do primeiro item do LinkedHashMap é a menos recentemente usada
                    int firstKey = buffer.keySet().iterator().next();
                    buffer.remove(firstKey);
                }
                // Incrementa o número de page faults
                faults++;
            }

            // Adiciona ou atualiza a página no buffer 
            buffer.put(page, 1);

            // Exibe o estado atual do buffer a cada iteração
            System.out.println("Buffer: " + buffer.keySet());
        }

        // Mostra o total de falhas de página ao final da simulação
        System.out.println("Page faults (LRU): " + faults);
    }

    // Classe LRU que implementa a interface AlgoritmoSubstituicao
    public class LRU implements AlgoritmoSubstituicao {

        @Override
        public void simula(int[] paginas, int numeroFrames) {
            throw new UnsupportedOperationException("Não implementado ainda.");
        }

        @Override
        public String getNomeAlgoritmo() {
            return "LRU";
        }
    }
}



