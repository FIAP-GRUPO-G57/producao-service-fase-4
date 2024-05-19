package br.com.fiap.lanchonete.core.usecases.pedido;

import br.com.fiap.lanchonete.core.domain.entities.HistoricoPedido;
import br.com.fiap.lanchonete.core.domain.entities.Pedido;
import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.HistoricoPedidoRepositoryPort;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.PedidoRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CompletePedidoUsecase {

	private final PedidoRepositoryPort pedidoPort;
	private final HistoricoPedidoRepositoryPort historicoPedidoRepositoryPort;

	public Pedido complete(Long id) {
		Pedido pedido = pedidoPort.get(id);
		if (Objects.isNull(pedido)) {
			throw new EntityNotFoundException("Pedido nao encontrado para o id: " + id);
		}
		pedido.setStatus(StatusEnum.FINALIZADO);
		Pedido updatedPedido = pedidoPort.save(pedido);
		HistoricoPedido historicoPedido = HistoricoPedido.builder()
				.idPedido(updatedPedido.getId())
				.status(updatedPedido.getStatus())
				.build();
		historicoPedidoRepositoryPort.save(historicoPedido);
		return updatedPedido;
	}

}
