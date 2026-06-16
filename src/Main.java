/**
 * Ponto de entrada do Jogo da Forca.
 *
 * <p>Esta classe contem unicamente o metodo {@code main}, responsavel por
 * instanciar o controlador principal ({@link Jogo}) e dar inicio ao jogo.</p>
 *
 * <p><b>Como executar:</b></p>
 * <pre>
 *   javac -encoding UTF-8 -d out src\*.java
 *   java -cp out Main
 * </pre>
 *
 * @author Equipe
 * @version 1.0
 */
public class Main {

    /**
     * Metodo principal. Instancia e inicia o {@link Jogo}.
     *
     * @param args argumentos de linha de comando (nao utilizados)
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
    }
}
