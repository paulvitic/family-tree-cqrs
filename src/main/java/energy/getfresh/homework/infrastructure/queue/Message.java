package energy.getfresh.homework.infrastructure.queue;

import lombok.Value;

@Value
public class Message {
    private String type;
    private String msg;
}
