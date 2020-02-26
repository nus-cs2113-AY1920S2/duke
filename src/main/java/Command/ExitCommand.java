package Command;

import Storage.Storage;
import Task.TaskList;
import UI.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
                                                + "Example: " + COMMAND_WORD;


    @Override
    public void execute() {
        System.out.println("LISA: Bye, hope to see you again!");
        // make use of the tasks and print out number of left over items
    }



    @Override
    public boolean isExit() {
        return true;
    }
}
