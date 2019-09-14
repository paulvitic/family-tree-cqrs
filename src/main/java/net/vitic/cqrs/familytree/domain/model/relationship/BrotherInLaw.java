package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class BrotherInLaw extends Relative {

    BrotherInLaw(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        FamilyMember spouse = familyMember.spouse();
        if (spouse!=null){
            return spouse
                    .mother()
                    .children()
                    .stream()
                    .filter(person -> person.isMale() && !person.name().equals(spouse.name()))
                    .map(FamilyMember::name)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
