package com.chrish.paymentsystem.acquirercomms.listener;

import com.chrish.paymentsystem.acquirercomms.message.AcquirerOutMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerOutMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.listener.QueueListener;

public abstract class AcquirerCommsOutListener extends QueueListener<AcquirerOutMessage, AcquirerOutMessageSerialiser> {

    public AcquirerCommsOutListener(AcquirerOutMessageSerialiser serialiser) {
        super(serialiser);
    }

}
