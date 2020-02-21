package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import static duke.common.Messages.ADD_MESSAGE;

public class AddTodoCommand extends Command {

    private static String description;
    public static String COMMAND_PHRASE = "todo (item)";
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator() +
            "-Adds a todo item to the list";

    public AddTodoCommand(String description) {
        super();
        this.description = description;
    }

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
