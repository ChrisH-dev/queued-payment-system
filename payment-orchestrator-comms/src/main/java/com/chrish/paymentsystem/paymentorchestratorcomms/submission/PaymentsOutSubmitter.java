package com.chrish.paymentsystem.paymentorchestratorcomms.submission;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.paymentorchestratorcomms.PaymentsQueueConfiguration;
import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentOutMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentOutMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.submission.QueueSubmitter;

@Component
public class PaymentsOutSubmitter extends QueueSubmitter<PaymentOutMessage, PaymentOutMessageSerialiser> {

    public PaymentsOutSubmitter(PaymentOutMessageSerialiser serialiser, RabbitTemplate rabbitTemplate) {
        super(PaymentsQueueConfiguration.PAYMENTS_OUT_QUEUE_NAME, serialiser, rabbitTemplate);
    }

}
