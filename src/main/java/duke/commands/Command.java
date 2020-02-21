package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

import java.io.IOException;

public class Command {
    protected String parameters;
    //protected String parameters;

    public Command(String parameters) {
        this.parameters = parameters;
    }

    public void Execute(TaskList tasks) {
        try {
            Storage.saveToFile(tasks);
        } catch (IOException e) {
            System.out.println("Error Saving");
        }
    }
}
