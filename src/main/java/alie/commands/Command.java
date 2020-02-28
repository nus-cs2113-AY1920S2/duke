package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

/**
 * Commands given by user to be executed
 */
public abstract class Command {
    /**
     * Indentation strings to make sure the messages printed by ALIE is aligned.
     */
    protected static final String INDENTATION = "      ";
    protected static final String MORE_INDENTATION = "        ";

    /**
     * Perform the desired actions of the specific Command.
     * @param taskLists TaskManager containing the user's tasks.
     * @param ui Ui to deal with any input and output when required.
     * @param storage Storage to save data when required.
     * @return Result of executing the command.
     * @throws Exception Any exception that could be encountered while executing command.
     */
    public abstract CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) throws
            Exception;

    /**
     * To convert a integer from one-based numbering to zero-based numbering.
     * @param index One-based integer to be converted.
     * @return Integer with zero-based numbering
     */
    protected int convertToZeroBased (int index) {
        return index - 1;
    }

    /**
     * To convert a integer from zero-based numbering to one-based numbering.
     * @param index Zero-based integer to be converted.
     * @return Integer with one-based numbering
     */
    protected int convertToOneBased (int index) {
        return index + 1;
    }
}
