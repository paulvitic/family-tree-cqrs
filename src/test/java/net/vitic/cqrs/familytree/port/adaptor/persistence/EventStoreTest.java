package net.vitic.cqrs.familytree.port.adaptor.persistence;

import net.vitic.cqrs.familytree.PersistenceTest;
import net.vitic.cqrs.familytree.domain.model.event.DomainEvent;
import net.vitic.cqrs.familytree.domain.model.event.EventLog;
import net.vitic.cqrs.familytree.domain.model.family.ChildAdded;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EventStoreTest extends PersistenceTest {

    @Test
    public void handle() {
        EventStore eventStore = EventStore.get();
        DomainEvent event = new ChildAdded("Henry", "Lisa",
                "Paul", "Male");
        eventStore.handle(event);
    }

    @Test
    public void eventsSince() {
        EventStore eventStore = EventStore.get();

        List<EventLog> res = eventStore.eventsSince(0);
        assertEquals(31, res.size());

        res = eventStore.eventsSince(31);
        assertEquals(0, res.size());
    }
}