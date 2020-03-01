package duke.command;

import duke.taskList.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class Command {
    private String nameOfCommand;
    private String Argument;
    protected TaskList taskList;


    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "Sheena: Yay! %1$d tasks listed!";

    public static String getMessageForTaskListShownSummary(ArrayList<Task> tasksDisplayed) {
        return String.format(MESSAGE_TASKS_LISTED_OVERVIEW, tasksDisplayed.size());
    }

    public CommandOption execute() {
        throw new UnsupportedOperationException("This method must be implemented by child classes");
    }

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public String getCommandName() {
        return nameOfCommand;
    }

    public void setCommandName(String commandName) {
        nameOfCommand = commandName;
    }

    public String getArgs() {
        return Argument;
    }

    public void setArgs(String args) {
        Argument = args;
    }
}