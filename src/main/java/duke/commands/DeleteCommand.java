package duke.commands;

import duke.common.ExceptionMessage;

import java.io.IOException;

import static duke.Duke.tasks;
import static duke.storage.Storage.deleteFileContent;

public class DeleteCommand extends Command {
    
    public static final String COMMAND_WORD = "delete";
    
    private static final String COMMAND_DELETE_DESC = "Deletes the task from the list.";
    private static final String COMMAND_DELETE_PARAMETER = "INDEX NUMBER";
    private static final String COMMAND_DELETE_EXAMPLE = COMMAND_WORD + " 1";
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_DELETE_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DELETE_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + LS;
    
    private static final String COMMAND_DELETE_MESSAGE =
            "Noted. I've removed this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    private String targetIndex;
    
    public DeleteCommand(String targetIndex) {
        this.targetIndex = targetIndex;
    }
    
    public String getTargetIndex() {
        return targetIndex;
    }
    
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
