package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        Ui.printByeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
