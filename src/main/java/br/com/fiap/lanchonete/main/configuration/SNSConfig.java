package br.com.fiap.lanchonete.main.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;


@Configuration
public class SNSConfig {


  

    @Value("${cloud.aws.sns.topic.pedido-pago}")
    private String pedidoPagoTopicName;

    @Value("${cloud.aws.sns.topic.pedido-cancelado}")
    private String pedidoCanceladoTopicName;

    @Value("${cloud.aws.end-point.uri}")
    private String sqsUrl;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKeyId;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretAccessKey;


    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl , region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                .build();
    }




    @Bean
    public String pedidoPagoTopicArn() {
        AmazonSNS snsClient = snsClient();
        CreateTopicRequest request = new CreateTopicRequest(pedidoPagoTopicName);
        CreateTopicResult result = snsClient.createTopic(request);
        return result.getTopicArn();
    }

    @Bean
    public String pedidoCanceladoTopicArn() {
        AmazonSNS snsClient = snsClient();
        CreateTopicRequest request = new CreateTopicRequest(pedidoCanceladoTopicName);
        CreateTopicResult result = snsClient.createTopic(request);
        return result.getTopicArn();
    }
    
}
