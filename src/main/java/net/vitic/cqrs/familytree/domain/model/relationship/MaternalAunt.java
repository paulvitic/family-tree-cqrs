package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class MaternalAunt extends Relative {

    MaternalAunt(FamilyMember familyMember) {
        super(familyMember);
    }

    @Override
    public Set<String> of() {
        final FamilyMember mother = familyMember.mother();
        if (mother!=null){
            return mother.mother()
                    .children()
                    .stream()
                    .filter(person -> person.isFemale() && !person.name().equals(mother.name()))
                    .map(FamilyMember::name)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();

    }
}
