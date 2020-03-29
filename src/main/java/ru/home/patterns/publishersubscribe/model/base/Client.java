package ru.home.patterns.publishersubscribe.model.base;

public interface Client {

    // Should be unique to all clients
    long getId();

    String getName();

    /**
     * Get unique id from broker
     */
    void authorize();
}
