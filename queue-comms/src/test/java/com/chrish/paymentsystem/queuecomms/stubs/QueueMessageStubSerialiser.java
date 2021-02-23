package com.chrish.paymentsystem.queuecomms.stubs;

import javax.xml.bind.JAXBException;

import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

public class QueueMessageStubSerialiser extends MessageSerialiser<QueueMessageStub> {

    public QueueMessageStubSerialiser() throws JAXBException {
        super(QueueMessageStub.class);
    }
}
