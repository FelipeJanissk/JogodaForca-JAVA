# Manual do Usuário - Jogo da Forca

Este é um jogo da forca simples feito em Java para rodar direto no terminal. O jogo escolhe uma palavra aleatória de um arquivo de texto e você tem 6 tentativas para tentar adivinhar qual é.

## Como compilar e executar

Se você estiver usando Windows, deixamos dois arquivos `.bat` para facilitar:
1. Primeiro, dê dois cliques no arquivo `compilar.bat`. Ele vai compilar o código e criar uma pasta chamada `out/`.
2. Depois, dê dois cliques em `executar.bat` para iniciar o jogo.

**Pelo terminal (Linux/Mac ou se preferir rodar manualmente):**
Abra o terminal na pasta principal do projeto e rode os seguintes comandos:
```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```
(Atenção: é importante rodar o programa sempre a partir da pasta principal do projeto, senão o código não vai encontrar o arquivo de texto com as palavras).

## Como jogar

Ao iniciar o programa, ele vai te pedir um nome. Em seguida, a tela do jogo vai mostrar a forca, a quantidade de letras da palavra oculta e as tentativas restantes (que começam em 6).

- Digite uma letra e aperte Enter.
- Se você acertar, a letra aparece no lugar dela na palavra.
- Se errar, o desenho da forca avança e você perde uma tentativa. As letras erradas ficam anotadas na tela para você não se perder.
- Se você digitar uma letra que já tinha tentado antes (certa ou errada), o jogo só avisa e não desconta nenhuma tentativa.
- O jogo termina se você adivinhar a palavra inteira (vitória) ou se esgotar as 6 tentativas e o boneco for enforcado (derrota).

Quando a partida acabar, o jogo pergunta se você quer jogar de novo. É só digitar "S" para continuar ou "N" para sair.

## Como alterar o banco de palavras

As palavras usadas no jogo ficam salvas no arquivo `dados/palavras.txt`. 
Se quiser adicionar palavras novas ou mudar o tema, é só abrir esse arquivo em um bloco de notas e colocar as palavras que quiser (uma em cada linha). 

O jogo já está programado para lidar com palavras acentuadas. Então, se a palavra no arquivo for "Coração", o jogador só precisa digitar "C" ou "A" normalmente que o jogo vai entender.

## Problemas comuns

- **Erro de javac não reconhecido:** Significa que o Java (JDK) não está instalado ou configurado no computador.
- **Arquivo não encontrado:** Acontece se você tentar rodar o jogo estando dentro da pasta `src`. Volte para a pasta principal e tente executar de novo.
