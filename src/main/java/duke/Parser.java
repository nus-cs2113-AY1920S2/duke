package duke;

import duke.command.Command;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeNullException;

import java.util.Arrays;

/**
 * The Parser class is in charged of parsing user input into a Command Object.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Parser {

    /**
     * Empty constructor for Parser.
     */
    public Parser() {
    }

    /**
     * Parse the user input into a Command Object based on the command keywords.
     * @param userCommand User input.
     * @return Command Object if command keyword, description and date is valid or DukeNullException otherwise.
     */
    public Command parseCommand(String userCommand) throws DukeArgumentException, DukeNullException {
        String commandKeyWord = getCommandKeyWord(userCommand);
        Command command;
        switch (commandKeyWord) {
        case "list":
            return parseListCommand(userCommand);
        /*case "done":
            //doneCommand(tasks, stringSplit);
            //taskList.doneTask(Integer.parseInt(stringSplit[1]) - 1);
            break;
        case "bye":
            //isBye = byeCommand(stringSplit, tasks);
            break;*/
        case "todo":
            return parseTodoCommand(userCommand);
        /*case "deadline":
            //deadlineCommand(tasks, userCommand);
            //taskList.addTask(new Deadline(userCommand.substring(0, userCommand.indexOf(" /by")).replace("deadline ", ""), userCommand.substring(userCommand.indexOf("/by ")).replace("/by ", "")));
            break;
        case "event":
            //eventCommand(tasks, userCommand);
            //taskList.addTask(new Event(userCommand.substring(0, userCommand.indexOf(" /at")).replace("event ", ""), userCommand.substring(userCommand.indexOf("/at ")).replace("/at ", "")));
            break;
        case "delete":
            //deleteCommand(tasks, stringSplit);
            //taskList.deleteTask(Integer.parseInt(stringSplit[1]) - 1);
            break;
        case "find":
            //findCommand(tasks, userCommand);
            break;*/
        case "help":
            return parseHelpCommand(userCommand);
        default:
            throw new DukeNullException("     :( OOPS!!! Command does not exist.");
        }
    }

    /**
     * Extract and return the command keyword from the user input.
     * @param userCommand User input.
     * @return Command keyword.
     */
    public String getCommandKeyWord(String userCommand) {
        String[] userCommandSplit = userCommand.split(" ");
        return userCommandSplit[0];
    }

    /**
     * Extract and return the description from the user input.
     * @param userCommand User input.
     * @return Description for the command to process.
     */
    public String getDescription(String userCommand) {
        String userCommandKeyWord = getCommandKeyWord(userCommand);
        if (userCommand.equals("deadline")) {
            return "To be edited in parser class";
        } else if (userCommand.equals("event")) {
            return "Edit Parser class";
        }
        //String description = description = String.join(" ", Arrays.copyOfRange(stringSplit, 1, stringSplit.length));
        return userCommand.substring(userCommandKeyWord.length()).strip();
    }

    /**
     * Parse userCommand as TodoCommand that add todo Task given with a description.
     * @param userCommand User input.
     * @throws DukeArgumentException If missing parameter for index.
     * @return TodoCommand which adds Todo Task into the TaskList.
     */
    public TodoCommand parseTodoCommand(String userCommand) throws DukeArgumentException {
        String description = getDescription(userCommand);
        if (description.length() == 0) {
            throw new DukeArgumentException("     :( OOPS!!! Missing description for todo.");
        }
        return new TodoCommand(description);
    }

    /**
     * Parse userCommand as ListCommand that list all the Task stored.
     * @param userCommand User input.
     * @throws DukeArgumentException If additional parameter is provided.
     * @return ListCommand Object that provides the stored tasks.
     */
    public ListCommand parseListCommand(String userCommand) throws DukeArgumentException {
        if (getDescription(userCommand).length() > 0) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for list.");
        }
        return new ListCommand();
    }

    /**
     * Parse userCommand as HelpCommand that show the list of available command.
     * @param userCommand User input.
     * @throws DukeArgumentException If additional parameter is provided.
     * @return HelpCommand Object that show the list of available command.
     */
    public HelpCommand parseHelpCommand(String userCommand) throws DukeArgumentException {
        if (getDescription(userCommand).length() > 0) {
            throw new DukeArgumentException("     :( OOPS!!! Description not required for help.");
        }
        return new HelpCommand();
    }
}