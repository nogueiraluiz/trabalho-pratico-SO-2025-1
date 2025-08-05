package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.dcc062_2025_1.grupo16.Resultado;

public class FCFS implements AlgoritmoSubstituicao {

    @Override
    public Resultado simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new HashSet<>();
        Queue<Integer> ordem = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("Simulando FCFS com " + numeroFrames + " frames...");

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
        
        int swaps = pageFaults - frames.size();
        
        System.out.println("\nFCFS - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps + "\n");
        
        return new Resultado(getNomeAlgoritmo(), pageFaults, swaps);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "FCFS";
    }
}
