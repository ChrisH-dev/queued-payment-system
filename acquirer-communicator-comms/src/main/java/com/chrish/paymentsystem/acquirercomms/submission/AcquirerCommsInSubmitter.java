package com.chrish.paymentsystem.acquirercomms.submission;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.chrish.paymentsystem.acquirercomms.AcquirerCommsConfiguration;
import com.chrish.paymentsystem.acquirercomms.message.AcquirerInMessage;
import com.chrish.paymentsystem.acquirercomms.serialisation.AcquirerInMessageSerialiser;
import com.chrish.paymentsystem.queuecomms.submission.QueueSubmitter;

@Component
public class AcquirerCommsInSubmitter extends QueueSubmitter<AcquirerInMessage, AcquirerInMessageSerialiser> {

    public AcquirerCommsInSubmitter(AcquirerInMessageSerialiser serialiser, RabbitTemplate rabbitTemplate) {
        super(AcquirerCommsConfiguration.ACQUIRER_COMMS_IN_QUEUE_NAME, serialiser, rabbitTemplate);
    }

}
