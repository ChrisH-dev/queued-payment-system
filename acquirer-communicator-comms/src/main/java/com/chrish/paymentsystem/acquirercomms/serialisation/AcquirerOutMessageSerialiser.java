package com.chrish.paymentsystem.acquirercomms.serialisation;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.acquirercomms.message.AcquirerOutMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

@Component
public class AcquirerOutMessageSerialiser extends MessageSerialiser<AcquirerOutMessage> {

    public AcquirerOutMessageSerialiser() throws JAXBException {
        super(AcquirerOutMessage.class);
    }
}
