package br.com.fiap.pagamento;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.repository.PagamentoRepository;
import br.com.fiap.pagamento.services.PagamentoService;
import br.com.fiap.pagamento.exception.PagamentoNotFoundException;
import br.com.fiap.pagamento.model.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository repository;

    @InjectMocks
    private PagamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        public void testPagamentoAprovado() {
            PagamentoRequest request = new PagamentoRequest();
            request.idPedido = "001";
            request.idCartao = "123456";
            request.valor = BigDecimal.valueOf(1000.00);
            request.dataAprovacao = "2025-05-17";

            // Simula que o pagamento salvo será retornado com status APROVADO
            when(repository.save(any())).thenAnswer(invocation -> {
                Pagamento p = invocation.getArgument(0);
                p.setStatus("APROVADO");
                return p;
            });

            PagamentoResponse response = service.processarPagamento(request);
            assertEquals("APROVADO", response.status);
            assertEquals("001", response.idPedido);
        }
        @Test
        public void testPagamentoRecusado () {

            PagamentoRequest request = new PagamentoRequest();
            request.idPedido = "002";
            request.idCartao = "654321";
            request.valor = new BigDecimal("2000.00");
            request.dataAprovacao = "2025-05-17";

            Pagamento pagamentoMock = new Pagamento("002", "654321", new BigDecimal("2000.00"), "RECUSADO");
            when(repository.save(any())).thenReturn(pagamentoMock);

            var response = service.processarPagamento(request);
            assertEquals("RECUSADO", response.status);
        }

        @Test
        public void testProcessarPagamento () {
            PagamentoRequest request = new PagamentoRequest();
            request.idPedido = "001";
            request.idCartao = "123456";
            request.valor = BigDecimal.valueOf(1000.00);
            request.dataAprovacao = "2025-05-17";

            Pagamento pagamentoMock = new Pagamento("001", "123456", new BigDecimal("1000.00"), "APROVADO");
            when(repository.save(any())).thenReturn(pagamentoMock);

            PagamentoResponse response = service.processarPagamento(request);
            assertEquals("APROVADO", response.status);
            assertEquals("001", response.idPedido);
        }

        @Test
        public void testBuscarPorPedidoComSucesso () {
            Pagamento pagamento = new Pagamento("002", "789456", new BigDecimal("1500.00"), "RECUSADO");
            when(repository.findByIdPedido("002")).thenReturn(pagamento);

            PagamentoResponse response = service.buscarPorPedido("002");
            assertEquals("RECUSADO", response.status);
        }

        @Test
        public void testBuscarPorPedidoNaoEncontrado () {
            when(repository.findByIdPedido("999")).thenReturn(null);
            assertThrows(PagamentoNotFoundException.class, () -> service.buscarPorPedido("999"));
        }

        @Test
        public void testListarTodos () {
            Pagamento pag1 = new Pagamento("003", "741852", new BigDecimal("800.00"), "APROVADO");
            Pagamento pag2 = new Pagamento("004", "963258", new BigDecimal("1200.00"), "RECUSADO");
            when(repository.findAll()).thenReturn(Arrays.asList(pag1, pag2));

            List<PagamentoResponse> lista = service.listarTodos();
            assertEquals(2, lista.size());
            assertEquals("003", lista.get(0).idPedido);
            assertEquals("004", lista.get(1).idPedido);
        }



    }