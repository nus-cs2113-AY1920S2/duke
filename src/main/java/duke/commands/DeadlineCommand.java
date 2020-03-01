package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {

    protected String by;
    protected String description;

    public DeadlineCommand(String command, String description, String by) {
        super(command);
        this.description = description;
        this.by = by;
    }

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
