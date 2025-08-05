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
        long tempoInicio = System.currentTimeMillis();
        for (int pagina : paginas) {
            if (!frames.contains(pagina)) {
                if (frames.size() >= numeroFrames) {
                    int removida = ordem.poll();
                    frames.remove(removida);
                }
                frames.add(pagina);
                ordem.add(pagina);
                pageFaults++;
                System.out.print("Página: " + pagina + " (FAULT)→ ");
            } else {
                System.out.print("Página: " + pagina + " (HIT)  → ");
            }
            System.out.println("Memória: " + frames);
            Thread.sleep(Constantes.MILISSEGUNDOS_SLEEP_ITERACOES);
        }

        long tempoExecucao = System.currentTimeMillis() - tempoInicio;
        int swaps = pageFaults - frames.size();
        
        System.out.println("\nFCFS - Total de page faults: " + pageFaults);
        System.out.println("Total de swaps: " + swaps);
        System.out.println("Tempo de execução: " + tempoExecucao + " ms\n");
        
        return new Resultado(getNomeAlgoritmo(), pageFaults, swaps, tempoExecucao);
    }

    @Override
    public String getNomeAlgoritmo() {
        return "FCFS";
    }
}
