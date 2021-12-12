package br.com.banco.contas;

import br.com.banco.enums.TipoTransacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transacao {

    private static ArrayList<Transacao> transacoes = new ArrayList<>();

    private final TipoTransacao tipo;
    private final Conta contaOrigem;
    private final Conta contaDestino;
    private final double valor;
    private final LocalDate data;

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

        if (tipo == TipoTransacao.SAQUE ) {
            return String.format("\nTipo de transaçao: %s\nConta de origem: %s\nConta de destino: %s\nValor: %.2f\nData: %s\n"
                    , tipo, contaOrigem.getConta(), "sem destinatário", valor, data);
        }

        if (tipo == TipoTransacao.DEPOSITO) {
            return String.format("\nTipo de transaçao: %s\nConta de origem: %s\nConta de destino: %s\nValor: %.2f\nData: %s\n"
                    , tipo, "sem conta de origem", contaDestino.getConta(), valor, data);
        }

        return String.format("\nTipo de transaçao: %s\nConta de origem: %s\nConta de destino: %s\nValor: %.2f\nData: %s\n"
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
