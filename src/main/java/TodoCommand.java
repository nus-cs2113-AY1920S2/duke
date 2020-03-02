public class TodoCommand extends Command {
    private ToDo todo;

    public TodoCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        todo = new ToDo(command.substring(5));
        textUi.printTodoMessage(todo);
        Duke.tasks.add(todo);
    }
}
