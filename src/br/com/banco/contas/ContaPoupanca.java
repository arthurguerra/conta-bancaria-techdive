package br.com.banco.contas;

import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca extends Conta{

    private static List<ContaPoupanca> listaContasPoupanca = new ArrayList<>();

    public ContaPoupanca(String nome, String cpf, double renda, int agencia, double saldo) {
        super(nome, cpf, renda, agencia, saldo);
    }

    public void calculaRentabilidade(int meses, double rentabilidadePoupanca) {

        double saldoFinal = getSaldo();

//        Iq = [(1 + It)^q/t – 1] x 100//
//        Onde://
//        Iq = taxa de juros no período que você quer
//                It = taxa de juros no período que você tem
//                q = período que você quer
//                t = período que você tem

//        double percentual = rentabilidade / 100;
//        double rentabM = Math.pow((1 + percentual), 1.0 / 12.0) - 1
//        Taxa equivalente = [(1+ taxa) elevado a: prazo que quero/prazo que tenho – 1] x 100

        double rentabilidadeMensal = Math.pow((1 + rentabilidadePoupanca / 100), 1.0/12.0) - 1;
        rentabilidadeMensal *= 100;
//        double rentabilidadeMensal = Math.pow((1 + rentabilidadePoupanca / 100), 1.0/12.0 - 1) * 100;
        for (int i = 0; i < meses; i++) {
            saldoFinal += (getSaldo() * rentabilidadeMensal / 100);
        }

        System.out.printf("Com um saldo atual de R$%.2f e um rendimento anual de %.2f, após %d meses  você terá: R$%.2f\n"
                , getSaldo(), rentabilidadePoupanca, meses, saldoFinal);
    }

    public static List<ContaPoupanca> getListaContasPoupanca() {
        return listaContasPoupanca;
    }
}
