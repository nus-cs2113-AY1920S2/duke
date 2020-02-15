package duke.command;

import duke.task.Deadline;
import duke.task.TaskManager;
import duke.ui.Output;

public class DeadlineCommand extends Command {

    private String userInput;

    public DeadlineCommand (TaskManager manager, Output printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }


    /**
     * Executes the add deadline command by checking for the correct
     * format
     */
    @Override
    public void execute () {
        int indexOfBy = userInput.indexOf("/by");

        if (indexOfBy == -1) {
            printer.printInvalidDeadline();
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String byDate = userInput.substring(indexOfBy + 3).trim();

            taskManager.addTask(new Deadline(taskDescription, byDate));
        }

    }

}
