package duke.command;

import duke.exception.TaskException;
import duke.task.TaskManager;
import duke.ui.Output;

public class DeleteCommand extends Command {

    private String userInput;

    public DeleteCommand (TaskManager manager, Output printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }


    @Override
    public void execute () {
        int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)) - 1;

        try {
            taskManager.removeTask(taskIndex);
        } catch (TaskException e) {
            printer.displayMessage(e.toString());
        }
    }

}
