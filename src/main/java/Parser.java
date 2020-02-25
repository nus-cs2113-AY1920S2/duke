import common.Messages;
import data.*;
import tasklist.TaskList;

import static common.Messages.*;

public class Parser {

    public static final int MAX_SUBSTRING_FIELDS = 2;
    public boolean isExitCommandInvoked = false;
    private TaskList taskList;
    private Messages messageContainer = new Messages();

    public Parser(TaskList taskListInput) {
        this.taskList = taskListInput;
    }

    //command to validate task
    public Command parseCommand(String userInput) throws NoRemarkException, IllegalKeywordException,
            NoDescriptionException, MissingParameterException {

        Command newCommand;

        String[] tokenizedInput = userInput.split(WHITESPACE_DELIMITER);
        String commandKeyword = tokenizedInput[0];
        switch (commandKeyword.toLowerCase()) {
        case (BYE_COMMAND):
        case (LIST_COMMAND):
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
                //throw MissingParameterException if any of the commands given without additional par
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

    public String[] splitUserInput(String originalInput) throws MissingParameterException,
            NoDescriptionException, NoRemarkException, IllegalKeywordException {
        String[] returnValue = new String[MAX_SUBSTRING_FIELDS];
        if (originalInput.contains(REMARKS_DELIMITER)){
            String[] separatedSections = originalInput.split(REMARKS_DELIMITER);
            String commandWord = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[0];
            //todo should not have a remark section
            if (commandWord.toLowerCase().equals(TODO_COMMAND)) {
                throw new IllegalKeywordException(TODO_HAS_REMARK_SECTION_ERROR_MESSAGE);
            }

            try {
                String testForMissingRemarks = separatedSections[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingParameterException(executeCommandInsufficientParameterErrorMessage(commandWord));
            }

            // get description part of userInput without the command word
            try{
                returnValue[0] = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingParameterException(messageContainer.executeCommandInsufficientParameterErrorMessage(commandWord));
            }

            if (returnValue[0].trim().length() == 0) {
                throw new NoDescriptionException(/*description is empty message*/messageContainer.addTaskEmptyDescriptionErrorMessage(commandWord));
            }
            // get additional remark part of userInput
            returnValue[1] = separatedSections[1];
            boolean isRemarksEmpty = ((commandWord.toLowerCase().equals(EVENT_COMMAND)
                    || commandWord.toLowerCase().equals(DEADLINE_COMMAND))  && returnValue[1].trim().length() == 0);
            if (isRemarksEmpty){
                throw new NoRemarkException(/*remarks is empty message*/messageContainer.addTaskEmptyRemarksErrorMessage(commandWord));
            }
            return returnValue;
        } else {
            // get description part of userInput without the command word
            String[] separatedSections = originalInput.split(WHITESPACE_DELIMITER);
            String commandWord = separatedSections[0].split(WHITESPACE_DELIMITER, 2)[0];
            //only the keyword input
            if (Integer.valueOf(separatedSections.length).equals(Integer.valueOf(1))) {
                throw new MissingParameterException(messageContainer.executeCommandInsufficientParameterErrorMessage(commandWord));
            }
            //similar to above, event and deadline should not be missing a description section
            if (commandWord.toLowerCase().equals(EVENT_COMMAND) || commandWord.toLowerCase().equals(DEADLINE_COMMAND)) {
                throw new IllegalKeywordException(/*event or deadline should have remarks section msg*/messageContainer.splitInputEventOrDeadlineMissingRemarksErrorMessage(commandWord));
            }
            returnValue[0] = originalInput.trim().split(WHITESPACE_DELIMITER, 2)[1];

            // remark column is an empty string
            returnValue[1] = "";
            return returnValue;
        }
    }

    public boolean exitCommandNotEncountered(){
        return !this.isExitCommandInvoked;
    }
}
