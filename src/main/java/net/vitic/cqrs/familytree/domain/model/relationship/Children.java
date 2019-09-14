package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

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
