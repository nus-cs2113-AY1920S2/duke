package duke.command;

import duke.taskList.TaskList;
import duke.task.Task;
import duke.Ui.TextUi;
import java.util.ArrayList;

import static duke.taskList.TaskList.filterTheList;
public class CommandFind extends Command{

    public static final String EMPTY_LIST_MESSAGE = "Sheena: Erm, List is currently empty.";
    String Keyword;
    public CommandFind (String filtered_word){

        this.Keyword = filtered_word;
    }

    @Override
    public CommandOption execute() {
        try {
            ArrayList<Task> duplicateList = TaskList.copy();
            ArrayList<Task> listToFilter = filterTheList(duplicateList, Keyword);
            System.out.println("Sheena: Yay! Here are the matching tasks in your list:\n");
            TaskList.printList(listToFilter);
            return new CommandOption(String.format(getMessageForTaskListShownSummary(listToFilter),listToFilter));
        } catch (NullPointerException e) {
            return new CommandOption(EMPTY_LIST_MESSAGE);
        }

    }
}
