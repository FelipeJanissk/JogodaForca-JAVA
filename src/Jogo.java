/**
 * Controlador principal do Jogo da Forca.
 *
 * <p>Orquestra o fluxo completo de uma sessao de jogo: solicitar nome do
 * jogador, executar partidas, processar tentativas e oferecer nova partida
 * ao final de cada rodada.</p>
 *
 * <p>Constantes de configuracao (tentativas e caminho do arquivo de palavras)
 * estao declaradas nesta classe para facilitar a manutencao.</p>
 *
 * @author Equipe
 * @version 1.0
 */
public class Jogo {

    /**
     * Numero maximo de tentativas incorretas por partida.
     * Deve estar entre 1 e 6 para corresponder aos estagios da forca.
     */
    private static final int TENTATIVAS_MAXIMAS = 6;

    /** Caminho relativo para o arquivo de palavras (relativo ao diretorio de execucao). */
    private static final String CAMINHO_PALAVRAS = "dados/palavras.txt";

    /** Tempo de espera (ms) apos cada feedback de tentativa, para o jogador ler a mensagem. */
    private static final int PAUSA_FEEDBACK_MS = 1200;

    /** Tempo de espera (ms) ao exibir a tela de vitoria ou derrota. */
    private static final int PAUSA_RESULTADO_MS = 2500;

    /** Gerenciador responsavel por fornecer palavras aleatorias. */
    private GerenciadorPalavras gerenciadorPalavras;

    /** Interface de linha de comando para interacao com o usuario. */
    private Interface ui;

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------

    /**
     * Inicializa o jogo carregando o banco de palavras e preparando a interface.
     */
    public Jogo() {
        this.gerenciadorPalavras = new GerenciadorPalavras(CAMINHO_PALAVRAS);
        this.ui                  = new Interface();
    }

    // -------------------------------------------------------------------------
    // Fluxo principal
    // -------------------------------------------------------------------------

    /**
     * Inicia o fluxo principal do jogo.
     *
     * <p>Solicita o nome do jogador e gerencia o loop de partidas ate que
     * o jogador opte por sair.</p>
     */
    public void iniciar() {
        String nomeJogador = ui.lerNome();
        ui.exibirMensagem("Bem-vindo, " + nomeJogador + "! Vamos comecar!");

        boolean continuar = true;
        while (continuar) {
            jogar(nomeJogador);
            continuar = ui.perguntarNovoJogo();
        }

        ui.exibirMensagem("Obrigado por jogar! Ate a proxima, " + nomeJogador + "!");
        ui.fechar();
    }

    // -------------------------------------------------------------------------
    // Logica de partida
    // -------------------------------------------------------------------------

    /**
     * Executa uma partida completa do Jogo da Forca.
     *
     * <p>Cria um novo {@link Jogador} e uma nova {@link Palavra} para cada
     * partida, mantendo o loop de adivinhacao ate vitoria ou derrota.</p>
     *
     * @param nomeJogador nome do jogador para a partida
     */
    private void jogar(String nomeJogador) {
        Jogador jogador = new Jogador(nomeJogador, TENTATIVAS_MAXIMAS);
        Palavra palavra = new Palavra(gerenciadorPalavras.getPalavraAleatoria());

        // Loop principal da partida
        while (jogador.temTentativas() && !palavra.isCompleta()) {
            ui.exibirEstado(jogador, palavra);
            char letra = ui.lerLetra();
            processarTentativa(jogador, palavra, letra);
            aguardar(PAUSA_FEEDBACK_MS);
        }

        // Exibe resultado e aguarda o jogador ler
        if (palavra.isCompleta()) {
            ui.exibirVitoria(jogador, palavra);
        } else {
            ui.exibirDerrota(jogador, palavra);
        }
        aguardar(PAUSA_RESULTADO_MS);
    }

    /**
     * Processa a tentativa de uma letra e exibe o feedback adequado ao jogador.
     *
     * @param jogador jogador que realizou a tentativa
     * @param palavra palavra sendo adivinhada
     * @param letra   letra tentada pelo jogador
     */
    private void processarTentativa(Jogador jogador, Palavra palavra, char letra) {
        ResultadoTentativa resultado = palavra.tentarLetra(letra);

        switch (resultado) {
            case ACERTO:
                ui.exibirMensagem("Boa! A letra '" + letra + "' esta na palavra!");
                break;
            case ERRO:
                jogador.diminuirTentativas();
                ui.exibirMensagem("Ops! '" + letra + "' nao esta na palavra. "
                        + jogador.getTentativasRestantes() + " tentativa(s) restante(s).");
                break;
            case JA_TENTADA:
                ui.exibirMensagem("A letra '" + letra
                        + "' ja foi tentada. Escolha outra!");
                break;
            default:
                break;
        }
    }

    // -------------------------------------------------------------------------
    // Utilitarios
    // -------------------------------------------------------------------------

    /**
     * Pausa a execucao pelo tempo especificado em milissegundos.
     *
     * @param ms tempo de pausa em milissegundos
     */
    private void aguardar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
