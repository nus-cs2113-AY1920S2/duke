package duke.commands;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.data.task.ToDo;

import static duke.ui.TextUi.printTask;

/**
 * Handles the addition of new tasks to the list.
 */
public class AddCommand extends Command {

    /**
     * Contains information related to taskType.
     */
    String taskType;
    /**
     * Contains description related to the task.
     */
    String description;
    /**
     * Contains addition information related to the task's extension .
     */
    String extension;

    /**
     * Constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     *
     * @param extension   Contains addition information related to the task.
     * @param description Contains description on the task.
     * @param taskType    Denotes the type of the task to be added.
     */
    public AddCommand(String taskType, String description, String extension) {
        this.taskType = taskType;
        this.description = description;
        this.extension = extension;
    }

    /**
     * Overload constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     * Set extension to null.
     *
     * @param description Contains description on the task
     * @param taskType    Denotes the type of the task to be added
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.extension = null;
    }

    /**
     * Adds a new task to the list with the information provided by calling
     * {@code tasklist#add(new ToDo(description))} (or) {@code tasklist#add(new Event(description.trim(), extension.trim())}
     * (or) {@code tasklist#add(new Deadline(description.trim(), extension.trim()))} as require
     *
     * @param tasklist Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public void execute(TaskList tasklist) {
        int taskCounter = tasklist.size();
        switch (taskType) {
        case ("todo"):
            tasklist.add(new ToDo(description));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        case ("event"):
            tasklist.add(new Event(description.trim(), extension.trim()));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        case ("deadline"):
            tasklist.add(new Deadline(description.trim(), extension.trim()));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        }
    }
}
