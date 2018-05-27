package energy.getfresh.homework.domain.model.relationship;

import energy.getfresh.homework.domain.model.family.FamilyMember;
import energy.getfresh.homework.domain.model.family.Person;

import java.util.Set;
import java.util.stream.Collectors;

public class Children extends Relative {


    Children(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        return familyMember.children()
                .stream()
                .map(FamilyMember::name)
                .collect(Collectors.toSet());
    }
}
