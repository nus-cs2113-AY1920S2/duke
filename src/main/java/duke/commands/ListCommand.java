package duke.commands;

import duke.taskmanager.Tasks;
import duke.util.UI;

import java.util.List;

public class ListCommand extends Command {
    private static UI ui;
    public ListCommand(UI ui) {
        ListCommand.ui = ui;
    }

    public static void printIntro() {
        System.out.println(ui.SPLIT_UPPER_BOUNDARY);
        System.out.printf(ui.FORMAT, "Your current task list:");
    }

    /**
     * Message print when the current task list is empty.
     */
    public static void printEmpty() {
        System.out.println(ui.SPLIT_UPPER_BOUNDARY);
        System.out.printf(ui.FORMAT, "You have no ongoing task.");
        System.out.println(ui.SPLIT_LOWER_BOUNDARY);
    }

    /**
     * Print the current task list. Check whether the
     * task list is empty, and print different message if
     * the list is empty. If it is not empty, iterate through
     * the current task list and print it on the screen.
     * @param list the current task list
     */
    public static void execute(List<Tasks> list) {
        printIntro();
        if (list.size() == 0){
            printEmpty();
        } else {
            int index = 0;
            for (Tasks task : list) {
                System.out.printf(ui.FORMAT, index + ". "+ task.toString());
                index++;
            }
            System.out.println(ui.SPLIT_LOWER_BOUNDARY);
        }
    }
}
