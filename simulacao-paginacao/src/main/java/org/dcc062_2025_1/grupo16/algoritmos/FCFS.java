package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.*;

/**
 * Implementação da simulação do algoritmo FCFS (First-Come, First-Served).
 * Este algoritmo substitui páginas na ordem em que elas chegam.
 */
public class FCFS implements AlgoritmoSubstituicao {

    @Override
    public void simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new HashSet<>();
        Queue<Integer> ordem = new LinkedList<>();
        int pageFaults = 0;

        for (int pagina : paginas) {
            if (!frames.contains(pagina)) {
                if (frames.size() >= numeroFrames) {
                    int removida = ordem.poll();
                    frames.remove(removida);
                }
                frames.add(pagina);
                ordem.add(pagina);
                pageFaults++;
            }
            System.out.println("Página: " + pagina + " → Memória: " + frames);
        }
        System.out.println("FCFS - Total de page faults: " + pageFaults + "\n");
        System.out.println("Total de swaps: " + (pageFaults - frames.size()) + "\n");
    }

    @Override
    public String getNomeAlgoritmo() {
        return "FCFS";
    }
}
