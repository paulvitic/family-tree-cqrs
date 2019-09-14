package net.vitic.cqrs.familytree.domain.model.relationship;

import net.vitic.cqrs.familytree.domain.model.family.FamilyMember;

import java.util.Optional;

public interface Relation {

    static Optional<Relative> to(String of, FamilyMember to){
        switch (of.toUpperCase()) {
            case "MATERNAL-AUNT":
                return Optional.of(new MaternalAunt(to));
            case "PARENTAL-UNCLE":
                return Optional.of(new PaternalUncle(to));
            case "BROTHER-IN-LAW":
                return Optional.of(new BrotherInLaw(to));
            case "SISTER-IN-LAW":
                return Optional.of(new SisterInLaw(to));
            case "CHILDREN":
                return Optional.of(new Children(to));
            case "SON":
                return Optional.of(new Son(to));
            case "DAUGHTER":
                return Optional.of(new Daughter(to));
            case "SIBLINGS":
                return Optional.of(new Siblings(to));
            default:
                return Optional.empty();
        }
    }
}
