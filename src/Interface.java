import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Interface de linha de comando (CLI) do Jogo da Forca.
 *
 * <p>Responsavel por toda a interacao visual com o usuario: exibicao da forca,
 * estado da palavra, letras erradas e leitura de entradas do teclado.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public class Interface {

    /** Scanner compartilhado para leitura da entrada padrao. */
    private Scanner scanner;

    /**
     * Arte ASCII da forca em 7 estagios (0 = forca vazia, 6 = boneco completo).
     * Cada estagio corresponde a um numero de erros do jogador.
     */
    private static final String[] FORCA = {
        // Estagio 0 - forca vazia
        "  +---+\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // Estagio 1 - cabeca
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // Estagio 2 - cabeca + corpo
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // Estagio 3 - cabeca + corpo + braco esquerdo
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // Estagio 4 - cabeca + corpo + dois bracos
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // Estagio 5 - cabeca + corpo + bracos + perna esquerda
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " /    |\n" +
        "      |\n" +
        "=========",

        // Estagio 6 - boneco completo (derrota)
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " / \\  |\n" +
        "      |\n" +
        "========="
    };

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------

    /**
     * Cria a interface e inicializa o leitor de entrada padrao.
     */
    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    // -------------------------------------------------------------------------
    // Interacao inicial
    // -------------------------------------------------------------------------

    /**
     * Exibe a tela de boas-vindas e solicita o nome do jogador.
     *
     * @return nome informado pelo jogador (nunca nulo nem vazio)
     */
    public String lerNome() {
        limparTela();
        exibirBanner();
        System.out.println();
        System.out.print("  Digite seu nome: ");
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "Jogador" : nome;
    }

    // -------------------------------------------------------------------------
    // Exibicao do estado do jogo
    // -------------------------------------------------------------------------

    /**
     * Limpa a tela e exibe o estado completo do jogo:
     * forca, dados do jogador, palavra mascarada e letras erradas.
     *
     * @param jogador jogador da partida atual
     * @param palavra palavra sendo adivinhada
     */
    public void exibirEstado(Jogador jogador, Palavra palavra) {
        limparTela();
        exibirBanner();
        System.out.println();

        // Forca proporcional ao numero de erros
        int erros = jogador.getTentativasMaximas() - jogador.getTentativasRestantes();
        exibirForca(erros);

        System.out.println();
        System.out.println("  Jogador        : " + jogador.getNome());
        System.out.println("  Tentativas     : " + jogador.getTentativasRestantes()
                + " de " + jogador.getTentativasMaximas() + " restantes");
        System.out.println("  Tamanho        : " + palavra.getTamanho() + " letras");
        System.out.println();
        System.out.println("  Palavra        : " + palavra.getEstadoMascarado());
        System.out.println();

        exibirLetrasErradas(palavra.getLetrasErradas());
        System.out.println();
    }

    /**
     * Exibe a arte ASCII da forca no estagio correspondente ao numero de erros.
     *
     * @param erros numero de erros cometidos (0 a 6)
     */
    private void exibirForca(int erros) {
        int estagio = Math.min(erros, FORCA.length - 1);
        for (String linha : FORCA[estagio].split("\n")) {
            System.out.println("  " + linha);
        }
    }

    /**
     * Exibe as letras incorretas tentadas pelo jogador em ordem alfabetica.
     *
     * @param letrasErradas conjunto de letras erradas
     */
    private void exibirLetrasErradas(Set<Character> letrasErradas) {
        if (letrasErradas.isEmpty()) {
            System.out.println("  Letras erradas : (nenhuma ainda)");
        } else {
            List<Character> ordenadas = new ArrayList<>(letrasErradas);
            Collections.sort(ordenadas);
            StringBuilder sb = new StringBuilder("  Letras erradas : ");
            for (char c : ordenadas) {
                sb.append('[').append(c).append("] ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    // -------------------------------------------------------------------------
    // Leitura de entrada
    // -------------------------------------------------------------------------

    /**
     * Solicita e valida uma letra do jogador.
     * Continua pedindo ate receber exatamente uma letra do alfabeto.
     *
     * @return letra digitada, convertida para maiusculo
     */
    public char lerLetra() {
        while (true) {
            System.out.print("  Digite uma letra: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                return entrada.charAt(0);
            }
            System.out.println("  [!] Entrada invalida. Digite apenas UMA letra.");
        }
    }

    /**
     * Pergunta ao jogador se deseja jogar novamente.
     *
     * @return {@code true} se o jogador deseja iniciar uma nova partida
     */
    public boolean perguntarNovoJogo() {
        System.out.print("  Deseja jogar novamente? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }

    // -------------------------------------------------------------------------
    // Mensagens de feedback
    // -------------------------------------------------------------------------

    /**
     * Exibe uma mensagem de feedback indentada.
     *
     * @param mensagem texto a ser exibido
     */
    public void exibirMensagem(String mensagem) {
        System.out.println("  >> " + mensagem);
    }

    /**
     * Exibe a tela de vitoria ao final de uma partida vencida.
     *
     * @param jogador jogador vencedor
     * @param palavra palavra que foi adivinhada
     */
    public void exibirVitoria(Jogador jogador, Palavra palavra) {
        limparTela();
        exibirBanner();
        exibirForca(0);
        System.out.println();
        System.out.println("  +======================================+");
        System.out.println("  |  PARABENS, " + padRight(jogador.getNome() + "!", 26) + "|");
        System.out.println("  |  Voce acertou a palavra!             |");
        System.out.println("  +======================================+");
        System.out.println();
        System.out.println("  Palavra           : " + palavra.getPalavraOriginal());
        System.out.println("  Tentativas usadas : "
                + (jogador.getTentativasMaximas() - jogador.getTentativasRestantes())
                + " de " + jogador.getTentativasMaximas());
        System.out.println();
    }

    /**
     * Exibe a tela de derrota ao final de uma partida perdida.
     *
     * @param jogador jogador derrotado
     * @param palavra palavra que nao foi adivinhada
     */
    public void exibirDerrota(Jogador jogador, Palavra palavra) {
        limparTela();
        exibirBanner();
        exibirForca(6);
        System.out.println();
        System.out.println("  +======================================+");
        System.out.println("  |            GAME OVER                 |");
        System.out.println("  |  Suas tentativas acabaram!           |");
        System.out.println("  +======================================+");
        System.out.println();
        System.out.println("  Jogador   : " + jogador.getNome());
        System.out.println("  A palavra era: " + palavra.getPalavraOriginal());
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // Utilitarios de tela
    // -------------------------------------------------------------------------

    /** Exibe o banner/titulo do jogo. */
    private void exibirBanner() {
        System.out.println("  +========================================+");
        System.out.println("  |          J O G O   D A   F O R C A    |");
        System.out.println("  |          Desenvolvido em Java          |");
        System.out.println("  +========================================+");
    }

    /**
     * Limpa a tela do terminal.
     * Tenta executar o comando de limpeza nativo do sistema operacional.
     */
    private void limparTela() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            // Fallback: imprimir linhas em branco
            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Formata uma string com padding a direita ate o tamanho especificado.
     *
     * @param texto  texto a formatar
     * @param tamanho tamanho total desejado
     * @return string com espacos adicionais a direita
     */
    private String padRight(String texto, int tamanho) {
        return String.format("%-" + tamanho + "s", texto);
    }

    /**
     * Fecha o {@link Scanner}, liberando o recurso de entrada.
     * Deve ser chamado ao encerrar o jogo.
     */
    public void fechar() {
        scanner.close();
    }
}
