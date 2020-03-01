package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

public abstract class Command {

    /** User commands available to the user */
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
    protected Ui printer;

    public Command (TaskManager taskManager, Ui printer) {
        this.taskManager = taskManager;
        this.printer = printer;
    }

    public abstract CommandResult execute ();

}
