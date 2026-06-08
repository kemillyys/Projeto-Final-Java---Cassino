public class Aposta {
    private Jogador jogador;
    private Jogo jogo;
    private double valorApostado;
    private double valorPremio;
    private String resultado;

    public Aposta(Jogador jogador, Jogo jogo, double valorApostado, double valorPremio, String resultado) {
        this.jogador = jogador;
        this.jogo = jogo;
        this.valorApostado = valorApostado;
        this.valorPremio = valorPremio;
        this.resultado = resultado;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public double getValorApostado() {
        return valorApostado;
    }

    public void setValorApostado(double valorApostado) {
        this.valorApostado = valorApostado;
    }

    public double getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(double valorPremio) {
        this.valorPremio = valorPremio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Jogador: " + jogador.getNome() + " | Jogo: " + jogo.getNome() + " | Apostado: R$ " + valorApostado + " | Premio: R$ " + valorPremio + " | Resultado: " + resultado;
    }

    public String escreveObjetoCsv(){
        return jogador.getLogin() + "," + jogo.getNome() + "," + valorApostado + "," + valorPremio + "," + resultado;
    }
}
