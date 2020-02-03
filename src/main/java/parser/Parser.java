package parser;


import commands.*;
import commands.add.AddDeadlineCommand;
import commands.add.AddEventCommand;
import commands.add.AddTodoCommand;
import common.Messages;
import data.exceptions.ParseException;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.TodoTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    /** index suffix for done and delete command */
    public static final int DELETE_INDEX = 7;
    public static final int DONE_INDEX = 5;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */

    /**
     * split the user input, command word and the description
     * commandWord stores the whole user input
     * commandWordFirstPart [0] stores the Command Word
     * description stores additional information
     */
    public Command parseCommand(String userInput) {

        final String commandWord = userInput;
        final String []commandWordFirstPart = commandWord.split(" ");

        /**
         * further split the user input, get the secondary part, the description
         */
        final String commandWordDescription = commandWord.substring(commandWordFirstPart[0].length());

        //operates according to different command word
        switch (commandWordFirstPart[0]){
        //find task
        case FindCommand.COMMAND_WORD:
            return prepareFind(commandWordDescription);
        //add event task
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventTask(commandWordDescription);
        //add deadline task
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineTask(commandWordDescription);
        //add todo task
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodoTask(commandWordDescription);
        //done
        case DoneCommand.COMMAND_WORD:
            return prepareDone(commandWord);
        //list
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(commandWord);
        //exit
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        //clear
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        //help and default
        case HelpCommand.COMMAND_WORD:
        default:
            return new HelpCommand();
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareFind(String commandDescription) {
        //
        return null;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddEventTask(String commandDescription) {
        //split the commandDescription to task and start time
        String [] temp = commandDescription.split("/");
        return new AddEventCommand(new EventTask(temp[0],temp[1]));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddDeadlineTask(String commandDescription) {
        //split the commandDescription to task and deadline
        String [] temp = commandDescription.split("/");
        return new AddDeadlineCommand(new DeadlineTask(temp[0],temp[1]));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandDescription full user input string (without the command Action)
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddTodoTask(String commandDescription) {
        return new AddTodoCommand(new TodoTask(commandDescription));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param args full user input string
     * @return the DoneCommand obj constructed on the user input
     */
    private Command prepareDone (String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args, DONE_INDEX);//get target index
            return new DoneCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        //return new DoneCommand(Integer.parseInt(commandWord.substring(5)));
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args, DELETE_INDEX);//get target index
            return new DeleteCommand(targetIndex); //delete
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args, int index) throws ParseException, NumberFormatException,
            StringIndexOutOfBoundsException{
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (args.length() < index) {
            throw new StringIndexOutOfBoundsException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return Integer.parseInt(args.substring(index));
    }

}
