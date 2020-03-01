package duke.commands;

import duke.data.TaskList;

import java.util.ArrayList;

import static duke.ui.Messages.FIND_MESSAGE;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FORMAT = "find <search word(s)>";

    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Integer> searchedTaskIndices = TaskList.filter(searchWord);
        return new CommandResult(FIND_MESSAGE, true, searchedTaskIndices);
    }
}
