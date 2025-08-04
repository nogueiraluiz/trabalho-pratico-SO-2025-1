package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.LinkedHashMap;

/**
 * Implementação da simulação do algoritmo LRU.
 */
public class LRU implements AlgoritmoSubstituicao {

    @Override
    public void simula(int[] paginas, int numeroFrames) {

        // Capacidade máxima do buffer
        int capacidade = 3;

        // Criação do buffer com capacidade descrita, fator de carga 0.75 e true como ordem de acesso
        LinkedHashMap<Integer, Integer> buffer = new LinkedHashMap<>(capacidade, 0.75f, true);

        // Contador de page faults 
        int faults = 0;

        // For para percorrer todas as páginas acessadas
        for (int pagina : paginas) {

            // Verifica se a página está no buffer 
            if (!buffer.containsKey(pagina)) {

                // Se o buffer está cheio, remove a página menos recentemente usada
                if (buffer.size() == capacidade)
                    buffer.remove(buffer.keySet().iterator().next());

                // Incrementa o número de page faults
                faults++;
            }

            // Adiciona ou atualiza a página no buffer 
            buffer.put(pagina, 1);

            // Printa o estado atual
            System.out.println("Buffer: " + buffer.keySet());
        }

        // Printa o total de falhas
        System.out.println("Page faults (LRU): " + faults);
        System.out.println("Total de swaps: " + (faults - buffer.size()));
    }

    @Override
    public String getNomeAlgoritmo() {
        // Printa o tipo de algoritmo
        return "LRU";
    }
}
