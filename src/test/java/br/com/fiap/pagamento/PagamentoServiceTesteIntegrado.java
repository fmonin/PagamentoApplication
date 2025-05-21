package br.com.fiap.pagamento;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.repository.PagamentoRepository;
import br.com.fiap.pagamento.services.PagamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PagamentoServiceTesteIntegrado {

    @Autowired
    private PagamentoService service;

    @Autowired
    private PagamentoRepository repository;

    @Test
    void deveSalvarERecuperarPagamento() {
        PagamentoRequest request = new PagamentoRequest("integ1", "456", new BigDecimal("1499.00"), "2024-05-18");
        PagamentoResponse response = service.processarPagamento(request);

        assertEquals("APROVADO", response.status);
    }
}
