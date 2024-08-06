package br.com.fiap.lanchonete.infra.db.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "pedidos")
@NoArgsConstructor
@Getter
@Setter
public class PedidoMongoEntity {

    @Id
    private Long idPedido;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @CreationTimestamp
    private LocalDateTime timestampCriacao;

    @UpdateTimestamp
    private LocalDateTime timestampAlteracao;


    // Getters and Setters
}