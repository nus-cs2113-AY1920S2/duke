package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import java.time.format.DateTimeParseException;


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
    public void execute(TaskList tasks) {
        Task new_task;
        try {
            int findSeparator;
            switch (this.command.toLowerCase()) {
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
            default:
                throw new IllegalStateException("Unexpected value: " + this.command);
            }
            Ui.printAddMessage(tasks.getSize());
            super.execute(tasks);
        } catch (StringIndexOutOfBoundsException | DukeException e) {
            Ui.printFieldEmpty();
        } catch (DateTimeParseException e) {
            Ui.printInvalidDate();
        }
    }

}
