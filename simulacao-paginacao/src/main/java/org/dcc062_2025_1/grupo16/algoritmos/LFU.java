package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.dcc062_2025_1.grupo16.Resultado;

public class LFU implements AlgoritmoSubstituicao {

    @Override
    public Resultado simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new LinkedHashSet<>();
        Map<Integer, Integer> frequencias = new HashMap<>();
        int pageFaults = 0;
        int swaps = 0;

        System.out.println("Simulando LFU com " + numeroFrames + " frames...");

        for (int pagina : paginas) {
            if (frames.contains(pagina)) {
                frequencias.put(pagina, frequencias.get(pagina) + 1);
                System.out.print("Página: " + pagina + " (HIT)  → ");
            } else {
                pageFaults++;
                System.out.print("Página: " + pagina + " (FAULT)→ ");

                if (frames.size() >= numeroFrames) {
                    int paginaParaRemover = -1;
                    int minFrequencia = Integer.MAX_VALUE;

                    for (int frame : frames) {
                        if (frequencias.get(frame) < minFrequencia) {
                            minFrequencia = frequencias.get(frame);
                            paginaParaRemover = frame;
                        }
                    }

                    frames.remove(paginaParaRemover);
                    frequencias.remove(paginaParaRemover);
                    swaps++;
                }

                frames.add(pagina);
                frequencias.put(pagina, 1);
            }

            System.out.println("Memória: " + frames + " | Frequências: " + frequencias);
        }

        System.out.println("\nLFU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps + "\n");
        
        return new Resultado(getNomeAlgoritmo(), pageFaults, swaps);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LFU";
    }
}