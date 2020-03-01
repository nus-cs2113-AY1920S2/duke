package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String keyWord;

    public static final String COMMAND_WORD = "find";

    public FindCommand(String keyWord) {
        this.keyWord = keyWord.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.listFoundTasks(tasks.findTasks(this.keyWord));
    }
}
