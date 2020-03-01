package duke.command;

import duke.task.TaskManager;

/**
 * A class representing a user command. It could be either a {@link ClearCommand}, a {@link DeadlineCommand},
 * a {@link DeleteCommand}, a {@link DoneCommand}, an {@link EventCommand}, an {@link ExitCommand},
 * a {@link HelpCommand}, an {@link InvalidCommand}, a {@link ListCommand}, a {@link TodoCommand}, or
 * a {@link FindCommand}.
 */
public abstract class Command {

    /** Commands available to the user */
    public static final String CMD_ADD_TODO = "todo";
    public static final String CMD_ADD_DEADLINE = "deadline";
    public static final String CMD_ADD_EVENT = "event";
    public static final String CMD_DONE = "done";
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_HELP = "help";
    public static final String CMD_CLEAR_WINDOW = "clc";
    public static final String CMD_DELETE = "delete";
    public static final String CMD_FIND = "find";

    protected TaskManager taskManager;

    public Command (TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the command based on its type.
     *
     * @return User feedback about the execution of the command.
     */
    public abstract CommandResult execute ();

}
