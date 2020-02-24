package duke.commands;

import duke.printer.Printer;
import duke.storage.Storage;

import java.io.File;
import java.util.List;

public class HelpCommand extends Command {
    @Override
    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        Printer.printHelp();
    }
}
