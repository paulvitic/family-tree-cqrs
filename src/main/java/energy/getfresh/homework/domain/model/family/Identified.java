package energy.getfresh.homework.domain.model.family;


import energy.getfresh.homework.domain.model.event.DomainEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class Identified implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Version
    private Integer version;

    @Transient
    List<DomainEvent> events = new ArrayList<>();

    Identified() {
        super();
    }

    Identified(Integer id) {
        super();
        this.id = id;
    }

    int id() {
        return this.id;
    }

    public void publishEvents(){
        events.forEach(DomainEvent::publish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identified identified = (Identified) o;

        return id == identified.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
