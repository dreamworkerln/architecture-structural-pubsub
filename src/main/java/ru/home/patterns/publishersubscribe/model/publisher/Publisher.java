package ru.home.patterns.publishersubscribe.model.publisher;

import ru.home.patterns.publishersubscribe.model.base.Client;
import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.services.MessagingService;

public interface Publisher extends Client {

    //Publishes new message to MessagingService
    void publish(Message message);
}
