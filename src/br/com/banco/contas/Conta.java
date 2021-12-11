package br.com.banco.contas;

import br.com.banco.enums.TipoTransacao;
import br.com.banco.utils.Cpf;
import br.com.banco.utils.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Conta {

    private String nome;
    private String cpf;
    private double renda;
    private int conta;
    private int agencia;
    private double saldo;
    private double saldoInial;
    private static int totalContas;
    private static ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Transacao> extrato = new ArrayList<>();

    public Conta(String nome, String cpf, double renda, int agencia, double saldo) {
        this.nome = nome;
        try {
            if (Cpf.validaCpf(cpf)) {
                this.cpf = cpf;
            } else {
                throw new Exception("CPF inválido!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        this.renda = renda;
        this.agencia = agencia;
        this.saldo = saldo;
        this.saldoInial = saldo;
        totalContas++;
        this.conta = totalContas;
    }

    public boolean sacar(double valorSaque) {
        if (valorSaque <= this.saldo) {
            this.saldo -= valorSaque;
            Transacao transacao = new Transacao(TipoTransacao.SAQUE, this, null, valorSaque);
            getExtrato().add(transacao);
            return true;
        } else {
            System.out.println("Saldo insuficiente!");
            return false;
        }
    }

    public void depositar(double valorDeposito) {

        this.saldo += valorDeposito;
        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, null, this, valorDeposito);
        getExtrato().add(transacao);
    }

    public String verificaSaldo() {
        return "Olá, " + this.nome + "! Seu saldo atual é: R$"+this.saldo;
    }

/*    public void imprimeExtrato() {

        List<Transacao> extratoCompleto = getExtrato();

        for (Transacao transacao: Transacao.getTransacoes()) {
            if (transacao.getTipo() == TipoTransacao.TRANSFERENCIA && transacao.) {

            }
        }
    }*/

    public boolean transferir(Conta contaDestino, double valorTransferencia) {
        if (valorTransferencia <= this.saldo) {
            this.saldo -= valorTransferencia;
            contaDestino.saldo += valorTransferencia;
            Transacao transacao = new Transacao(TipoTransacao.TRANSFERENCIA,this, contaDestino, valorTransferencia);
            this.getExtrato().add(transacao);
            contaDestino.getExtrato().add(transacao);
            return true;
        } else {
            System.out.println("Saldo insuficiente para efetuar a transferência!\nSaldo atual: R$"+this.saldo);
            return false;
        }
    }

    public void alteraDados() {
        System.out.println(this);
        Menus.menuAlteraConta(this);
    }

    public static Conta criaConta(int opcaoTipoDeConta) {

        Scanner sc = new Scanner(System.in);

        try {
            String nome, cpf;
            double renda, saldo;
            int agencia;

            System.out.print("Digite o seu nome: ");
            nome = sc.nextLine();

            do {
                System.out.print("Digite o seu CPF (apenas números): ");
                cpf = sc.nextLine();
                if (!Cpf.validaCpf(cpf)) {
                    System.out.println("CPF inválido!");
                }
            } while (!Cpf.validaCpf(cpf));

            System.out.print("Digite a sua renda mensal: ");
            renda = sc.nextDouble();

            do {
                System.out.print("Digite a sua agência: ");
                System.out.println("[ 1 ] Florianópolis / [ 2 ] São Jose");
                agencia = sc.nextInt();
            } while (agencia != 1 && agencia!= 2);

            System.out.print("Digite o seu saldo inicial: R$");
            saldo = sc.nextDouble();

            Conta conta = null;

            switch (opcaoTipoDeConta) {
                case 1:
                    conta = new ContaCorrente(nome, cpf, renda, agencia, saldo);
                    ContaCorrente.getContasCorrentes().add(conta);
                    System.out.println("Conta corrente criada com sucesso!");
                    break;
                case 2:
                    conta = new ContaPoupanca(nome, cpf, renda, agencia, saldo);
                    ContaPoupanca.getListaContasPoupanca().add((ContaPoupanca) conta);
                    System.out.println("Conta poupança criada com sucesso");
                    break;
                case 3:
                    break;
                default:
                    return null;
            }
            Conta.getContas().add(conta);
            return conta;

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar conta. Tente novamente!");
        }
        return null;
    }

    public static ArrayList<Conta> getContas() {
        return contas;
    }

    public ArrayList<Transacao> getExtrato() {
        return extrato;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    @Override
    public String toString() {
        System.out.println("---------------------------");
        return String.format("Conta %d\nCPF: %s\nRenda: %.2f\nAgência: %d - %s", conta, cpf, renda, agencia, agencia == 1 ? "Florianópolis" : "São José");

//        return "---------------------------\nConta{" +
//        System.out.println("nome => '" + nome + '\'' +
//                ", cpf => '" + cpf + '\'' +
//                ", renda => " + renda +
//                ", conta => " + conta +
//                ", agencia => " + agencia +
//                ", saldo => " + saldo +
//                ", saldoInial => " + saldoInial +
//                ", transacoes => " + extrato +
//                '}';
    }
}
