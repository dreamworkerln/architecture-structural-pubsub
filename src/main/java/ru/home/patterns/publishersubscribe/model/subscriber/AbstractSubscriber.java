package ru.home.patterns.publishersubscribe.model.subscriber;

import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.base.AbstractClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSubscriber extends AbstractClient implements Subscriber {

    protected List<String> topics = new ArrayList<>();

    @Override
    public void notify(Message message) {

        System.out.println(name + ": " + message);
    }


    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }



    public void subscribeAll() {
        for (String t : topics) {
            messagingService.subscribe(t, this);
        }

    }

    public void subscribe(String topic) {
        messagingService.subscribe(topic, this);
    }

    public void unSubscribe(String topic) {
        messagingService.unSubscribe(topic, this);
    }

}
