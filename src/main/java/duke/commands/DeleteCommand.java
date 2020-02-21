package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.DELETE_MESSAGE;

public class DeleteCommand extends Command {

    private static int index;

    public static final String COMMAND_PHRASE = "delete (index)";

    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Deletes the task at the specified index";

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        index -= 1;
        String feedback = DELETE_MESSAGE + System.lineSeparator() + tasks.getIndex(index)
                + System.lineSeparator() + "Now you have " + (tasks.getSize()-1) + " in the list.";
        tasks.removeTask(index);
        storage.saveChange(textUi,tasks);
        return new CommandResult(feedback);
    }
}
