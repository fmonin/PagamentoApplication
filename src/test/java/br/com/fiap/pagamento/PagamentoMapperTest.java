package br.com.fiap.pagamento;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.exception.PagamentoNotFoundException;
import br.com.fiap.pagamento.mapper.PagamentoMapper;
import br.com.fiap.pagamento.model.Pagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoMapperTest {

    @Test
    public void testToModel() {
        PagamentoRequest request = new PagamentoRequest();
        request.idPedido = "999";
        request.idCartao = "000000";
        request.valor = new BigDecimal("500.00");
        request.dataAprovacao = "2025-05-20T00:00:00";

        Pagamento pagamento = PagamentoMapper.toModel(request);
        assertEquals("999", pagamento.getIdPedido());
        assertEquals("000000", pagamento.getIdCartao());
        assertEquals(new BigDecimal("500.00"), pagamento.getValor());
    }

}

