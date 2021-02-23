package com.chrish.paymentsystem.acquirercommunicator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"BROKER_URL=testBrokerUrl"})
public class AcquirerCommunicationApplicationTests {

	@MockBean
	private RabbitAdmin rabbitAdminMock;

	@Test
	public void contextLoads() {
	}

}
