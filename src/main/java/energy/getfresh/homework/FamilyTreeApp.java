package energy.getfresh.homework;

import energy.getfresh.homework.application.familymember.FamilyMemberService;
import energy.getfresh.homework.infrastructure.queue.MessageQueue;
import energy.getfresh.homework.port.adaptor.console.Dialog;
import energy.getfresh.homework.port.adaptor.persistence.Database;
import energy.getfresh.homework.port.adaptor.persistence.EventStore;
import energy.getfresh.homework.port.adaptor.view.TreeView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FamilyTreeApp {

    public static void main(String[] args) {
        Database.migrate();

        MessageQueue queue = MessageQueue.get(TreeView.get(), EventStore.get());
        queue.start();

        Dialog dialog = new Dialog(FamilyMemberService.get());
        dialog.start();

        queue.shutdown();
        Database.shutdown();
        log.info("Application exiting");
    }
}
