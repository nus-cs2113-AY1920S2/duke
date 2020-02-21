package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.common.Messages.COMPLETE_MESSAGE;

public class DoneCommand extends Command {

    private static int index;

    public static final String COMMAND_PHRASE = "done (index)";

    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Marks task at specified index as done";

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        index -= 1;
        tasks.markTask(index);
        String feedback = COMPLETE_MESSAGE + System.lineSeparator() + tasks.getIndex(index).toString();
        storage.saveChange(textUi,tasks);
        return new CommandResult(feedback);
    }
}