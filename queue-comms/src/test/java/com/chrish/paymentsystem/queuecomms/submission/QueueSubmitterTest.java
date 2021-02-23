package com.chrish.paymentsystem.queuecomms.submission;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.chrish.paymentsystem.queuecomms.stubs.QueueMessageStub;
import com.chrish.paymentsystem.queuecomms.stubs.QueueMessageStubSerialiser;

public class QueueSubmitterTest {

    @Mock private QueueMessageStubSerialiser queueMessageStubSerialiserMock;
    @Mock private RabbitTemplate rabbitTemplateMock;

    private QueueSubmitterStub queueSubmitterStub;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        queueSubmitterStub = new QueueSubmitterStub(queueMessageStubSerialiserMock, rabbitTemplateMock);
    }

//    @Test
//    public void name() {
//        queueSubmitterStub.submit();
//    }

    private class QueueSubmitterStub extends QueueSubmitter<QueueMessageStub, QueueMessageStubSerialiser> {
        public static final String QUEUE_NAME = "stub queue";

        public QueueSubmitterStub(QueueMessageStubSerialiser serialiser, RabbitTemplate rabbitTemplate) {
            super(QUEUE_NAME, serialiser, rabbitTemplate);
        }
    }

}