package net.vitic.cqrs.familytree.domain.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_log")
public class EventLog {

    @Id
    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    private String data;

    public EventLog() {
    }

    public EventLog(Integer sequence, String type, String data) {
        this.sequence = sequence;
        this.type = type;
        this.data = data;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
