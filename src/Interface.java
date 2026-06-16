import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Interface {

    private Scanner scanner;

    private static final String[] FORCA = {

        "  +---+\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " /    |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " / \\  |\n" +
        "      |\n" +
        "========="
    };


    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public String lerNome() {
        limparTela();
        exibirBanner();
        System.out.println();
        System.out.print("  Digite seu nome: ");
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "Jogador" : nome;
    }

    public void exibirEstado(Jogador jogador, Palavra palavra) {
        limparTela();
        exibirBanner();
        System.out.println();

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

    private void exibirForca(int erros) {
        int estagio = Math.min(erros, FORCA.length - 1);
        for (String linha : FORCA[estagio].split("\n")) {
            System.out.println("  " + linha);
        }
    }

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

    public boolean perguntarNovoJogo() {
        System.out.print("  Deseja jogar novamente? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println("  >> " + mensagem);
    }

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

    private void exibirBanner() {
        System.out.println("  +========================================+");
        System.out.println("  |          J O G O   D A   F O R C A    |");
        System.out.println("  |          Desenvolvido em Java          |");
        System.out.println("  +========================================+");
    }

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
            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }

    private String padRight(String texto, int tamanho) {
        return String.format("%-" + tamanho + "s", texto);
    }


    public void fechar() {
        scanner.close();
    }
}
