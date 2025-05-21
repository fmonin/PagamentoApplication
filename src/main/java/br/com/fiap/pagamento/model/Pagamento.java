package br.com.fiap.pagamento.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "pagamentos")
public class Pagamento {
    @Id
    private String id;
    @Setter
    @Getter
    private String idPedido;
    @Setter
    @Getter
    private String idCartao;
    @Setter
    @Getter
    private BigDecimal valor;
    @Setter
    @Getter
    private String status;
    @Setter
    @Getter
    private String dataAprovacao;

    public Pagamento(String idPedido, String idCartao, BigDecimal valor, String status) {
        this.idPedido = idPedido;
        this.idCartao = idCartao;
        this.valor = valor;
        this.dataAprovacao = LocalDateTime.now().toString();
        this.status = valor.compareTo(new BigDecimal("1500")) < 0 ? "APROVADO" : "RECUSADO";
    }

    public Pagamento(String number, String id, BigDecimal bigDecimal, String s, String aprovado) {
    }
}