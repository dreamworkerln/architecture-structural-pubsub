package ru.home.patterns.publishersubscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.home.patterns.publishersubscribe.model.Message;
import ru.home.patterns.publishersubscribe.model.publisher.Publisher;
import ru.home.patterns.publishersubscribe.model.subscriber.SimpleSubscriber;
import ru.home.patterns.publishersubscribe.model.subscriber.Subscriber;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    

    @Override
    public void run(ApplicationArguments args) {




        List<String> topics = Stream.of("Java Core", "Hibernate", "MQTT").collect(Collectors.toList());
        SimpleSubscriber java = context.getBean(SimpleSubscriber.class, "Java", topics);
        java.authorize();
        java.subscribeAll();


        topics = Stream.of("kubelet", "etcd", "Helm").collect(Collectors.toList());
        SimpleSubscriber devops = context.getBean(SimpleSubscriber.class, "Devops", topics);
        devops.authorize();
        devops.subscribe(devops.getTopics().get(0));
        devops.subscribe(devops.getTopics().get(1));



        Publisher dzone = context.getBean(Publisher.class, "DZone");
        dzone.authorize();

        // -------------------------------------

        Message message = new Message("Java Core", "bla-bla-bla1");
        dzone.publish(message);

        message = new Message("MQTT", "bla-bla-bla2");
        dzone.publish(message);

        message = new Message("AMQP", "bla-bla-bla3");
        dzone.publish(message);

        // --------------------------------------------------------------------------

        message = new Message("kubelet", "bla-bla-bla4");
        dzone.publish(message);

        message = new Message("etcd", "bla-bla-bla5");
        dzone.publish(message);

        message = new Message("Helm", "bla-bla-bla6");
        dzone.publish(message);

        // --------------------------------------------------------------------------


        message = new Message("Java Core", "bla-bla-bla7");
        dzone.publish(message);

        java.unSubscribe("Java Core");

        message = new Message("Java Core", "bla-bla-bla8");
        dzone.publish(message);

    }
}
