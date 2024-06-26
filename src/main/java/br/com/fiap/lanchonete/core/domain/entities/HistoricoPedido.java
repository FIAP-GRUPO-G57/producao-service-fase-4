package br.com.fiap.lanchonete.core.domain.entities;

import java.time.LocalDateTime;

import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HistoricoPedido {
    private Long id;
    private Long idPedido;
    private StatusEnum status;
    private LocalDateTime timestampCriacao;
}
