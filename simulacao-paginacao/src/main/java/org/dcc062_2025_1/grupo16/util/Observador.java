package org.dcc062_2025_1.grupo16.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe para observar e registrar resultados de simulações usando os algoritmos de paginação.
 *
 * @see Resultado
 * @see org.dcc062_2025_1.grupo16.algoritmos.AlgoritmoSubstituicao
 */
public class Observador {

    /**
     * Mapa para registro dos resultados. Associa o nome do algoritmo ao resultado.
     */
    private final Map<String, Resultado> resultados;

    public Observador() {
        this.resultados = new HashMap<>();
    }

    /**
     * Guarda o resultado da execução de uma simulação
     *
     * @param algoritmo algoritmo usado
     * @param resultado resultado da simulação
     */
    public void registraResultado(String algoritmo, Resultado resultado) {
        resultados.put(algoritmo, resultado);
    }

    /**
     * Compara os resultados obtidos nas simulações realizadas usando os parâmetros listados
     *
     * @param numeroDePaginas número de páginas na memória virtual usado nas simulações
     * @param numeroDeFrames número de frames na memória física usado nas simulações
     * @param tamanhoSequencia tamanho da sequência de referências usado nas simulações
     */
    public void comparaResultados(int numeroDePaginas, int numeroDeFrames, int tamanhoSequencia) {
        if (resultados.isEmpty()) {
            System.out.println("Não há resultados para comparar.");
            return;
        }

        System.out.println("Resultados obtidos com simulações com " + numeroDePaginas + " páginas, " + numeroDeFrames
                + " frames e " + tamanhoSequencia + " referências.");
        System.out.println("\n===== COMPARAÇÃO DE RESULTADOS =====");
        System.out.printf("%-" + Constantes.LARGURA_ALGORITMO + "s%-" +
                Constantes.LARGURA_PAGE_FAULTS + "s%-" + Constantes.LARGURA_SWAPS + "s%n",
                "Algoritmo", "Page Faults", "Swaps");

        String menosPageFaults = null;
        String menosSwaps = null;
        int minPageFaults = Integer.MAX_VALUE;
        int minSwaps = Integer.MAX_VALUE;

        List<Entry<String, Resultado>> sortedEntries = new java.util.ArrayList<>(resultados.entrySet());
        sortedEntries.sort(Comparator.comparingInt(e -> e.getValue().pageFaults()));

        for (Map.Entry<String, Resultado> entry : sortedEntries) {
            String algoritmo = entry.getKey();
            Resultado resultado = entry.getValue();

            System.out.printf("%-" + Constantes.LARGURA_ALGORITMO + "s%-" +
                    Constantes.LARGURA_PAGE_FAULTS + "d%-" + Constantes.LARGURA_SWAPS + "d%n",
                    algoritmo, resultado.pageFaults(), resultado.swaps());

            if (resultado.pageFaults() < minPageFaults) {
                minPageFaults = resultado.pageFaults();
                menosPageFaults = algoritmo;
            }

            if (resultado.swaps() < minSwaps) {
                minSwaps = resultado.swaps();
                menosSwaps = algoritmo;
            }

        }
        
        System.out.println("\nMelhor algoritmo em termos de page faults: " + menosPageFaults + " (" + minPageFaults + " page faults)");
        System.out.println("Melhor algoritmo em termos de swaps: " + menosSwaps + " (" + minSwaps + " swaps)");
        System.out.println("=====================================\n");
    }

    /**
     * Remove os resultados armazenados
     */
    public void limpaResultados() {
        resultados.clear();
    }

}