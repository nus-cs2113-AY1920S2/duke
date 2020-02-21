package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

import static duke.common.Messages.ADD_MESSAGE;

public class AddEventCommand extends Command {

    private static String description;
    private static String duration;
    public static String COMMAND_PHRASE = "event (item) /at (date or time)";
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator() +
            "-Adds an event to the list";

    public AddEventCommand(String description, String duration) {
        super();
        this.description = description;
        this.duration = duration;
    }

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