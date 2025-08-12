# Trabalho Prático da Disciplina DCC062 - Sistemas Operacionais

Este trabalho consiste da implementação de três algoritmos de substituição de página: FCFS, LRU e LFU. 

O programa não acessa memória diretamente, mas sim trata simulações de memória física e virtual para observar a diferença de comportamento entre as três abordagens para o problema.

# Como Executar o Programa

## Requisitos

É necessário ter o JRE 21 instalado na sua máquina

## Passo a passo

1. Clonar este respositório para o diretório desejado
2. Navegar até o diretório do projeto `"trabalho-pratico-SO-20251-1/simulacao-paginacao"`
3. Gerar o arquivo JAR usando o Maven wrapper usando o comando: `./mvnw clean package`
4. Executar o programa usando o Java com o comando: `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar <argumento1> <argumento2>`
5. Inserir os três valores inteiros solicitados no terminal
   1. Número de páginas presentes na simulação da memória virtual
   2. Número de frames presentes na simulação da memória física
   3. Número de referências a páginas que deseja simular

### Opções

Executar o programa com o comando definido no item 4 dará o comportamento padrão básico, ou seja, executará o programa simulando todos os algoritmos e sem o uso de sleeps para facilitar a legibilidade no terminal. Alguns argumentos podem ser passados para o projeto para mudar o comportamento, permitindo executar as simulações de apenas um subconjunto dos algoritmos ou usar sleeps para visualizar a execução pausadamente.

Os argumentos permitidos são:
1. `-sleep`: ativa o uso de sleeps para visualização mais lenta da simulação. Não recomendamos o uso desse argumento para simulações grandes.
2. `FCFS`: informa ao programa que o algoritmo FCFS deve ser simulado
3. `LFU`: informa ao programa que o algoritmo LFU deve ser simulado
4. `LRU`: informa ao programa que o algoritmo LRU deve ser simulado

Qualquer combinação desses argumentos em qualquer ordem atinge o mesmo resultado. A única restrição é que argumentos não sejam repetidos.

#### Exemplos de execuções

- `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar`: executa o programa sem usar sleeps e simulando todos os algoritmos
- `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar -sleep`: executa o programa usando sleeps e simulando todos os algoritmos 
- `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar FCFS LFU`: executa o programa sem usar sleeps e simulando somente os algoritmos FCFS e LFU
- `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar LRU -sleep`: executa o programa usando sleeps e simulando somente o algoritmo LRU

#### Observações importantes

1. Não há argumentos inválidos, mas argumentos não identificados resultarão no comportamento padrão descrito no começo desta seção. Por exemplo, executar usando `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar sleep FCF` faz com que o programa seja executado sem sleeps e simulando todos os algoritmos.
2. Argumentos de algoritmo repetidos não têm impacto, já que um algoritmo é simulado somente uma vez por execução. Exemplo: `java -jar target/simulacao-paginacao-1.0-SNAPSHOT.jar LRU LRU`