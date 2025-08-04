package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFU implements AlgoritmoSubstituicao {

    @Override
    public void simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new LinkedHashSet<>();
        // mapa para armazenar a frequência de cada página
        Map<Integer, Integer> frequencias = new HashMap<>();
        int pageFaults = 0;
        int swaps = 0;

        System.out.println("Simulando LFU com " + numeroFrames + " frames...");

        // itera sobre cada página solicitada
        for (int pagina : paginas) {
            // verifica se a página ja ta na memória (Page Hit)
            if (frames.contains(pagina)) {
                // se sim, apenas incrementa sua frequência
                frequencias.put(pagina, frequencias.get(pagina) + 1);
                System.out.print("Página: " + pagina + " (HIT)  → ");
            } else {
                //  se a página nao ta na memória (Page Fault)
                pageFaults++;
                System.out.print("Página: " + pagina + " (FAULT)→ ");

                // verifica se a memória está cheia
                if (frames.size() >= numeroFrames) {
                    // lógica de substituição: encontrar a página menos usada
                    int paginaParaRemover = -1;
                    int minFrequencia = Integer.MAX_VALUE;

                    // itera sobre os frames na ordem em que entraram (graças ao LinkedHashSet)
                    for (int frame : frames) {
                        if (frequencias.get(frame) < minFrequencia) {
                            minFrequencia = frequencias.get(frame);
                            paginaParaRemover = frame;
                        }
                    }

                    // remove a página escolhida dos frames e do mapa de frequências
                    frames.remove(paginaParaRemover);
                    frequencias.remove(paginaParaRemover);
                    swaps++;
                }

                // adiciona a nova página na memória
                frames.add(pagina);
                // define sua frequência inicial como 1
                frequencias.put(pagina, 1);
            }

            // exibe o estado atual da memória e das frequências
            System.out.println("Memória: " + frames + " | Frequências: " + frequencias);
        }

        System.out.println("\nLFU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps + "\n");
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LFU";
    }
}