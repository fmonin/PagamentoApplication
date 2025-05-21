package br.com.fiap.pagamento.dto;

import java.math.BigDecimal;

public class PagamentoResponse {
    public String idPedido;
    public String idCartao;
    public BigDecimal valor;
    public String status;
    public String dataAprovacao;
}