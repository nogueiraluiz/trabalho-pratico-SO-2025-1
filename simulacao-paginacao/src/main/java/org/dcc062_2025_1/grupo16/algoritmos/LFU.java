package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.util.Resultado;
import org.dcc062_2025_1.grupo16.util.Constantes;
import org.dcc062_2025_1.grupo16.util.Sleeper;

public class LFU implements AlgoritmoSubstituicao {

    @Override
    @SneakyThrows
    public Resultado simula(int[] paginas, int numeroFrames, boolean usaSleeps) {
        Set<Integer> frames = new LinkedHashSet<>();
        Map<Integer, Integer> frequencias = new HashMap<>();
        int pageFaults = 0;
        long duracaoSleep = usaSleeps ? Constantes.MILISSEGUNDOS_SLEEP_ITERACOES : 0;


        System.out.println("Simulando LFU com " + numeroFrames + " frames...");

        for (int pagina : paginas) {
            boolean isHit = frames.contains(pagina);
            
            System.out.print(Constantes.formatarPagina(pagina) + " ");
            System.out.print(Constantes.formatarStatus(isHit) + " → ");
            
            if (isHit) {
                frequencias.put(pagina, frequencias.get(pagina) + 1);
            } else {
                pageFaults++;

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

            System.out.print("Memória: " + String.format("%-" + Constantes.LARGURA_MEMORIA + "s", frames));
            System.out.println(" | Frequências: " + String.format("%-" + Constantes.LARGURA_FREQUENCIA + "s", frequencias));
            Sleeper.sleep(duracaoSleep);
        }

        var swaps = pageFaults - frames.size();
        System.out.println("\nLFU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps);

        return new Resultado(pageFaults, swaps);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LFU";
    }
}