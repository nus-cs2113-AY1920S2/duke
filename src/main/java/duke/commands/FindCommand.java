package duke.commands;

import duke.Util.UI;
import duke.taskmanager.Tasks;

import java.util.List;

public class FindCommand extends Command {
    UI ui;
    private static boolean isFound = false;
    public FindCommand(UI ui) {
        this.ui = ui;
    }

    public void execute(List<Tasks> tasks) {
        System.out.println("    Please enter the task that you want to find:");
        String keyword = ui.getStringInput();
        int index = 0;
        for (Tasks task : tasks) {
            if (task.getTask().contains(keyword)) {
                System.out.println("    "+ index + ": " +
                        task);
                isFound = true;
            }
            index ++;
        }
        if (!isFound) {
            System.out.println("    Task " + keyword + " is not in the list.");
        }
    }
}