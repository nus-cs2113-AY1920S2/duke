package duke.commands;

import duke.common.ExceptionMessage;

import java.io.IOException;

import static duke.Duke.tasks;
import static duke.storage.Storage.deleteFileContent;

/**
 * Deletes a task identified by the targetIndex given by the user
 */
public class DeleteCommand extends Command {
    
    public static final String COMMAND_WORD = "delete";
    
    private static final String COMMAND_DELETE_DESC = "Deletes the task from the list.";
    private static final String COMMAND_DELETE_PARAMETER = "<INDEX>";
    private static final String COMMAND_DELETE_EXAMPLE = COMMAND_WORD + " 1";
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_DELETE_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DELETE_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + LS;
    
    private static final String COMMAND_DELETE_MESSAGE =
            "Noted. I've removed this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    private String targetIndex;
    
    /**
     * Constructor for DeleteCommand
     *
     * @param targetIndex the index string to be deleted
     */
    public DeleteCommand(String targetIndex) {
        this.targetIndex = targetIndex;
    }
    
    public String getTargetIndex() {
        return targetIndex;
    }
    
    /**
     * Execute the delete operation flow
     *
     * @return the delete message to be shown to the user
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > tasks size
     */
    @Override
    public CommandResult execute() {
        try {
            String index = getTargetIndex();
            int removeTaskIndex = Integer.parseInt(index) - 1;
            if (removeTaskIndex >= tasks.size() || removeTaskIndex < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String taskDescription = tasks.get(removeTaskIndex).toString();
            deleteFileContent(removeTaskIndex);
            tasks.remove(removeTaskIndex);
            return new CommandResult(
                    DIVIDER + LS + String.format(COMMAND_DELETE_MESSAGE, taskDescription, tasks.size()) + LS + DIVIDER);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            return new CommandResult(DIVIDER + LS + ExceptionMessage.COMMAND_INVALID_INDEX_MESSAGE + LS + DIVIDER);
        }
    }
    
}
