package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.LinkedHashMap;

/**
 * Implementação da simulação do algoritmo LRU (Least Recently Used).
 * Este algoritmo substitui a página menos recentemente utilizada quando necessário.
 */
public class LRU implements AlgoritmoSubstituicao {

    @Override
    public void simula(int[] paginas, int numeroFrames) {
        // true para manter ordem LRU
        LinkedHashMap<Integer, Integer> frames = new LinkedHashMap<>(numeroFrames, 0.75f, true);
        
        int pageFaults = 0;
        int swaps = 0;
        
        System.out.println("Simulando LRU com " + numeroFrames + " frames...");

        for (int pagina : paginas) {
            if (frames.containsKey(pagina)) {
                System.out.print("Página: " + pagina + " (HIT)  → ");
                frames.put(pagina, 1);
            } else {
                pageFaults++;
                System.out.print("Página: " + pagina + " (FAULT)→ ");
                
                if (frames.size() >= numeroFrames) {
                    Integer paginaRemovida = frames.keySet().iterator().next();
                    frames.remove(paginaRemovida);
                    swaps++;
                }
                
                frames.put(pagina, 1);
            }
            
            System.out.println("Memória: " + frames.keySet());
        }
        
        System.out.println("\nLRU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps + "\n");
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LRU";
    }
}
