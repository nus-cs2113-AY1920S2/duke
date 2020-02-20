package duke.command;

import duke.TaskList;
import duke.Storage;


public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bob thanks you for coming! See you again soon!");
    }

}
