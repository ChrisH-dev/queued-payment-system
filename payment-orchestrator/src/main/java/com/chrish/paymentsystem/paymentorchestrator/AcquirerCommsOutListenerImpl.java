package com.chrish.paymentsystem.paymentorchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chrish.paymentsystem.acquirercomms.listener.AcquirerCommsOutListener;
import com.chrish.paymentsystem.acquirercomms.message.AcquirerOutMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerOutMessageSerialiser;
import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentOutMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.submission.PaymentsOutSubmitter;
import com.chrish.paymentsystem.queuecomms.exception.QueueSubmissionException;

@Service
public class AcquirerCommsOutListenerImpl extends AcquirerCommsOutListener {

    private static final Logger LOG = LoggerFactory.getLogger(AcquirerCommsOutListenerImpl.class);

    private final PaymentsOutSubmitter paymentsOutSubmitter;

    public AcquirerCommsOutListenerImpl(
            AcquirerOutMessageSerialiser acquirerOutMessageSerialiser,
            PaymentsOutSubmitter paymentsOutSubmitter
    ) {
        super(acquirerOutMessageSerialiser);
        this.paymentsOutSubmitter = paymentsOutSubmitter;
    }

    @Override
    public void onMessage(AcquirerOutMessage acquirerMessage) {
//        LOG.info("Received message: {}", acquirerMessage);

        PaymentOutMessage paymentOutMessage = new PaymentOutMessage();
        paymentOutMessage.setId(acquirerMessage.getId());
        paymentOutMessage.setResult("Yay!");

        LOG.info("Sending message to API Gateway");
        try {
            paymentsOutSubmitter.submit(paymentOutMessage);
        } catch (QueueSubmissionException e) {
            //TODO handle this
            e.printStackTrace();
        }
    }
}
