package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to delete a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor to create a new delete command
     *
     * @param parameters index of the task to be deleted
     */
    public DeleteCommand(String parameters) {
        super(parameters);
    }

    /**
     * Deletes a task at the index specified by the user. Then
     * saves the list of tasks to a .txt file.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            index = Integer.parseInt(this.parameters);
            Ui.printDeleteMessage(tasks.get(index - 1).getDescription(), tasks.getSize() - 1);
            tasks.remove(index - 1);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.printDoesNotExist();
        } catch (NumberFormatException e) {
            Ui.printInvalidTaskNum();
        }
        super.execute(tasks);
    }
}
