public class Todo extends Task{
    public Todo (String command) {
        super("[T][✗] " + command.replaceFirst("todo\\s", "").trim());
    }
}
