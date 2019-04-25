package energy.getfresh.homework.domain.model.relationship;

import energy.getfresh.homework.domain.model.family.FamilyMember;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class PaternalUncle extends Relative {

    PaternalUncle(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        final FamilyMember father = familyMember.father();
        if (father!=null){
            return father.mother()
                    .children()
                    .stream()
                    .filter(person -> person.isMale() && !person.name().equals(father.name()))
                    .map(FamilyMember::name)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
