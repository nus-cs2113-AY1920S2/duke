package Duke.Command;

import Duke.DukeException;
import Duke.UI.Ui;
import Duke.TaskList;
import Duke.Storage;
import java.io.IOException;

/**
 * Command that saves the current task list in the program and terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks object of TaskList class containing list of tasks in the program.
     * @param ui object of Ui class handling printing output to the user.
     * @param storage object of Storage class for saving program to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            storage.save();
            ui.showGoodbye();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Indicates program ready to exit.
     *
     * @return isExit() is true and program should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
