package br.com.fiap.pagamento;

import br.com.fiap.pagamento.controller.PagamentoController;
import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.services.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PagamentoController.class)
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService pagamentoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testProcessarPagamento() throws Exception {
        PagamentoRequest request = new PagamentoRequest();
        request.idPedido = "121";
        request.idCartao = "999";
        request.valor = new BigDecimal("120.00");

        PagamentoResponse response = new PagamentoResponse();
        response.idPedido = "121";
        response.idCartao = "999";
        response.valor = new BigDecimal("120.00");
        response.status = "APROVADO";
        response.dataAprovacao = "2025-05-20T12:00:00";

        Mockito.when(pagamentoService.processarPagamento(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APROVADO"));
    }

    @Test
    public void testBuscarPorPedido() throws Exception {
        PagamentoResponse response = new PagamentoResponse();
        response.idPedido = "121";
        response.status = "APROVADO";

        Mockito.when(pagamentoService.buscarPorPedido("121")).thenReturn(response);

        mockMvc.perform(get("/pagamentos/121"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APROVADO"));
    }

    @Test
    public void testListarTodos() throws Exception {
        Mockito.when(pagamentoService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pagamentos"))
                .andExpect(status().isOk());
    }
}

