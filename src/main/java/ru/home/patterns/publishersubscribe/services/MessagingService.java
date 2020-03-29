package ru.home.patterns.publishersubscribe.services;

import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.publisher.Publisher;
import ru.home.patterns.publishersubscribe.model.subscriber.Subscriber;
import ru.home.patterns.publishersubscribe.utils.ClientType;
import ru.home.patterns.publishersubscribe.utils.Credentials;

public interface MessagingService {

    String getIdentity();

    /**
     * Publish new message
     * @param message
     */
    void publish(Message message, Publisher publisher);

    /**
     * Subscribe to topic
     * @param topic Topic
     * @param subscriber Subscriber
     */
    void subscribe(String topic, Subscriber subscriber);


    /**
     * Unsubscribe from topic
     * @param topic Topic
     * @param subscriber Subscriber
     */
    void unSubscribe(String topic, Subscriber subscriber);


    /**
     * Authorize clients
     * @param type ClientType
     * @param credentials Credentials
     * @return
     */
    long authorize(Credentials credentials);
}
