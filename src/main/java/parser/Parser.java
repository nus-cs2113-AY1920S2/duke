package parser;


import commands.*;
import data.task.Task;
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

        //add.todo task
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(commandWordDescription);
        //done
        case DoneCommand.COMMAND_WORD:
            return prepareDone(commandWord);
        //list
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        //exit
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        //add
        default:
            //return prepareAddTodo(commandWord);
            return null;
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord full user input string
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAddTodo(String commandWord) {
        return new AddTodoCommand(new TodoTask(commandWord));
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
