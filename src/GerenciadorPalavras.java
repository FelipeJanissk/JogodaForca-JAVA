import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gerencia o banco de palavras do Jogo da Forca.
 *
 * <p>Carrega palavras de um arquivo de texto (uma palavra por linha) e
 * fornece palavras aleatorias para cada partida. Caso o arquivo nao seja
 * encontrado, um conjunto padrao de palavras e utilizado como fallback.</p>
 *
 * <p><b>Formato do arquivo:</b> texto simples, UTF-8, uma palavra por linha.
 * Linhas vazias e palavras com menos de 4 caracteres sao ignoradas.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public class GerenciadorPalavras {

    /** Caminho para o arquivo de palavras. */
    private String caminhoPalavras;

    /** Lista de palavras carregadas. */
    private List<String> palavras;

    /** Gerador de numeros aleatorios para sorteio da palavra. */
    private Random random;

    /** Palavras padrao usadas como fallback se o arquivo nao for encontrado. */
    private static final String[] PALAVRAS_PADRAO = {
        "JAVA", "PROGRAMACAO", "COMPUTADOR", "ALGORITMO",
        "SOFTWARE", "INTERFACE", "ORIENTACAO", "HERANCA"
    };

    /** Tamanho minimo de uma palavra para ser incluida no banco. */
    private static final int TAMANHO_MINIMO = 4;

    /**
     * Cria um {@code GerenciadorPalavras} e carrega as palavras do arquivo.
     *
     * @param caminhoPalavras caminho relativo ou absoluto do arquivo de palavras
     */
    public GerenciadorPalavras(String caminhoPalavras) {
        this.caminhoPalavras = caminhoPalavras;
        this.palavras        = new ArrayList<>();
        this.random          = new Random();
        carregarPalavras();
    }

    // -------------------------------------------------------------------------
    // Carregamento de dados
    // -------------------------------------------------------------------------

    /**
     * Le o arquivo de palavras linha por linha e popula a lista interna.
     * Em caso de falha, carrega as palavras padrao.
     */
    private void carregarPalavras() {
        File arquivo = new File(caminhoPalavras);

        if (!arquivo.exists()) {
            System.err.println("[AVISO] Arquivo nao encontrado: " + caminhoPalavras);
            System.err.println("[AVISO] Usando banco de palavras padrao.");
            carregarPalavrasPadrao();
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty() && linha.length() >= TAMANHO_MINIMO) {
                    palavras.add(linha.toUpperCase());
                }
            }

            System.out.println("[INFO] " + palavras.size()
                    + " palavras carregadas de \"" + caminhoPalavras + "\".");

        } catch (IOException e) {
            System.err.println("[ERRO] Falha ao ler arquivo: " + e.getMessage());
            carregarPalavrasPadrao();
        }
    }

    /**
     * Carrega o conjunto padrao de palavras quando o arquivo nao esta disponivel.
     */
    private void carregarPalavrasPadrao() {
        for (String p : PALAVRAS_PADRAO) {
            palavras.add(p);
        }
        System.out.println("[INFO] " + palavras.size() + " palavras padrao carregadas.");
    }

    // -------------------------------------------------------------------------
    // Acesso publico
    // -------------------------------------------------------------------------

    /**
     * Retorna uma palavra escolhida aleatoriamente do banco.
     *
     * @return palavra aleatoria em letras maiusculas
     */
    public String getPalavraAleatoria() {
        if (palavras.isEmpty()) {
            return "JAVA";
        }
        return palavras.get(random.nextInt(palavras.size()));
    }

    /**
     * Retorna a quantidade total de palavras disponiveis no banco.
     *
     * @return numero de palavras carregadas
     */
    public int getQuantidadePalavras() {
        return palavras.size();
    }
}
