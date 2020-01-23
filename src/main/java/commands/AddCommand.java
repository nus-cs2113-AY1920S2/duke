package commands;

import data.task.Task;

public abstract class AddCommand extends Command{

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n  [%c][%c] %s";

    public AddCommand() {
    }

}
