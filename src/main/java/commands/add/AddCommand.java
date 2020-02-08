package commands.add;

import commands.Command;

/**
 * Adds a task to the task list.
 */

public abstract class AddCommand extends Command {



    public static final String MESSAGE_TODO_SUCCESS = "Got it. I've added this task: \n  [ID:%d][%c][%c] %s";
    public static final String MESSAGE_DEADLINE_SUCCESS = "Got it. I've added this task: \n %s";
    public static final String MESSAGE_EVENT_SUCCESS = "Got it. I've added this task: \n  [ID:%d][%c][%c] %s (%s)";

    public AddCommand() {
    }

}
