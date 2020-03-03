package Duke.Command;
import Duke.UI.*;
import Duke.Tasks.*;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
