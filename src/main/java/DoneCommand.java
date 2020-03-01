public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super(command);
        Duke.tasks.get(Integer.parseInt(Character.toString(
                command.charAt(command.length() - 1))) - 1).setDone();
    }
}
