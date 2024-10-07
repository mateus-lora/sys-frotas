import java.util.Scanner;

public class ViewCadVeiculo {
    private static ServiceVeiculo service = new ServiceVeiculo();
    static Scanner input = new Scanner(System.in);

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void aguardarEnter() {
        System.out.print("Pressione Enter para voltar ao Menu Inicial...");
        input.nextLine();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.print("Entrada inválida! Por favor, digite um número: ");
            }
        } while (!entradaValida);
        return valor;
    }

    private static int inputTipoVeiculo(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                if (valor == 1 || valor == 2) {
                    entradaValida = true;
                } else {
                    System.out.print("Entrada inválida! Por favor, digite 1 ou 2: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida! Por favor, digite um número: ");
            }
        } while (!entradaValida);
        return valor;
    }

    private static int inputMenu(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        String invalido = "Entrada inválida! Por favor, digite um número dentro das opções acima: ";
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                if (valor >= 0 && valor <= 4) {
                    entradaValida = true;
                } else {
                    System.out.print(invalido);
                }
            } catch (Exception e) {
                System.out.print(invalido);
            }
        } while (!entradaValida);
        return valor;
    }

    private static void adicionar() {
        limparTela();
        Veiculo novoVeiculo;
        String tipo = "";
        System.out.println("ADICIONANDO NOVO VEÍCULO");
        int tipoVeiculo;

        tipoVeiculo = inputTipoVeiculo("Qual o tipo de veículo: \n1 - Carro \n2 - Moto\nDigite a opção desejada: ");
        if (tipoVeiculo == 1) {
            novoVeiculo = new Carro();
            tipo = "do carro";
        } else {
            novoVeiculo = new Moto();
            tipo = "da moto";
        }
        
        System.out.print("Informe a marca " + tipo + ": ");
        String marca = input.nextLine();
        novoVeiculo.setMarca(marca);

        System.out.print("Informe o modelo " + tipo + ": ");
        String modelo = input.nextLine();
        novoVeiculo.setModelo(modelo);

        int ano = inputNumerico("Informe o ano " + tipo + ": ");
        novoVeiculo.setAno(ano);

        System.out.print("Informe a placa " + tipo + ": ");
        String placa = input.nextLine();
        novoVeiculo.setPlaca(placa);

        if (tipoVeiculo == 1) {
            int numeroPortas = inputNumerico("Insira o número de portas do carro: ");
            ((Carro) novoVeiculo).setNumeroPortas(numeroPortas);
        } else if (tipoVeiculo == 2) {
            boolean partidaEletrica = false;
            boolean entradaValida = false;
            do {
                int possuiPartidaEletrica = inputNumerico("A moto possui partida elétrica: (1) Sim - (2)Não ");
                if (possuiPartidaEletrica == 1) {
                    partidaEletrica = true;
                    entradaValida = true;
                } else if (possuiPartidaEletrica == 2) {
                    entradaValida = true;
                } else {
                    System.out.println("Opção Inválida!");
                }
            } while (!entradaValida);
            ((Moto) novoVeiculo).setPartidaEletrica(partidaEletrica);
        }
        try {
            service.salvar(novoVeiculo);
            System.out.println("VEÍCULO ADICIONADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO");
            System.out.println(e.getMessage());
        }
        aguardarEnter();
    }

    private static void listar() {
        limparTela();
        var veiculos = service.encontarTodos();
        if (veiculos.isEmpty()) {
            System.out.println("NÃO POSSUI NENHUM VEÍCULO CADASTRADO");
        } else {
            System.out.println("LISTA DE VEÍCULOS CADASTRADOS");
            int i = 0;
            for (Veiculo veiculo : veiculos) {
                ++i;
                System.out.println("Veículo " + i + veiculo.toString());
            }
        }
    }

    private static void pesquisarPorPlaca() {
        limparTela();
        System.out.println("PESQUISA DE VEÍCULO POR PLACA");
        System.out.print("Informe a placa que deseja pesquisar: ");
        String placa = input.nextLine();
        try {
            Veiculo veiculo = service.encontrarPorPlaca(placa);
            System.out.println("Veículo" + veiculo.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        aguardarEnter();
    }

    private static void removerPorPlaca() {
        System.out.println("REMOÇÃO DE VEÍCULOS");
        listar();
        System.out.print("Insira a placa do veículo que deseja REMOVER: ");
        String placa = input.nextLine();
        try {
            service.removerPorPlaca(placa);
            System.out.println("Veículo removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        aguardarEnter();

    }

    public static void main(String[] args) {
        String menu = """
                SISTEMA DE GERENCIAMENTO DE FROTAS
                MENU DE OPÇÕES:
                1 - Cadastrar novo Veículo
                2 - Listar todos Veículos cadastrados
                3 - Pesquisar Veículo pela placa
                4 - Remover Veículo
                0 - Sair
                Digite a opção desejada:""" + " ";

        int opcao;
        do {
            limparTela();
            opcao = inputMenu(menu);
            switch (opcao) {
                case 0:
                    limparTela();
                    System.out.println("VOLTE SEMPRE!");
                    break;
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    aguardarEnter();
                    break;
                case 3:
                    pesquisarPorPlaca();
                    break;
                case 4:
                    removerPorPlaca();
                    break;
            }
        } while (opcao != 0);
    }
}