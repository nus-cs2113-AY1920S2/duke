package Command;

/**
 * Displays a list of commands available
 */
public class HelpCommand extends Command {
    @Override
    public void execute() {
        ui.printHelp();
    }
}
