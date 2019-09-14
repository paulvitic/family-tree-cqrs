package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

import java.util.Set;

public abstract class Relative implements Relation {

    final FamilyMember familyMember;

    Relative(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public abstract Set<String> of();
}
