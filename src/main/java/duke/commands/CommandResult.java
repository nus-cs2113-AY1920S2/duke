package duke.commands;

import duke.task.Task;

import java.util.ArrayList;

public class CommandResult {

    public final String feedback;

    public final ArrayList<Task> tasks;

    public CommandResult(String feedback) {
        this.feedback = feedback;
        tasks = null;
    }

    public CommandResult(String feedback,ArrayList<Task> tasks) {
        this.feedback = feedback;
        this.tasks = tasks;
    }

}
