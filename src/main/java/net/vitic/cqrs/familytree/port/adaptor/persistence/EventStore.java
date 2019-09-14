package net.vitic.cqrs.familytree.port.adaptor.persistence;

import net.vitic.cqrs.familytree.domain.model.event.DomainEvent;
import net.vitic.cqrs.familytree.domain.model.event.EventLog;
import net.vitic.cqrs.familytree.domain.model.event.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Slf4j
public class EventStore implements EventSubscriber {

    private static EventStore instance;

    public static EventStore get(){
        if (instance==null) {
            instance = new EventStore();
            DomainEvent.subscribe(instance);
        }
        return instance;
    }

    @Override
    public void handle(DomainEvent event) {
        try (Session session = Database.session()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(new EventLog(
                    Database.eventSequence(),
                    event.getClass().getSimpleName(),
                    event.toString()));
            transaction.commit();
        } catch (Exception e) {
            log.error("Error while saving event", e);
        }
    }


    public List<EventLog> eventsSince(int lastSequence) {
        Session session = Database.session();
        List<EventLog> res = session.createQuery("select eventLog " +
                "from net.vitic.cqrs.familytree.domain.model.event.EventLog eventLog " +
                "where eventLog.sequence >= :lastSequence", EventLog.class)
                .setParameter("lastSequence", lastSequence)
                .getResultList();
        session.close();
        return res;
    }
}
