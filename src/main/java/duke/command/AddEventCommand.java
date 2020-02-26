package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;

/**
 * a command class that executes the operation to add a event task to task list
 */
public class AddEventCommand extends Command {

    private String taskName;
    private String timeSlot;

    /**
     * constructor of this class (need to split user command to find more information about the event task)
     * @param command command entered by user that contains attributes of this event task
     */
    public AddEventCommand(String command) {
        String[] attributes = command.split("/", 2);
        this.taskName = attributes[0].trim();
        this.timeSlot = attributes[1].split(" ",2)[1];
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String description = tasks.addEvent(taskName,timeSlot);
        ui.printFormat(Ui.ADD_TASK+description);
    }

}
