package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;
import net.vitic.cqrs.familytree.domain.model.family.Person;

import java.util.Set;
import java.util.stream.Collectors;

public class Daughter extends Relative {


    Daughter(FamilyMember familyMember) {
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
