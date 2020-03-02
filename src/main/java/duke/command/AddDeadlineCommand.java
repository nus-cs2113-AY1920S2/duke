package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.task.Deadline;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private String taskDescription;

    private String date;

    public AddDeadlineCommand(String taskDescription, String date){
        this.taskDescription = taskDescription;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline deadline = new Deadline(taskDescription, date);
        tasks.addToList(deadline);
        Ui.printAddedTask(deadline);
        storage.writeToFile(tasks);
    }
}
