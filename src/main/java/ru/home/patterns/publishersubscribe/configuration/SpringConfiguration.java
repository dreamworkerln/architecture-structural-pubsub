package ru.home.patterns.publishersubscribe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.home.patterns.publishersubscribe.model.publisher.Publisher;
import ru.home.patterns.publishersubscribe.model.publisher.SimplePublisher;
import ru.home.patterns.publishersubscribe.model.subscriber.SimpleSubscriber;

import java.util.List;

@Configuration
public class SpringConfiguration {
    @Bean
    @Scope(value = "prototype")
    public SimpleSubscriber simpleSubscriber(String name, List<String> topics) {

        SimpleSubscriber result = new SimpleSubscriber();
        result.setName(name);
        result.setTopics(topics);
        return result;
    }


    @Bean
    @Scope(value = "prototype")
    public Publisher publisher(String name) {

        SimplePublisher result = new SimplePublisher();
        result.setName(name);
        return result;
    }
}
