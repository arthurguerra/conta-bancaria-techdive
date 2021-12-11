package br.com.banco.utils;

import br.com.banco.contas.Conta;
import br.com.banco.contas.ContaCorrente;
import br.com.banco.contas.ContaInvestimento;
import br.com.banco.contas.ContaPoupanca;
import br.com.banco.controllers.MainController;

import java.awt.*;
import java.util.Scanner;

public class Menus {

//    static Scanner scanner = new Scanner(System.in);

    public static void exibeMenuInicial() {
        System.out.println("----------------------------------------");
        System.out.println("[ 1 ] - Cadastrar uma nova conta.");
        System.out.println("[ 2 ] - Listar contas.");
        System.out.println("[ 3 ] - Utilizar conta existente.");
        System.out.println("[ 4 ] - Gerar Relatório.");
        System.out.println("[ 9 ] - Sair do sistema.");
        System.out.print("Digite o número correspondente à operação: ");

    }

    public static void escolheOpcao(int opcao) {
        switch (opcao) {
            case 1:
                exibeMenuCadastroConta();
                break;
            case 2:
                if (Conta.getContas().isEmpty()) {
                    System.out.println("Ainda não há contas cadastradas.");
                } else {
                    for(Conta conta: Conta.getContas()) {
                        System.out.println(conta);
                    }
                }
                break;
            case 3:
                menuConta(utilizaContaExistente());
                break;
            case 4:
                break;
            case 9:
                System.out.println("\nEncerrando sistema...");
                break;
            default:
                System.err.println("Opção Inválida!");
                break;
        }
    }

    public static void exibeMenuCadastroConta() {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("--------------------------------------------");
            System.out.println("Escolha o tipo de conta desejada:");
            System.out.println("[ 1 ] - Conta corrente");
            System.out.println("[ 2 ] - Conta poupança");
            System.out.println("[ 3 ] - Conta investimento");
            System.out.println("[ 9 ] - Voltar");
            System.out.print("Opção desejada: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                e.getMessage();
            }

            switch (opcao) {
                case 1:
                case 2:
                case 3:
                    Conta conta = MainController.criaConta(opcao);
                    if(conta != null) {
                        Menus.menuConta(conta);
                    }
                    break;
                case 9:
                    System.out.println("Voltando ao menu inicial...");
                    break;
                default:
                    System.err.println("Opção inválida!");
            }

        } while(opcao != 9);
    }

    private static void menuConta(Conta conta) {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        double quantia = 0;

        do {
            System.out.println("--------------------------------");
            System.out.printf("Número da conta: Conta %d  /  Titular: %s  /  Saldo: %.2f", conta.getConta(), conta.getNome(), conta.getSaldo());
            System.out.println("--------------------------------");
            System.out.println("Operações Disponíveis:");
            System.out.println("[ 1 ] - Sacar");
            System.out.println("[ 2 ] - Depositar");
            System.out.println("[ 3 ] - Saldo atual");
            System.out.println("[ 4 ] - Extrato bancário");
            System.out.println("[ 5 ] - Transferência");
            System.out.println("[ 6 ] - Alterar dados cadastrais");
            if (conta instanceof ContaPoupanca) {
                System.out.println("[ 7 ] - Simulação de rendimento");
            }
            System.out.println("[ 9 ] - Voltar");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor que gostaria de sacar: ");
                        quantia = Double.parseDouble(sc.nextLine());
                        if (conta.sacar(quantia)) {
                            System.out.println("Operação realizada com sucesso!");
                        }
                        break;
                    case 2:
                        System.out.print("Digite a quantia que gostaria de depositar: ");
                        quantia = Double.parseDouble(sc.nextLine());
                        conta.depositar(quantia);
                        System.out.println("Operação realizada com sucesso!");
                        break;
                    case 3:
                        System.out.println(conta.verificaSaldo());
                        break;
                    case 4:
                        System.out.println(conta.getExtrato());
                        break;
                    case 5:
                        if (Conta.getContas().size() < 2) {
                            System.out.println("Ainda não existem contas suficiente para efetuar uma transferência.");
                        } else {
                            menuTransferencia();
                        }
                        break;
                    case 6:
                        conta.alteraDados();
                        break;
                    case 7:
                        if (conta instanceof ContaPoupanca) {
                            Menus.menuContaPoupanca((ContaPoupanca) conta);
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        break;
                    case 9:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.err.println("Opção inválida!");
                }

            } catch (Exception e) {
                e.getMessage();
            }

        } while (opcao != 9);
    }

    public static void menuAlteraConta(Conta conta) {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("-----------------------------------");
            System.out.println("Qual dado você gostaria de alterar?");
            System.out.println("[ 1 ] - Nome");
            System.out.println("[ 2 ] - Renda");
            System.out.println("[ 3 ] - Agencia");
            System.out.println("[ 9 ] - Voltar");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    conta.setNome(nome);
                    System.out.println("Nome alterado com sucesso!");
                    break;
                case 2:
                    System.out.println("Renda: ");
                    double renda = Double.parseDouble(sc.nextLine());
                    conta.setRenda(renda);
                    System.out.println("Renda alterada com sucesso!");
                    break;
                case 3:
                    System.out.println("[ 1 ] Florianópolis\n[ 2 ] - São José");
                    int agencia = 0;
                    do {
                        System.out.print("Agência: ");
                        agencia = Integer.parseInt(sc.nextLine());
                    } while (agencia != 1 && agencia !=2);
                    conta.setAgencia(agencia);
                    break;
                case 9:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }
        } while(opcao != 9);
    }

    public static void menuContaPoupanca(ContaPoupanca contaPoupanca) {

        Scanner sc = new Scanner(System.in);

        try {
            int meses;
            double rentabilidadeAnual;

            System.out.println("Informe o período de tempo (em meses) e a rentabilidade anual da poupança para calcular o rendimento.");

            System.out.print("Período de tempo (em meses): ");
            meses = Integer.parseInt(sc.nextLine());

            System.out.println("Rentabilidade anual (%): ");
            rentabilidadeAnual = Double.parseDouble(sc.nextLine());

            contaPoupanca.calculaRentabilidade(meses, rentabilidadeAnual);
        } catch(Exception e) {
            System.err.println("Erro ao realizar simulação!");
        }
    }

    public static Conta utilizaContaExistente() {

        Scanner scanner = new Scanner(System.in);
        int idconta;
        Conta contaSelecionada = null;

        if (Conta.getContas().isEmpty()) {
            System.out.println("Ainda não há contas cadastradas.");
            return null;
        } else {
            for (Conta conta: Conta.getContas()) {
                System.out.println(conta);
            }

            System.out.println("Digite o número da conta: ");
            try {
                idconta = Integer.parseInt(scanner.nextLine());
                contaSelecionada = Conta.getContas().get(idconta - 1);
            } catch (Exception e) {
                System.err.println("Erro ao selecionar conta.");
            }
            return contaSelecionada;
        }
    }

    public static void menuTransferencia() {

        Scanner scanner = new Scanner(System.in);
        double valorTransferencia;

        System.out.print("Quanto você quer transferir? ");
        valorTransferencia = Double.parseDouble(scanner.nextLine());

    }

    public static void menuRelatorio() {

        Scanner scanner = new Scanner(System.in);
        int opcao;

    }
}
