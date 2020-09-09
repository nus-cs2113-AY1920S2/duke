package duke.commands;

import java.util.ArrayList;

/**
 * <h3>Command Result</h3>
 * The <b>Command Result</b> is the result after executing a <b>Command</b>. It contains the feedback message to the
 * user, as well as instruction for the <b>UI.java</b> to show the full <i>or</i> filtered <b>Task List</b> to the user if
 * necessary.
 * @see Command
 * @see duke.data.TaskList
 */
public class CommandResult {
    private final String message;
    private final boolean isShowList; // Checks if it is an instruction for the UI.java to show the list
    private ArrayList<Integer> searchedIndices; // Contains the indices referencing to the filtered tasks in the task list

    public CommandResult(String message) {
        this.message = message;
        isShowList = false;
        searchedIndices = null;
    }

    public CommandResult(String message, boolean isPrint) {
        this.message = message;
        isShowList = isPrint;
        searchedIndices = null;
    }

    public CommandResult(String message, boolean isPrint, ArrayList<Integer> list) {
        this.message = message;
        isShowList = isPrint;
        searchedIndices = list;
    }

    /**
     * Returns the feedback message to the user.
     *
     * @return The feedback message to the user
     */
    public String getMessage() {
        return message;
    }

    /**
     * Checks if the <b>Task List</b> is to be shown to the user.
     *
     * @return <code>TRUE</code> if the <b>Task List</b> is to be shown to the user, and <code>FALSE</code>
     * otherwise
     * @see duke.data.TaskList
     */
    public boolean getShowListStatus() {
        return isShowList;
    }

    /**
     * Returns the <code>Array List</code> of the indices of the <b>Task List</b> referencing the <i>tasks</i> to
     * be shown to the user.
     *
     * @return An <code>Array List</code> of the indices of the <b>Task List</b> eferencing the <i>tasks</i> to
     * be shown to the user
     * @see duke.data.TaskList
     */
    public ArrayList<Integer> getSearchedIndices() {
        return searchedIndices;
    }

}
