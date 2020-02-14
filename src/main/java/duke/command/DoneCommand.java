package duke.command;

import duke.exception.TaskException;
import duke.task.TaskManager;
import duke.ui.Output;

public class DoneCommand extends Command {

    private String userInput;

    public DoneCommand (TaskManager manager, Output printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }

    /**
     * Processes user input and marks the task as done
     */
    @Override
    public void execute () {
        int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)) - 1;

        try {
            taskManager.markTaskAsDone(taskIndex);
        } catch (TaskException e) {
            printer.displayMessage(e.toString());
        }
    }

}
