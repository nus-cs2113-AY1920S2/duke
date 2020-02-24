package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

public abstract class Command {
    protected static final String INDENTATION = "      ";
    protected static final String MORE_INDENTATION = "        ";

    public abstract CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) throws
            Exception;

    protected int convertToZeroBased (int index) {
        return index - 1;
    }

    protected int convertToOneBased (int index) {
        return index + 1;
    }
}
