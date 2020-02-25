package duke.commands;

import duke.common.ExceptionMessage;

import java.io.IOException;

import static duke.Duke.tasks;
import static duke.storage.Storage.modifyFileContent;

/**
 * Marks a task done identified by the targetIndex given by the user
 */
public class DoneCommand extends Command {
    
    public static final String COMMAND_WORD = "done";
    
    private static final String COMMAND_DONE_DESC = "Marks the task done in the list.";
    private static final String COMMAND_DONE_PARAMETER = "INDEX NUMBER";
    private static final String COMMAND_DONE_EXAMPLE = COMMAND_WORD + " 1";
    
    private static final String COMMAND_DONE_MESSAGE = TAB + "Nice! I've marked this task as done:";
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_DONE_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DONE_PARAMETER) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DONE_EXAMPLE) + LS;
    
    private String targetIndex;
    
    /**
     * Constructor for DoneCommand
     *
     * @param targetIndex the index string to be deleted
     */
    public DoneCommand(String targetIndex) {
        this.targetIndex = targetIndex;
    }
    
    private String getTargetIndex() {
        return targetIndex;
    }
    
    /**
     * Execute the done operation flow
     *
     * @return the done message to the user
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > tasks size
     * @throws IOException                    if there is an error during an input-output operation
     */
    @Override
    public CommandResult execute() {
        String index = getTargetIndex();
        try {
            if (index.equals("")) {
                throw new IOException();
            }
            int doneTaskIndex = Integer.parseInt(index) - 1;
            if (doneTaskIndex >= tasks.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.get(doneTaskIndex).markAsDone();
            String taskDescription = tasks.get(doneTaskIndex).toString();
            modifyFileContent(doneTaskIndex, tasks.get(doneTaskIndex).toStorage());
            return new CommandResult(DIVIDER + LS + COMMAND_DONE_MESSAGE + LS + TAB + taskDescription + LS + DIVIDER);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            return new CommandResult(DIVIDER + LS + ExceptionMessage.COMMAND_INVALID_INDEX_MESSAGE + LS + DIVIDER);
        }
    }
    
}
