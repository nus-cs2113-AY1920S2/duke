package duke;

import java.io.IOException;

import static duke.Duke.*;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.delete(index,storage);
    }
}
