package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.task.Event;

import java.io.IOException;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private String taskDescription;

    private String date;

    public AddEventCommand(String taskDescription, String date){
        this.taskDescription = taskDescription;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event event = new Event(taskDescription, date);
        tasks.addToList(event);
        Ui.printAddedTask(event);
        storage.writeToFile(tasks);
    }
}
