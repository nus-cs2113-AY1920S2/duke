package Command;

public class HelpCommand extends Command {
    @Override
    public void execute() {
        ui.printHelp();
    }
}
