package org.dcc062_2025_1.grupo16.algoritmos;

import org.dcc062_2025_1.grupo16.util.Resultado;

/**
 * Uma abstração para todos os algoritmos
 */
public interface AlgoritmoSubstituicao {

    /**
     * Simula o comportamento de um algoritmo de substituição de páginas
     *
     * @param paginas número de páginas na memória virtual
     * @param numeroFrames número de frames na memória física
     * @param usaSleeps opção de usar ou não sleeps na simulação para desacelerar a execução
     * @return {{@link Resultado}} da simulação
     */
    Resultado simula(int[] paginas, int numeroFrames, boolean usaSleeps);
    String getNomeAlgoritmo();

}
