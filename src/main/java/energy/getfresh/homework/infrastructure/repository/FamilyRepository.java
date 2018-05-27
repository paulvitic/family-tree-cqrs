package energy.getfresh.homework.infrastructure.repository;

import energy.getfresh.homework.domain.model.family.FamilyMember;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.Optional;

@Slf4j
public class FamilyRepository {

    private final Session session;

    public FamilyRepository(Session session) {
        this.session = session;
    }

    public Optional<FamilyMember> findByName(String name) {
        try {
            FamilyMember member = session.createQuery("select e " +
                    "from energy.getfresh.homework.domain.model.family.FamilyMember e " +
                    "where e.name = :name", FamilyMember.class)
                    .setParameter( "name", name)
                    .getSingleResult();
            return Optional.of(member);
        } catch (PersistenceException e) {
            log.warn("Something went wrong while trying to start member {}", name);
            return Optional.empty();
        }
    }


    public void save(FamilyMember familyMember) throws PersistenceException {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.merge(familyMember);
        transaction.commit();
    }
}
