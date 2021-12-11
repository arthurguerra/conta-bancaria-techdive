package br.com.banco.contas;

import br.com.banco.enums.TipoTransacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transacao {

    private static ArrayList<Transacao> transacoes = new ArrayList<>();

    private TipoTransacao tipo;
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;
    private LocalDate data;

    public Transacao(TipoTransacao tipo, Conta contaOrigem, Conta contaDestino, double valor) {
        this.tipo = tipo;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public static ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    @Override
    public String toString() {

        System.out.println("------------------------");
        return String.format("Tipo da transaçao: %s\nConta de origem: %s\nConta de destino: %s\nValor: %.2f\nData: %s\n"
                , tipo, contaOrigem.getConta(), contaDestino.getConta(), valor, data);
//        return "Transacao {" +
//                "tipo => " + tipo +
//                ", contaOrigem => " + contaOrigem +
//                ", contaDestino => " + contaDestino +
//                ", valor => " + valor +
//                ", data => " + data +
//                '}';
    }
}
