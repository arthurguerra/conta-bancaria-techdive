package br.com.banco.utils;

import br.com.banco.contas.Conta;
import br.com.banco.contas.ContaCorrente;
import br.com.banco.contas.ContaPoupanca;

import java.util.ArrayList;

public class Relatorios {

    public static void listaContaCorrente() {

        if (ContaCorrente.getContasCorrentes().isEmpty()) {
            System.out.println("Ainda não existem contas nessa categoria.");
        } else {
            for (Conta conta: ContaCorrente.getContasCorrentes()) {
                System.out.println(conta);
            }
        }
    }

    public static void listaContaPoupanca() {

        if (ContaPoupanca.getListaContasPoupanca().isEmpty()) {
            System.out.println("Ainda não existem contas nessa categoria.");
        } else {
            for (Conta conta: ContaPoupanca.getListaContasPoupanca()) {
                System.out.println(conta);
            }
        }
    }

    public static void listaContasComSaldoNegativo() {

        ArrayList<Conta> contasSaldoNegativo = new ArrayList<>();

        for (Conta conta: ContaCorrente.getContasCorrentes()) {
            if (conta.getSaldo() < 0) {
                contasSaldoNegativo.add(conta);
            }
        }

        if (contasSaldoNegativo.isEmpty()) {
            System.out.println("Não há contas com saldo negativo!");
        } else {
            for (Conta conta: contasSaldoNegativo) {
                System.out.println(conta);
            }
        }
    }

    public static void totalValorInvestido() {

        double total = 0;
        for (Conta conta: Conta.getContas()) {
            total += conta.getSaldo();
        }
    }

    public static void listaTransacoesCliente(Conta conta) {
    }
}
