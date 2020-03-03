package Duke.Command;
import Duke.UI.*;
import Duke.Tasks.*;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;
import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            storage.save();
            ui.showGoodbye();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
