package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * a command class that executes the operation to add a deadline task to task list
 */
public class AddDeadlineCommand extends Command {

    private String taskName;
    private String deadline;

    /**
     * constructor of this class (need to split user command to find more information about the deadline task)
     * @param taskName task name
     * @param deadline deadline of the task
     */
    public AddDeadlineCommand(String taskName, String deadline){
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String description = tasks.addDeadline(taskName, deadline);
        ui.printFormat(Ui.ADD_TASK+description);
    }

}
