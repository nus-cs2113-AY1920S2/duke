package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.UI;

/**
 * ToDoCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class ToDoCommand extends Command {

    /**
     * The description of the to-do entered by the user.
     */

    protected String description;

    /**
     * Constructs the ToDoCommand object.
     *
     * @param command     the command prompt entered by the user.
     * @param description the description of the to-do entered by the user.
     */

    public ToDoCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    /**
     * Executes the ToDoCommand and creates a new to-do task in the tasklist.
     *
     * @param tasklist the list containing all current tasks.
     * @param ui       the object containing user interface functions.
     * @param storage  the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ToDo todo = new ToDo(this.description);
        tasklist.addTask(todo);
        storage.writeFileLine(todo);
        ui.displayAddToDoMessage(todo, tasklist.getTaskList());
    }
}
