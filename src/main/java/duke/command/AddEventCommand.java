package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class AddEventCommand extends Command {

    private String taskName;
    private String timeSlot;

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
