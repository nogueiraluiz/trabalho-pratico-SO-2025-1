package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.Resultado;
import org.dcc062_2025_1.grupo16.util.Constantes;

public class LFU implements AlgoritmoSubstituicao {

    @Override
    @SneakyThrows
    public Resultado simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new LinkedHashSet<>();
        Map<Integer, Integer> frequencias = new HashMap<>();
        int pageFaults = 0;

        System.out.println("Simulando LFU com " + numeroFrames + " frames...");
        long tempoInicio = System.currentTimeMillis();

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
                }

                frames.add(pagina);
                frequencias.put(pagina, 1);
            }

            System.out.println("Memória: " + frames + " | Frequências: " + frequencias);
            Thread.sleep(Constantes.MILISSEGUNDOS_SLEEP_ITERACOES);
        }

        var swaps = pageFaults - frames.size();
        long tempoExecucao = System.currentTimeMillis() - tempoInicio;

        System.out.println("\nLFU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps);
        System.out.println("Tempo de execução: " + tempoExecucao + " ms\n");
        
        return new Resultado(getNomeAlgoritmo(), pageFaults, swaps, tempoExecucao);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LFU";
    }
}