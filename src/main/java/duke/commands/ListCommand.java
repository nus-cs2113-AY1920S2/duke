package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all the task in the list
 * of tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String parameters) {
        super(parameters);
    }

    /**
     * Prints the list of tasks in order of insertion.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            Ui.printList(tasks);
        }
    }
}
