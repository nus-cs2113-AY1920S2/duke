package Parser;

import Command.Command;
import Command.ListCommand;
import Command.FailedCommand;
import Command.DoneCommand;
import Command.HelpCommand;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;

/**
 * Parse user input
 */
public class Parser {

    private static final int SIZE_DONE_COMMAND = 2;
    private static final int SIZE_DELETE_COMMAND = 2;
    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';

    /**
     * Parses input into command for execution
     *
     * @param userCommand user input, including command word and corresponding parameters
     * @return Command based on user input
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
        case "bye":
            return new ExitCommand();
        default:
            System.out.println("[Error]: Command not recognised\n");
            return new HelpCommand();

        }
    }

    /**
     * Auxiliary function to validate user input
     *
     * @param wordIndex index of task to be deleted
     * @param wordLength length of command from user
     * @return command to be executed
     */
    private static Command prepareDeleteCommand(String wordIndex, int wordLength) {
        if (wordLength != SIZE_DELETE_COMMAND) {
            String errorMessage = "[Error][Delete]: Wrong format for command \"Delete\"\n";
            return new FailedCommand(errorMessage);
        }
        return new DeleteCommand(wordIndex);
    }

    /**
     * Auxiliary function to validate user input
     *
     * @param wordIndex index of task to be set complete
     * @param wordLength length of command from user
     * @return command to be executed
     */
    private static Command prepareDoneCommand(String wordIndex, int wordLength) {
        if (wordLength != SIZE_DONE_COMMAND) {
            String errorMessage = "[Error][Done]: Wrong format for command \"done\"";
            return new FailedCommand(errorMessage);
        }
        return new DoneCommand(wordIndex);
    }

}
