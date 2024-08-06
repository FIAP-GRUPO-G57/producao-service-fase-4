package br.com.fiap.lanchonete.infra.db.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@ToString
@Document(collection = "historico")
@NoArgsConstructor
@Getter
@Setter
public class HistoricoMongoEntity {

    @Id
    private String id;
    
    @DBRef
    @Field("pedido_idPedido")
    private PedidoMongoEntity pedido;

    @Field("status")
    private StatusEnum status;

    @Field("timestampCriacao")
    private LocalDateTime timestampCriacao;
}