package duke.command;

import duke.task.TaskManager;
import duke.ui.Output;

public abstract class Command {

    // TODO Refactor command information and command execution into
    // their own classes

    /** User commands available to the user */
    public static final String CMD_ADD_TODO = "todo";
    public static final String CMD_ADD_DEADLINE = "deadline";
    public static final String CMD_ADD_EVENT = "event";
    public static final String CMD_DONE = "done";
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_HELP = "help";
    public static final String CMD_CLEAR_WINDOW = "clear";

    /** Easter egg */
    public static final String CMD_DELETE = "delete";

    protected TaskManager taskManager;
    protected Output printer;

    public Command (TaskManager taskManager, Output printer) {
        this.taskManager = taskManager;
        this.printer = printer;
    }

    public abstract void execute ();

}
