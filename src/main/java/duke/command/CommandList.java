package duke.command;
import duke.taskList.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class CommandList extends Command{

    public static final String EMPTY_LIST = "Sheena: Erm. There is nothing in the list...";

    @Override
    public CommandOption execute() {
        try {

            ArrayList<Task> duplicateTaskList = TaskList.copy();
            System.out.println("Sheena: Yay! Now you have these ~");
            TaskList.printList(duplicateTaskList);
            return new CommandOption(String.format(getMessageForTaskListShownSummary(duplicateTaskList),duplicateTaskList));
        } catch (NullPointerException e) {
            return new CommandOption(EMPTY_LIST);
        }

    }
}
