package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class SisterInLaw extends Relative {


    protected SisterInLaw(FamilyMember familyMember) {
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
                    .filter(person -> person.isFemale() && !person.name().equals(spouse.name()))
                    .map(FamilyMember::name)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}

