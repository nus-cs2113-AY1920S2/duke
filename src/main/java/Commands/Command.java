package Commands;

import Exceptions.DukeException;
import Exceptions.MissingDateException;
import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;


public abstract class Command {
    protected boolean isExit;
    protected String rawUserInput;

    public Command(String rawUserInput) {
        this.isExit = false;
        this.rawUserInput = rawUserInput;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public void setExitTrue() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public String[] removeCommandWord(String rawUserInput) throws MissingDescriptionException {
        String[] removedCommandWord = rawUserInput.trim().split(" ", 2);
        if (removedCommandWord.length != 2) {
            throw new MissingDescriptionException(rawUserInput);
        } else {
            return removedCommandWord;
        }
    }

    public String[] splitDate(String userInput)
            throws MissingDateException, MissingDescriptionException {
        String[] splitDate = userInput.trim().split("/", 2);
        if (splitDate.length != 2 || isEmptyString(splitDate[1])) {
            throw new MissingDateException(rawUserInput);
        } else if (isEmptyString(splitDate[0])) {
            throw new MissingDescriptionException(rawUserInput);
        } else {
            return splitDate;
        }
    }

    private boolean isEmptyString(String myString) {
        return (myString.length() == 0);
    }
}
