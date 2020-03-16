package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand class is the Object that find all Task with description that matches a keyword or phrase.
 * FindCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class FindCommand implements Command {
    private String description;

    /**
     * Public constructor for FindCommand.
     * @param description Keyword or phrase of the Task that user wants to find.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find function and find all Task with description that matches the keyword or phrase.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        boolean containDescription = false;
        int matchNumber = 1;
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            if (taskList.getTask(i).getDescription().contains(description)) {
                if (!containDescription) {
                    System.out.println("     Here are the matching tasks in your list:");
                    containDescription = true;
                }
                System.out.println("     " + matchNumber + "." + taskList.getTask(i).toString());
                matchNumber++;
            }
        }
        if (!containDescription) {
            System.out.println("     There is no matching tasks");
        }
    }

    /**
     * Boolean result indicate to the program if it should be exited.
     * @return False since command keyword does not match "bye".
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
