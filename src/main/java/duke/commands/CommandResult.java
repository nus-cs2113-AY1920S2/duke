package duke.commands;

import duke.task.Task;

import java.util.ArrayList;

public class CommandResult {
    
    public final String feedbackToUser;
    private final ArrayList<Task> taskResult;
    
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.taskResult = null;
    }
    
    public CommandResult(String feedbackToUser, ArrayList<Task> taskResult) {
        this.feedbackToUser = feedbackToUser;
        this.taskResult = taskResult;
    }
    
}
