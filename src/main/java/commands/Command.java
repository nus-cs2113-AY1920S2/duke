package commands;

import exceptions.DukeException;
import exceptions.MissingDateException;
import exceptions.MissingDescriptionException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Abstract Class for Commands
 */
public abstract class Command {
    protected boolean isExit;
    protected String rawUserInput;

    /**
     * Constructs a default Command object with rawUserInput
     * @param rawUserInput String provided by user through System.in
     */
    public Command(String rawUserInput) {
        this.isExit = false;
        this.rawUserInput = rawUserInput;
    }

    /**
     * Abstract class that handles execution of the respective commands
     * @param taskList object handling task operations
     * @param ui object handling default user interface messages
     * @param storage object handling storage operations
     * @throws DukeException abstract class that handles all custom Exceptions
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Sets isExit to true
     */
    public void setExitTrue() {
        isExit = true;
    }

    /**
     * Returns the value of boolean isExit
     * @return boolean value of isExit
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns an array of the separated command word and descriptions
     * Separates the command word from user input
     * @param rawUserInput unedited String provided from user
     * @return Array containing separated Command Word and description
     * @throws MissingDescriptionException throws Exception when user fails to provide description
     */
    public String[] removeCommandWord(String rawUserInput) throws MissingDescriptionException {
        String[] removedCommandWord = rawUserInput.trim().split(" ", 2);
        if (removedCommandWord.length != 2) {
            throw new MissingDescriptionException(rawUserInput);
        } else {
            return removedCommandWord;
        }
    }

    /**
     * Returns an array of the separated description and date
     * Separates the description and date from user input
     * @param userInput String object provided from user without the command word
     * @return Array containing Description and Date
     * @throws MissingDateException throws when user fails to provide date
     * @throws MissingDescriptionException throws when user fails to provide description
     */
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

    /**
     * Returns boolean true if string is empty
     * otherwise returns false
     * @param myString string to check if empty
     * @return true if string is empty otherwise, false
     */
    private boolean isEmptyString(String myString) {
        return (myString.length() == 0);
    }
}