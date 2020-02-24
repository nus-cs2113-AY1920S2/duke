package duke.commands;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Constructor to create a new exit command.
     *
     * @param parameters takes in an empty string as no
     *                   parameters needed for this command
     */
    public ExitCommand(String parameters) {
        super(parameters);
    }

    /**
     * Prints the exit message and exits loop in the main function.
     */
    public void Execute() {
        System.out.println(" Bye. Hope to see you again soon!\n");
    }
}
