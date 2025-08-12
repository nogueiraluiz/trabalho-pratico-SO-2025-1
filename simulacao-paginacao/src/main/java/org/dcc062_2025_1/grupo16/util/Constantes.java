package org.dcc062_2025_1.grupo16.util;

/**
 * Classe não instanciável para armazenamento das constantes usadas no projeto.
 * <p>
 * Fornece métodos estáticos utilitários para formatação de impressão dos resultados no terminal.
 */
public class Constantes {

    private Constantes() {}

    public static final long MILISSEGUNDOS_SLEEP_ITERACOES = 1000;
    
    // Constantes para formatação de saída
    public static final int LARGURA_PAGINA = 10;      // Largura para o número da página
    public static final int LARGURA_STATUS = 10;      // Largura para o status (HIT/FAULT)
    public static final int LARGURA_MEMORIA = 30;     // Largura para representação da memória
    public static final int LARGURA_FREQUENCIA = 30;  // Largura para representação das frequências (LFU)
    
    // Constantes para formatação da tabela de comparação
    public static final int LARGURA_ALGORITMO = 15;   // Largura para o nome do algoritmo
    public static final int LARGURA_PAGE_FAULTS = 15; // Largura para o número de page faults
    public static final int LARGURA_SWAPS = 15;       // Largura para o número de swaps
    
    // Métodos de formatação
    public static String formatarPagina(int pagina) {
        return String.format("Página: %-" + LARGURA_PAGINA + "d", pagina);
    }
    
    public static String formatarStatus(boolean isHit) {
        return String.format("%-" + LARGURA_STATUS + "s", isHit ? "(HIT)" : "(FAULT)");
    }
}
