package duke;

import duke.exception.*;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class used to parse the user input. Helps check for error as well as formatting the input for the {@link Command}
 * class to use
 * <p></p>
 * <p>
 * When an error is found, it will throw the respective errors given
 * </p>
 */
public class Parser {
    public static final int TYPE_OF_COMMAND = 0;
    public static final int TASK_NUMBER = 1;
    public static final String FIND = "find";
    public static final int MISSING_SLASH = 3;
    public static final String BYE = "bye";
    public static final String YES = "yes";
    public static final String EMPTY = "";
    public static final String WRONG = "wrong";
    public static final int SLASH_WORD = 4;
    public static final String ON = "on";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final int DESCRIPTION = 0;
    private static final int TIME = 1;
    private static final int TIME_INCLUDED = 2;
    public static final String BY = "by";
    public static final String AT = "at";


    public Parser() {
    }


    /**
     * Parse the user input. It formats the user input so that it transform the user input (the task) to becomes a valid
     * executable command. Likewise, if there is any error in the user input, it will throw an exception and explain
     * what the error in the user input is .
     * @param userInput the raw user input
     * @return the formatted command stored in a {@link Command} class
     * @throws InvalidTaskException        exception thrown when the task given is not a valid task and hence not a
     *                                     valid command
     * @throws MissingDescriptionException exception thrown when the task given has no description attached to it
     *                                     despite expecting it
     * @throws MissingNumberFieldException exception thrown when the task given lacks a number field. Only used for
     *                                     <code>DELETE</code> and <code>DONE</code> commands
     * @throws MissingTimeFieldException   exception thrown when no date or time given despite expecting it
     * @throws MissingSlashWordException   exception thrown when no slash word is given despite expecting it
     * @throws DateTimeException           exception thrown when the date given cannot be parsed
     * @throws WrongSlashWordException     exception thrown when the slash word provided is not correct. Only used for
     *                                     <code>EVENT</code> and <code>DEADLINE</code>
     * @see Command
     */
    public Command parseUserInput(String userInput)
            throws InvalidTaskException, MissingDescriptionException, MissingNumberFieldException,
            MissingTimeFieldException, DateTimeException, MissingSlashWordException, WrongSlashWordException {
        Command command = new Command();

        /* Format and split up the user input task so as to be checked by the validateTask method */
        String[] spaceUserInputSplit = splitInputBySpace(userInput);
        String[] partialUserInputSplit = splitInputIntoTaskDescription(userInput);

        validateTask(spaceUserInputSplit, partialUserInputSplit);

        /* Once validated, format and prepare the command itself to be executed */
        command.setFullCommand(userInput);
        command.setSplitCommand(spaceUserInputSplit);
        command.setTypeOfCommand(spaceUserInputSplit[TYPE_OF_COMMAND]);
        command.setDescriptionOfCommand(partialUserInputSplit[DESCRIPTION]);
        command.setTimeOfCommand(partialUserInputSplit[TIME]);
        if (spaceUserInputSplit[TYPE_OF_COMMAND].toLowerCase().equals(DONE) ||
                spaceUserInputSplit[TYPE_OF_COMMAND].toLowerCase().equals(DELETE)) {
            command.setNumber(partialUserInputSplit[DESCRIPTION]);
        }
        return command;
    }

    /**
     * Check to see if the user input obtained does not equal to "bye". If it is "bye", it means the user wants to exit
     * duke
     * <p></p>
     * <p>
     * Only used during the Ui loop to check if the user wants to exit
     * </p>
     * @param userInput the raw user input
     * @return <code>TRUE</code> if user wants to exit (user input "bye"), else <code>FALSE</code>
     */
    public boolean checkIsNotBye(String userInput) {
        return !userInput.equals(BYE);
    }


