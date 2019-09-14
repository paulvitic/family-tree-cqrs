package net.vitic.cqrs.familytree.infrastructure.queue;

import lombok.Value;

@Value
public class Message {
    private String type;
    private String msg;
}
