package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;
import static duke.common.Messages.ADD_MESSAGE;

/**
 * Adds an event to the list of tasks.
 */
public class AddEventCommand extends Command {

    /** Description of the task */
    private static String description;

    /** Time at which the task takes place */
    private static String duration;

    /** Format of the command */
    public static String COMMAND_PHRASE = "event (item) /at (date or time)";

    /** Usage of the command */
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator() +
            "-Adds an event to the list";

    public AddEventCommand(String description, String duration) {
        super();
        this.description = description;
        this.duration = duration;
    }

    /**
     * Returns a <code>CommandResult</code> with feedback to the user initialised.
     *
     * @param tasks <code>TaskList</code> object that the command will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        Event newEvent = new Event(description,duration);
        String feedback = ADD_MESSAGE + System.lineSeparator()
                + newEvent.toString() + System.lineSeparator() + "Now you have " + (tasks.getSize()+1) + " tasks in the list";
        tasks.add(newEvent);
        storage.writeEvent(textUi,description,duration);
        return new CommandResult(feedback);
    }

}