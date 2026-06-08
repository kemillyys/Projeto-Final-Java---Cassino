public class Carteira {
    private double saldo;

    public Carteira(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        saldo = saldo + valor;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo = saldo - valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Saldo: R$ " + saldo;
    }
}
