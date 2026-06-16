/**
 * Enum que representa o resultado de uma tentativa de adivinhacao no Jogo da Forca.
 *
 * <p>Utilizado pela classe {@link Palavra} para indicar o resultado
 * de cada letra tentada pelo jogador.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public enum ResultadoTentativa {

    /** A letra tentada esta presente na palavra secreta. */
    ACERTO,

    /** A letra tentada nao esta presente na palavra secreta. */
    ERRO,

    /** A letra ja foi tentada anteriormente nesta partida. */
    JA_TENTADA
}
