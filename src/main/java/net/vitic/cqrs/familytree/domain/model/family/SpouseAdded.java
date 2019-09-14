package net.vitic.cqrs.familytree.domain.model.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.vitic.cqrs.familytree.domain.model.event.DomainEvent;

public class SpouseAdded extends DomainEvent {

    @JsonProperty("name")
    private String name;

    @JsonProperty("spouseName")
    private String spouseName;

    @JsonProperty("spouseGender")
    private String spouseGender;

    public SpouseAdded(String name, String spouseName, String spouseGender) {
        this.name = name;
        this.spouseName = spouseName;
        this.spouseGender = spouseGender;
    }
}
