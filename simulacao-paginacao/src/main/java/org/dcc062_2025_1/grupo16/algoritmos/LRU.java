package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.LinkedHashMap;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.Resultado;
import org.dcc062_2025_1.grupo16.util.Constantes;

public class LRU implements AlgoritmoSubstituicao {

    @Override
    @SneakyThrows
    public Resultado simula(int[] paginas, int numeroFrames) {
        // true para manter ordem LRU
        LinkedHashMap<Integer, Integer> frames = new LinkedHashMap<>(numeroFrames, 0.75f, true);
        
        int pageFaults = 0;
        int swaps = 0;
        
        System.out.println("Simulando LRU com " + numeroFrames + " frames...");

        for (int pagina : paginas) {
            boolean isHit = frames.containsKey(pagina);
            
            System.out.print(Constantes.formatarPagina(pagina) + " ");
            System.out.print(Constantes.formatarStatus(isHit) + " → ");
            
            if (isHit) {
                frames.put(pagina, 1);
            } else {
                pageFaults++;
                
                if (frames.size() >= numeroFrames) {
                    Integer paginaRemovida = frames.keySet().iterator().next();
                    frames.remove(paginaRemovida);
                    swaps++;
                }
                
                frames.put(pagina, 1);
            }

            System.out.println("Memória: " + String.format("%-" + Constantes.LARGURA_MEMORIA + "s", frames.keySet()));
            Thread.sleep(Constantes.MILISSEGUNDOS_SLEEP_ITERACOES);
        }
        
        System.out.println("\nLRU - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps);
        return new Resultado(pageFaults, swaps);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "LRU";
    }
}
