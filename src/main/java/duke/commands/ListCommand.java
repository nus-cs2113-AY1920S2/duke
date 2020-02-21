package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.common.Messages.LIST_MESSAGE;
import static duke.common.Messages.NO_TASK_MESSAGE;

public class ListCommand extends Command {

    public static final String COMMAND_PHRASE = "list";

    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Shows all tasks in the list currently";

    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {

        String feedback;
        if (tasks.getSize() == 0) {
            feedback = NO_TASK_MESSAGE;
            return new CommandResult(feedback);
        } else {
            feedback = LIST_MESSAGE;
            ArrayList<Task> instructions = new ArrayList<>();
            for (int i = 0; i < tasks.getSize(); i++) {
                instructions.add(tasks.getIndex(i));
            }
            return new CommandResult(feedback,instructions);
        }
    }
}
