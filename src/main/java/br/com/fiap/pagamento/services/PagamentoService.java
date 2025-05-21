package br.com.fiap.pagamento.services;

import br.com.fiap.pagamento.dto.PagamentoRequest;
import br.com.fiap.pagamento.dto.PagamentoResponse;
import br.com.fiap.pagamento.exception.PagamentoNotFoundException;
import br.com.fiap.pagamento.mapper.PagamentoMapper;
import br.com.fiap.pagamento.model.Pagamento;
import br.com.fiap.pagamento.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public PagamentoResponse processarPagamento(PagamentoRequest request) {
        Pagamento pagamento = PagamentoMapper.toModel(request);
        return PagamentoMapper.toResponse(repository.save(pagamento));
    }

    public PagamentoResponse buscarPorPedido(String idPedido) {
        Pagamento pagamento = repository.findByIdPedido(idPedido);
        if (pagamento == null) throw new PagamentoNotFoundException("Pedido não encontrado");
        return PagamentoMapper.toResponse(pagamento);
    }

    public List<PagamentoResponse> listarTodos() {
        return repository.findAll().stream().map(PagamentoMapper::toResponse).collect(Collectors.toList());
    }
}