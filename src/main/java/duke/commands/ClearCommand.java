package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.Scanner;

/**
 * ClearCommand inherits from command and is the public class responsible for storing information and executing the command.
 */

public class ClearCommand extends Command {

    /**
     * Constructs the ClearCommand object.
     *
     * @param command the command prompt entered by the user.
     */

    public ClearCommand(String command) {
        super(command);
    }

    /**
     * Executes the ClearCommand and clears the task list.
     *
     * @param tasklist the list containing all current tasks.
     * @param ui       the object containing user interface functions.
     * @param storage  the object containing storage functions.
     */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) {
        ui.displayListClearConfirmationMessage();
        Scanner sc = new Scanner(System.in);
        String confirmation = sc.nextLine().trim().toUpperCase();
        if (confirmation.equals("Y")) {
            tasklist.clearList();
            storage.clearFile();
            ui.displayListClearMessage();
        } else {
            ui.displayListNotClearedMessage();
        }
    }
}
