package duke.command;

import duke.task.Event;
import duke.task.TaskManager;
import duke.ui.Output;

public class EventCommand extends Command {

    private String userInput;

    public EventCommand (TaskManager manager, Output printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }


    /**
     * Executes the add event command by checking for the correct format
     */
    @Override
    public void execute () {
        int indexOfBy = userInput.indexOf("/at");

        if (indexOfBy == -1) {
            printer.printInvalidEvent();
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String eventDate = userInput.substring(indexOfBy + 3).trim();

            taskManager.addTask(new Event(taskDescription, eventDate));
        }

    }

}
