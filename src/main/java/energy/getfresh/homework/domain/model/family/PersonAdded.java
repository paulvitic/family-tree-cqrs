package energy.getfresh.homework.domain.model.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import energy.getfresh.homework.domain.model.event.DomainEvent;

public class PersonAdded extends DomainEvent {

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    protected String gender;

    public PersonAdded(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
}
