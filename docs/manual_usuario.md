# Manual do Usuário — Jogo da Forca

**Versão:** 1.0  
**Linguagem:** Java  
**Interface:** Linha de Comando (CLI)

---

## 1. Introdução

O **Jogo da Forca** é um jogo de adivinhação de palavras. O sistema escolhe uma palavra aleatória de um banco de dados e o jogador deve descobri-la letra por letra, antes que a figura da forca seja completamente desenhada.

---

## 2. Requisitos do Sistema

| Item | Requisito mínimo |
|------|-----------------|
| Sistema Operacional | Windows 10/11, Linux ou macOS |
| Java | JDK 8 ou superior |
| Espaço em disco | < 1 MB |

Verifique se o Java está instalado executando no terminal:
```
java -version
```

---

## 3. Instalação e Configuração

### 3.1 Estrutura de Arquivos

Certifique-se de que a estrutura de pastas está correta:

```
jogo-da-forca/
├── src/         ← código-fonte Java
├── dados/
│   └── palavras.txt   ← banco de palavras (obrigatório)
├── compilar.bat
└── executar.bat
```

### 3.2 Banco de Palavras

O arquivo `dados/palavras.txt` contém as palavras do jogo.  
**Formato:** uma palavra por linha, codificação UTF-8.

Exemplo:
```
Tecnologia
Aventura
Programacao
```

Para adicionar novas palavras, basta editar o arquivo `palavras.txt` com qualquer editor de texto.

---

## 4. Como Compilar

### Windows (recomendado)

Clique duas vezes em **`compilar.bat`** ou execute no terminal:
```bat
compilar.bat
```

### Terminal manual
```bash
javac -encoding UTF-8 -d out src\*.java
```

Se a compilação for bem-sucedida, será criada a pasta `out/` com os arquivos `.class`.

---

## 5. Como Executar

### Windows (recomendado)

Clique duas vezes em **`executar.bat`** ou execute no terminal:
```bat
executar.bat
```

> ⚠️ **Importante:** execute sempre a partir da pasta raiz do projeto (`jogo-da-forca/`), pois o arquivo `dados/palavras.txt` é lido a partir do diretório de execução.

### Terminal manual
```bash
java -cp out Main
```

---

## 6. Como Jogar

### 6.1 Tela Inicial

Ao iniciar o jogo, o sistema solicitará seu nome:

```
  +========================================+
  |          J O G O   D A   F O R C A    |
  |          Desenvolvido em Java          |
  +========================================+

  Digite seu nome: _
```

### 6.2 Tela de Jogo

Durante a partida, a tela exibe:

```
  +---+
  |   |
  O   |        ← Forca (avança a cada erro)
 /|   |
      |
      |
=========

  Jogador        : Maria
  Tentativas     : 4 de 6 restantes    ← Tentativas restantes
  Tamanho        : 10 letras           ← Tamanho da palavra

  Palavra        : T E C N O L O G _ _  ← Letras reveladas

  Letras erradas : [A] [B] [F]          ← Letras incorretas
```

### 6.3 Regras

| Situação | O que acontece |
|----------|---------------|
| Letra correta | Letra é revelada na(s) posição(ões) correspondente(s) |
| Letra errada | A forca avança um estágio; tentativas diminuem em 1 |
| Letra repetida | Ignorada; nenhuma penalidade |
| 6 erros acumulados | **Derrota** — palavra revelada |
| Todas as letras reveladas | **Vitória** — parabéns! |

> 💡 **Dica:** Palavras com acentos (ex.: "Inspiração") podem ser descobertas digitando a letra sem acento (ex.: "A" resolve tanto "A" quanto "Á", "Ã", "Â").

### 6.4 Fim de Partida

Ao terminar a partida (vitória ou derrota), o sistema pergunta:
```
  Deseja jogar novamente? (S/N):
```
- Digite **S** ou **SIM** para iniciar nova partida
- Digite **N** ou qualquer outra tecla para sair

---

## 7. Estágios da Forca

| Erros | Estágio |
|-------|---------|
| 0 | Forca vazia |
| 1 | Cabeça |
| 2 | Cabeça + Corpo |
| 3 | Cabeça + Corpo + Braço esquerdo |
| 4 | Cabeça + Corpo + Dois braços |
| 5 | Cabeça + Corpo + Braços + Perna esquerda |
| 6 | Boneco completo → **DERROTA** |

---

## 8. Solução de Problemas

| Problema | Solução |
|----------|---------|
| `javac: command not found` | Instale o Java JDK e configure o PATH |
| `Arquivo nao encontrado: dados/palavras.txt` | Execute o jogo a partir da pasta raiz do projeto |
| Caracteres estranhos no terminal | No Windows, execute `chcp 65001` no terminal antes de rodar o jogo |
| `Main.class nao encontrado` | Execute `compilar.bat` antes de `executar.bat` |

---

## 9. Contato / Suporte

Em caso de dúvidas, contate a equipe de desenvolvimento:
- [email ou contato da equipe]
