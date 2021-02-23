package com.chrish.paymentsystem.paymentorchestratorcomms.listener;

import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentInMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentInMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.listener.QueueListener;

public abstract class PaymentsInListener extends QueueListener<PaymentInMessage, PaymentInMessageSerialiser> {

    public PaymentsInListener(PaymentInMessageSerialiser serialiser) {
        super(serialiser);
    }

}
