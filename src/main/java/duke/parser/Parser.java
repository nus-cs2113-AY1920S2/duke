package duke.parser;

import duke.common.ExceptionMessage;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.commands.*;

/**
 * Parse user Input
 */
public class Parser {
    
    public static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
    /**
     * Parse user input into command for execution
     *
     * @param userInput full user Input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        
        String[] words = splitInputLine(userInput, SPILT_BY_SPACE);
        final String commandWord = words[0];
        final String arguments = words[1];
        
        switch (commandWord.toLowerCase()) {
        case AddCommand.COMMAND_WORD_TODO:
        case AddCommand.COMMAND_WORD_DEADLINE:
        case AddCommand.COMMAND_WORD_EVENT:
            return parseAddCommand(commandWord, arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return parseDoneCommand(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FindCommand.COMMAND_WORD:
            return parseFindCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }
    
    /**
     * Parse arguments in the context of add task command
     *
     * @param commandWord command word of the user input string
     * @param arguments   the parameters of the command string
     * @return the prepared command
     */
    private Command parseAddCommand(String commandWord, String arguments) {
        
        String[] paramAndDate = splitInputLine(arguments, SPILT_BY_SLASH);
        String commandArgs = paramAndDate[0].trim();
        String commandDate = paramAndDate[1].trim();
        try {
            hasEmptyDescription(commandWord, commandArgs);
            hasEmptyDate(commandWord, commandDate);
            switch (commandWord) {
            case AddCommand.COMMAND_WORD_TODO:
                return new AddCommand(commandWord, arguments);
            case AddCommand.COMMAND_WORD_DEADLINE:
            case AddCommand.COMMAND_WORD_EVENT:
                return new AddCommand(commandWord, commandArgs, commandDate);
            }
        } catch (DukeException e) {
            return new ExceptionCommand(e.toString());
        }
        return new ExceptionCommand("Task adding has ended in failure");
    }
    
    /**
     * Parse arguments in the context of delete task command
     *
     * @param argument the parameter of the command String
     * @return the prepared command
     */
    private Command parseDeleteCommand(String argument) {
        return new DeleteCommand(argument);
    }
    
    /**
     * Parse arguments in the context of done task command
     *
     * @param argument the parameter of the command String
     * @return the prepared command
     */
    private Command parseDoneCommand(String argument) {
        return new DoneCommand(argument);
    }
    
    private Command parseFindCommand(String argument) {
        return new FindCommand(argument);
    }
    
    /**
     * Execute the command in context with the command type passed in
     *
     * @param command the type of command
     * @return the result that is parse into CommandResult
     */
    public CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            Ui.printToConsole(e.toString());
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Check if the add task command has empty description
     *
     * @param commandWord command word of the user input string
     * @param commandArgs the parameters of the command string
     * @throws DukeException if the description is empty string
     */
    private static void hasEmptyDescription(String commandWord, String commandArgs) throws DukeException {
        if ((commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_TODO) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_DEADLINE) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_EVENT)) && commandArgs.equals("")) {
            throw new DukeException(String.format(ExceptionMessage.MISSING_DESCRIPTION_MESSAGE_EXCEPTION, commandWord));
        }
    }
    
    /**
     * Check if the add task command has empty date
     *
     * @param commandWord command word of the user input string
     * @param commandDate the parameters of the command string
     * @throws DukeException if the date is empty string
     */
    private static void hasEmptyDate(String commandWord, String commandDate) throws DukeException {
        if ((commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_DEADLINE) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_EVENT)) && commandDate.equals("")) {
            throw new DukeException(String.format(ExceptionMessage.MISSING_DATE_MESSAGE_EXCEPTION, commandWord));
        }
    }
    
    /**
     * Split the user input into two parts with a specific regex
     *
     * @param rawUserInput full user input string
     * @param regex        the quantifier to separate the string
     * @return an array of size 2 separated by the quantifier
     */
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    public static String[] splitInputLine(String rawUserInput, String regex) {
        String[] split = rawUserInput.trim().split(regex, 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else no parameters
    }
    
}
    
