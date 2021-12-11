package br.com.banco.utils;

import br.com.banco.contas.Conta;
import br.com.banco.contas.ContaCorrente;
import br.com.banco.contas.ContaPoupanca;

public class Relatorios {

    public static void listaContaCorrente() {
        System.out.println(ContaCorrente.getContasCorrentes());
    }

    public static void listaContaPoupanca() {
        System.out.println(ContaPoupanca.getListaContasPoupanca());
    }

    public static void listaContasComSaldoNegativo() {
        for (Conta conta: Conta.getContas()) {
            if (conta.getSaldo() < 0) {
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
