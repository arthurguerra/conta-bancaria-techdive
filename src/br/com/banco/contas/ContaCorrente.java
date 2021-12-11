package br.com.banco.contas;

import br.com.banco.enums.TipoTransacao;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente extends Conta{

    // o cheque especial corresponde à metade da renda mensal do cliente;
    private static double chequeEspecial;
    private static List<Conta> listaContasCorrente = new ArrayList<>();

    public ContaCorrente(String nome, String cpf, double renda, int agencia, double saldo) {
        super(nome, cpf, renda, agencia, saldo);
        chequeEspecial = renda / 2;
    }

    @Override
    public boolean sacar(double valorSaque) {
        if (valorSaque <= getSaldo() + chequeEspecial) {
            setSaldo(getSaldo() - valorSaque);
            Transacao transacao = new Transacao(TipoTransacao.SAQUE, this, null, valorSaque);
            getExtrato().add(transacao);
            return true;
        } else {
            System.out.println("Saldo insuficiente!\nSaldo atual: R$"+getSaldo() + " + R$"+chequeEspecial + "(cheque especial)");
            return false;
        }
    }

    @Override
    public String verificaSaldo() {
//        return "Olá, " + getNome() + "! Seu saldo atual é: R$"+getSaldo() + " + R$" + chequeEspecial + "(cheque especial)";
        return String.format("Olá, %s! Saldo atual: R$%.2f\nCheque Especial: R$%.2f \nSaldo Total: R$%.2f\n", getNome(), getSaldo(), chequeEspecial, getSaldo() + chequeEspecial);
    }

    @Override
    public boolean transferir(Conta contaDestino, double valorTransferencia) {
        if (valorTransferencia <= getSaldo() + chequeEspecial) {
            setSaldo(getSaldo() - valorTransferencia);
            contaDestino.setSaldo(contaDestino.getSaldo() + valorTransferencia);
            Transacao transacao = new Transacao(TipoTransacao.TRANSFERENCIA,this, contaDestino, valorTransferencia);
            getExtrato().add(transacao);
            contaDestino.getExtrato().add(transacao);
            return true;
        } else {
            System.out.println("Saldo insuficiente para efetuar a transferência!");
            System.out.printf("Saldo atual: R$%.2f\nCheque Especial: R$%.2f \nSaldo Total: R$%.2f\n", getSaldo(), chequeEspecial, getSaldo()+chequeEspecial);
            return false;
        }
    }

    public static List<Conta> getContasCorrentes() {
        return listaContasCorrente;
    }
}
