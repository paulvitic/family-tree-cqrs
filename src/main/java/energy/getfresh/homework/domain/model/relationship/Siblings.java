package energy.getfresh.homework.domain.model.relationship;

import energy.getfresh.homework.domain.model.family.FamilyMember;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class Siblings extends Relative {

    Siblings(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        return familyMember
                .mother()
                .children()
                .stream()
                .filter(person -> !person.name().equals(familyMember.name()))
                .map(FamilyMember::name)
                .collect(Collectors.toSet());
    }
}
