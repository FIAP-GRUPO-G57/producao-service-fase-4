package br.com.fiap.lanchonete.core.usecases.pedido;

import br.com.fiap.lanchonete.core.domain.entities.HistoricoPedido;
import br.com.fiap.lanchonete.core.domain.entities.Pedido;
import br.com.fiap.lanchonete.core.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.HistoricoPedidoRepositoryPort;
import br.com.fiap.lanchonete.core.usecases.ports.repositories.PedidoRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompletePedidoUsecaseTest {

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private HistoricoPedidoRepositoryPort historicoPedidoRepositoryPort;

    @InjectMocks
    private CompletePedidoUsecase completePedidoUsecase;

    @Test
    void should_throws_if_pedido_not_found() {
        Long pedidoId = 1L;
        when(pedidoPort.get(pedidoId)).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            completePedidoUsecase.complete(pedidoId);
        });

        assertEquals("Pedido nao encontrado para o id: " + pedidoId, thrown.getMessage());
    }

    @Test
    void should_update_pedido_status_to_finalizado_if_pedido_exists() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(StatusEnum.EM_PRODUCAO);

        when(pedidoPort.get(pedidoId)).thenReturn(pedido);
        when(pedidoPort.save(any(Pedido.class))).thenReturn(pedido);

        Pedido result = completePedidoUsecase.complete(pedidoId);

        assertEquals(StatusEnum.FINALIZADO, result.getStatus());
        verify(pedidoPort, times(1)).save(pedido);
    }

    @Test
    void should_save_historico_pedido_if_pedido_is_finalizado() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(StatusEnum.EM_PRODUCAO);

        when(pedidoPort.get(pedidoId)).thenReturn(pedido);
        when(pedidoPort.save(any(Pedido.class))).thenReturn(pedido);

        completePedidoUsecase.complete(pedidoId);

        ArgumentCaptor<HistoricoPedido> historicoCaptor = ArgumentCaptor.forClass(HistoricoPedido.class);
        verify(historicoPedidoRepositoryPort, times(1)).save(historicoCaptor.capture());
        HistoricoPedido historicoPedido = historicoCaptor.getValue();
        assertEquals(pedidoId, historicoPedido.getIdPedido());
        assertEquals(StatusEnum.FINALIZADO, historicoPedido.getStatus());
    }
}
