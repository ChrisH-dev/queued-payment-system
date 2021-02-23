package com.chrish.paymentsystem.paymentorchestratorcomms.serialisation;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentOutMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;


@Component
public class PaymentOutMessageSerialiser extends MessageSerialiser<PaymentOutMessage> {

    public PaymentOutMessageSerialiser() throws JAXBException {
        super(PaymentOutMessage.class);
    }
}
