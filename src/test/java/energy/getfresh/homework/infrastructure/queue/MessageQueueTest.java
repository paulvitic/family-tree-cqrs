package energy.getfresh.homework.infrastructure.queue;

import energy.getfresh.homework.domain.model.event.EventLog;
import energy.getfresh.homework.port.adaptor.persistence.EventStore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class MessageQueueTest {

    @Test
    public void run() {
        MessageConsumer consumer = Mockito.mock(MessageConsumer.class);
        EventStore eventStrore = Mockito.mock(EventStore.class);

        Mockito.when(eventStrore.eventsSince(any(Integer.class)))
                .thenReturn(Stream.of(
                        new EventLog(1, "SampleEvent", "{}"),
                        new EventLog(2, "SampleEvent", "{}"))
                        .collect(Collectors.toList()));

        MessageQueue queue = MessageQueue.get(consumer, eventStrore);
        queue.start();
        assertNotNull(queue);
        queue.shutdown();
    }
}