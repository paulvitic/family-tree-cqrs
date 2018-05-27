package energy.getfresh.homework.domain.model.event;

public interface EventSubscriber {

    void handle(DomainEvent event);
}
