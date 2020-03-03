package Duke.Command;
import Duke.UI.*;
import Duke.Tasks.*;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;

public class ListCommand extends Command {

    public String displayTask (int i, Task t) {
        int number = i + 1;
        return Integer.toString(number) + "." + t;
    }

    public String displayList() {
        String printedList;
        if (TaskList.getSize() == 0) {
             printedList = Messages.EMPTY_LIST_MESSAGE;
        } else {
            printedList = Messages.LIST_HEADER_MESSAGE;
            for (int i = 0; i < TaskList.getSize(); i++) {
                printedList += (displayTask(i, TaskList.fetchTask(i)) + "\n");
            }
        }
        return printedList;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.out.println(displayList());
    }

    @Override
    public boolean isExit(){
        return false;
    };

}
