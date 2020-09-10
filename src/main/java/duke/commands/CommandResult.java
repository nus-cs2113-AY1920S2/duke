package duke.commands;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represent the result of a command execution
 */
public class CommandResult {
    
    public final String feedbackToUser;
    private final ArrayList<Task> taskResult;
    
    /**
     * Constructor of CommandResult
     *
     * @param feedbackToUser the input of the results from other commands
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.taskResult = null;
    }
    
    /**
     * Constructor of CommandResult
     *
     * @param feedbackToUser the input of the results from other commands
     * @param taskResult     holds the information of the ArrayList
     */
    public CommandResult(String feedbackToUser, ArrayList<Task> taskResult) {
        this.feedbackToUser = feedbackToUser;
        this.taskResult = taskResult;
    }
    
}
