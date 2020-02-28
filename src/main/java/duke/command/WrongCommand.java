package duke.command;

import duke.TaskList;

public class WrongCommand extends Command {

    public WrongCommand(String type) {
        super(type);
    }

    @Override
    public void execute(TaskList tasks) {
    }
}
