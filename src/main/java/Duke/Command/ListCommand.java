package Duke.Command;

import Duke.UI.Ui;
import Duke.UI.Messages;
import Duke.Tasks.Task;
import Duke.TaskList;
import Duke.Storage;

/**
 * Command that displays the list of tasks in the program.
 */
public class ListCommand extends Command {

    /**
     * Displays each task in the list in correct format.
     *
     * @param i the current iteration in the list.
     * @param t the current task in the list.
     * @return the line to display.
     */
    public String displayTask (int i, Task t) {
        int number = i + 1;
        return Integer.toString(number) + "." + t;
    }

    /**
     * Displays the entire lists of tasks in the program.
     *
     * @return the list to print
     */
    public String displayList() {
        String printedList;
        if (TaskList.getSize() == 0) {
             printedList = Messages.EMPTY_LIST_MESSAGE;
        } else {
            printedList = Messages.LIST_HEADER_MESSAGE;
            for (int i = 0; i < TaskList.getSize(); i++) {
                printedList += (displayTask(i, TaskList.fetchTask(i)) + "\n");
            }
        }
        return printedList;
    }

    /**
     * Executes the command.
     *
     * @param tasks The object class containing list of tasks in the program.
     * @param ui The object class handling printing output to the user.
     * @param storage The object class for saving program to file.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.out.println(displayList());
    }

    /**
     * Indicates program not ready to exit.
     *
     * @return isExit() is false and program should continue running.
     */
    @Override
    public boolean isExit(){
        return false;
    };

}
