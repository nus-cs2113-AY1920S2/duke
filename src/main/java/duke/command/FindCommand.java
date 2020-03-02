package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.exceptions.NoTaskFoundException;
import src.main.java.duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword.toLowerCase();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskFoundException {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> foundTask = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                foundTask.add(task);
            }
        }
        int numberOfTaskFound = foundTask.size();
        if (numberOfTaskFound == 0) {
            throw new NoTaskFoundException();
        } else {
            StringBuilder foundList = new StringBuilder();
            for (int i = 0; i < numberOfTaskFound; i++) {
                foundList.append(i + 1).append(".").append(foundTask.get(i).toString()).append(System.lineSeparator());
            }
            ui.listFoundTasks(foundList.toString().trim());
        }
    }
}