    /**
     * Split the array up by spaces.
     * <p></p>
     * <p>
     * Only usage is just to get the type of command
     * </p>
     * @param userInput the raw user input
     * @return the <code>String[]</code> array with the split user input
     */
    private String[] splitInputBySpace(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    /**
     * Help split up the user input so as to separate the description and time of the task in the user's raw input,
     * which makes it easier to use for command creation.
     * <p></p>
     * <p>
     * Not only that, the array also contains addition information such as whether if the time or slash word is
     * missing for input that expects them. It also checks if the slash word provided is correct.
     * </p>
     * @param input the user input
     * @return a <code>String[]</code> array which contains the separated user input.
     */
    private String[] splitInputIntoTaskDescription(String input) {

        /* Get the task type */
        String[] obtainedDescription = input.split(" ", 2);
        String tempTaskType = obtainedDescription[0];

        /* If an empty string is given */
        if (tempTaskType.isBlank()) {
            return formatEmptyInput();
        }

        /* For the user input task that supposed to contain time/date, format the input and return the array */
        if (tempTaskType.toLowerCase().equals("event") || tempTaskType.toLowerCase().equals("deadline")) {
            return formatInputIntoArray(input);
        }

        /* For the user input task that do not expect to contain time/date, format the input and return the array */
        return formatNoDateInputIntoArray(input);
    }

    /**
     * For the raw input that contains nothing
     * @return an empty array to denote there is nothing in the input
     */
    private String[] formatEmptyInput() {
        String[] returnSplit = new String[5];
        returnSplit[DESCRIPTION] = EMPTY;
        returnSplit[TIME] = EMPTY;
        returnSplit[TIME_INCLUDED] = EMPTY;
        returnSplit[MISSING_SLASH] = EMPTY;
        returnSplit[SLASH_WORD] = EMPTY;
        return returnSplit;
    }


    /**
     * To split and format the raw input that do not contain any date into an array to be returned.
     * @param input the raw user input
     * @return a <code>String[]</code> array which contains the separated user input.
     */
    private String[] formatNoDateInputIntoArray(String input) {

        /* The array to be used to store the input and be returned */
        String[] returnSplit = new String[5];

        /* Split the input into task type and the full description to be checked later */
        String[] obtainedDescription = input.split(" ", 2);

        /* Placing the description into the array */
        if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
            returnSplit[DESCRIPTION] = EMPTY;
        } else {
            returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
        }

        /* Placing the leftover details into the array */
        returnSplit[TIME] = EMPTY;
        returnSplit[TIME_INCLUDED] = EMPTY;
        returnSplit[MISSING_SLASH] = EMPTY;
        returnSplit[SLASH_WORD] = EMPTY;
        return returnSplit;
    }

    /**
     * To split and format the raw input that expects and contains the date into an array to be returned.
     * @param input the raw user input
     * @return a <code>String[]</code> array which contains the separated user input.
     */
    private String[] formatInputIntoArray(String input) {

        /* The array to be used to store the input and be returned */
        String[] returnSplit = new String[5];

        /* Obtain the description based on the input given */
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);


