package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.ui.UI;

public class EventCommand extends Command {

    protected String at;
    protected String description;

    public EventCommand(String command, String description, String at) {
        super(command);
        this.description = description;
        this.at =at;
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        Event event = new Event(this.description, this.at);
        tasklist.addTask(event);
        storage.writeFileLine(event);
        ui.displayAddEventMessage(event, tasklist.getTaskList());
    }
}
