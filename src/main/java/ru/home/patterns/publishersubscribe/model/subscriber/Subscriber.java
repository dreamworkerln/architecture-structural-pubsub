package ru.home.patterns.publishersubscribe.model.subscriber;


import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.base.Client;

import java.util.List;

public interface Subscriber extends Client {

    /**
     * Push-notification about new message
     */
    void notify (Message message);

    List<String> getTopics();
}
