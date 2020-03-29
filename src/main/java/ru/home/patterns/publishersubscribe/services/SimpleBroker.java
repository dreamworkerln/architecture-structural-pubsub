package ru.home.patterns.publishersubscribe.services;

import org.springframework.stereotype.Service;
import ru.home.patterns.publishersubscribe.model.base.Client;
import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.publisher.Publisher;
import ru.home.patterns.publishersubscribe.model.subscriber.Subscriber;
import ru.home.patterns.publishersubscribe.utils.Credentials;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class SimpleBroker implements MessagingService {

    AtomicLong clientId = new AtomicLong();

    //private Set<Client> clients = new HashSet<>();

    //  Topic
    private Map<String, Set<Subscriber>> subsTopicMap = new HashMap<>();

    //Holds messages published by publishers
    //private Queue<Message> messagesQueue = new LinkedList<>();



    /**
     * Publish message to broker
     * @param message Message
     */
    @Override
    public void publish(Message message, Publisher publisher) {

        checkClient(publisher);

        // fanout
        broadcast(message);
    }


    /**
     * Add subscriber to topic
     * @param topic Topic
     * @param subscriber Subscriber
     */
    @Override
    public void subscribe(String topic, Subscriber subscriber) {

        checkClient(subscriber);

        Set<Subscriber> subsOfTopic = subsTopicMap.computeIfAbsent(topic, s -> new HashSet<>());
        subsOfTopic.add(subscriber); // will override if exists
    }


    /**
     * Remove subscriber from topic
     * @param topic Topic
     * @param subscriber Subscriber
     */
    @Override
    public void unSubscribe(String topic, Subscriber subscriber) {

        checkClient(subscriber);

        Set<Subscriber> subsOfTopic = subsTopicMap.get(topic);
        if(subsOfTopic != null) {
            subsOfTopic.remove(subscriber);
        }

    }


    /**
     * Authorize client and return client id
     * @param type ClientType
     * @param credentials Credentials
     * @return client id > 0, < 0 if not authorized
     */
    @Override
    public long authorize(Credentials credentials) {

        // check credentials ...

        // return -1 (any < 0) mean not authorized
        return clientId.incrementAndGet();
    }




    /**
     * Get Broker info
     */
    @Override
    public String getIdentity() {
        return "Simple Broker v0.1 - localhost";
    }


    
    // --------------------------------------------------------------------

    // Fanout exchanger
    private void broadcast(Message message) {

        // if have any subscriber interesting of this message topic
        Set<Subscriber> topicSubs = subsTopicMap.get(message.getTopic());

        if(topicSubs != null) {
            for (Subscriber sub : topicSubs) {
                sub.notify(message);
            }
        }
    }







    private void checkClient(Client client) {


        if(client.getId() <= 0 ) {
            throw new IllegalArgumentException("Client not authorized");
        }
    }





}
