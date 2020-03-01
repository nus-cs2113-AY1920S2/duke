package parser;

import ui.Ui;
import command.Command;
import common.Messages;
import exceptions.IllegalKeywordException;
import exceptions.MissingParameterException;
import exceptions.NoDescriptionException;
import exceptions.NoRemarkException;
import exceptions.NumberFieldException;
import tasklist.TaskList;

import static common.Messages.BYE_COMMAND;
import static common.Messages.LIST_COMMAND;
import static common.Messages.HELP_COMMAND;
import static common.Messages.DONE_COMMAND;
import static common.Messages.DELETE_COMMAND;
import static common.Messages.FIND_COMMAND;
import static common.Messages.TODO_COMMAND;
import static common.Messages.EVENT_COMMAND;
import static common.Messages.DEADLINE_COMMAND;
import static common.Messages.INVALID_COMMAND_ERROR_MESSAGE;
import static common.Messages.REMARKS_DELIMITER;
import static common.Messages.WHITESPACE_DELIMITER;
import static common.Messages.TODO_HAS_REMARK_SECTION_ERROR_MESSAGE;
import static common.Messages.executeCommandInsufficientParameterErrorMessage;

/**
 * This class parses the user input. It helps to process and convert input into a {@link Command} object.
 * <p></p>
 * <p>
 */
public class Parser {

    public static final int MAX_SUBSTRING_FIELDS = 2;
    public boolean isExitCommandInvoked = false;
    private TaskList taskList;
    private Messages messageContainer = new Messages();

    public Parser(TaskList taskListInput) {
        this.taskList = taskListInput;
    }

    /**
     * This method attempts to validate the user input and construct the required {@link Command} based on the parameters.
     * <p></p>
     * <p>The command keywords in the user input follow specified formats. If there are inconsistencies in the input,
     * an exception will be thrown with an error message informing the user of the inconsistency and advice for proper usage.
     * </p>
     * @param userInput a String containing the processed command input provided by the user
     * @return a command.Command object that can be used to execute the intended operation for the user
     * @throws NumberFieldException an exception thrown in DONE and DELETE command operations; when the task number given is not a number, or outside the range of existing tasks
     * @throws NoRemarkException an exception thrown in EVENT and DEADLINE command operations; when the new task does not contain a remarks field
     * @throws IllegalKeywordException an exception thrown when the command keyword is not recognized as a valid command
     * @throws NoDescriptionException an exception thrown in TODO, EVENT and DEADLINE command operations; when the new task does not contain a description field
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    public Command parseCommand(String userInput) throws NoRemarkException, IllegalKeywordException,
            NoDescriptionException, MissingParameterException {
        Command newCommand;
        String[] tokenizedInput = userInput.split(WHITESPACE_DELIMITER);
        String commandKeyword = tokenizedInput[0];
        switch (commandKeyword.toLowerCase()) {
        case (BYE_COMMAND):
        case (LIST_COMMAND):
        case (HELP_COMMAND):
            if (commandKeyword.equals(BYE_COMMAND)) {
                isExitCommandInvoked = true;
            }
            newCommand = new Command(commandKeyword);
            break;
        case(DONE_COMMAND):
        case(DELETE_COMMAND):
        case(FIND_COMMAND):
            try {
                newCommand = new Command(commandKeyword, tokenizedInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingParameterException(
                        messageContainer.executeCommandInsufficientParameterErrorMessage(commandKeyword));
            }
            break;
        case (TODO_COMMAND):
        case (EVENT_COMMAND):
        case (DEADLINE_COMMAND):
            String[] separatedDescriptionRemarksInput = splitUserInput(userInput);
            newCommand = new Command(commandKeyword, tokenizedInput, separatedDescriptionRemarksInput);
            break;
        default:
            throw new IllegalKeywordException(INVALID_COMMAND_ERROR_MESSAGE);
        }
        return newCommand;
    }

    /**
     * This method attempts to split the user input into substrings containing the description and remarks (if applicable) of the Task.
     * <p></p>
     * <p>It is only applicable for the TODO, COMMAND and DEADLINE commands.</p>
     * @param originalInput
     * @return a String array of size 2, containing the description and remarks for creation of a Task. If the Task is a
     * @throws MissingParameterException this exception occurs if either the description or remarks field is not detected (ie. the amount of substrings is lesser than expected)
     * @throws NoDescriptionException this exception occurs if the description field (the section before the " /") of the Task is empty
     * @throws NoRemarkException this exception occurs if the remarks field (the section after the " /") of the Task is empty
     * @throws IllegalKeywordException this exception occurs if the command keyword in the input is not TODO/EVENT/DEADLINE
     */
    private String[] splitUserInput(String originalInput) throws MissingParameterException,
            NoDescriptionException, NoRemarkException, IllegalKeywordException {
        String[] returnValue = new String[MAX_SUBSTRING_FIELDS];
        if (originalInput.contains(REMARKS_DELIMITER)){
            String[] separatedSections = originalInput.split(REMARKS_DELIMITER);
            String commandWord = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[0];
            //todo should not have a remark section
            if (commandWord.toLowerCase().equals(TODO_COMMAND)) {
                throw new IllegalKeywordException(TODO_HAS_REMARK_SECTION_ERROR_MESSAGE);
            }

            if (Integer.valueOf(separatedSections.length).equals(Integer.valueOf(1))){
                throw new MissingParameterException(executeCommandInsufficientParameterErrorMessage(commandWord));
            }

            // get description part of userInput without the command word
            try{
                returnValue[0] = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingParameterException(messageContainer.executeCommandInsufficientParameterErrorMessage(commandWord));
            }

            if (returnValue[0].trim().length() == 0) {
                throw new NoDescriptionException(messageContainer.addTaskEmptyDescriptionErrorMessage(commandWord));
            }
            // get additional remark part of userInput
            returnValue[1] = separatedSections[1];
            boolean isRemarksEmpty = ((commandWord.toLowerCase().equals(EVENT_COMMAND)
                    || commandWord.toLowerCase().equals(DEADLINE_COMMAND))  && returnValue[1].trim().length() == 0);
            if (isRemarksEmpty){
                throw new NoRemarkException(messageContainer.addTaskEmptyRemarksErrorMessage(commandWord));
            }
            return returnValue;
        } else {
            // get description part of userInput without the command word
            String[] separatedSections = originalInput.split(WHITESPACE_DELIMITER);
            String commandWord = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[0];
            if (Integer.valueOf(separatedSections.length).equals(Integer.valueOf(1))) {
                throw new MissingParameterException(messageContainer.executeCommandInsufficientParameterErrorMessage(commandWord));
            }
            //similar to above, event and deadline should not be missing a description section
            if (commandWord.toLowerCase().equals(EVENT_COMMAND) || commandWord.toLowerCase().equals(DEADLINE_COMMAND)) {
                throw new IllegalKeywordException(messageContainer.splitInputEventOrDeadlineMissingRemarksErrorMessage(commandWord));
            }
            returnValue[0] = originalInput.trim().split(WHITESPACE_DELIMITER, 2)[1];

            // remark column is null (for todo, it won't be used)
            returnValue[1] = null;
            return returnValue;
        }
    }

    public boolean exitCommandNotEncountered(){
        return !this.isExitCommandInvoked;
    }
}
