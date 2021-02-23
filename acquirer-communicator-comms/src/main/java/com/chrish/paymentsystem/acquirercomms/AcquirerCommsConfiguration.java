package com.chrish.paymentsystem.acquirercomms;

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

import com.chrish.paymentsystem.acquirercomms.listener.AcquirerCommsInListener;
import com.chrish.paymentsystem.acquirercomms.listener.AcquirerCommsOutListener;

@Configuration
@ComponentScan
public class AcquirerCommsConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(AcquirerCommsConfiguration.class);

    public static final String ACQUIRER_COMMS_IN_QUEUE_NAME = "acquirer-comms.in";
    public static final String ACQUIRER_COMMS_OUT_QUEUE_NAME = "acquirer-comms.out";

    @Bean
    Queue acquirerCommsInQueue() {
        return new Queue(ACQUIRER_COMMS_IN_QUEUE_NAME);
    }

    @Bean
    Queue acquirerCommsOutQueue() {
        return new Queue(ACQUIRER_COMMS_OUT_QUEUE_NAME);
    }

    @Bean
    Binding acquirerCommsInBinding(Queue acquirerCommsInQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(acquirerCommsInQueue).to(directExchange).with(ACQUIRER_COMMS_IN_QUEUE_NAME);
    }

    @Bean
    Binding acquirerCommsOutBinding(Queue acquirerCommsOutQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(acquirerCommsOutQueue).to(directExchange).with(ACQUIRER_COMMS_OUT_QUEUE_NAME);
    }

    @Bean
    @ConditionalOnBean(AcquirerCommsInListener.class)
    SimpleMessageListenerContainer acquirerCommsInContainer(ConnectionFactory connectionFactory, AcquirerCommsInListener acquirerCommsInListener) {
        LOG.debug("Configuring AcquirerCommsInListener");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(ACQUIRER_COMMS_IN_QUEUE_NAME);
        container.setMessageListener(acquirerCommsInListener);
        container.setConcurrentConsumers(10);
        return container;
    }

    @Bean
    @ConditionalOnBean(AcquirerCommsOutListener.class)
    SimpleMessageListenerContainer acquirerCommsOutContainer(ConnectionFactory connectionFactory, AcquirerCommsOutListener acquirerCommsOutListener) {
        LOG.debug("Configuring AcquirerCommsOutListener");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(ACQUIRER_COMMS_OUT_QUEUE_NAME);
        container.setMessageListener(acquirerCommsOutListener);
        container.setConcurrentConsumers(10);
        return container;
    }

}
