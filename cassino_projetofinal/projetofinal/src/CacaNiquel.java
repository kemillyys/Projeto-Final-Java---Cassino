public class CacaNiquel extends Jogo {

    public CacaNiquel() {
        super("Caca Niquel", 3.0);
    }

    @Override
    public double jogar(double valorApostado) {
        int n1 = (int)(Math.random() * 3);
        int n2 = (int)(Math.random() * 3);
        int n3 = (int)(Math.random() * 3);

        if (n1 == n2 && n2 == n3) {
            return valorApostado * 4;
        }
        if (n1 == n2 || n1 == n3 || n2 == n3) {
            return valorApostado * 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + " - ganha se repetir simbolos";
    }
}
