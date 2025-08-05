package org.dcc062_2025_1.grupo16;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Observador {

    private final Map<String, Resultado> results;

    public Observador() {
        this.results = new HashMap<>();
    }

    public void registraResultado(String algoritmo, Resultado result) {
        results.put(algoritmo, result);
    }

    public void comparaResultados(int numeroDePaginas, int numeroDeFrames, int tamanhoSequencia) {
        if (results.isEmpty()) {
            System.out.println("Não há resultados para comparar.");
            return;
        }

        System.out.println("Resultados obtidos com simulações com " + numeroDePaginas + " páginas, " + numeroDeFrames
                + " frames e " + tamanhoSequencia + " referências.");
        System.out.println("\n===== COMPARAÇÃO DE RESULTADOS =====");
        System.out.println("Algoritmo\tPage Faults\tSwaps\t\tTempo (ms)");

        String menosPageFaults = null;
        String menosSwaps = null;
        String maisRapido = null;
        int minPageFaults = Integer.MAX_VALUE;
        int minSwaps = Integer.MAX_VALUE;
        long minTempo = Long.MAX_VALUE;

        List<Entry<String, Resultado>> sortedEntries = new java.util.ArrayList<>(results.entrySet());
        sortedEntries.sort(Comparator.comparingInt(e -> e.getValue().getPageFaults()));

        for (Map.Entry<String, Resultado> entry : sortedEntries) {
            String algoritmo = entry.getKey();
            Resultado resultado = entry.getValue();

            System.out.println(algoritmo + "\t\t" + resultado.getPageFaults() + "\t\t" +
                    resultado.getSwaps() + "\t\t" + resultado.getTempoExecucao());

            if (resultado.getPageFaults() < minPageFaults) {
                minPageFaults = resultado.getPageFaults();
                menosPageFaults = algoritmo;
            }

            if (resultado.getSwaps() < minSwaps) {
                minSwaps = resultado.getSwaps();
                menosSwaps = algoritmo;
            }

            if (resultado.getTempoExecucao() < minTempo) {
                minTempo = resultado.getTempoExecucao();
                maisRapido = algoritmo;
            }
        }
        
        System.out.println("\nMelhor algoritmo em termos de page faults: " + menosPageFaults + " (" + minPageFaults + " page faults)");
        System.out.println("Melhor algoritmo em termos de swaps: " + menosSwaps + " (" + minSwaps + " swaps)");
        System.out.println("Algoritmo mais rápido: " + maisRapido + " (" + minTempo + " ms)");
        System.out.println("=====================================\n");
    }

    public void limpaResultados() {
        results.clear();
    }

}