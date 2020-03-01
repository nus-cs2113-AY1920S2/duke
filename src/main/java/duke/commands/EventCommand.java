package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.ui.UI;

/**
 * EventCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class EventCommand extends Command {

    /**
     * The location of the event entered by the user.
     */

    protected String at;

    /**
     * The description of the event entered by the user.
     */

    protected String description;

    /**
     * Constructs the EventCommand object.
     * @param command the command prompt entered by the user.
     * @param description the description of the event entered by the user.
     * @param at the location of the event entered by the user.
     */

    public EventCommand(String command, String description, String at) {
        super(command);
        this.description = description;
        this.at =at;
    }

    /**
     * Executes the EventCommand and creates a new event task in the tasklist.
     * @param tasklist the list containing all current tasks.
     * @param ui the object containing user interface functions.
     * @param storage the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        Event event = new Event(this.description, this.at);
        tasklist.addTask(event);
        storage.writeFileLine(event);
        ui.displayAddEventMessage(event, tasklist.getTaskList());
    }
}
