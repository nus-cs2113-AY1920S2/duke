package duke.parser;

import duke.commands.*;
import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.exceptions.WhitespaceExceptions;

import java.util.Scanner;

public class Parser {

    public Command parse(Scanner myScanner, String function) {
        Command command = null;
        String restOfInput;
        try {
            switch (function) {
                case "todo":
                    restOfInput = myScanner.nextLine();
                    command = new TodoCommand(restOfInput);
                    break;

                case "deadline":
                    restOfInput = myScanner.nextLine();
                    command = new DeadlineCommand(restOfInput);
                    break;

                case "event":
                    restOfInput = myScanner.nextLine();
                    command = new EventCommand(restOfInput);
                    break;

                case "bye":
                    command = new ExitCommand();
                    break;

                case "list":
                    command = new ListCommand();
                    break;

                case "done":
                    restOfInput = myScanner.nextLine();
                    command = new DoneCommand(restOfInput);
                    break;

                case "delete":
                    restOfInput = myScanner.nextLine();
                    command = new DeleteCommand(restOfInput);
                    break;

                case "reset":
                    command = new ResetCommand();
                    break;

                case "find":
                    restOfInput = myScanner.nextLine();
                    command = new FindCommand(restOfInput);
                    break;

                case "help":
                    command = new HelpCommand();
                    break;

                default:
                    //loop till valid function entered
                    //String redundant = myScanner.nextLine();
                    System.out.println("Please key in a valid function");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS! Error storing date and time!! Please try again.");
        } catch (NullPointerException e) {
            System.out.println("OH NO! task number is missing!! Please try storing a task first.");
        } catch (IllegalArgumentException e) {
            System.out.println("OOPS! Missing command! Please try command: done [task number] ");
        }

        return command;
    }
}
