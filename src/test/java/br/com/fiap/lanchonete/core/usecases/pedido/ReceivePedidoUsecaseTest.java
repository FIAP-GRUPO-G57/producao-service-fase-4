package br.com.fiap.lanchonete.core.usecases.pedido;

import br.com.fiap.lanchonete.core.domain.entities.HistoricoPedido;
import br.com.fiap.lanchonete.core.domain.entities.Pedido;
import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.HistoricoPedidoRepositoryPort;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.PedidoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceivePedidoUsecaseTest {

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private HistoricoPedidoRepositoryPort historicoPedidoRepositoryPort;

    @InjectMocks
    private ReceivePedidoUsecase receivePedidoUsecase;

    @Test
    void should_save_pedido_with_status_em_producao() {
        Long pedidoId = 1L;
        Pedido pedido = Pedido.builder()
                .id(pedidoId)
                .status(StatusEnum.EM_PRODUCAO)
                .build();

        when(pedidoPort.save(any(Pedido.class))).thenReturn(pedido);
        when(pedidoPort.get(pedidoId)).thenReturn(pedido);

        Pedido result = receivePedidoUsecase.receive(pedidoId);

        assertEquals(pedidoId, result.getId());
        assertEquals(StatusEnum.EM_PRODUCAO, result.getStatus());
        verify(pedidoPort, times(1)).save(any(Pedido.class));
        verify(pedidoPort, times(1)).get(pedidoId);
    }

    @Test
    void should_save_historico_pedido() {
        Long pedidoId = 1L;
        Pedido pedido = Pedido.builder()
                .id(pedidoId)
                .status(StatusEnum.EM_PRODUCAO)
                .build();

        when(pedidoPort.save(any(Pedido.class))).thenReturn(pedido);
        when(pedidoPort.get(pedidoId)).thenReturn(pedido);

        receivePedidoUsecase.receive(pedidoId);

        ArgumentCaptor<HistoricoPedido> historicoCaptor = ArgumentCaptor.forClass(HistoricoPedido.class);
        verify(historicoPedidoRepositoryPort, times(1)).save(historicoCaptor.capture());
        HistoricoPedido historicoPedido = historicoCaptor.getValue();
        assertEquals(pedidoId, historicoPedido.getIdPedido());
        assertEquals(StatusEnum.EM_PRODUCAO, historicoPedido.getStatus());
    }
}
