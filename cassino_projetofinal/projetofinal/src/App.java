import java.util.Scanner;

public class App {
    static Scanner entrada = new Scanner(System.in);
    static Cassino cassino = new Cassino();

    public static void main(String[] args) {
        cassino.carregarDados();
        menuInicial();
    }

    public static void menuInicial() {
        int opcao = -1;

        System.out.println("---------- CASSINO DA SORTE ----------");

        while (opcao != 0) {
            System.out.println("\n---------- MENU INICIAL ----------");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastrar novo usuario");
            System.out.println("3 - Ver usuarios cadastrados");
            System.out.println("0 - Encerrar sistema");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            if (opcao == 1) {
                Jogador jogadorLogado = fazerLogin();

                if (jogadorLogado != null) {
                    menuJogador(jogadorLogado);
                }
            } else if (opcao == 2) {
                cadastrarJogador();
            } else if (opcao == 3) {
                cassino.mostrarJogadores();
            } else if (opcao == 0) {
                cassino.salvarTodosJogadores();
                cassino.salvarTodasApostas();
                System.out.println("Sistema encerrado.");
            } else {
                System.out.println("Opcao invalida.");
            }
        }
    }

    public static Jogador fazerLogin() {
        Jogador jogadorLogado = null;
        int tentativas = 0;

        System.out.println("\n---------- LOGIN ----------");

        while (jogadorLogado == null) {
            System.out.print("Login: ");
            String login = entrada.nextLine();

            Jogador jogador = cassino.buscarJogadorPorLogin(login);

            if (jogador == null) {
                System.out.println("Login nao encontrado.");
                return null;
            } else {
                System.out.print("Senha: ");
                String senha = entrada.nextLine();

                if (jogador.conferirSenha(senha)) {
                    jogadorLogado = jogador;
                    System.out.println("Login realizado com sucesso!");
                } else {
                    tentativas++;
                    System.out.println("Senha incorreta. Tentativa " + tentativas + " de 3.");

                    if (tentativas == 3) {
                        trocarSenhaObrigatoria(jogador);
                        tentativas = 0;
                        System.out.println("Agora faca login novamente com a nova senha.");
                        return null;
                    }
                }
            }
        }
        return jogadorLogado;
    }

    public static void trocarSenhaObrigatoria(Jogador jogador) {
        boolean trocou = false;

        System.out.println("\nVoce errou 3 vezes. Cadastre uma nova senha.");
        System.out.println("A nova senha deve ser diferente das 3 ultimas.");

        while (!trocou) {
            System.out.print("Nova senha: ");
            String novaSenha = entrada.nextLine();

            if (jogador.trocarSenha(novaSenha)) {
                cassino.salvarTodosJogadores();
                trocou = true;
                System.out.println("Senha alterada com sucesso!");
            } else {
                System.out.println("Essa senha ja foi usada. Digite outra.");
            }
        }
    }

    public static void menuJogador(Jogador jogadorLogado) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n---------- MENU DO JOGADOR ----------");
            System.out.println("Jogador logado: " + jogadorLogado.getNome());
            System.out.println("Saldo: R$ " + jogadorLogado.getCarteira().getSaldo());
            System.out.println("1 - Depositar saldo");
            System.out.println("2 - Sacar saldo");
            System.out.println("3 - Jogar roleta");
            System.out.println("4 - Jogar caca niquel");
            System.out.println("5 - Consultar apostas cadastradas");
            System.out.println("6 - Relatorio geral");
            System.out.println("0 - Sair da conta");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            if (opcao == 1) {
                depositarSaldo(jogadorLogado);
            } else if (opcao == 2) {
                sacarSaldo(jogadorLogado);
            } else if (opcao == 3) {
                fazerAposta(jogadorLogado, cassino.buscarJogoPorNome("Roleta"));
            } else if (opcao == 4) {
                fazerAposta(jogadorLogado, cassino.buscarJogoPorNome("Caca Niquel"));
            } else if (opcao == 5) {
                cassino.mostrarApostas();
            } else if (opcao == 6) {
                cassino.gerarRelatorio();
            } else if (opcao == 0) {
                cassino.salvarTodosJogadores();
                cassino.salvarTodasApostas();
                System.out.println("Saindo da conta...");
            } else {
                System.out.println("Opcao invalida.");
            }
        }
    }

    public static void sacarSaldo(Jogador jogador) {
        System.out.println("Digite o valor do saque:");
        double valor = lerDouble();

        if (valor <= 0) {
            System.out.println("Valor invalido.");
        } else {
            boolean conseguiuSacar = jogador.getCarteira().sacar(valor);

            if (conseguiuSacar) {
                cassino.salvarTodosJogadores();
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Saldo atual: R$ " + jogador.getCarteira().getSaldo());
            } else {
                System.out.println("Saldo insuficiente para saque.");
            }
        }
    }
    
    public static void cadastrarJogador() {
        System.out.println("\n---------- CADASTRO DE USUARIO ----------");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("CPF: ");
        String cpf = entrada.nextLine();

        System.out.print("Login: ");
        String login = entrada.nextLine();

        if (cassino.buscarJogadorPorLogin(login) != null) {
            System.out.println("Ja existe usuario com esse login.");
            return;
        }

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Jogador jogador = new Jogador(nome, cpf, login, senha, "", "", 50.0);
        cassino.cadastrarJogador(jogador);
        System.out.println("Usuario cadastrado com saldo inicial de R$ 50.0");
    }

    public static void depositarSaldo(Jogador jogador) {
        System.out.print("Valor para depositar: R$ ");
        double valor = lerDouble();

        if (valor > 0) {
            jogador.getCarteira().depositar(valor);
            cassino.salvarTodosJogadores();
            System.out.println("Deposito realizado.");
        } else {
            System.out.println("Valor invalido.");
        }
    }

    public static void fazerAposta(Jogador jogador, Jogo jogo) {
        System.out.println("\nJogo escolhido: " + jogo.getNome());
        System.out.println("Valor minimo: R$ " + jogo.getValorMinimo());
        System.out.print("Valor da aposta: R$ ");
        double valor = lerDouble();

        if (valor < jogo.getValorMinimo()) {
            System.out.println("Valor menor que a aposta minima.");
            return;
        }

        if (!jogador.getCarteira().sacar(valor)) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        double premio = jogo.jogar(valor);
        String resultado = "Perdeu";

        if (premio > 0) {
            jogador.getCarteira().depositar(premio);
            resultado = "Ganhou";
        }

        Aposta aposta = new Aposta(jogador, jogo, valor, premio, resultado);
        cassino.cadastrarAposta(aposta);

        System.out.println("Resultado: " + resultado);
        System.out.println("Premio: R$ " + premio);
        System.out.println("Saldo atual: R$ " + jogador.getCarteira().getSaldo());
    }

    public static int lerInteiro() {
        int numero = -1;
        try {
            numero = Integer.parseInt(entrada.nextLine());
        } catch (Exception e) {
            System.out.println("Digite apenas numeros.");
        }
        return numero;
    }

    public static double lerDouble() {
        double numero = 0;
        try {
            numero = Double.parseDouble(entrada.nextLine());
        } catch (Exception e) {
            System.out.println("Digite apenas numeros. Exemplo: 10.5");
        }
        return numero;
    }
}
