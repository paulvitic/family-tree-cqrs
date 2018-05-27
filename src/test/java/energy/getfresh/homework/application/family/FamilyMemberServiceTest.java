package energy.getfresh.homework.application.family;

import energy.getfresh.homework.PersistenceTest;
import energy.getfresh.homework.application.familymember.AddChildCommand;
import energy.getfresh.homework.application.familymember.FamilyMemberService;
import energy.getfresh.homework.application.familymember.GetRelativesCommand;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FamilyMemberServiceTest extends PersistenceTest {

    @Test
    public void addChild() {
        FamilyMemberService service = FamilyMemberService.get();
        service.addChild(new AddChildCommand("Flora", "Paul", "MALE"));
        Set<String> res = service.namesOfRelatives(new GetRelativesCommand("Flora", "Son"));
        assertEquals(2, res.size());
        assertTrue(res.contains("Paul"));
    }

    @Test
    public void shouldReturnBrotherInLaws() {
        Set<String> res = FamilyMemberService.get()
                .namesOfRelatives(new GetRelativesCommand("Flora", "Brother-In-Law"));
        assertEquals(3, res.size());
        assertTrue(res.contains("Charlie")
                && res.contains("Percy")
                && res.contains("Ronald"));
    }

    @Test
    public void shouldReturnSisterInLaws() {
        Set<String> res = FamilyMemberService.get()
                .namesOfRelatives(new GetRelativesCommand("Flora", "sister-in-law"));
        assertEquals(1, res.size());
        assertTrue(res.contains("Ginerva"));
    }

    @Test
    public void shouldReturnChildren() {
        Set<String> res = FamilyMemberService.get()
                .namesOfRelatives(new GetRelativesCommand("Percy", "children"));
        assertEquals(2, res.size());
        assertTrue(res.contains("Molly") && res.contains("Lucy"));
    }


    @Test
    public void shouldReturnDaughters() {
        Set<String> res = FamilyMemberService.get()
                .namesOfRelatives(new GetRelativesCommand("Percy", "daughter"));
        assertEquals(2, res.size());
        assertTrue(res.contains("Molly") && res.contains("Lucy"));
    }


    @Test
    public void shouldReturnSons() {
        Set<String> res = FamilyMemberService.get()
                .namesOfRelatives(new GetRelativesCommand("Rose", "Son"));
        assertEquals(1, res.size());
        assertTrue(res.contains("Draco"));
    }
}