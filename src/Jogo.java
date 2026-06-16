public class Jogo {

    private static final int TENTATIVAS_MAXIMAS = 6;

    private static final String CAMINHO_PALAVRAS = "dados/palavras.txt";

    private static final int PAUSA_FEEDBACK_MS = 1200;

    private static final int PAUSA_RESULTADO_MS = 2500;

    private GerenciadorPalavras gerenciadorPalavras;

    private Interface ui;

    public Jogo() {
        this.gerenciadorPalavras = new GerenciadorPalavras(CAMINHO_PALAVRAS);
        this.ui                  = new Interface();
    }

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

    private void jogar(String nomeJogador) {
        Jogador jogador = new Jogador(nomeJogador, TENTATIVAS_MAXIMAS);
        Palavra palavra = new Palavra(gerenciadorPalavras.getPalavraAleatoria());

        while (jogador.temTentativas() && !palavra.isCompleta()) {
            ui.exibirEstado(jogador, palavra);
            char letra = ui.lerLetra();
            processarTentativa(jogador, palavra, letra);
            aguardar(PAUSA_FEEDBACK_MS);
        }

        if (palavra.isCompleta()) {
            ui.exibirVitoria(jogador, palavra);
        } else {
            ui.exibirDerrota(jogador, palavra);
        }
        aguardar(PAUSA_RESULTADO_MS);
    }

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

    private void aguardar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
