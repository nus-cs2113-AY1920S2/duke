package Duke;

import Duke.Exception.InvalidTaskException;
import Duke.Exception.MissingDescriptonException;
import Duke.Exception.MissingNumberFieldException;
import Duke.Exception.MissingTimeFieldException;

/**
 * Class used to parse the user input. Helps check for error as well as formatting the input for the {@link Command} class to use
 * <p></p>
 * <p>
 *     When an error is found, it will throw the respective errors given
 * </p>
 */
public class Parser {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final int DESCRIPTION = 0;
    private static final int TIME = 1;
    private static final int TIME_INCLUDED = 2;
    public static final int TYPE_OF_COMMAND = 0;
    public static final int TASK_NUMBER = 1;

    public Parser() {
    }

    /**
     * Split the array up by spaces.
     * <p></p>
     * <p>
     *     Only usage is just to get the type of command
     * </p>
     * @param userInput the raw user input
     * @return the <code>String[]</code> array with the split user input
     */
    private static String[] splitTheUserInput(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    private static boolean isDescriptionBlank(String[] strings) {
        return strings[0].isBlank();
    }

    /**
     * Help split up the user input (without the type of command) so as to separate the different parts of the user input into an array. This makes it easier for the command creation
     * @param input the user input without the type of command (<code>TODO,DEADLINE,DONE</code> etc...) in front of it
     * @return a <code>String[]</code> array which contains the separated user input.
     */
    private static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[3];

        // For the user input task that do not contain time/date
        if (!input.contains("/")) {
            String[] obtainedDescription = input.split(" ", 2);

            // Placing the description into the array
            if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
                returnSplit[DESCRIPTION] = "";
            } else {
                returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
            }

            //Placing the leftover details into the array
            returnSplit[TIME] = "";
            returnSplit[TIME_INCLUDED] = "";
            return returnSplit;
        }

        // For the user input task that supposed to contain time/date
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);

        // Placing the description into the array as well as the time
        if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
            returnSplit[DESCRIPTION] = "";
            returnSplit[TIME] = "";
        } else {
            returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
            returnSplit[TIME] = obtainedSplit[1].trim();
            returnSplit[TIME_INCLUDED] = "yes";
        }
        return returnSplit;
    }

    /**
     * The actual method used to check the task input by the user. If there is any error in the user input, it will throw the respective exception calls
     * @param splitUserInput the <code>String[]</code> array which contains the user input split by space (so as to obtain the type of command)
     * @param fullUserSplit the <code>String[]</code> array which contains the user input split into its description and time/date
     * @throws InvalidTaskException exception thrown when the task given is not a valid task and hence not a valid command
     * @throws MissingDescriptonException exception thrown when the task given has no description attached to it despite expecting it
     * @throws MissingNumberFieldException exception thrown when the task given lacks a number field. Only used for <code>DELETE</code> and <code>DONE</code> commands
     * @throws MissingTimeFieldException exception thrown when no date or time given despite expecting it
     */
    private static void validateTask(String[] splitUserInput, String[] fullUserSplit)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {

        String nameOfTask = splitUserInput[0].toLowerCase();

        // Check if task type is supported by Duke
        if (!nameOfTask.equals(TODO)
                && !nameOfTask.equals(EVENT)
                && !nameOfTask.equals(DEADLINE)
                && !nameOfTask.equals(DONE)
                && !nameOfTask.equals(LIST)
                && !nameOfTask.equals(DELETE)) {
            throw new InvalidTaskException("Input is invalid. No such task");
        } else {
            // Check if the task has correct input
            if ((nameOfTask.toLowerCase().equals(DONE)
                    || nameOfTask.toLowerCase().equals(DELETE)) && splitUserInput.length == 1) {
                throw new MissingNumberFieldException(nameOfTask.toUpperCase() + "'s number field is empty!");
            } else if (isDescriptionBlank(fullUserSplit) && !nameOfTask.equals(LIST)) {
                throw new MissingDescriptonException("Missing description!");
            } else if (nameOfTask.equals(TODO)
                    || nameOfTask.equals(EVENT)
                    || nameOfTask.toLowerCase().equals(DEADLINE)) {
                if (fullUserSplit[2].equals("yes")) {
                    String obtainedTime = fullUserSplit[1];
                    String[] timeCheck = obtainedTime.split(" ", 2);
                    if (timeCheck.length == 1 || timeCheck[1].isBlank()) {
                        throw new MissingTimeFieldException("Missing time or missing slash word!");
                    }
                }

            }
        }
    }

    /**
     * Parse the user input. It formats the user input so that it transform the user input (the task) to becomes a valid executable command. Likewise, if there is any error in the user input, it will throw an exception and explain what the error in the user input is .
     * @param userInput the raw user input
     * @return the formatted command stored in a {@link Command} class
     * @throws InvalidTaskException exception thrown when the task given is not a valid task and hence not a valid command
     * @throws MissingDescriptonException exception thrown when the task given has no description attached to it despite expecting it
     * @throws MissingNumberFieldException exception thrown when the task given lacks a number field. Only used for <code>DELETE</code> and <code>DONE</code> commands
     * @throws MissingTimeFieldException exception thrown when no date or time given despite expecting it
     * @see Command
     */
    public Command parseUserInput(String userInput)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {
        Command command = new Command();

        //Format and split up the user input task so as to be checked by the validateTask method
        String[] splitUserInput = splitTheUserInput(userInput);
        String[] fullUserSplit = splitTaskDescription(userInput);

        validateTask(splitUserInput, fullUserSplit);

        // Once validated, format and prepare the command itself to be executed
        command.setFullCommand(userInput);
        command.setSplitCommand(splitUserInput);
        command.setTypeOfCommand(splitUserInput[TYPE_OF_COMMAND]);
        command.setDescriptionOfCommand(fullUserSplit[DESCRIPTION]);
        command.setTimeOfCommand(fullUserSplit[TIME]);
        if (splitUserInput[0].toLowerCase().equals(DONE) || splitUserInput[0].toLowerCase().equals(DELETE)) {
            command.setNumber(splitUserInput[TASK_NUMBER]);

        }
        return command;
    }

    /**
     * Check to see if the user input obtained does not equal to "bye". If it is "bye", it means the user wants to exit duke
     * <p></p>
     * <p>
     *     Only used during the Ui loop to check if the user wants to exit
     * </p>
     * @param userInput the raw user input
     * @return <code>TRUE</code> if user wants to exit (user input "bye"), else <code>FALSE</code>
     */
    public boolean isNotBye(String userInput) {
        return !userInput.equals("bye");
    }
}
