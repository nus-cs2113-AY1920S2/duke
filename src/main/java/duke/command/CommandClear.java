package duke.command;

import duke.taskList.TaskList;

public class CommandClear extends Command {

    public static final String SUCCESS = "Sheena: So sad but yeah the list has been cleared..";

    public CommandClear() {
    }

    @Override
    public CommandOption execute() {
        TaskList.clear();
        return new CommandOption(SUCCESS);
    }
}
