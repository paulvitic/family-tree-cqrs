package net.vitic.cqrs.familytree.domain.model.family;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(indexes = @Index(columnList = "name", name = "person_name"))
public class Person extends Identified {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    Person() {}

    Person(Integer id, String name, Gender gender) {
        super(id);
        this.name = name;
        this.gender = gender;
    }

    public String name(){
        return this.name;
    }

    public boolean isMale(){
        return this.gender.isMale();
    }

    public boolean isFemale(){
        return this.gender.isFemale();
    }
}
