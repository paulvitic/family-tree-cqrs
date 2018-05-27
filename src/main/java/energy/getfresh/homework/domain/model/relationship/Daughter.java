package energy.getfresh.homework.domain.model.relationship;

import energy.getfresh.homework.domain.model.family.FamilyMember;
import energy.getfresh.homework.domain.model.family.Person;

import java.util.Set;
import java.util.stream.Collectors;

public class Daughter extends Relative {


    protected Daughter(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        return familyMember.children().stream()
                .filter(Person::isFemale)
                .map(FamilyMember::name)
                .collect(Collectors.toSet());
    }
}
