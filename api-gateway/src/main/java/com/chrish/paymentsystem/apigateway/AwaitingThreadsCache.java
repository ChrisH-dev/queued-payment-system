package com.chrish.paymentsystem.apigateway;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class AwaitingThreadsCache {

    private Map<String, Object> awaitingThreads = new ConcurrentHashMap<>();

    public void addThread(String paymentId, Object monitor) {
        awaitingThreads.put(paymentId, monitor);
    }

    public void notifyThread(String paymentId) {
        Object monitor = awaitingThreads.get(paymentId);
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
}
