package org.dcc062_2025_1.grupo16.algoritmos;

import org.dcc062_2025_1.grupo16.Resultado;

/**
 * Uma abstração para todos os algoritmos
 */
public interface AlgoritmoSubstituicao {

    Resultado simula(int[] paginas, int numeroFrames);
    String getNomeAlgoritmo();

}
