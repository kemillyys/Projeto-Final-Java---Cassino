public class Jogador extends Pessoa {
    private String login;
    private String senhaAtual;
    private String senhaAnterior1;
    private String senhaAnterior2;
    private Carteira carteira;

    public Jogador(String nome, String cpf, String login, String senhaAtual, String senhaAnterior1, String senhaAnterior2, double saldo) {
        super(nome, cpf);
        this.login = login;
        this.senhaAtual = senhaAtual;
        this.senhaAnterior1 = senhaAnterior1;
        this.senhaAnterior2 = senhaAnterior2;
        this.carteira = new Carteira(saldo);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaAnterior1() {
        return senhaAnterior1;
    }

    public void setSenhaAnterior1(String senhaAnterior1) {
        this.senhaAnterior1 = senhaAnterior1;
    }

    public String getSenhaAnterior2() {
        return senhaAnterior2;
    }

    public void setSenhaAnterior2(String senhaAnterior2) {
        this.senhaAnterior2 = senhaAnterior2;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public boolean conferirSenha(String senhaDigitada) {
        return senhaAtual.equals(senhaDigitada);
    }

    public boolean senhaJaUsada(String novaSenha) {
        if (senhaAtual.equals(novaSenha)) {
            return true;
        }
        if (senhaAnterior1.equals(novaSenha)) {
            return true;
        }
        if (senhaAnterior2.equals(novaSenha)) {
            return true;
        }
        return false;
    }

    public boolean trocarSenha(String novaSenha) {
        if (senhaJaUsada(novaSenha)) {
            return false;
        }
        senhaAnterior2 = senhaAnterior1;
        senhaAnterior1 = senhaAtual;
        senhaAtual = novaSenha;
        return true;
    }

    @Override
    public String toString() {
        return getNome() + " - CPF: " + getCpf() + " - Login: " + login + " - " + carteira.toString();
    }

    public String escreveObjetoCsv(){
        return getNome() + "," + getCpf() + "," + login + "," + senhaAtual + "," + senhaAnterior1 + "," + senhaAnterior2 + "," + carteira.getSaldo();
    }
}
