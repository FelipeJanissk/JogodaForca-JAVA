/**
 * Representa o jogador do Jogo da Forca.
 *
 * <p>Armazena as informacoes do jogador, como nome e controle
 * de tentativas disponiveis durante a partida.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public class Jogador {

    /** Nome do jogador. */
    private String nome;

    /** Numero de tentativas ainda disponiveis. */
    private int tentativasRestantes;

    /** Numero maximo de tentativas definido para a partida. */
    private int tentativasMaximas;

    /**
     * Cria um novo jogador com nome e numero maximo de tentativas.
     *
     * @param nome             nome do jogador
     * @param tentativasMaximas numero maximo de tentativas permitidas na partida
     */
    public Jogador(String nome, int tentativasMaximas) {
        this.nome = nome;
        this.tentativasMaximas = tentativasMaximas;
        this.tentativasRestantes = tentativasMaximas;
    }

    /**
     * Reduz o numero de tentativas restantes em uma unidade.
     * O valor nunca ficara abaixo de zero.
     */
    public void diminuirTentativas() {
        if (tentativasRestantes > 0) {
            tentativasRestantes--;
        }
    }

    /**
     * Verifica se o jogador ainda possui tentativas disponiveis.
     *
     * @return {@code true} se ha tentativas restantes; {@code false} caso contrario
     */
    public boolean temTentativas() {
        return tentativasRestantes > 0;
    }

    /**
     * Retorna o nome do jogador.
     *
     * @return nome do jogador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o numero de tentativas restantes.
     *
     * @return tentativas restantes
     */
    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    /**
     * Retorna o numero maximo de tentativas da partida.
     *
     * @return tentativas maximas
     */
    public int getTentativasMaximas() {
        return tentativasMaximas;
    }
}
