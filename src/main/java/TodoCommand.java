public class TodoCommand extends Command {
    private ToDo todo;

    public TodoCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        todo = new ToDo(command.substring(5));
        textUi.printTodoMessage(todo);
        Duke.tasks.add(todo);
    }
}


/*
Task task = new ToDo(userInput.substring(5));
            tasks.add(task);
 */
