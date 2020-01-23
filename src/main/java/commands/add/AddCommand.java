package commands;

import data.task.Task;

public abstract class AddCommand extends Command{

    public static final String MESSAGE_TODO_SUCCESS = "Got it. I've added this task: \n  [%c][%c] %s";
    public static final String MESSAGE_DEADLINE_SUCCESS = "Got it. I've added this task: \n  [%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_SUCCESS = "Got it. I've added this task: \n  [%c][%c] %s (%s)";

    public AddCommand() {
    }

}
