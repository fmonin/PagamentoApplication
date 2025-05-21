package br.com.fiap.pagamento.mapper;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.model.Pagamento;

public class PagamentoMapper {

    public static Pagamento toModel(PagamentoRequest request) {
        return new Pagamento(request.idPedido, request.idCartao, request.valor, request.dataAprovacao);
    }

    public static PagamentoResponse toResponse(Pagamento pagamento) {
        PagamentoResponse response = new PagamentoResponse();
        response.idPedido = pagamento.getIdPedido();
        response.idCartao = pagamento.getIdCartao();
        response.valor = pagamento.getValor();
        response.status = pagamento.getStatus();
        response.dataAprovacao = pagamento.getDataAprovacao();
        return response;
    }
}