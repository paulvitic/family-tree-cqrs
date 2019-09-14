package net.vitic.cqrs.familytree;

import net.vitic.cqrs.familytree.application.familymember.FamilyMemberService;
import net.vitic.cqrs.familytree.infrastructure.queue.MessageQueue;
import net.vitic.cqrs.familytree.port.adaptor.console.Dialog;
import net.vitic.cqrs.familytree.port.adaptor.persistence.Database;
import net.vitic.cqrs.familytree.port.adaptor.persistence.EventStore;
import net.vitic.cqrs.familytree.port.adaptor.view.TreeView;
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
