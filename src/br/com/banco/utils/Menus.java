package br.com.banco.utils;

import br.com.banco.contas.*;
import br.com.banco.controllers.MainController;

import java.awt.*;
import java.util.Scanner;

public class Menus {

//    static Scanner scanner = new Scanner(System.in);

    public static void exibeMenuInicial() {
        System.out.println("----------------------------------------");
        System.out.println("[ 1 ] - Cadastrar uma nova conta");
        System.out.println("[ 2 ] - Listar contas");
        System.out.println("[ 3 ] - Utilizar conta existente");
        System.out.println("[ 4 ] - Gerar Relatório");
        System.out.println("[ 9 ] - Sair do sistema");
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
                Menus.menuRelatorio();
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
            System.out.println("Escolha o tipo de conta que deseja criar:");
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
                    Conta conta = Conta.criaConta(opcao);
                    if(conta != null) {
                        Menus.menuConta(conta);
                    }
                    break;
                case 9:
                    System.out.println("Voltando ao menu inicial...");
                    break;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }

        } while(opcao != 9);
    }

    private static void menuConta(Conta conta) {

        if (conta == null) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        double quantia = 0;

        do {
            System.out.println("--------------------------------");
            System.out.println("Operações Disponíveis:");
            System.out.println("[ 1 ] - Sacar");
            System.out.println("[ 2 ] - Depositar");
            System.out.println("[ 3 ] - Saldo atual");
            System.out.println("[ 4 ] - Extrato bancário");
            System.out.println("[ 5 ] - Transferência");
            System.out.println("[ 6 ] - Alterar dados cadastrais");
            if (conta instanceof ContaPoupanca || conta instanceof ContaInvestimento) {
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
                        conta.imprimeExtrato();
                        conta.verificaSaldo();
                        break;
                    case 5:
                        if (Conta.getContas().size() < 2) {
                            System.out.println("Ainda não existem contas suficiente para efetuar uma transferência.");
                        } else {
                            menuTransferencia(conta);
                        }
                        break;
                    case 6:
                        Menus.menuAlteraConta(conta);
                        break;
                    case 7:
                        if (conta instanceof ContaPoupanca) {
                            Menus.menuContaPoupanca((ContaPoupanca) conta);
                        } else if (conta instanceof ContaInvestimento) {
                            ContaInvestimento.menuRendimento();
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
            System.out.println(conta);
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
                    System.out.print("Renda: ");
                    double renda = Double.parseDouble(sc.nextLine());
                    conta.setRenda(renda);
//                    if (conta instanceof ContaCorrente) {
//                        ((ContaCorrente) conta).setChequeEspecial(conta.getSaldo());
//                    }
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
                    System.out.println("Agência alterada com sucesso!");
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

            System.out.print("Rentabilidade anual (%): ");
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

            System.out.print("Digite o número da conta: ");
            try {
                idconta = Integer.parseInt(scanner.nextLine());
                contaSelecionada = Conta.getContas().get(idconta - 1);
            } catch (Exception e) {
                System.err.println("Erro ao selecionar conta.");
            }
            return contaSelecionada;
        }
    }

    public static void menuTransferencia(Conta conta) {

        Scanner scanner = new Scanner(System.in);
        Conta contaDestino;
        double valorTransferencia;
        String confirmacaoTransferencia;

        System.out.print("Quanto você quer transferir? ");
        valorTransferencia = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));

        System.out.println("Para qual conta deseja transferir?");

        contaDestino = utilizaContaExistente();

        if (contaDestino == null) {
            System.out.println("Erro ao selecionar conta");
        } else {
            System.out.printf("Transferir RS%.2f para %s, Conta %d?\n", valorTransferencia, contaDestino.getNome(), contaDestino.getConta());
            do {
                System.out.print("[ S ] / [ N ] : ");
                confirmacaoTransferencia = scanner.nextLine().trim().toUpperCase();
            } while (!confirmacaoTransferencia.equals("S") && !confirmacaoTransferencia.equals("N"));
            if (confirmacaoTransferencia.equals("S") && !conta.equals(contaDestino)) {
                conta.transferir(contaDestino, valorTransferencia);
                System.out.println("Transferência realizada com sucesso.");
            } else {
                System.out.println("Transferência cancelada.");
            }
        }
    }

    public static void menuRelatorio() {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("Qual relatório deseja gerar");
            System.out.println("[ 1 ] - Listar todas as contas");
            System.out.println("[ 2 ] - Contas com saldo negativo");
            System.out.println("[ 3 ] - Total investido");
            System.out.println("[ 4 ] - Transferências entre contas");
            System.out.println("[ 5 ] - Transações de um cliente específico");
            System.out.println("[ 9 ] - Voltar");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                e.getMessage();
            }

            switch (opcao) {
                case 1:
                    if (Conta.getContas().isEmpty()) {
                        System.out.println("Ainda não há contas cadastradas");
                    } else {
                        menuListarContas();
                    }
                    break;
                case 2:
                    if (ContaCorrente.getContasCorrentes().isEmpty()) {
                        System.out.println("Ainda não há contas cadastradas");
                    } else {
                        Relatorios.listaContasComSaldoNegativo();
                    }
                    break;
                case 3:
                    double totalInvestido = 0;
                    if (ContaInvestimento.getListaContasInvestimento().isEmpty()) {
                        System.out.println("Ainda não há contas cadastradas.");
                    } else {
                        Relatorios.listaContaInvestimento();
                        for (Conta conta: ContaInvestimento.getListaContasInvestimento()) {
                            totalInvestido += conta.getSaldo();
                        }
                        System.out.printf("Total investido: RS%.2f", totalInvestido);
                    }
                    break;
                case 4:
                    if (Transacao.getTransacoes().isEmpty()) {
                        System.out.println("Nenhuma transação foi efetuada.");
                    } else {
                        for (Transacao transacao: Transacao.getTransacoes()) {
                            System.out.println(transacao);
                        }
                    }
                    break;
                case 5:
                    Conta conta = Menus.utilizaContaExistente();
                    if (conta != null) {
                        conta.imprimeExtrato();
                    }
                    break;
                case 9:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }
        } while (opcao != 9);

    }

    public static void menuListarContas() {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("-----------------------------------------");
            System.out.println("Qual tipo de conta gostaria de listar? ");
            System.out.println("[ 1 ] - Conta Corrente");
            System.out.println("[ 2 ] - Conta Poupança");
            System.out.println("[ 3 ] - Conta Investimento");
            System.out.println("[ 9 ] - Voltar");
            System.out.print("Qual tipo de conta você quer listar? ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            }catch (Exception e) {
                e.getMessage();
            }

            switch (opcao) {
                case 1:
                    Relatorios.listaContaCorrente();
                    break;
                case 2:
                    Relatorios.listaContaPoupanca();
                    break;
                case 3:
                    Relatorios.listaContaInvestimento();
                    break;
                case 9:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }

        } while (opcao != 9);

    }
}
