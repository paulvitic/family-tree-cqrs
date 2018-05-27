package energy.getfresh.homework.application.familymember;

import energy.getfresh.homework.domain.model.relationship.Relation;
import energy.getfresh.homework.domain.model.relationship.Relative;
import energy.getfresh.homework.port.adaptor.persistence.Database;
import energy.getfresh.homework.infrastructure.repository.FamilyRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.Set;

@Slf4j
public class FamilyMemberService {

    private static FamilyMemberService familyApplicationSevice;

    private FamilyMemberService(){}

    public static FamilyMemberService get(){
        if (familyApplicationSevice == null)
            familyApplicationSevice = new FamilyMemberService();
        return familyApplicationSevice;
    }


    public void addChild(AddChildCommand command) {
        Session session = Database.session();
        final FamilyRepository repository = new FamilyRepository(session);
        repository.findByName(command.getMothersName())
                .ifPresent(member -> {
                    member.addChildToMother(command.getChildsName(), command.getChildsGender());
                    try {
                        repository.save(member);
                        member.publishEvents();
                    } catch (PersistenceException e) {
                        log.error("Error while saving memer.", e);
                    }
                });
        session.close();
    }


    public Set<String> namesOfRelatives(GetRelativesCommand command) {
        Session session = Database.session();
        Set<String> relatives = new FamilyRepository(session)
                .findByName(command.getName())
                .flatMap(m -> Relation.to(command.getRelationship(), m))
                .map(Relative::of)
                .orElse(Collections.emptySet());
        session.close();
        return relatives;
    }
}
