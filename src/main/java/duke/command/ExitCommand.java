package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;

/**Print out End Message to user
 * Return true for isExit() method to break loop in Main method and Terminate the program
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printEndMessage();
    }

    public boolean isExit() {
        return true;
    }
}
