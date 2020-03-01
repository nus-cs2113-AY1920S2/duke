package duke.command;
import duke.taskList.TaskList;
import duke.task.Task;

public class CommandDone extends Command {

    int numberToMark;

    public static final String MARK_TASK_DONE = "Sheena: YAY! I have marked it as done: %1$s";
    public static final String INVALID_TASK = "Sheena: Erm. Maybe you provided a wrong number ?";
    public static final String TASK_NOT_EXIST = "Sheena: Erm, I can't delete because you have not add task %s yet..";

    public CommandDone(int numberToMark) {
        this.numberToMark = numberToMark;
    }


    @Override
    public CommandOption execute() {
        try {
            Task t = TaskList.markAsDone(numberToMark-1);
            return new CommandOption(String.format(MARK_TASK_DONE,t.toString()));
        } catch (TaskList.TaskNotFoundException tnf) {
            return new CommandOption(TASK_NOT_EXIST);
        } catch (IndexOutOfBoundsException ie) {
            //ie: if the user type 0 / number that is not within the list
            return new CommandOption(INVALID_TASK);
        }
    }
}
