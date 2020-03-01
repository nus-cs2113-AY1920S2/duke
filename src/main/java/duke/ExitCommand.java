package duke;

import java.io.IOException;

import static duke.Duke.BYE_MESSAGE;

public class ExitCommand extends Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showBye();
    }
}
