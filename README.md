# Jogo da Forca em Java

Projeto acadêmico da disciplina de Modelagem e Programação de Sistemas Comptacionais na UNOESC CHAPECÓ.
Data de entrega: 16/06/2026.

Este é um Jogo da Forca clássico que roda no terminal (linha de comando). O jogo carrega palavras aleatórias de um arquivo de texto e o jogador tem 6 tentativas para adivinhar a palavra, vendo a forca ser desenhada na tela a cada erro que comete.

## Integrantes do Grupo

- [Felipe Samuel Janissk] - [@FelipeJanissk]
- [Ricardo Kerppers Guarnieri] - []

## Como rodar o jogo

**Se você estiver no Windows:**
O jeito mais fácil é usar os arquivos que já deixamos prontos na pasta:
1. Dê dois cliques em `compilar.bat` para compilar o código.
2. Dê dois cliques em `executar.bat` para começar a jogar.

**No Linux/Mac (ou pelo terminal):**
Abra o terminal na pasta principal do projeto e digite:
```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

## Como o código está organizado

Nós dividimos o projeto aplicando conceitos de Programação Orientada a Objetos (POO). As principais classes na pasta `src` são:

- `Main.java`: Onde o programa inicia.
- `Jogo.java`: Controla o andamento da partida (o loop do jogo).
- `Jogador.java`: Guarda o nome do jogador e quantas tentativas ele ainda tem.
- `Palavra.java`: Gerencia a palavra que está sendo adivinhada, as letras reveladas e ignora acentos se o usuário digitar sem.
- `Interface.java`: Cuida de desenhar a forca na tela e ler as letras que o jogador digita.
- `GerenciadorPalavras.java`: Lê o arquivo de texto `dados/palavras.txt` para sortear uma palavra diferente a cada partida.

Na pasta `diagramas/` estão os arquivos com os diagramas UML solicitados (Casos de Uso, Classes, Sequência e Atividades) que usamos para planejar essa estrutura.

## Mais detalhes

Para ver as regras certinhas do jogo, como funciona e como adicionar palavras novas no banco de dados, dê uma olhada no `manual_usuario.md` que está dentro da pasta `docs/`.
