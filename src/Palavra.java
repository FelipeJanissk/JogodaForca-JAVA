import java.text.Normalizer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Palavra {

    private String palavraOriginal;

    private String palavraNormalizada;

    private char[] letrasReveladas;

    private Set<Character> letrasErradas;

    private Set<Character> letrasAcertadas;

    public Palavra(String palavra) {
        this.palavraOriginal    = palavra.toUpperCase().trim();
        this.palavraNormalizada = removerAcentos(this.palavraOriginal);
        this.letrasReveladas    = new char[this.palavraOriginal.length()];
        this.letrasErradas      = new HashSet<>();
        this.letrasAcertadas    = new HashSet<>();
        inicializarLetrasReveladas();
    }

    private void inicializarLetrasReveladas() {
        for (int i = 0; i < palavraOriginal.length(); i++) {
            char c = palavraOriginal.charAt(i);
            letrasReveladas[i] = Character.isLetter(c) ? '_' : c;
        }
    }

    public ResultadoTentativa tentarLetra(char letra) {
        char letraNorm = removerAcentos(
                String.valueOf(Character.toUpperCase(letra))).charAt(0);

        if (letrasAcertadas.contains(letraNorm) || letrasErradas.contains(letraNorm)) {
            return ResultadoTentativa.JA_TENTADA;
        }

        boolean encontrou = false;
        for (int i = 0; i < palavraNormalizada.length(); i++) {
            if (palavraNormalizada.charAt(i) == letraNorm) {
                letrasReveladas[i] = palavraOriginal.charAt(i);
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

    public boolean isCompleta() {
        for (char c : letrasReveladas) {
            if (c == '_') return false;
        }
        return true;
    }

    public String getEstadoMascarado() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letrasReveladas.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(letrasReveladas[i]);
        }
        return sb.toString();
    }

    public static String removerAcentos(String texto) {
        String nfd = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return nfd.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    public Set<Character> getLetrasErradas() {
        return Collections.unmodifiableSet(letrasErradas);
    }

    public Set<Character> getLetrasAcertadas() {
        return Collections.unmodifiableSet(letrasAcertadas);
    }

    public int getTamanho() {
        return palavraOriginal.length();
    }

    public String getPalavraOriginal() {
        return palavraOriginal;
    }
}
