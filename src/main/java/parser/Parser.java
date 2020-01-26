package parser;


import commands.*;
import commands.add.AddDeadlineCommand;
import commands.add.AddEventCommand;
import commands.add.AddTodoCommand;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.TodoTask;


/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */

    public Command parseCommand(String userInput) {

        /**
         * split the user input, command word and the description
         * commandWord stores the whole user input
         * commandWordFirstPart [0] stores the Command Word
         * description stores additional information
         */
        final String commandWord = userInput;
        final String []commandWordFirstPart = commandWord.split(" ");

        /**
         * further split the user input, get the secondary part, the description
         */
        final String commandWordDescription = commandWord.substring(commandWordFirstPart[0].length());

        //operates according to different command word
        switch (commandWordFirstPart[0]){
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
     * @param commandWord full user input string
     * @return the DoneCommand obj constructed on the user input
     */
    private Command prepareDone (String commandWord) {
        return new DoneCommand(Integer.parseInt(commandWord.substring(5)));
    }

}
