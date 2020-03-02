public class NoSuchCommand extends Command {
    public NoSuchCommand(String command) throws IllegalArgumentException{
        super(command);
        throw new IllegalArgumentException();
    }
}
