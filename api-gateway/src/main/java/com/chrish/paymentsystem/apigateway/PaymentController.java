package com.chrish.paymentsystem.apigateway;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.chrish.paymentsystem.paymentorchestratorcomms.message.PaymentInMessage;
import com.chrish.paymentsystem.paymentorchestratorcomms.submission.PaymentsInSubmitter;
import com.chrish.paymentsystem.queuecomms.exception.QueueSubmissionException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentsInSubmitter paymentsInSubmitter;
    private final DeferredResultCache deferredResultCache;

    public PaymentController(
            PaymentsInSubmitter paymentsInSubmitter,
            DeferredResultCache deferredResultCache
    ) {
        this.paymentsInSubmitter = paymentsInSubmitter;
        this.deferredResultCache = deferredResultCache;
    }

//    @RequestMapping("/test")
//    public String test() {
////        Executors.newFixedThreadPool(1).execute(() -> {
////            for (int i = 0; i < 10000; i++) {
////                rabbitTemplate.convertAndSend(ApiGatewayApplication.PAYMENTS_QUEUE_NAME, "Hello Mr Rabbit!");
////            }
////        });
//
//        PaymentMessage paymentMessage = new PaymentMessage();
//        paymentMessage.setId(UUID.randomUUID().toString());
//        paymentMessage.setCardNumber("4444333322221111");
//        paymentMessage.setCvc("123");
//        paymentMessage.setAmount(100L);
//        paymentMessage.setCurrencyCode("GBP");
//
//        try {
//            String message = paymentMessageSerialiser.serialise(paymentMessage);
//
//            LOG.info("Sending: {}", message);
//            rabbitTemplate.convertAndSend(PaymentsQueueConfiguration.PAYMENTS_IN_QUEUE_NAME, message);
//
//            Object monitor = new Object();
//            awaitingThreadsCache.addThread(paymentMessage.getId(), monitor);
//
//            //TODO: add timeout e.g. wait(1000)
//            monitor.wait();
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        LOG.info("Returning Hello! for {}", paymentMessage.getId());
//        return "Hello!";
//    }

    @RequestMapping("/test2")
    public DeferredResult<String> test2() {
        LOG.info("Received request at /test2");

        PaymentInMessage paymentInMessage = new PaymentInMessage();
        paymentInMessage.setId(UUID.randomUUID().toString());
        paymentInMessage.setCardNumber("4444333322221111");
        paymentInMessage.setCvc("123");
        paymentInMessage.setAmount(100L);
        paymentInMessage.setCurrencyCode("GBP");

        DeferredResult<String> result = new DeferredResult<>();

        LOG.info("Sending: {}", paymentInMessage);
        try {
            paymentsInSubmitter.submit(paymentInMessage);

            deferredResultCache.add(paymentInMessage.getId(), result);
        } catch (QueueSubmissionException e) {
            LOG.warn("Errors!", e);
            result.setResult("Errors!");
        }

        LOG.info("Returning deferredResult");

        return result;
    }

}
