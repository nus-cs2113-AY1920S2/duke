package duke.parser;

import duke.common.ExceptionMessage;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.commands.*;


public class Parser {
    
    public static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
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
        default:
            return new InvalidCommand();
        }
    }
    
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
    
    private Command parseDeleteCommand(String argument) {
        return new DeleteCommand(argument);
    }
    
    private Command parseDoneCommand(String argument) {
        return new DoneCommand(argument);
    }
    
    public CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            Ui.printToConsole(e.toString());
            throw new RuntimeException(e);
        }
    }
    
    private static void hasEmptyDescription(String commandWord, String commandArgs) throws DukeException {
        if ((commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_TODO) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_DEADLINE) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_EVENT)) && commandArgs.equals("")) {
            throw new DukeException(String.format(ExceptionMessage.MISSING_DESCRIPTION_MESSAGE_EXCEPTION, commandWord));
        }
    }
    
    private static void hasEmptyDate(String commandWord, String commandDate) throws DukeException {
        if ((commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_DEADLINE) ||
                commandWord.equalsIgnoreCase(AddCommand.COMMAND_WORD_EVENT)) && commandDate.equals("")) {
            throw new DukeException(String.format(ExceptionMessage.MISSING_DATE_MESSAGE_EXCEPTION, commandWord));
        }
    }
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    public static String[] splitInputLine(String rawUserInput, String regex) {
        String[] split = rawUserInput.trim().split(regex, 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else no parameters
    }
    
}
    
