package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;

/* a command class that executes the operation to add a deadline task to task list */
public class AddDeadlineCommand extends Command {

    private String taskName;
    private String deadline;

    /* constructor of this class (need to split user command to find more information about the deadline task) */
    public AddDeadlineCommand(String command) {
        String[] attributes = command.split("/",2);
        this.taskName = attributes[0].trim();
        this.deadline = attributes[1].split(" ",2)[1];
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
