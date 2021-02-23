package com.chrish.paymentsystem.queuecomms.listener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;

import com.chrish.paymentsystem.queuecomms.stubs.QueueMessageStub;
import com.chrish.paymentsystem.queuecomms.stubs.QueueMessageStubSerialiser;

public class QueueListenerTest {

    @Mock private Message messageMock;
    @Mock private QueueMessageStub queueMessageStubMock;
    @Mock private QueueMessageStubSerialiser queueMessageStubSerialiserMock;

    private QueueListenerStub queueListenerStub;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        queueListenerStub = new QueueListenerStub(queueMessageStubSerialiserMock);
    }

    @Test
    public void onMessageCalledWithDeserialisedMessage() throws Exception {
        byte[] messageBody = {0x11, 0x22};
        when(messageMock.getBody()).thenReturn(messageBody);

        when(queueMessageStubSerialiserMock.deserialise(messageBody)).thenReturn(queueMessageStubMock);

        queueListenerStub.onMessage(messageMock);

        assertEquals(queueMessageStubMock, queueListenerStub.getQueueMessage());
    }

    private class QueueListenerStub extends QueueListener<QueueMessageStub, QueueMessageStubSerialiser> {

        private QueueMessageStub queueMessage;

        public QueueListenerStub(QueueMessageStubSerialiser serialiser) {
            super(serialiser);
        }

        @Override
        public void onMessage(QueueMessageStub queueMessage) {
            this.queueMessage = queueMessage;
        }

        public QueueMessageStub getQueueMessage() {
            return queueMessage;
        }
    }
}
