# Jogo da Forca — Java CLI

> Projeto desenvolvido para a disciplina de Modelagem e Programação de Sistemas Computacionais
> UNOESC CHAPECÓ 
> Data de entrega: 16/06/2026

---

## 📋 Descrição

Implementação do clássico **Jogo da Forca** em Java, com interface de linha de comando (CLI).  
O sistema seleciona palavras aleatórias de um banco de dados em arquivo texto e desafia o jogador a adivinhá-las letra por letra, com no máximo **6 tentativas incorretas**.

---

## 👥 Integrantes da Equipe

| Nome | GitHub |
|------|--------|
| [Ricardo Kerppers] | [] |
| [Felipe Samuel Janissk] | [@FelipeJanissk] |

---

## 🗂️ Estrutura do Projeto

```
jogo-da-forca/
├── src/
│   ├── Main.java                  # Ponto de entrada do programa
│   ├── Jogo.java                  # Controlador principal (lógica do jogo)
│   ├── Jogador.java               # Entidade jogador (nome e tentativas)
│   ├── Palavra.java               # Entidade palavra (estado e adivinhação)
│   ├── GerenciadorPalavras.java   # Persistência: leitura do arquivo de palavras
│   ├── Interface.java             # Interface CLI (exibição e entrada)
│   └── ResultadoTentativa.java    # Enum de resultados de tentativa
├── dados/
│   └── palavras.txt               # Banco de palavras (uma por linha)
├── diagramas/
│   ├── diagrama_casos_uso.png
│   ├── diagrama_classes.png
│   ├── diagrama_sequencia.png
│   └── diagrama_atividades.png
├── docs/
│   └── manual_usuario.md          # Manual de uso do sistema
├── compilar.bat                   # Script de compilação (Windows)
├── executar.bat                   # Script de execução (Windows)
└── README.md
```

---

## ▶️ Como Compilar e Executar

### Pré-requisito
- Java JDK 8 ou superior instalado
- Variável `JAVA_HOME` configurada (ou `javac`/`java` no PATH)

### Windows (scripts prontos)

```bat
:: 1. Compilar
compilar.bat

:: 2. Executar
executar.bat
```

### Terminal (manual)

```bash
# Na raiz do projeto:
javac -encoding UTF-8 -d out src\*.java
java -cp out Main
```

---

## 🎮 Funcionalidades

- ✅ Escolha aleatória de palavra a partir do arquivo `dados/palavras.txt`
- ✅ Configuração explícita de **6 tentativas** por partida
- ✅ Exibição da **forca ASCII** em 7 estágios progressivos
- ✅ Exibição do tamanho da palavra, letras erradas e tentativas restantes
- ✅ Ignora letras já tentadas (sem penalidade)
- ✅ Suporte a palavras acentuadas (jogador digita sem acento)
- ✅ Detecção de vitória e derrota com tela dedicada
- ✅ Opção de jogar novamente ao final de cada partida

---

## 🏗️ Arquitetura (POO)

O projeto aplica os princípios de **Programação Orientada a Objetos**:

| Princípio | Aplicação |
|-----------|-----------|
| **Encapsulamento** | Atributos privados com getters em `Jogador`, `Palavra` |
| **Responsabilidade Única** | Cada classe tem uma responsabilidade bem definida |
| **Abstração** | `Interface` abstrai toda a I/O do resto do sistema |
| **Enum** | `ResultadoTentativa` tipifica os resultados possíveis |

---

## 📊 Diagramas UML

Os diagramas estão disponíveis na pasta `diagramas/`:

- **Casos de Uso**: atores e funcionalidades do sistema
- **Classes**: estrutura e relacionamentos entre as classes
- **Sequência**: interação entre objetos na adivinhação
- **Atividades**: fluxo de iniciar jogo e fazer adivinhação

---

## 📄 Licença

Projeto acadêmico — uso educacional.
