package br.com.banco.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaInvestimento extends Conta{

    private static final double TESOURO_DIRETO = 0.0805;
    private static final double CDB = 0.15;
    private static final double LCI = 0.19;
    private static final double FUNDO_IMOBILIARIO = 0.0627;
    private static final double FUNDO_DE_INVESTIMENTO = 0.27;

    private static List<Conta> listaContasInvestimento = new ArrayList<>();

    public ContaInvestimento(String nome, String cpf, double renda, int agencia, double saldo) {
        super(nome, cpf, renda, agencia, saldo);
    }

    public static List<Conta> getListaContasInvestimento() {
        return listaContasInvestimento;
    }

    public static void menuInvestimento() {
        System.out.println("------------------------------------");
        System.out.printf("[ 1 ] - Tesouro Direto: %.2f%%\n", TESOURO_DIRETO * 100);
        System.out.printf("[ 2 ] - CDB: %.2f%%\n", CDB * 100);
        System.out.printf("[ 3 ] - LCI: %.2f%%\n", LCI * 100);
        System.out.printf("[ 4 ] - Fundo Imobiliário: %.2f%%\n", FUNDO_IMOBILIARIO * 100);
        System.out.printf("[ 5 ] - Fundo de Investimento: %.2f%%\n", FUNDO_DE_INVESTIMENTO * 100);
        System.out.println("[ 9 ] - Voltar");
    }

    public static void menuRendimento() {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        double valorInvestimento;
        int anos;

        do {
            menuInvestimento();
            try {
                System.out.print("Escolha uma modalidade para simular o rendimento: ");
                opcao = Integer.parseInt(scanner.nextLine());


                if (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao !=9) {
                    System.err.println("Opção inválida!");

                } else if (opcao == 9) {
                    return;
                } else {
                    System.out.print("Valor do investimento: ");
                    valorInvestimento = Double.parseDouble(scanner.nextLine());

                    System.out.print("Tempo de investimento (anos): ");
                    anos = Integer.parseInt(scanner.nextLine());

                    simulaRendimento(opcao, valorInvestimento, anos);
                }


            } catch (Exception e) {
                System.err.println("Simulação cancelada.");
            }


        } while (opcao != 9);
    }

    private static void simulaRendimento(int opcao, double valorInvestido, int anos) {

        String modalidade = null;
        double valorFinal = 0.0;
        double rendimento = 0.0;

        switch (opcao) {
            case 1:
                modalidade = "Tesouro Direto";
                valorFinal = calculaRendimento(ContaInvestimento.TESOURO_DIRETO, valorInvestido, anos);
                rendimento = ContaInvestimento.TESOURO_DIRETO * 100;
                break;
            case 2:
                modalidade = "CDB";
                valorFinal = calculaRendimento(ContaInvestimento.CDB, valorInvestido, anos);
                rendimento = ContaInvestimento.CDB * 100;
                break;
            case 3:
                modalidade = "LCI";
                valorFinal = calculaRendimento(ContaInvestimento.LCI, valorInvestido, anos);
                rendimento = ContaInvestimento.LCI * 100;
                break;
            case 4:
                modalidade = "Fundo Imobiliário";
                valorFinal = calculaRendimento(ContaInvestimento.FUNDO_IMOBILIARIO, valorInvestido, anos);
                rendimento = ContaInvestimento.FUNDO_IMOBILIARIO * 100;
                break;
            case 5:
                modalidade = "Fundo de Investimento";
                valorFinal = calculaRendimento(ContaInvestimento.FUNDO_DE_INVESTIMENTO, valorInvestido, anos);
                rendimento = ContaInvestimento.FUNDO_DE_INVESTIMENTO* 100;
                break;
            case 9:
                System.out.println("Voltando...");
                break;
            default:
                System.err.println("Opção inválida!");
        }

        if (modalidade == null || valorFinal == 0.0) {
            System.out.println("Erro ao efetuar simulação.");
        } else {
            System.out.println("--------------------------------------");
            System.out.printf("Modalidade: %s\nRendimento da modalidade: %.2f\nPeríodo do investimento: %d anos\nRendimento Final: %.2f\n"
                    , modalidade, rendimento, anos, valorFinal);
        }
    }

    private static double calculaRendimento(double redimentoInvestimento,double valorInvestido, int anos) {

        double valorFinal = 0;

        for (int i = 0; i < anos; i++) {
            valorFinal += valorInvestido * redimentoInvestimento;
        }

        return valorFinal + valorInvestido;
    }

}
