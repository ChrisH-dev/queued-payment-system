package com.chrish.paymentsystem.acquirercomms.listener;

import com.chrish.paymentsystem.acquirercomms.message.AcquirerInMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerInMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.listener.QueueListener;

public abstract class AcquirerCommsInListener extends QueueListener<AcquirerInMessage, AcquirerInMessageSerialiser> {

    public AcquirerCommsInListener(AcquirerInMessageSerialiser serialiser) {
        super(serialiser);
    }

}
