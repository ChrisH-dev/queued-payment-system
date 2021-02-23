package com.chrish.paymentsystem.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chrish.paymentsystem.paymentorchestratorcomms.listener.PaymentsOutListener;
import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentOutMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.serialisation.PaymentOutMessageSerialiser;

@Service
public class PaymentsOutListenerImpl extends PaymentsOutListener {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentsOutListenerImpl.class);

    private final DeferredResultCache deferredResultCache;

    public PaymentsOutListenerImpl(
            PaymentOutMessageSerialiser paymentOutMessageSerialiser,
            DeferredResultCache deferredResultCache
    ) {
        super(paymentOutMessageSerialiser);
        this.deferredResultCache = deferredResultCache;
    }

    @Override
    public void onMessage(PaymentOutMessage paymentOutMessage) {
        LOG.info("Received message: {}", paymentOutMessage);

        deferredResultCache.notify(paymentOutMessage.getId());
    }

}
