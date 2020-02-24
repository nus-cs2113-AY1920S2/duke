package duke.commands;

import duke.TaskList;

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
    public void Execute(TaskList tasks){
        try {
            index = Integer.parseInt(this.parameters);
            System.out.println("  You have deleted: " + tasks.get(index - 1).getDescription() + "\n");
            tasks.remove(index - 1);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e) {
            System.out.println("Please specify a task number!");
        }
        super.Execute(tasks);
    }
}
