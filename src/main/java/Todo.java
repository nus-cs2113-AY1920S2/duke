public class Todo extends Task{
    public Todo (String command) throws DukeException{
        super("[T][✗] " + command.replaceFirst("todo\\s", "").trim());
        if (command.matches("todo\\s+")) {
            throw new DukeException(1,"todo");
        }
    }
}
