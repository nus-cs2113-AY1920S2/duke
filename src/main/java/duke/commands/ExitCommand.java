package duke.commands;

import duke.data.task.TaskList;
import duke.ui.TextUi;


public class ExitCommand extends Command {

    /**
     * Prints the exit message by calling {@link TextUi#printExit()}.
     * Set isExit to true.
     *
     * @param tasklist Contains the list of tasks to be searched on.
     * @see TextUi#printExit()
     */
    @Override
    public void execute(TaskList tasklist) {
        super.isExit = true;
        TextUi.printExit();
    }
}
