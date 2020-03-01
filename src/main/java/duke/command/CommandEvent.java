package duke.command;

import duke.taskList.TaskList;
import duke.task.Events;

public class CommandEvent extends Command {

    public static final String SUCCESS = "Sheena: YAY! added this task: \n"
            +"\t%s.\n Sheena: Now you have %d tasks in your list ~";

    public static final String DUPLICATE = "Sheena: Erm. This task is already in the list...";

    private Events Add;

    public CommandEvent(Events Add) {
        this.Add = Add;
    }

    public CommandEvent(String desc, String date) throws StringIndexOutOfBoundsException {
        if(!(!desc.isEmpty()) || !(!date.isEmpty())){
            throw new StringIndexOutOfBoundsException();
        }
        this.Add = new Events(desc, date);
    }

    @Override
    public CommandOption execute() {
        try {
            TaskList.add(Add);
            return new CommandOption(String.format(SUCCESS, Add, TaskList.size()));
        } catch (TaskList.DuplicateTaskException dpe) {
            return new CommandOption(DUPLICATE);
        }

    }
}
