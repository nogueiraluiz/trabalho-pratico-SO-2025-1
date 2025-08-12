package org.dcc062_2025_1.grupo16.algoritmos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.Resultado;
import org.dcc062_2025_1.grupo16.util.Constantes;

public class FCFS implements AlgoritmoSubstituicao {

    @Override
    @SneakyThrows
    public Resultado simula(int[] paginas, int numeroFrames) {
        Set<Integer> frames = new HashSet<>();
        Queue<Integer> ordem = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("Simulando FCFS com " + numeroFrames + " frames...");
        for (int pagina : paginas) {
            boolean isHit = frames.contains(pagina);
            
            System.out.print(Constantes.formatarPagina(pagina) + " ");
            System.out.print(Constantes.formatarStatus(isHit) + " → ");
            
            if (!isHit) {
                if (frames.size() >= numeroFrames) {
                    int removida = ordem.poll();
                    frames.remove(removida);
                }
                frames.add(pagina);
                ordem.add(pagina);
                pageFaults++;
            }
            
            System.out.println("Memória: " + String.format("%-" + Constantes.LARGURA_MEMORIA + "s", frames));
            Thread.sleep(Constantes.MILISSEGUNDOS_SLEEP_ITERACOES);
        }

        int swaps = pageFaults - frames.size();
        System.out.println("\nFCFS - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps);

        return new Resultado(pageFaults, swaps);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "FCFS";
    }
}
