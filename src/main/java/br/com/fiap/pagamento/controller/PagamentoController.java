package br.com.fiap.pagamento.controller;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.services.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public PagamentoResponse realizarPagamento(@RequestBody PagamentoRequest request) {
        return service.processarPagamento(request);
    }

    @GetMapping("/{idPedido}")
    public PagamentoResponse buscar(@PathVariable String idPedido) {
        return service.buscarPorPedido(idPedido);
    }

    @GetMapping
    public List<PagamentoResponse> listar() {
        return service.listarTodos();
    }
}