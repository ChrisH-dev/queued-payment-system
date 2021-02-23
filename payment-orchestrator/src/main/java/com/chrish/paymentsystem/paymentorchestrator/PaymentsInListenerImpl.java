package com.chrish.paymentsystem.paymentorchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chrish.paymentsystem.acquirercomms.message.AcquirerInMessage;
import com.chrish.paymentsystem.acquirercomms.submission.AcquirerCommsInSubmitter;
import com.chrish.paymentsystem.paymentorchestratorcomms.listener.PaymentsInListener;
import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentInMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentInMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.exception.QueueSubmissionException;

@Service
public class PaymentsInListenerImpl extends PaymentsInListener {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentsInListenerImpl.class);

    private final AcquirerCommsInSubmitter acquirerCommsInSubmitter;

    public PaymentsInListenerImpl(
            PaymentInMessageSerialiser paymentInMessageSerialiser,
            AcquirerCommsInSubmitter acquirerCommsInSubmitter
    ) {
        super(paymentInMessageSerialiser);
        this.acquirerCommsInSubmitter = acquirerCommsInSubmitter;
    }

    @Override
    public void onMessage(PaymentInMessage paymentInMessage) {
//        LOG.info("Received message: {}", paymentInMessage);

        AcquirerInMessage acquirerInMessage = new AcquirerInMessage();
        acquirerInMessage.setId(paymentInMessage.getId());

        LOG.info("Sending message to acquirer communicator");
        try {
            acquirerCommsInSubmitter.submit(acquirerInMessage);
        } catch (QueueSubmissionException e) {
            //TODO: handle this
            e.printStackTrace();
        }
    }
}
