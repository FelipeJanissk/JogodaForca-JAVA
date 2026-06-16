public class Jogador {

    private String nome;

    private int tentativasRestantes;

    private int tentativasMaximas;

    public Jogador(String nome, int tentativasMaximas) {
        this.nome = nome;
        this.tentativasMaximas = tentativasMaximas;
        this.tentativasRestantes = tentativasMaximas;
    }

    public void diminuirTentativas() {
        if (tentativasRestantes > 0) {
            tentativasRestantes--;
        }
    }

    public boolean temTentativas() {
        return tentativasRestantes > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public int getTentativasMaximas() {
        return tentativasMaximas;
    }
}
