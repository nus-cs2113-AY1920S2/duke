package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;

import java.io.File;
import java.util.List;

/**
 * A parent class for the various commands to inherit.
 */
public abstract class Command {
    public static final List<String> COMMAND_LIST = Parser.getCommandList();
    public static final int LIMIT = 2;
    public static final int LIST_INDEX = 1;
    public static final int TASK_DESCRIPTION = 1;
    public static final int TASK_DESCRIPTION_AND_DATE = 1;
    public static final int TASK_DEADLINE = 0;
    public static final int TASK_EVENT_AT = 0;
    public static final int DUKE_COMMAND = 0;

    /**
     * For children class to implement
     *
     * @param myTasks The list where children command will store Tasks.
     * @param saveFile The place where children command will save Tasks.
     * @param commands The rest of the description that has not been parsed yet. e.g., "team meeting /at central lib"
     * @param command The actual command for the children. e.g., "deadline", "event".
     */
    public abstract void execute(Storage myTasks, File saveFile, List<String> commands,
                                 String command);
}
