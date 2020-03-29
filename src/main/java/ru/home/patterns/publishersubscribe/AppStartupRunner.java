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






        /*

        //Instantiate publishers, subscribers and MessagingService
        Publisher javaPublisher = new PublisherConcrete();
        Publisher pythonPublisher = new PublisherConcrete();

        SubscriberConcrete javaSubscriber = new SubscriberConcrete();
        SubscriberConcrete allLanguagesSubscriber = new SubscriberConcrete();
        SubscriberConcrete pythonSubscriber = new SubscriberConcrete();

        MessagingService pubSubService = new MessagingService();

        //Declare Messages and Publish Messages to MessagingService
        Message javaMsg1 = new Message("Java", "Core Java Concepts");
        Message javaMsg2 = new Message("Java", "Spring MVC : Dependency Injection and AOP");
        Message javaMsg3 = new Message("Java", "JPA & Hibernate");

        javaPublisher.publish(javaMsg1, pubSubService);
        javaPublisher.publish(javaMsg2, pubSubService);
        javaPublisher.publish(javaMsg3, pubSubService);

        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        Message pythonMsg2 = new Message("Python", "Advanced Python message");

        pythonPublisher.publish(pythonMsg1, pubSubService);
        pythonPublisher.publish(pythonMsg2, pubSubService);

        //Declare Subscribers
        javaSubscriber.addSubscriber("Java",pubSubService);		//Java subscriber only subscribes to Java topics
        pythonSubscriber.addSubscriber("Python",pubSubService);   //Python subscriber only subscribes to Python topics
        allLanguagesSubscriber.addSubscriber("Java", pubSubService);	//all subscriber, subscribes to both Java and Python
        allLanguagesSubscriber.addSubscriber("Python", pubSubService);

        //Trying unSubscribing a subscriber
        //pythonSubscriber.unSubscribe("Python", pubSubService);

        //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in MessagingService
        pubSubService.broadcast();

        //Print messages of each subscriber to see which messages they got
        System.out.println("Messages of Java Subscriber are: ");
        javaSubscriber.printMessages();

        System.out.println("\nMessages of Python Subscriber are: ");
        pythonSubscriber.printMessages();

        System.out.println("\nMessages of All Languages Subscriber are: ");
        allLanguagesSubscriber.printMessages();

        //After broadcast the messagesQueue will be empty, so publishing new messages to server
        System.out.println("\nPublishing 2 more Java Messages...");
        Message javaMsg4 = new Message("Java", "JSP and Servlets");
        Message javaMsg5 = new Message("Java", "Struts framework");

        javaPublisher.publish(javaMsg4, pubSubService);
        javaPublisher.publish(javaMsg5, pubSubService);

        javaSubscriber.getMessagesForSubscriberOfTopic("Java", pubSubService);
        System.out.println("\nMessages of Java Subscriber now are: ");
        javaSubscriber.printMessages();


        */

    }
}
