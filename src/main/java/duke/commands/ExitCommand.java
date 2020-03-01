package duke.commands;

import duke.TaskList;
import duke.Storage;

public class ExitCommand extends ExecuteCommand {

    public ExitCommand() {
        this.toExit = true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
