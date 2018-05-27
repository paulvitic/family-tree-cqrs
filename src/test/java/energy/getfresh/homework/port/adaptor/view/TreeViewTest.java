package energy.getfresh.homework.port.adaptor.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import energy.getfresh.homework.domain.model.family.ChildAdded;
import energy.getfresh.homework.domain.model.family.PersonAdded;
import energy.getfresh.homework.domain.model.family.SpouseAdded;
import energy.getfresh.homework.infrastructure.queue.Message;
import org.junit.Test;

public class TreeViewTest {

    @Test
    public void onMessage() throws JsonProcessingException {
        TreeView treeView = TreeView.get("./build/tmp/view/");

        PersonAdded personAdded = new PersonAdded("King Arthur", "male");
        treeView.onMessage(new Message(personAdded.getClass().getSimpleName(), personAdded.toString()));

        SpouseAdded spouseAdded = new SpouseAdded("King Arthur", "Qeen Margret", "female");
        treeView.onMessage(new Message(spouseAdded.getClass().getSimpleName(), spouseAdded.toString()));

        ChildAdded childAdded = new ChildAdded("King Arthur", "Qeen Margret", "Bill", "male");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        childAdded = new ChildAdded("King Arthur", "Qeen Margret", "Charlie", "male");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        childAdded = new ChildAdded("King Arthur", "Qeen Margret", "Percy", "male");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        spouseAdded = new SpouseAdded("Percy", "Audrey", "female");
        treeView.onMessage(new Message(spouseAdded.getClass().getSimpleName(), spouseAdded.toString()));

        childAdded = new ChildAdded("Percy", "Audrey", "Molly", "female");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        childAdded = new ChildAdded("Percy", "Audrey", "Lucy", "female");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        spouseAdded = new SpouseAdded("Bill", "Flora", "female");
        treeView.onMessage(new Message(spouseAdded.getClass().getSimpleName(), spouseAdded.toString()));

        childAdded = new ChildAdded("Bill","Flora", "Victoire", "female");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));

        spouseAdded = new SpouseAdded("Victoire", "Ted", "male");
        treeView.onMessage(new Message(spouseAdded.getClass().getSimpleName(), spouseAdded.toString()));

        childAdded = new ChildAdded("Ted","Victoire", "Remus", "male");
        treeView.onMessage(new Message(childAdded.getClass().getSimpleName(), childAdded.toString()));
    }
}
