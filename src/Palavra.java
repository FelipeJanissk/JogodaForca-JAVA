import java.text.Normalizer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a palavra secreta do Jogo da Forca.
 *
 * <p>Gerencia o estado de letras reveladas, letras erradas e o progresso
 * da adivinhacao. Suporta palavras com acentos e caracteres especiais
 * (ex.: hifens), que sao tratados automaticamente.</p>
 *
 * <p>A comparacao de letras e feita sem diferenciar acentos, permitindo
 * que o jogador digite "A" para acertar tanto "A" quanto "A" com acento.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public class Palavra {

    /** Palavra original com acentos e maiusculas. */
    private String palavraOriginal;

    /** Palavra normalizada (sem acentos) para comparacao de letras. */
    private String palavraNormalizada;

    /** Estado atual da palavra: letras reveladas ou '_' para nao adivinhadas. */
    private char[] letrasReveladas;

    /** Conjunto de letras (normalizadas) que o jogador errou. */
    private Set<Character> letrasErradas;

    /** Conjunto de letras (normalizadas) que o jogador acertou. */
    private Set<Character> letrasAcertadas;

    /**
     * Cria uma nova instancia de {@code Palavra}.
     *
     * <p>Caracteres nao-alfabeticos (ex.: hifens) sao revelados automaticamente
     * na inicializacao.</p>
     *
     * @param palavra a palavra secreta a ser adivinhada
     */
    public Palavra(String palavra) {
        this.palavraOriginal    = palavra.toUpperCase().trim();
        this.palavraNormalizada = removerAcentos(this.palavraOriginal);
        this.letrasReveladas    = new char[this.palavraOriginal.length()];
        this.letrasErradas      = new HashSet<>();
        this.letrasAcertadas    = new HashSet<>();
        inicializarLetrasReveladas();
    }

    // -------------------------------------------------------------------------
    // Inicializacao
    // -------------------------------------------------------------------------

    /**
     * Inicializa o vetor de letras reveladas.
     * Letras ficam como '_'; caracteres nao-alfabeticos sao revelados de imediato.
     */
    private void inicializarLetrasReveladas() {
        for (int i = 0; i < palavraOriginal.length(); i++) {
            char c = palavraOriginal.charAt(i);
            letrasReveladas[i] = Character.isLetter(c) ? '_' : c;
        }
    }

    // -------------------------------------------------------------------------
    // Logica de jogo
    // -------------------------------------------------------------------------

    /**
     * Processa a tentativa de uma letra pelo jogador.
     *
     * <p>A letra e convertida para maiuscula e normalizada antes da comparacao,
     * portanto acentos sao ignorados na busca.</p>
     *
     * @param letra a letra tentada pelo jogador
     * @return {@link ResultadoTentativa#ACERTO} se a letra esta na palavra,
     *         {@link ResultadoTentativa#ERRO} se nao esta, ou
     *         {@link ResultadoTentativa#JA_TENTADA} se ja foi tentada
     */
    public ResultadoTentativa tentarLetra(char letra) {
        char letraNorm = removerAcentos(
                String.valueOf(Character.toUpperCase(letra))).charAt(0);

        // Verifica se a letra ja foi tentada anteriormente
        if (letrasAcertadas.contains(letraNorm) || letrasErradas.contains(letraNorm)) {
            return ResultadoTentativa.JA_TENTADA;
        }

        // Verifica se a letra existe na palavra normalizada
        boolean encontrou = false;
        for (int i = 0; i < palavraNormalizada.length(); i++) {
            if (palavraNormalizada.charAt(i) == letraNorm) {
                letrasReveladas[i] = palavraOriginal.charAt(i); // revela com acento original
                encontrou = true;
            }
        }

        if (encontrou) {
            letrasAcertadas.add(letraNorm);
            return ResultadoTentativa.ACERTO;
        } else {
            letrasErradas.add(letraNorm);
            return ResultadoTentativa.ERRO;
        }
    }

    /**
     * Verifica se todas as letras da palavra foram reveladas (vitoria).
     *
     * @return {@code true} se a palavra esta completamente adivinhada
     */
    public boolean isCompleta() {
        for (char c : letrasReveladas) {
            if (c == '_') return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // Representacao
    // -------------------------------------------------------------------------

    /**
     * Retorna o estado atual da palavra com letras reveladas separadas por espaco.
     * Letras nao adivinhadas aparecem como '_'.
     *
     * <p>Exemplo: {@code "J _ V _"}</p>
     *
     * @return string com o estado mascarado da palavra
     */
    public String getEstadoMascarado() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letrasReveladas.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(letrasReveladas[i]);
        }
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // Utilitarios
    // -------------------------------------------------------------------------

    /**
     * Remove acentos e diacriticos de uma string usando normalizacao Unicode NFD.
     *
     * @param texto texto a ser normalizado
     * @return texto sem acentos em letras maiusculas
     */
    public static String removerAcentos(String texto) {
        String nfd = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return nfd.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /**
     * Retorna o conjunto imutavel de letras erradas pelo jogador.
     *
     * @return conjunto de letras erradas (normalizadas, sem acentos)
     */
    public Set<Character> getLetrasErradas() {
        return Collections.unmodifiableSet(letrasErradas);
    }

    /**
     * Retorna o conjunto imutavel de letras acertadas pelo jogador.
     *
     * @return conjunto de letras acertadas (normalizadas, sem acentos)
     */
    public Set<Character> getLetrasAcertadas() {
        return Collections.unmodifiableSet(letrasAcertadas);
    }

    /**
     * Retorna o tamanho total da palavra, incluindo caracteres nao-alfabeticos.
     *
     * @return numero de caracteres na palavra
     */
    public int getTamanho() {
        return palavraOriginal.length();
    }

    /**
     * Retorna a palavra original em maiusculas, com acentos preservados.
     *
     * @return palavra original
     */
    public String getPalavraOriginal() {
        return palavraOriginal;
    }
}
