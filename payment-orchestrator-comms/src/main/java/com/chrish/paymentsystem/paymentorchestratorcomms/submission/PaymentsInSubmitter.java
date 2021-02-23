package com.chrish.paymentsystem.paymentorchestratorcomms.submission;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.paymentorchestratorcomms.PaymentsQueueConfiguration;
import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentInMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentInMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.submission.QueueSubmitter;

@Component
public class PaymentsInSubmitter extends QueueSubmitter<PaymentInMessage, PaymentInMessageSerialiser> {

    public PaymentsInSubmitter(PaymentInMessageSerialiser serialiser, RabbitTemplate rabbitTemplate) {
        super(PaymentsQueueConfiguration.PAYMENTS_IN_QUEUE_NAME, serialiser, rabbitTemplate);
    }

}
