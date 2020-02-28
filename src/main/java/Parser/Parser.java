package Parser;

import Command.Command;
import Command.ListCommand;
import Command.FailedCommand;
import Command.DoneCommand;
import Command.HelpCommand;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;

import Exceptions.NoParameterException;
import Exceptions.EmptyListException;
import org.w3c.dom.ls.LSOutput;

import java.security.spec.RSAOtherPrimeInfo;

public class Parser {

    private static final int SIZE_DONE_COMMAND = 2;
    private static final int SIZE_DELETE_COMMAND = 2;
    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';

    public static Command parseCommand(String userCommand) {

            String[] words = userCommand.split(" ");
            int wordLength = words.length;

            switch (words[0]) {
            case "list":
                return new ListCommand();
                //return printList(tasks);
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

            case "find":
                return new FindCommand(words[1]);

            case "bye":
                return new ExitCommand();

            default:
                System.out.println("Command not recognised\n");
                return new HelpCommand();
            }
    }

    private static Command prepareDeleteCommand(String word, int wordLength) {
        if (wordLength != SIZE_DELETE_COMMAND) {
            String errorMessage = "Wrong format for command \"Delete\"\n";
            return new FailedCommand(errorMessage);
        }
        //try {
            int index = Integer.parseInt(word);
            return new DeleteCommand(index);
            /*
        } catch (NumberFormatException e) {
            String errorMessage = "Please input a valid number\n";
            return new FailedCommand(errorMessage);
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "Task not found, please try again\n";
            return new FailedCommand(errorMessage);
        } catch (EmptyListException e) {
            String errorMessage = "List is empty";
            return new FailedCommand(errorMessage);
        }
             */
    }

    private static Command prepareDoneCommand(String word, int wordLength) {
        if (wordLength != SIZE_DONE_COMMAND) {
            String errorMessage = "Wrong format for command \"done\"";
            return new FailedCommand(errorMessage);
        }
       // try {
            int index = Integer.parseInt(word);
            return new DoneCommand(index);
            /*
        } catch (NumberFormatException e) {
            String errorMessage = "Please input a valid number\n";
            return new FailedCommand(errorMessage);
        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMessage = "Duke.Task not found, please try again";
            return new FailedCommand(errorMessage);
        }

             */
    }

}
