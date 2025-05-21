package br.com.fiap.pagamento.repository;

import br.com.fiap.pagamento.model.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagamentoRepository extends MongoRepository<Pagamento, String> {
    Pagamento findByIdPedido(String idPedido);
}