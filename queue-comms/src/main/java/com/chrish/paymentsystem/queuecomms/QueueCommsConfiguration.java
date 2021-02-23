package com.chrish.paymentsystem.queuecomms;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueCommsConfiguration {

    private static final String PAYMENT_SYSTEM_EXCHANGE_NAME = "payment-system";

    @Value("${BROKER_URL}")
    private String brokerUrl;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(PAYMENT_SYSTEM_EXCHANGE_NAME);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(brokerUrl);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}