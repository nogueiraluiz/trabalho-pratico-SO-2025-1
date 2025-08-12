package org.dcc062_2025_1.grupo16;

public class Resultado {

    private final int pageFaults;
    private final int swaps;

    public Resultado(int pageFaults, int swaps) {
        this.pageFaults = pageFaults;
        this.swaps = swaps;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public int getSwaps() {
        return swaps;
    }
}
