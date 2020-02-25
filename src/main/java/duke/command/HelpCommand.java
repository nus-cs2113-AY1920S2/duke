package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public class HelpCommand implements Command {
    private String commandWord;

    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelpMessage(commandWord);
    }
}
