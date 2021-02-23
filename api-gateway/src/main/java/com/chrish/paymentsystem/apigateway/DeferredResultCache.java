package com.chrish.paymentsystem.apigateway;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class DeferredResultCache {

    private Map<String, DeferredResult<String>> awaitingThreads = new ConcurrentHashMap<>();

    public void add(String paymentId, DeferredResult<String> deferredResult) {
        awaitingThreads.put(paymentId, deferredResult);
    }

    public void notify(String paymentId) {
        DeferredResult<String> deferredResult = awaitingThreads.remove(paymentId);
        if (deferredResult != null) {
            //TODO: log something if deferredResult is null and/or if setResult returns false.
            //TODO: aka the paymentId isn't in cache or the deferredResult has already been set
            //TODO: (neither should happen but good to know if it does)
            deferredResult.setResult(paymentId);
        }
    }
}
