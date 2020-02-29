package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class DoneCommand extends Command {
    int index;
    /**
     * Constructor to create a new done command.
     *
     * @param parameters index of the task to be marked as
     *                   completed
     */
    public DoneCommand(String parameters) {
        super(parameters);
    }

    /**
     * Marks the task specified by the user as completed. If
     * the task has already been marked as completed, user will
     * be informed. Then saves the list of tasks to a .txt file.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            index = Integer.parseInt(this.parameters);
            if (!tasks.get(index - 1).getIsDone()) {
                Ui.printDoneMessage(tasks.get(index - 1).getDescription(), !tasks.get(index - 1).getIsDone());
                tasks.get(index - 1).markAsDone();
            } else {
                Ui.printDoneMessage(tasks.get(index - 1).getDescription(), !tasks.get(index - 1).getIsDone());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.printDoesNotExist();
        } catch (NumberFormatException e) {
            Ui.printInvalidTaskNum();
        }
        super.execute(tasks);
    }
}
