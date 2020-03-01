package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DeadlineCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class DeadlineCommand extends Command {

    /**
     * The deadline/date entered by the user.
     */

    protected String by;

    /**
     * The description of the deadline entered by the user.
     */

    protected String description;

    /**
     * Constructs the DeadlineCommand object.
     * @param command the command prompt entered by the user.
     * @param description the description of the deadline entered by the user.
     * @param by the deadline/date entered by the user.
     */

    public DeadlineCommand(String command, String description, String by) {
        super(command);
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand and creates a new deadline task in the tasklist.
     * @param tasklist the list containing all current tasks.
     * @param ui the object containing user interface functions.
     * @param storage the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        Deadline deadline;
        try {
            LocalDate date = LocalDate.parse(this.by);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            String formattedDate = date.format(formatter);
            deadline = new Deadline(this.description, formattedDate);
        } catch (Exception e) {
            deadline = new Deadline(this.description, this.by);
        }
        tasklist.addTask(deadline);
        storage.writeFileLine(deadline);
        ui.displayAddDeadlineMessage(deadline, tasklist.getTaskList());
    }
}
