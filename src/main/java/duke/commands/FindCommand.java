package duke.commands;

import duke.Util.Tasklist;
import duke.Util.UI;

public class FindCommand extends Command {
    UI ui;

    public FindCommand(UI ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        System.out.println("    Please enter the task that you want to find:");
        String keyword = ui.getStringInput();
        Tasklist.find(keyword);
    }
}