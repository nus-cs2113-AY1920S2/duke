package duke.command;

import duke.exception.TaskException;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Output;

public class TodoCommand extends Command {

    private String userInput;

    public TodoCommand (TaskManager manager, Output printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }

    /**
     * Executes the to do command and adds the corresponding task
     * to the list
     */
    @Override
    public void execute() {

        try {
            String taskDescription = userInput.trim();

            if (taskDescription.length() == 0) {
                throw new TaskException("Failed to add todo. Reason: missing description");
            }

            taskManager.addTask(new Todo(taskDescription));

        } catch (TaskException e) {
            printer.displayMessage(e.toString());
        }

    }
}
