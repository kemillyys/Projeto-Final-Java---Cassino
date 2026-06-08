public class Roleta extends Jogo {

    public Roleta() {
        super("Roleta", 5.0);
    }

    @Override
    public double jogar(double valorApostado) {
        int numero = (int)(Math.random() * 10);

        if (numero == 7) {
            return valorApostado * 5;
        }
        if (numero >= 5) {
            return valorApostado * 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + " - ganha se o numero sorteado for maior ou igual a 5";
    }
}
