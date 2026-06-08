public class Pessoa implements Comparable<Pessoa>{
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome + "," + cpf;
    }

    public String escreveObjetoCsv(){
        return nome + "," + cpf;
    }

    public int compareTo(Pessoa outra){
        return this.nome.compareTo(outra.nome);
    }
}
