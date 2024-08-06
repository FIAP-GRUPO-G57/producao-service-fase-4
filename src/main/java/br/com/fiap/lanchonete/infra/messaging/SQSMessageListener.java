package br.com.fiap.lanchonete.infra.messaging;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import br.com.fiap.lanchonete.core.event.PedidoCanceladoEvent;
import br.com.fiap.lanchonete.core.event.PedidoPagoEvent;

@Component
public class SQSMessageListener {


    @SqsListener("${cloud.aws.sqs.queue.producao-pedido-pago}")
    public void processarPedido(PedidoPagoEvent event) {
        // Lógica para processar pedido
    }

    @SqsListener("${cloud.aws.sqs.queue.producao-cancelamento}")
    public void cancelarPedido(PedidoCanceladoEvent event) {
        // Lógica para cancelar pedido
    }
}
