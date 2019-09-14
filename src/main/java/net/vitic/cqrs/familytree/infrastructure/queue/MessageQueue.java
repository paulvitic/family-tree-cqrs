package net.vitic.cqrs.familytree.infrastructure.queue;

import net.vitic.cqrs.familytree.port.adaptor.persistence.EventStore;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class MessageQueue implements Runnable {

    private final EventStore eventStore;
    private final BlockingQueue<Message> queue;
    private final MessageConsumer consumer;

    private boolean on = true;
    private int lastEvent = 0;

    private static MessageQueue instance;

    private MessageQueue(MessageConsumer consumer, EventStore eventStore, BlockingQueue<Message> queue) {
        this.queue = queue;
        this.eventStore = eventStore;
        this.consumer = consumer;
    }

    public static MessageQueue get(MessageConsumer consumer, EventStore eventStore){
        if (instance == null){
            instance = new MessageQueue(consumer, eventStore, new ArrayBlockingQueue<>(10));
        }
        return instance;
    }

    public void start(){
        new Thread(instance, "queue").start();
    }

    public void shutdown() {
        Message msg = new Message("Exit", null);
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            log.warn("Error while sending shutdown message.", e);
        }
        this.on = false;
    }


    @Override
    public void run() {
        initConsumer();
        do {
            poll();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.warn("Error while waiting to poll event store.", e);
            }
        } while (this.on);
        log.info("Queue shutdown.");
    }


    private void initConsumer(){
        log.info("Initializing message consumer");
        consumer.setQueue(queue);
        new Thread(consumer, "view").start();
    }


    private void poll() {
        eventStore.eventsSince(lastEvent)
                .forEach(event -> {
                    try {
                        log.info("Received event {}, {}", event.getSequence(), event.getType());
                        queue.put(new Message(event.getType(), event.getData()));
                        lastEvent++;
                    } catch (InterruptedException e) {
                        log.warn("Error while sending message to queue.", e);
                    }
                });
    }
}