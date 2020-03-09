package Parser;

import Command.Command;
import Command.ListCommand;
import Command.FailedCommand;
import Command.DoneCommand;
import Command.HelpCommand;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;

/**
 * Parses user input
 */
public class Parser {

    private static final int SIZE_DONE_COMMAND = 2;
    private static final int SIZE_DELETE_COMMAND = 2;
    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';

    /**
     * Parses user input into command for execution
     *
     * @param userCommand user input including command word and parameters
     * @return  command based on user input
     */
    public static Command parseCommand(String userCommand) {

            String[] words = userCommand.split(" ");
            int wordLength = words.length;

            switch (words[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return prepareDoneCommand(words[1], wordLength);

            case "help":
                return new HelpCommand();

            case "delete":
                return prepareDeleteCommand(words[1], wordLength);

            case "todo":
                return new AddCommand(userCommand, wordLength, TASK_TODO);

            case "event":
                return new AddCommand(userCommand, wordLength, TASK_EVENT);

            case "deadline":
                return new AddCommand(userCommand, wordLength, TASK_DEADLINE);

            case "find":
                return new FindCommand(words[1]);

            case "bye":
                return new ExitCommand();

            default:
                System.out.println("Command not recognised\n");
                return new HelpCommand();
            }
    }

    /**
     * Auxiliary function for delete command
     * validates input before parsing
     *
     * @param wordIndex index of task to be processed
     * @param wordLength length of user input for validation
     * @return command to execute
     */
    private static Command prepareDeleteCommand(String wordIndex, int wordLength) {
        if (wordLength != SIZE_DELETE_COMMAND) {
            String errorMessage = "Wrong format for command \"Delete\"\n";
            return new FailedCommand(errorMessage);
        }
            return new DeleteCommand(wordIndex);
    }

    /**
     * Auxiliary function for done command
     * validates input before parsing
     *
     * @param wordIndex index of task to be processed
     * @param wordLength length of user input for validation
     * @return command to execute
     */
    private static Command prepareDoneCommand(String wordIndex, int wordLength) {
        if (wordLength != SIZE_DONE_COMMAND) {
            String errorMessage = "Wrong format for command \"Done\"";
            return new FailedCommand(errorMessage);
        }
            return new DoneCommand(wordIndex);
    }

}
