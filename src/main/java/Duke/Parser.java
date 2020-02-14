package Duke;

import Duke.Exception.InvalidTaskException;
import Duke.Exception.MissingDescriptonException;
import Duke.Exception.MissingNumberFieldException;
import Duke.Exception.MissingTimeFieldException;

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
    public Parser() {
    }

    private static String[] splitTheUserInput(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    private static boolean isDescriptionBlank(String[] strings) {
        return strings[0].isBlank();
    }

    private static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[3];
        if (!input.contains("/")) {
            String[] obtainedDescription = input.split(" ", 2);
            if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
                returnSplit[DESCRIPTION] = "";
            } else {
                returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
            }
            returnSplit[TIME] = "";
            returnSplit[TIME_INCLUDED] = "";
            return returnSplit;
        }
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);
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

    private static void validateTask(String[] splitUserInput, String[] fullUserSplit)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {

        String nameOfTask = splitUserInput[0].toLowerCase();
        if (!nameOfTask.equals(TODO)
                && !nameOfTask.equals(EVENT)
                && !nameOfTask.equals(DEADLINE)
                && !nameOfTask.equals(DONE)
                && !nameOfTask.equals(LIST)
                && !nameOfTask.equals(DELETE)) {
            throw new InvalidTaskException("Input is invalid. No such task");
        } else {
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
                        throw new MissingTimeFieldException("Missing time!");
                    }
                }

            }
        }
    }

    public Command parseUserInput(String userInput)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {
        Command command = new Command();
        String[] splitUserInput = splitTheUserInput(userInput);
        String[] fullUserSplit = splitTaskDescription(userInput);
        validateTask(splitUserInput, fullUserSplit);
        command.setFullCommand(userInput);
        command.setSplitCommand(splitUserInput);
        command.setTypeOfCommand(splitUserInput[0]);
        command.setDescriptionOfCommand(fullUserSplit[DESCRIPTION]);
        command.setTimeOfCommand(fullUserSplit[TIME]);
        if (splitUserInput[0].toLowerCase().equals(DONE) || splitUserInput[0].toLowerCase().equals(DELETE)) {
            command.setNumber(splitUserInput[1]);

        }
        return command;
    }

    public boolean isNotBye(String userInput) {
        return !userInput.equals("bye");
    }
}
