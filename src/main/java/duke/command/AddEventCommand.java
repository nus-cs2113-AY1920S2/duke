package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * a command class that executes the operation to add a event task to task list
 */
public class AddEventCommand extends Command {

    private String taskName;
    private String timeSlot;

    /**
     * constructor of this class (need to split user command to find more information about the event task)
     * @param taskName task name
     * @param timeSlot time slot of the task
     */
    public AddEventCommand(String taskName, String timeSlot){
        this.taskName = taskName;
        this.timeSlot = timeSlot;
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
