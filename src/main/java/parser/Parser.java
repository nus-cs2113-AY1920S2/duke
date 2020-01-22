package parser;


import commands.*;
import data.Task;

import java.util.Scanner;
import java.util.regex.Matcher;


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

        //split the user input, command word and the description
        final String commandWord = userInput;
        final String []commandWordFirstPart = commandWord.split(" ");
        switch (commandWordFirstPart[0]){
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
            return prepareAdd(commandWord);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord full user input string
     * @return the AddCommand obj constructed on the user input
     */
    private Command prepareAdd(String commandWord) {
        return new AddCommand(new Task(commandWord));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord full user input string
     * @return the DoneCommand obj constructed on the user input
     */
    private Command prepareDone (String commandWord) {
        return new DoneCommand(Integer.valueOf(commandWord.substring(5)));
    }

}
