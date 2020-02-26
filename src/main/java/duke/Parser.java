package duke;

import duke.command.*;
import duke.exception.UnknownInputException;

/**
 * Parser class for parsing user inputs.
 */
public class Parser {

    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";
    private static final String END_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";

    /**
     * Processes user inputs.
     * @param userInput Full command that is given by the user
     * @return Command object that corresponds to the input given by the user
     * @throws UnknownInputException If command does not match commands supported by Duke
     */
    public static Command parse(String userInput) throws UnknownInputException {
        String[] words = userInput.split(" ");
        if (words[0].equals(TODO_COMMAND)) {
            return new TodoCommand(userInput);
        } else if (words[0].equals(EVENT_COMMAND)) {
            return new EventCommand(userInput);
        } else if (words[0].equals(DEADLINE_COMMAND)) {
            return new DeadlineCommand(userInput);
        } else if (words[0].equals(LIST_COMMAND)) {
            return new ListCommand();
        } else if (words[0].equals(DELETE_COMMAND)) {
            return new DeleteCommand(userInput);
        } else if (words[0].equals(DONE_COMMAND)) {
            return new DoneCommand(userInput);
        } else if (words[0].equals(END_COMMAND)) {
            return new ByeCommand();
        } else if (words[0].equals(FIND_COMMAND)) {
            return new FindCommand(userInput);
        } else {
            throw new UnknownInputException("There is no such input!");
        }
    }
}
