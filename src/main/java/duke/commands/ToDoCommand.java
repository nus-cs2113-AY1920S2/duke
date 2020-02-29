package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.ToDo;

import java.util.regex.Pattern;

/**
 * Class for a todo command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class ToDoCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^todo\\s+(\\w\\s*)+", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "todo math homework";
    public static final String ERROR_MESSAGE = "Command needs to be in form: todo <description>";
    public static final String KEYWORD = "todo";
    public ToDo toDo;

    public ToDoCommand(TaskList taskList, String description) {
        super(taskList);
        this.toDo = new ToDo(description);
    }

    /**
     * Adds the todo task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(toDo);
    }
}
