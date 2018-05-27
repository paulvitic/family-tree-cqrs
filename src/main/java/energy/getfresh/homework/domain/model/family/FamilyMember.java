package energy.getfresh.homework.domain.model.family;

import energy.getfresh.homework.port.adaptor.persistence.Database;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FAMILY_MEMBER")
public class FamilyMember extends Person {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private FamilyMember father;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private FamilyMember mother;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private FamilyMember spouse;

    @OneToMany(mappedBy = "mother", cascade = CascadeType.MERGE)
    private Set<FamilyMember> childrenIfMother;

    @OneToMany(mappedBy = "father", cascade = CascadeType.MERGE)
    private Set<FamilyMember> childrenIfFather;

    public FamilyMember() {
    }

    public FamilyMember(Integer id, String name, Gender gender,
                        FamilyMember father, FamilyMember mother) {
        super(id, name, gender);
        this.father = father;
        this.mother = mother;
    }

    public FamilyMember mother() {
        return this.mother;
    }

    public FamilyMember father() {
        return this.father;
    }

    public FamilyMember spouse() {
        return this.spouse;
    }

    public Set<FamilyMember> children() {
        return this.isFemale() ? childrenIfMother : childrenIfFather;
    }

    public void addChildToMother(String name, String gender){
        if (this.isFemale()) {

            Integer id = Database.createId();
            Gender childGender = Gender.valueOf(gender.toUpperCase());
            FamilyMember father = spouse();
            FamilyMember child = new FamilyMember(id, name, childGender, father, this);
            this.childrenIfMother.add(child);



            this.events.add(new ChildAdded(spouse.name(), name(), name, gender));
        }
    }
}
