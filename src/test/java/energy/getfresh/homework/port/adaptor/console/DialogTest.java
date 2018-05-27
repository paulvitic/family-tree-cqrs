package energy.getfresh.homework.port.adaptor.console;

import energy.getfresh.homework.application.familymember.FamilyMemberService;
import org.junit.Test;
import org.mockito.Mockito;

public class DialogTest {

    @Test
    public void start() {
        Dialog dialog = new Dialog(Mockito.mock(FamilyMemberService.class));
        dialog.loadFile("./input/input.txt");
    }
}