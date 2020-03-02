package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.task.Todo;

import java.io.IOException;

/**Instantiate new Todo class to insert into TaskList
 *
 * @param taskDescription  Name of task
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private String taskDescription;

    public AddTodoCommand(String taskDescription){
        this.taskDescription = taskDescription;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todo todo = new Todo(taskDescription);
        tasks.addToList(todo);
        Ui.printAddedTask(todo);
        storage.writeToFile(tasks);
    }
}
