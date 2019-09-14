package net.vitic.cqrs.familytree.domain.model.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.vitic.cqrs.familytree.domain.model.event.DomainEvent;

public class ChildAdded extends DomainEvent {

    @JsonProperty("fathersName")
    private final String fathersName;

    @JsonProperty("mothersName")
    private final String mothersName;

    @JsonProperty("childsName")
    private final String childsName;

    @JsonProperty("childsGender")
    private final String childsGender;


    public ChildAdded(String fathersName, String mothersName,
                      String childsName, String childsGender) {
        super();
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.childsName = childsName;
        this.childsGender = childsGender;
    }
}
