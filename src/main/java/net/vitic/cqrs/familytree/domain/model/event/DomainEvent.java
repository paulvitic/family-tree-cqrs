package net.vitic.cqrs.familytree.domain.model.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DomainEvent {

    private final static List<EventSubscriber> subscribers = new ArrayList<>();

    public static void subscribe(EventSubscriber subscriber){
        subscribers.add(subscriber);
    }

    public static void publish(DomainEvent event){
        subscribers.forEach(subscriber -> subscriber.handle(event));
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error while serializing event data.", e);
        }
        return null;
    }
}
