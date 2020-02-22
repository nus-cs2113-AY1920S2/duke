package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);
        super.setExitTrue();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }
}