        /* Placing the description into the array as well as the time */
        if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
            returnSplit[DESCRIPTION] = EMPTY;
            returnSplit[TIME] = EMPTY;
        } else {
            returnSplit[DESCRIPTION] = obtainedDescription[1].trim();

            /* Check for missing slash word */
            if (obtainedSplit.length == 1 || Character.isWhitespace(obtainedSplit[1].charAt(0))) {
                returnSplit[MISSING_SLASH] = YES;
                returnSplit[TIME_INCLUDED] = YES;
                returnSplit[TIME] = EMPTY;
                returnSplit[SLASH_WORD] = EMPTY;
                return returnSplit;
            }

            String[] slashWordTimeArray = obtainedSplit[1].trim().split(" ", 2);

            /* Check for the correctness of slash word */
            if (obtainedDescription[0].toLowerCase().equals(DEADLINE) &&
                    !slashWordTimeArray[0].toLowerCase().equals(BY)) {
                returnSplit[MISSING_SLASH] = WRONG;
            } else if (obtainedDescription[0].toLowerCase().equals(EVENT) &&
                    !(slashWordTimeArray[0].toLowerCase().equals(ON) ||
                            slashWordTimeArray[0].toLowerCase().equals(AT))) {
                returnSplit[MISSING_SLASH] = WRONG;
            } else {
                returnSplit[MISSING_SLASH] = EMPTY;
            }

            /* Get the time in the input given */
            returnSplit[TIME] = obtainedSplit[1].trim();
            returnSplit[TIME_INCLUDED] = YES;
        }
        return returnSplit;
    }

    /**
     * The actual method used to check the task input by the user. If there is any error in the user input, it will
     * throw the respective exception calls
     * @param spaceUserInputSplit   the <code>String[]</code> array which contains the user input split by space (so as
     *                              to
     *                              obtain the type of command)
     * @param partialUserInputSplit the <code>String[]</code> array which contains the user input split into its
     *                              description
     *                              and time/date
     * @throws InvalidTaskException        exception thrown when the task given is not a valid task and hence not a
     *                                     valid command
     * @throws MissingDescriptionException exception thrown when the task given has no description attached to it
     *                                     despite expecting it
     * @throws MissingNumberFieldException exception thrown when the task given lacks a number field. Only used for
     *                                     <code>DELETE</code> and <code>DONE</code> commands
     * @throws MissingTimeFieldException   exception thrown when no date or time given despite expecting it
     * @throws MissingSlashWordException   exception thrown when no slash word is given despite expecting it
     * @throws DateTimeException           exception thrown when the date given cannot be parsed
     * @throws WrongSlashWordException     exception thrown when the slash word provided is not correct. Only used for
     *                                     <code>EVENT</code> and <code>DEADLINE</code>
     */
    private void validateTask(String[] spaceUserInputSplit, String[] partialUserInputSplit)
            throws InvalidTaskException, MissingDescriptionException, MissingNumberFieldException,
            MissingTimeFieldException, DateTimeException, MissingSlashWordException, WrongSlashWordException {

        String nameOfTask = spaceUserInputSplit[TYPE_OF_COMMAND].toLowerCase();

        /* Check if task type is supported by Duke */
        if (!nameOfTask.equals(TODO) && !nameOfTask.equals(EVENT) && !nameOfTask.equals(DEADLINE) &&
                !nameOfTask.equals(DONE) && !nameOfTask.equals(LIST) && !nameOfTask.equals(DELETE) &&
                !nameOfTask.equals(FIND)) {

            /* Task is not a valid/supported task */
            throw new InvalidTaskException(Ui.displayInputInvalidError());

        } else if ((nameOfTask.toLowerCase().equals(DONE) || nameOfTask.toLowerCase().equals(DELETE)) &&
                spaceUserInputSplit.length == 1) {

            /* DELETE or DONE is missing the number field */
            throw new MissingNumberFieldException(Ui.displayMissingNumberFieldError(nameOfTask));

        } else if ((nameOfTask.equals(TODO) || nameOfTask.equals(EVENT) || nameOfTask.equals(DEADLINE) ||
                nameOfTask.equals(FIND))) {
            if (checkIsDescriptionBlank(partialUserInputSplit)) {

                /* The task is missing its description */
                throw new MissingDescriptionException(Ui.displayMissingDescriptionError());
            }
            if (partialUserInputSplit[TIME_INCLUDED].equals(YES)) {

                /* Test for the correct slash word */
                testSlashWord(partialUserInputSplit);

                String obtainedTime = partialUserInputSplit[1];
                String[] timeCheck = obtainedTime.split(" ", 2);

                /* Test if the date is present and if so, if the date is formatted correctly */
                testParseDate(timeCheck);

            }

        }
    }

    /**
     * Helper method to test and check if the input is missing or have the wrong slash word
     * @param partialUserInputSplit the <code>String[]</code> array which contains the user input split into its
     *                              description and time/date
     * @throws MissingSlashWordException exception thrown when no slash word is given despite expecting it
     * @throws WrongSlashWordException   exception thrown when the slash word given is wrong
     */
    private void testSlashWord(String[] partialUserInputSplit)
            throws MissingSlashWordException, WrongSlashWordException {
        if (partialUserInputSplit[MISSING_SLASH].equals(YES)) {
            throw new MissingSlashWordException(Ui.displayMissingSlashWordError());
        }
        if (partialUserInputSplit[MISSING_SLASH].equals(WRONG)) {
            throw new WrongSlashWordException(Ui.displayWrongSlashWord());
        }
    }


    /**
     * Helper method to test and check if the input date is missing (if duke expects it) or is formatted wrongly
     * @param timeCheck the supposed string that should contain a date itself
     * @throws MissingTimeFieldException exception thrown when no date given despite expecting it
     * @throws DateTimeException         exception thrown when the date given cannot be parsed
     */
    private void testParseDate(String[] timeCheck)
            throws MissingTimeFieldException, DateTimeException {
        if (timeCheck.length == 1 || timeCheck[1].isBlank()) {
            throw new MissingTimeFieldException(Ui.displayMissingTimeError());
        }
        try {
            LocalDate d1 = LocalDate.parse(timeCheck[1].trim());

        } catch (DateTimeParseException m) {
            throw new DateTimeException(
                    Ui.displayDateUnableToBeParsedError());
        }
    }


    /**
     * Check if the description given the partial input array contains any description
     * @param partialDescriptionTaskArray the <code>String[]</code> array that contains the partial input
     * @return true if the description is blank. Else false
     */
    private boolean checkIsDescriptionBlank(String[] partialDescriptionTaskArray) {
        return partialDescriptionTaskArray[0].isBlank();
    }
}
