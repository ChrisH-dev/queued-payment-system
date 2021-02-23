package com.chrish.paymentsystem.queuecomms.listener;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.chrish.paymentsystem.queuecomms.message.QueueMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

public abstract class QueueListener<T extends QueueMessage, S extends MessageSerialiser<T>> implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(QueueListener.class);

    private final S serialiser;

    public QueueListener(S serialiser) {
        this.serialiser = serialiser;
    }

    @Override
    public void onMessage(Message message) {
        try {
            T queueMessage = serialiser.deserialise(message.getBody());

            LOG.info("Received message: {}", queueMessage);

            onMessage(queueMessage);
        } catch (JAXBException e) {
            //TODO: log/handle this
            e.printStackTrace();
        }
    }

    public abstract void onMessage(T queueMessage);
}
