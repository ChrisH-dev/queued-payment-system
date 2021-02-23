package com.chrish.paymentsystem.paymentorchestratorcomms.serialisation;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentInMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

@Component
public class PaymentInMessageSerialiser extends MessageSerialiser<PaymentInMessage> {

    public PaymentInMessageSerialiser() throws JAXBException {
        super(PaymentInMessage.class);
    }
}
