package net.vitic.cqrs.familytree.port.adaptor.console;

import net.vitic.cqrs.familytree.application.familymember.FamilyMemberService;
import org.junit.Test;
import org.mockito.Mockito;

public class DialogTest {

    @Test
    public void start() {
        Dialog dialog = new Dialog(Mockito.mock(FamilyMemberService.class));
        dialog.loadFile("./input/input.txt");
    }
}