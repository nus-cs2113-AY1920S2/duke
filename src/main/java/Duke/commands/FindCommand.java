package duke.commands;

import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

public class FindCommand implements Command {
    private String line;

    public FindCommand(String restOfInput){
        this.line = restOfInput;
    }

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        boolean found = false;
        String[] keywords = line.trim().split("\\s+");
        ui.printToUser(DisplayUI.MATCHING_TASK_MESSAGE);
        for (String word : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                String taskDescription = tasks.get(i).getDescription();
                if (taskDescription.contains(word)) {
                    int index = i + 1;
                    ui.printToUser("    " + index + "." + tasks.get(i));
                    found = true;
                }
            }
        }
        if (!found) {
            ui.printToUser(DisplayUI.NO_MATCH_MESSAGE + DisplayUI.LINE_DIVIDER);
        }
        return true;
    }
}
