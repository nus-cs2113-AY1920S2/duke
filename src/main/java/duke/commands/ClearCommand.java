package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.Scanner;

public class ClearCommand extends Command {

    public ClearCommand(String command) {
        super(command);
    }

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
