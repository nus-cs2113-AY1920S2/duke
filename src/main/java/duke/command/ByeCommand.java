package duke.command;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.exception.DukeFileException;

import java.io.IOException;

/**
 * The ByeCommand class is the Object that store all the Task into the hard disk and exit the program..
 * ByeCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class ByeCommand implements Command {

    /**
     * Empty constructor for ByeCommand.
     */
    public ByeCommand() {
    }

    /**
     * Executes the bye function to store all the Task into the hard disk and exit the program.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeFileException {
        try {
            storage.saveTask(taskList);
        } catch (IOException e) {
            throw new DukeFileException("     :( OOPS!!! Something went wrong, aborting file save!");
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Boolean result indicate to the program if it should be exited.
     * @return True since command keyword matches "bye".
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
