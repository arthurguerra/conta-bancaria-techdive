package br.com.banco.contas;

import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento extends Conta{

    private static List<ContaInvestimento> listaContasInvestimento = new ArrayList<>();

    public ContaInvestimento(String nome, String cpf, double renda, int agencia, double saldo) {
        super(nome, cpf, renda, agencia, saldo);
    }

    public static List<ContaInvestimento> getListaContasInvestimento() {
        return listaContasInvestimento;
    }
}
