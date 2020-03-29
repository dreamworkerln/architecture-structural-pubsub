package ru.home.patterns.publishersubscribe.model.publisher;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.base.AbstractClient;
import ru.home.patterns.publishersubscribe.services.MessagingService;


public class AbstractPublisher extends AbstractClient implements Publisher {


    @Override
    public void publish(Message message) {
        messagingService.publish(message, this);
    }

}
