package org.dcc062_2025_1.grupo16;

import java.util.Random;
import java.util.Scanner;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.algoritmos.AlgoritmoSubstituicao;
import org.dcc062_2025_1.grupo16.algoritmos.FCFS;
import org.dcc062_2025_1.grupo16.algoritmos.LFU;
import org.dcc062_2025_1.grupo16.algoritmos.LRU;

public class Main {
    
    private static final Observador observador = new Observador();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numeroPaginas;
            int numeroFrames;
            int tamanhoSequencia;
            System.out.print("Digite o número de páginas: ");
            numeroPaginas = scanner.nextInt();
            if (numeroPaginas <= 0) {
                throw new IllegalArgumentException("O número de páginas deve ser maior que zero.");
            }

            System.out.print("Digite o número de frames: ");
            numeroFrames = scanner.nextInt();
            if (numeroFrames <= 0) {
                throw new IllegalArgumentException("O número de frames deve ser maior que zero.");
            }

            System.out.print("Digite quantas referências deseja simular: ");
            tamanhoSequencia = scanner.nextInt();
            if (tamanhoSequencia <= 0) {
                throw new IllegalArgumentException("O tamanho da sequência deve ser maior que zero.");
            }

            int[] sequencia = geraSequenciaAleatoria(numeroPaginas, tamanhoSequencia);

            System.out.println("\nSequência de referências gerada:");
            for (int i = 0; i < sequencia.length; i++) {
                System.out.print(sequencia[i] + " ");
                if ((i + 1) % 20 == 0) {
                    System.out.println();
                }
            }
            System.out.println("\n");

            if (args.length == 0) {
                simulaTodos(sequencia, numeroFrames);
                observador.comparaResultados(numeroPaginas, numeroFrames, tamanhoSequencia);
            } else {
                for (String arg : args) {
                    simula(arg, sequencia, numeroFrames);
                }
                if (args.length > 1) {
                    observador.comparaResultados(numeroPaginas, numeroFrames, tamanhoSequencia);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Por favor, digite apenas números inteiros.");
        }
    }

    private static void simula(String algoritmo, int[] sequencia, int numeroFrames) {
        if (algoritmo.isEmpty()) {
            simulaTodos(sequencia, numeroFrames);
            return;
        }
        switch (algoritmo) {
            case "FCFS":
                simula(new FCFS(), sequencia, numeroFrames);
                break;
            case "LRU":
                simula(new LRU(), sequencia, numeroFrames);
                break;
            case "LFU":
                simula(new LFU(), sequencia, numeroFrames);
                break;
            default:
                System.out.println("Algoritmo não reconhecido.");
        }
    }

    private static void simulaTodos(int[] sequencia, int numeroFrames) {
        observador.limpaResultados();
        simula(new FCFS(), sequencia, numeroFrames);
        simula(new LRU(), sequencia, numeroFrames);
        simula(new LFU(), sequencia, numeroFrames);
    }

    @SneakyThrows
    private static void simula(AlgoritmoSubstituicao algoritmo, int[] sequencia, int numeroFrames) {
        Resultado resultado = algoritmo.simula(sequencia, numeroFrames);
        observador.registraResultado(algoritmo.getNomeAlgoritmo(), resultado);
        Thread.sleep(3000);
    }

    private static int[] geraSequenciaAleatoria(int numeroPaginas, int tamanho) {
        Random random = new Random();
        int[] sequencia = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            sequencia[i] = random.nextInt(numeroPaginas);
        }

        return sequencia;
    }
}
