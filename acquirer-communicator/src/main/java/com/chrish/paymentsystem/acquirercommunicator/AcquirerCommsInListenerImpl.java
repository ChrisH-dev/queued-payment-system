package com.chrish.paymentsystem.acquirercommunicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chrish.paymentsystem.acquirercomms.listener.AcquirerCommsInListener;
import com.chrish.paymentsystem.acquirercomms.message.AcquirerInMessage;
import com.chrish.paymentsystem.acquirercomms.message.AcquirerOutMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerInMessageSerialiser;
import com.chrish.paymentsystem.acquirercomms.submission.AcquirerCommsOutSubmitter;
import com.chrish.paymentsystem.queuecomms.exception.QueueSubmissionException;

@Service
public class AcquirerCommsInListenerImpl extends AcquirerCommsInListener {

    private static final Logger LOG = LoggerFactory.getLogger(AcquirerCommsInListenerImpl.class);

    private final AcquirerCommsOutSubmitter acquirerCommsOutSubmitter;

    public AcquirerCommsInListenerImpl(
            AcquirerInMessageSerialiser acquirerInMessageSerialiser,
            AcquirerCommsOutSubmitter acquirerCommsOutSubmitter
    ) {
        super(acquirerInMessageSerialiser);
        this.acquirerCommsOutSubmitter = acquirerCommsOutSubmitter;
    }

    @Override
    public void onMessage(AcquirerInMessage acquirerInMessage) {
        LOG.info("Sleeping for 3 seconds");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AcquirerOutMessage acquirerOutMessage = new AcquirerOutMessage();
        acquirerOutMessage.setId(acquirerInMessage.getId());
        acquirerOutMessage.setResult("Yay!");

        LOG.info("Returning result");
        try {
            acquirerCommsOutSubmitter.submit(acquirerOutMessage);
        } catch (QueueSubmissionException e) {
            //TODO handle this
            e.printStackTrace();
        }
    }
}
