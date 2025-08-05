package org.dcc062_2025_1.grupo16;

public class Resultado {

    private final int pageFaults;
    private final int swaps;
    private final long tempoExecucao; // tempo em milissegundos

    public Resultado(String algoritmo, int pageFaults, int swaps, long tempoExecucao) {
        this.pageFaults = pageFaults;
        this.swaps = swaps;
        this.tempoExecucao = tempoExecucao;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public int getSwaps() {
        return swaps;
    }
    
    public long getTempoExecucao() {
        return tempoExecucao;
    }
}
