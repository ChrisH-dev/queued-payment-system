package com.chrish.paymentsystem.acquirercomms.submission;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.acquirercomms.AcquirerCommsConfiguration;
import com.chrish.paymentsystem.acquirercomms.message.AcquirerOutMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerOutMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.submission.QueueSubmitter;

@Component
public class AcquirerCommsOutSubmitter extends QueueSubmitter<AcquirerOutMessage, AcquirerOutMessageSerialiser> {

    public AcquirerCommsOutSubmitter(AcquirerOutMessageSerialiser serialiser, RabbitTemplate rabbitTemplate) {
        super(AcquirerCommsConfiguration.ACQUIRER_COMMS_OUT_QUEUE_NAME, serialiser, rabbitTemplate);
    }

}
