package duke.command;
import duke.taskList.TaskList;
import duke.task.Task;

public class CommandDelete extends Command {

    int deleteIndex;

    public CommandDelete(int targetIndex) {
        this.deleteIndex = targetIndex;
    }

    public static final String INVALID_TASK = "Sheena: I can't delete, you have not added task %s yet..";

    public static final String DELETE = "Sheena: YAY! You deleted task: %1$s !\n";

    @Override

    public CommandOption execute() {
        try {
            Task t = TaskList.retrieve(deleteIndex-1);
            TaskList.remove(deleteIndex-1);
            return new CommandOption(String.format(DELETE,t.toString())
                    + "\nNow you have " + TaskList.size() + " tasks in your list ~");

        } catch (IndexOutOfBoundsException | TaskList.TaskNotFoundException ie) {
            return new CommandOption(String.format(INVALID_TASK,deleteIndex));
        }
    }
}
