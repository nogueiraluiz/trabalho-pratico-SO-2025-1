package org.dcc062_2025_1.grupo16;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import lombok.SneakyThrows;
import org.dcc062_2025_1.grupo16.algoritmos.AlgoritmoSubstituicao;
import org.dcc062_2025_1.grupo16.algoritmos.FCFS;
import org.dcc062_2025_1.grupo16.algoritmos.LFU;
import org.dcc062_2025_1.grupo16.algoritmos.LRU;
import org.dcc062_2025_1.grupo16.util.Observador;
import org.dcc062_2025_1.grupo16.util.Resultado;

public class Main {

    /**
     * Observador que instrumenta os simuladores para obter dados de performance resultados das simulações.
     */
    private static final Observador observador = new Observador();

    public static void main(String[] args) {
        boolean usaSleeps = usaSleep(args);
        var algoritmos = coletaAlgoritmos(args);

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

            for (String arg : algoritmos) {
                simula(arg, sequencia, numeroFrames, usaSleeps);
            }
            observador.comparaResultados(numeroPaginas, numeroFrames, tamanhoSequencia);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Por favor, digite apenas números inteiros.");
        }
    }

    /**
     * Checa se o usuário optou por usar ou não sleeps nas simulações
     *
     * @param args argumentos passados para o programa
     * @return true se o usuário optou por usar sleeps ou false caso contrário
     */
    private static boolean usaSleep(String[] args) {
        String argSleep = "";
        for (String arg : args) {
            if (arg.equals("-sleep")) {
                argSleep = arg;
                break;
            }
        }
        return argSleep.equals("-sleep");
    }

    /**
     * @param args argumentos passados para o programa
     * @return argumentos que correspondem a algoritmos de paginação
     */
    private static Collection<String> coletaAlgoritmos(String[] args) {
        Set<String> algoritmos = new HashSet<>();
        Set<String> algoritmosValidos = Set.of("FCFS", "LRU", "LFU");
        for (String arg : args) {
            if (algoritmosValidos.contains(arg)) {
                algoritmos.add(arg);
            }
        }
        if (!algoritmos.isEmpty()) {
            return algoritmos;
        }
        return algoritmosValidos;
    }

    /**
     * Simula um algoritmo específco ou todos eles com uma a parametrização
     *
     * @param algoritmo    algoritmo a ser simulado ou uma String vazia para simular todos
     * @param sequencia    sequência de referências a páginas
     * @param numeroFrames número de frames na memória física simulada
     */
    private static void simula(String algoritmo, int[] sequencia, int numeroFrames, boolean usaSleeps) {
        if (algoritmo.isEmpty()) {
            simulaTodos(sequencia, numeroFrames, usaSleeps);
            return;
        }
        switch (algoritmo) {
            case "FCFS":
                simula(new FCFS(), sequencia, numeroFrames, usaSleeps);
                break;
            case "LRU":
                simula(new LRU(), sequencia, numeroFrames, usaSleeps);
                break;
            case "LFU":
                simula(new LFU(), sequencia, numeroFrames, usaSleeps);
                break;
            default:
                System.out.println("Algoritmo não reconhecido.");
        }
    }

    /**
     * Simula todos os algoritmos com os mesmos parâmetros de configuração
     *
     * @param sequencia    sequência de referências a páginas
     * @param numeroFrames número de frames na memória física simulada
     */
    private static void simulaTodos(int[] sequencia, int numeroFrames, boolean usaSleeps) {
        observador.limpaResultados();
        simula(new FCFS(), sequencia, numeroFrames, usaSleeps);
        simula(new LRU(), sequencia, numeroFrames, usaSleeps);
        simula(new LFU(), sequencia, numeroFrames, usaSleeps);
    }

    /**
     * Simula o funcionamento de um algoritmo de substituição de paginas com uma sequência de referências a páginas
     * simulando também a memória física e registra o resultado para comparação ou relatório individual.
     *
     * @param algoritmo algoritmo de substituição desejado
     * @param sequencia sequência de referências a páginas
     * @param numeroFrames número de frames na memória física simulada
     * @see Observador
     * @see AlgoritmoSubstituicao
     */
    @SneakyThrows
    private static void simula(AlgoritmoSubstituicao algoritmo, int[] sequencia, int numeroFrames, boolean usaSleep) {
        Resultado resultado = algoritmo.simula(sequencia, numeroFrames, usaSleep);
        observador.registraResultado(algoritmo.getNomeAlgoritmo(), resultado);
        Thread.sleep(3000);
    }

    /**
     * Gera uma sequência de inteiros para simular uma sequência de páginas referenciadas.
     *
     * @param numeroPaginas número de páginas na simulação de memória virtual
     * @param tamanho tamanho desejado para a sequência
     * @return um vetor de inteiros com a sequência gerada
     */
    private static int[] geraSequenciaAleatoria(int numeroPaginas, int tamanho) {
        Random random = new Random();
        int[] sequencia = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            sequencia[i] = random.nextInt(numeroPaginas);
        }

        return sequencia;
    }
}
