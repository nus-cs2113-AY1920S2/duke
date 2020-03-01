package duke.commands;

import duke.data.TaskList;

import java.util.ArrayList;

import static duke.ui.Messages.FIND_MESSAGE;

/**
 * <h3>Find Command</h3>
 * A <b>Command</b> to show a filtered <b>Task List</b> to the user based on the <i>keyword(s)</i> provided. Filtering
 * is done in a <b>non-case-sensitive</b> manner. The <i>filtered list</i> contains <i>tasks</i> that have the
 * <i>keyword(s)</i> in their <i>task descriptions</i>.
 * @see Command
 * @see TaskList
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FORMAT = "find <keyword>";

    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord.toLowerCase();
    }

    /**
     * Executes the <b>Find Command</b> to show the filtered <b>Task List</b> to the user based on the
     * <code>searchWord</code>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Integer> searchedTaskIndices = TaskList.filter(searchWord);
        return new CommandResult(FIND_MESSAGE, true, searchedTaskIndices);
    }
}
