package parser;


import commands.AddCommand;
import commands.Command;
import commands.ExitCommand;
import commands.IncorrectCommand;
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
        switch (commandWord){
        //exit
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        //add
        default:
            return prepareAdd(commandWord);
        }
    }

    private Command prepareAdd(String commandWord) {
        //System.out.println("succ");
        return new AddCommand(new Task(commandWord));
    }
}
