public abstract class Jogo {
    private String nome;
    private double valorMinimo;

    public Jogo(String nome, double valorMinimo) {
        this.nome = nome;
        this.valorMinimo = valorMinimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public abstract double jogar(double valorApostado);

    @Override
    public String toString() {
        return nome + " - aposta minima: R$ " + valorMinimo;
    }
}
