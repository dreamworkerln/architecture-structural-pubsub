package ru.home.patterns.publishersubscribe.model.base;

import org.springframework.beans.factory.annotation.Autowired;
import ru.home.patterns.publishersubscribe.model.subscriber.AbstractSubscriber;
import ru.home.patterns.publishersubscribe.services.MessagingService;
import ru.home.patterns.publishersubscribe.utils.Credentials;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class AbstractClient implements Client {

    protected long id = -1;

    protected String name;

    protected MessagingService messagingService;

    protected Credentials credentials;

    @Autowired
    protected void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @PostConstruct
    private void postConstruct() {
        credentials = new Credentials("anonymous","anonymous");
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ------------------------------------------------------------


    @Override
    public void authorize() {

        long id = messagingService.authorize(credentials);

        if (id > 0) {
            this.id = id;
        }
    }

    // =============================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSubscriber)) return false;
        AbstractSubscriber that = (AbstractSubscriber) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
