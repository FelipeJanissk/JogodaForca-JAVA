import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorPalavras {

    private String caminhoPalavras;

    private List<String> palavras;

    private Random random;

    private static final String[] PALAVRAS_PADRAO = {
        "JAVA", "PROGRAMACAO", "COMPUTADOR", "ALGORITMO",
        "SOFTWARE", "INTERFACE", "ORIENTACAO", "HERANCA"
    };

    private static final int TAMANHO_MINIMO = 4;

    public GerenciadorPalavras(String caminhoPalavras) {
        this.caminhoPalavras = caminhoPalavras;
        this.palavras        = new ArrayList<>();
        this.random          = new Random();
        carregarPalavras();
    }

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

    private void carregarPalavrasPadrao() {
        for (String p : PALAVRAS_PADRAO) {
            palavras.add(p);
        }
        System.out.println("[INFO] " + palavras.size() + " palavras padrao carregadas.");
    }

    public String getPalavraAleatoria() {
        if (palavras.isEmpty()) {
            return "JAVA";
        }
        return palavras.get(random.nextInt(palavras.size()));
    }

    public int getQuantidadePalavras() {
        return palavras.size();
    }
}
