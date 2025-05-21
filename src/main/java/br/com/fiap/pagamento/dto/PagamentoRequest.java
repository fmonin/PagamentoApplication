package br.com.fiap.pagamento.dto;

import java.math.BigDecimal;

public class PagamentoRequest {
    public String idPedido;
    public String idCartao;
    public BigDecimal valor;
    public String dataAprovacao;

    public PagamentoRequest(String integ1, String number, BigDecimal v, String date) {
    }

    public PagamentoRequest() {

    }
}