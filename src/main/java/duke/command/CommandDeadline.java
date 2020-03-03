package duke.command;

import duke.taskList.TaskList;
import duke.task.Deadlines;

public class CommandDeadline extends Command {

    public static final String SUCCESS = "Sheena: YAY! This task is added: \n"
            +"\n%s.\n Sheena: Now you have %d tasks in your list ~ ";

    public static final String DUPLICATE = "Sheena: Erm. This task is already in the list...";

    private Deadlines Add;

    public CommandDeadline(Deadlines Add){
        this.Add = Add;
    }
    public CommandDeadline(String desc,String dueDate) throws StringIndexOutOfBoundsException {
        if (desc.isEmpty() || dueDate.isEmpty()) {
            throw new StringIndexOutOfBoundsException();
        }
        this.Add = new Deadlines(desc,dueDate);
    }

    @Override
    public CommandOption execute() {
        try {
            TaskList.add(Add);
            return new CommandOption(String.format(SUCCESS, Add.toString(),TaskList.size()));
        } catch (TaskList.DuplicateTaskException dpe) {
            return new CommandOption(DUPLICATE);
        }
    }
}
