package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents a command to add a task object to the list of tasks.
 */
public class AddCommand extends Command {
    String command;

    /**
     * Constructor to create a new add command.
     *
     * @param parameters the description and date (where applicable)
     *                   of the task to be added.
     * @param command the type of task to be added
     */
    public AddCommand(String parameters, String command) {
        super(parameters);
        this.command = command;
    }

    /**
     * Adds the task specified by the user to the list of tasks. Then
     * saves the list of tasks to a .txt file.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void Execute(TaskList tasks) {
        Task new_task;
        int findSeparator = 0;
        try {
            switch (this.command) {
            case ("todo"):
                new_task = new Todo(this.parameters);
                tasks.add(new_task);
                break;
            case ("deadline"):
                findSeparator = this.parameters.indexOf('/');
                new_task = new Deadline(this.parameters.substring(0, findSeparator), this.parameters.substring(findSeparator + 1));
                tasks.add(new_task);
                break;
            case ("event"):
                findSeparator = this.parameters.indexOf('/');
                new_task = new Event(this.parameters.substring(0, findSeparator), this.parameters.substring(findSeparator + 1));
                tasks.add(new_task);
                break;
            }
            Ui.printAddMessage(tasks.getSize());
            super.Execute(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Task description or date field is empty.");
        } catch (DukeException e) {
            System.out.println("Task description or date field is empty.");
        }
    }

}
