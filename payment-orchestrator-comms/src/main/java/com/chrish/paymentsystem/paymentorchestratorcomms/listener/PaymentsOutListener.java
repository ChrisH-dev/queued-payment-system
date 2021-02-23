package com.chrish.paymentsystem.paymentorchestratorcomms.listener;

import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentOutMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentOutMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.listener.QueueListener;

public abstract class PaymentsOutListener extends QueueListener<PaymentOutMessage, PaymentOutMessageSerialiser> {

    public PaymentsOutListener(PaymentOutMessageSerialiser serialiser) {
        super(serialiser);
    }

}
