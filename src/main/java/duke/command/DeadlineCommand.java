package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The DeadlineCommand class is the Object that adds Deadline Object into the TaskList.
 * DeadlineCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DeadlineCommand implements Command {
    private String description;

    /**
     * Public constructor for DeadlineCommand.
     * @param description Description of the Task.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("to be completed" + description);
    }
}
