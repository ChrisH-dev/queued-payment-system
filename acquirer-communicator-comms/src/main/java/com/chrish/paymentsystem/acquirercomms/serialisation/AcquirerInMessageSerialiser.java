package com.chrish.paymentsystem.acquirercomms.serialisation;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.acquirercomms.message.AcquirerInMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

@Component
public class AcquirerInMessageSerialiser extends MessageSerialiser<AcquirerInMessage> {

    public AcquirerInMessageSerialiser() throws JAXBException {
        super(AcquirerInMessage.class);
    }
}
