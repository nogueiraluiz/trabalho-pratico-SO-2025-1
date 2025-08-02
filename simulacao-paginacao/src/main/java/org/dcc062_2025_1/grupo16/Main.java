package org.dcc062_2025_1.grupo16;

import org.dcc062_2025_1.grupo16.algoritmos.AlgoritmoSubstituicao;
import org.dcc062_2025_1.grupo16.algoritmos.FCFS;
import org.dcc062_2025_1.grupo16.algoritmos.LFU;
import org.dcc062_2025_1.grupo16.algoritmos.LRU;

public class Main {

    public static void main(String[] args) {
        simula(args[0].trim());
    }

    private static void simula(String algoritmo) {
        if (algoritmo.isEmpty()) {
            simulaTodos();
        }
        switch (algoritmo) {
            case "FCFS":
                simula(new FCFS());
                break;
            case "LRU":
                simula(new LRU());
                break;
            case "LFU":
                simula(new LFU());
                break;
            default:
                System.out.println("Algoritmo não reconhecido.");
        }

    }

    private static void simulaTodos() {
        simula(new FCFS());
        simula(new LRU());
        simula(new LFU());
    }

    private static void simula(AlgoritmoSubstituicao algoritmo) {
        algoritmo.simula(new int[100], 10);
    }
}
