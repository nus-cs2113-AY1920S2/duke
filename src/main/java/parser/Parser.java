package parser;


import commands.Command;
import commands.ExitCommand;
import commands.IncorrectCommand;

import java.util.Scanner;


/**
 * Parses user input.
 */
public class Parser {

    /**
     * encapsulate user's input into a Command object
     * input: user input, return : Command 泛型
     */

    public Command parseCommand(String userInput) {

        //split the user input, command word and the description
        final String commandWord = userInput;
        switch (commandWord){
        //exit
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        //echo
        default:
            return new IncorrectCommand(commandWord);
        }
    }

}
