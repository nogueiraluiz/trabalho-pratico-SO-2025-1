package org.dcc062_2025_1.grupo16.util;

public class Sleeper {

    private Sleeper() {}

    /**
     * Tenta colocar a thread em execução em sleep
     *
     * @param duracao duração desejada em ms
     */
    public static void sleep(long duracao) {
        if (duracao == 0) {
            return;
        }
        try {
            Thread.sleep(duracao);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Erro ao colocar a thread em sleep.", e);
        }
    }

}
