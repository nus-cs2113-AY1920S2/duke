import java.io.File;
import java.util.List;

public class ByeCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        Printer.printLines();
        Printer.printIndentation();
        Printer.printBye();
        Printer.printLines();

        System.exit(0);
    }
}
