package br.com.fiap.lanchonete.core.usecases.pedido;

import br.com.fiap.lanchonete.core.domain.entities.HistoricoPedido;
import br.com.fiap.lanchonete.core.domain.entities.Pedido;
import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.HistoricoPedidoRepositoryPort;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.PedidoRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReceivePedidoUsecase {

	private final PedidoRepositoryPort pedidoPort;
	private final HistoricoPedidoRepositoryPort historicoPedidoRepositoryPort;

	public Pedido receive(Long id) {
		Pedido pedido = pedidoPort.save(Pedido.builder()
				.id(id)
				.status(StatusEnum.EM_PRODUCAO)
				.build());
		HistoricoPedido historicoPedido = HistoricoPedido.builder()
				.idPedido(pedido.getId())
				.status(pedido.getStatus())
				.build();
		historicoPedidoRepositoryPort.save(historicoPedido);
		return pedidoPort.get(id);
	}

}
