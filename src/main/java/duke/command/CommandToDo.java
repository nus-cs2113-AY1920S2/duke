package duke.command;
import duke.taskList.TaskList;
import duke.task.ToDos;

public class CommandToDo extends Command {

    private ToDos Add;

    public CommandToDo(ToDos Add) {
        this.Add = Add;
    }

    public CommandToDo(String desc) throws NullPointerException {
        if (!(!desc.isEmpty())) {
            throw new NullPointerException();
        }
        this.Add = new ToDos(desc);
    }

    public static final String SUCCESS = "Sheena: Yay! I've added this task: \n"
            + "\n %s.\nNow you have %d tasks in your list ~ ";
    public static final String DUPLICATE_TASK = "Sheena: This task already exists in the list ~";
    public static final String ERROR = "Sheena: Sorry, but the %s of a todo command cannot be empty ~";

    @Override
    public CommandOption execute() {
        try {
            taskList.add(Add);
            return new CommandOption(String.format(SUCCESS, Add.toString(), taskList.size()));
        } catch (NullPointerException NPE) {
            return new CommandOption(String.format(ERROR, "description"));
        } catch (TaskList.DuplicateTaskException dpe) {
            return new CommandOption(DUPLICATE_TASK);
        }
    }
}
