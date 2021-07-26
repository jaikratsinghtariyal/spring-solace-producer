package com.spring.solace.producer;

import com.solacesystems.jcsmp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoPublishEventHandler implements JCSMPStreamingPublishEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(DemoPublishEventHandler.class);

    @Autowired
    private SpringJCSMPFactory solaceFactory;

    public void publishMessage(String message) throws JCSMPException {
        final Topic topic = JCSMPFactory.onlyInstance().createTopic("tutorial/topic/test/india/banking");
        final JCSMPSession session = solaceFactory.createSession();
        XMLMessageProducer prod = session.getMessageProducer(this);

        TextMessage jcsmpMsg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        jcsmpMsg.setText(message);
        //jcsmpMsg.setDeliveryMode(DeliveryMode.PERSISTENT);

        logger.info("============= Sending " + message);
        prod.send(jcsmpMsg, topic);
    }

    public void responseReceived(String messageID) {
        logger.info("Producer received response for msg: " + messageID);
    }

    public void handleError(String messageID, JCSMPException e, long timestamp) {
        logger.info("Producer received error for msg: %s@%s - %s%n", messageID, timestamp, e);
    }
}
