package com.chrish.paymentsystem.queuecomms.submission;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.chrish.paymentsystem.queuecomms.exception.QueueSubmissionException;
import com.chrish.paymentsystem.queuecomms.message.QueueMessage;
import com.chrish.paymentsystem.queuecomms.serialisation.MessageSerialiser;

public abstract class QueueSubmitter<T extends QueueMessage, S extends MessageSerialiser<T>> {

    private static final Logger LOG = LoggerFactory.getLogger(QueueSubmitter.class);

    private final String queueName;
    private final S serialiser;
    private final RabbitTemplate rabbitTemplate;

    public QueueSubmitter(String queueName, S serialiser, RabbitTemplate rabbitTemplate) {
        this.queueName = queueName;
        this.serialiser = serialiser;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void submit(T queueMessage) throws QueueSubmissionException {
        LOG.info("Sending message: {}", queueMessage);
        try {
            String message = serialiser.serialise(queueMessage);

            rabbitTemplate.convertAndSend(queueName, message);
        } catch (JAXBException e) {
            throw new QueueSubmissionException("Problem serialising QueueMessage", e);
        }
    }
}
