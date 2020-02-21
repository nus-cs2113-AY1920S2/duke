package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.ADD_MESSAGE;

/**
 * Adds a deadline to the list of tasks.
 */
public class AddDeadlineCommand extends Command {

    /** Description of the task */
    private static String description;

    /** Time by which the task has to be finished */
    private static String by;

    /** Format of the command */
    public static String COMMAND_PHRASE = "deadline (item) /by (date or time)";

    /** Usage of the command */
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator() +
            "-Adds a deadline to the list";

    public AddDeadlineCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
    }

    /**
     * Returns a <code>CommandResult</code> with feedback to the user initialised
     *
     * @param tasks <code>TaskList</code> object that the command will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        Deadline newDeadline = new Deadline(description,by);
        String feedback = ADD_MESSAGE + System.lineSeparator()
                + newDeadline.toString() + System.lineSeparator() + "Now you have " + (tasks.getSize()+1) + " tasks in the list";
        tasks.add(newDeadline);
        storage.writeDeadline(textUi,description,by);
        return new CommandResult(feedback);
    }

}