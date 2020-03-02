package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * FindCommand extended from the Command class to deal with the execution of the find
 * function to find tasks with certain key words
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * FindCommand constructor with certain key word
     * @param keyWord word that is to be found in task's description
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /** Override abstract method of Command Class.
     * execute a FindCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.findTask(keyWord);
    }
}
