package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import static duke.common.Messages.ADD_MESSAGE;

/**
 * Adds a to-do to the list of tasks.
 */
public class AddTodoCommand extends Command {

    /** Description of the task */
    private static String description;

    /** Format of the command */
    public static String COMMAND_PHRASE = "todo (item)";

    /** Usage of the command */
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator() +
            "-Adds a todo item to the list";

    public AddTodoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Returns a <code>CommandResult</code> with feedback to the user initialised.
     *
     * @param tasks <code>TaskList</code> object that the command will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        Todo newTodo = new Todo(description);
        String feedback = ADD_MESSAGE + System.lineSeparator()
                + newTodo.toString() + System.lineSeparator() + "Now you have " + (tasks.getSize()+1) + " tasks in the list";
        tasks.add(newTodo);
        storage.writeTodo(textUi,description);
        return new CommandResult(feedback);
    }

}
