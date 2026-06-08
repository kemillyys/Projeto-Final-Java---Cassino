import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Cassino {
    private ArrayList<Jogador> jogadores;
    private ArrayList<Jogo> jogos;
    private ArrayList<Aposta> apostas;

    public Cassino() {
        jogadores = new ArrayList<>();
        jogos = new ArrayList<>();
        apostas = new ArrayList<>();

        jogos.add(new Roleta());
        jogos.add(new CacaNiquel());
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(ArrayList<Jogo> jogos) {
        this.jogos = jogos;
    }

    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(ArrayList<Aposta> apostas) {
        this.apostas = apostas;
    }

    public void carregarDados() {
        carregarJogadores();
        carregarApostas();
    }

    public void carregarJogadores() {
        try {
            FileReader fl = new FileReader("jogadores.csv");
            BufferedReader br = new BufferedReader(fl);
            String line = null;

            while ((line = br.readLine()) != null) {
                String dados[] = line.split(",");
                if (dados.length >= 7) {
                    Jogador j = new Jogador(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], Double.parseDouble(dados[6]));
                    jogadores.add(j);
                }
            }
            br.close();
        } catch (Exception e) {
            Jogador padrao = new Jogador("Kemilly", "000.000.000-00", "kemilly", "123", "111", "222", 100.0);
            jogadores.add(padrao);
            salvarTodosJogadores();
        }
    }

    public void carregarApostas() {
        try {
            FileReader fl = new FileReader("apostas.csv");
            BufferedReader br = new BufferedReader(fl);
            String line = null;

            while ((line = br.readLine()) != null) {
                String dados[] = line.split(",");
                if (dados.length >= 5) {
                    Jogador jogador = buscarJogadorPorLogin(dados[0]);
                    Jogo jogo = buscarJogoPorNome(dados[1]);
                    if (jogador != null && jogo != null) {
                        Aposta a = new Aposta(jogador, jogo, Double.parseDouble(dados[2]), Double.parseDouble(dados[3]), dados[4]);
                        apostas.add(a);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            salvarTodasApostas();
        }
    }

    public void salvarTodosJogadores() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("jogadores.csv"));
            for (int i = 0; i < jogadores.size(); i++) {
                bw.write(jogadores.get(i).escreveObjetoCsv());
                if (i < jogadores.size() - 1) {
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar jogadores.");
        }
    }

    public void salvarTodasApostas() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("apostas.csv"));
            for (int i = 0; i < apostas.size(); i++) {
                bw.write(apostas.get(i).escreveObjetoCsv());
                if (i < apostas.size() - 1) {
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar apostas.");
        }
    }

    public void cadastrarJogador(Jogador jogador) {
        jogadores.add(jogador);
        salvarTodosJogadores();
    }

    public void cadastrarAposta(Aposta aposta) {
        apostas.add(aposta);
        salvarTodasApostas();
        salvarTodosJogadores();
    }

    public Jogador buscarJogadorPorLogin(String login) {
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getLogin().equals(login)) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    public Jogo buscarJogoPorNome(String nome) {
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getNome().equals(nome)) {
                return jogos.get(i);
            }
        }
        return null;
    }

    public void mostrarJogadores() {
        if (jogadores.size() == 0) {
            System.out.println("Nenhum jogador cadastrado.");
        } else {
            for (int i = 0; i < jogadores.size(); i++) {
                System.out.println(jogadores.get(i));
            }
        }
    }

    public void mostrarJogos() {
        for (int i = 0; i < jogos.size(); i++) {
            System.out.println((i + 1) + " - " + jogos.get(i));
        }
    }

    public void mostrarApostas() {
        if (apostas.size() == 0) {
            System.out.println("Nenhuma aposta cadastrada.");
        } else {
            for (int i = 0; i < apostas.size(); i++) {
                System.out.println(apostas.get(i));
            }
        }
    }

    public void gerarRelatorio() {
        double totalApostado = 0;
        double totalPremios = 0;

        System.out.println("\n---------- RELATORIO DO CASSINO ----------");
        System.out.println("Quantidade de jogadores: " + jogadores.size());
        System.out.println("Quantidade de jogos: " + jogos.size());
        System.out.println("Quantidade de apostas: " + apostas.size());

        for (int i = 0; i < apostas.size(); i++) {
            totalApostado = totalApostado + apostas.get(i).getValorApostado();
            totalPremios = totalPremios + apostas.get(i).getValorPremio();
        }

        System.out.println("Total apostado: R$ " + totalApostado);
        System.out.println("Total pago em premios: R$ " + totalPremios);
        System.out.println("Lucro do cassino: R$ " + (totalApostado - totalPremios));
        System.out.println("\nApostas registradas:");
        mostrarApostas();
        System.out.println("------------------------------------------\n");
    }
}
