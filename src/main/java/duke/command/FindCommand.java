package duke.command;

import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.ui.Ui;
import duke.utility.Messages;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String userInput;

    public FindCommand (TaskManager manager, Ui printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput.trim();
    }

    public CommandResult execute () {

        if (userInput.isEmpty()) {
            return new CommandResult(Messages.EMPTY_FIND);
        }

        ArrayList<Task> tasks = taskManager.findTasks(userInput);

        String heading = String.format(Messages.FOUND_TASKS, tasks.size(), taskManager.getTaskListNoun(), userInput.trim()) +
                System.lineSeparator() + System.lineSeparator();

        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                heading += "\t\t" + (i + 1) + "." + tasks.get(i);
                continue;
            }
            heading += "\t\t" + (i + 1) + "." + tasks.get(i) + System.lineSeparator();
        }

        return new CommandResult(heading);
    }

}
