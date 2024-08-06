/*package br.com.fiap.lanchonete.main.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;

import jakarta.annotation.PostConstruct;

@Configuration
@DependsOn({"snsClient", "amazonSQSAsync"})
public class SNSSubscriptionConfig {

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    private AmazonSQS sqsClient;

    @Value("${cloud.aws.sqs.queue.producao-cancelamento}")
    private String producaoCancelamentoQueueName;

    @Value("${cloud.aws.sqs.queue.producao-pedido-pago}")
    private String producaoPedidoPagoQueueName;

    @Value("${cloud.aws.sns.topic.pedido-cancelado}")
    private String pedidoCanceladoTopicName;

    @Value("${cloud.aws.sns.topic.pedido-pago}")
    private String pedidoPagoTopicName;

    @PostConstruct
    public void subscribeQueuesToTopic() {
        String producaoCancelamentoQueueArn = sqsClient.getQueueAttributes(new GetQueueAttributesRequest(producaoCancelamentoQueueName).withAttributeNames("QueueArn")).getAttributes().get("QueueArn");

        String pedidoCanceladoTopicArn = snsClient.getTopicAttributes(pedidoCanceladoTopicName).getAttributes().get("TopicArn");


        String producaoPedidoPagoQueueArn = sqsClient.getQueueAttributes(new GetQueueAttributesRequest(producaoPedidoPagoQueueName).withAttributeNames("QueueArn")).getAttributes().get("QueueArn");
        String pedidoPagoTopicArn = snsClient.getTopicAttributes(pedidoPagoTopicName).getAttributes().get("TopicArn");

        snsClient.subscribe(new SubscribeRequest(pedidoPagoTopicArn, "sqs", producaoPedidoPagoQueueArn));

        snsClient.subscribe(new SubscribeRequest(pedidoCanceladoTopicArn, "sqs", producaoCancelamentoQueueArn));
    }
}

*/
