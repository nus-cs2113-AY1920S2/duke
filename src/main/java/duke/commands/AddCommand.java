package duke.commands;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.data.task.ToDo;

import static duke.ui.TextUi.printTask;

public class AddCommand extends Command {
    String taskType;
    String description;
    String extension;

    public AddCommand(String taskType, String description, String extension) {
        this.taskType = taskType;
        this.description = description;
        this.extension = extension;
    }

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.extension = null;
    }

    @Override
    public void execute(TaskList tasklist) {
        int taskCounter = tasklist.size();
        switch (taskType) {
        case ("todo"):
            tasklist.add(new ToDo(description));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        case ("event"):
            tasklist.add(new Event(description.trim(), extension.trim()));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        case ("deadline"):
            tasklist.add(new Deadline(description.trim(), extension.trim()));
            printTask(tasklist.get(taskCounter), taskCounter);
            break;
        }
    }
}
