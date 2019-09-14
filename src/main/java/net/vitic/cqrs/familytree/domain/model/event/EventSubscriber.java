package net.vitic.cqrs.familytree.domain.model.event;

public interface EventSubscriber {

    void handle(DomainEvent event);
}
