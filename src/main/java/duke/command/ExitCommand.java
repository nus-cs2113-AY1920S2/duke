package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printEndMessage();
    }

    /*Return true for Loop to end and Terminate Program*/
    public boolean isExit() {
        return true;
    }
}
