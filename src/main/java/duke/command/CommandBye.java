package duke.command;

public class CommandBye extends Command {

    public static final String EXIT = "Sheena: Let me save down everything ^^\n ";

    @Override
    public CommandOption execute() {
        return new CommandOption(EXIT);
    }

    public static boolean isExit(Command command) {
        return command instanceof CommandBye; // instanceof returns false if it is null
    }
}
