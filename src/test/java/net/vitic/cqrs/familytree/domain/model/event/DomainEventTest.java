package net.vitic.cqrs.familytree.domain.model.event;

import net.vitic.cqrs.familytree.domain.model.family.ChildAdded;
import org.junit.Test;

import static org.junit.Assert.*;

public class DomainEventTest {

    @Test
    public void shouldSerialize() {
        ChildAdded event = new ChildAdded("Frank", "Ginerva", "Paul", "MALE");
        String res = event.toString();
        assertNotNull(res);
    }
}