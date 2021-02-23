package com.chrish.paymentsystem.paymentorchestratorcomms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.chrish.paymentsystem.paymentorchestratorcomms.listener.PaymentsInListener;
import com.chrish.paymentsystem.paymentorchestratorcomms.listener.PaymentsOutListener;

@Configuration
@ComponentScan
public class PaymentsQueueConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentsQueueConfiguration.class);

    public static final String PAYMENTS_IN_QUEUE_NAME = "payments.in";
    public static final String PAYMENTS_OUT_QUEUE_NAME = "payments.out";

    @Bean
    Queue paymentsInQueue() {
        return new Queue(PAYMENTS_IN_QUEUE_NAME);
    }

    @Bean
    Queue paymentsOutQueue() {
        return new Queue(PAYMENTS_OUT_QUEUE_NAME);
    }

    @Bean
    Binding paymentsInBinding(Queue paymentsInQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(paymentsInQueue).to(directExchange).with(PAYMENTS_IN_QUEUE_NAME);
    }

    @Bean
    Binding paymentsOutBinding(Queue paymentsOutQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(paymentsOutQueue).to(directExchange).with(PAYMENTS_OUT_QUEUE_NAME);
    }

    @Bean
    @ConditionalOnBean(PaymentsInListener.class)
    SimpleMessageListenerContainer paymentsInContainer(ConnectionFactory connectionFactory, PaymentsInListener paymentsInListener) {
        LOG.debug("Configuring PaymentsInListener");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(PAYMENTS_IN_QUEUE_NAME);
        container.setMessageListener(paymentsInListener);
        container.setConcurrentConsumers(10);
        return container;
    }

    @Bean
    @ConditionalOnBean(PaymentsOutListener.class)
    SimpleMessageListenerContainer paymentsOutContainer(ConnectionFactory connectionFactory, PaymentsOutListener paymentsOutListener) {
        LOG.debug("Configuring PaymentsOutListener");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(PAYMENTS_OUT_QUEUE_NAME);
        container.setMessageListener(paymentsOutListener);
        container.setConcurrentConsumers(10);
        return container;
    }

}
