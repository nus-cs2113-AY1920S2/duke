package duke;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ManageCommand;
import duke.command.WrongCommand;
import duke.excpetions.DukeException;

public class Parser {
    /**
     * Returns a command which contains the information extract from the users' input.
     * If the command given is meaningless or is not in the right format, then it will return a wrong command.
     *
     * @param fullCommand The input given by the users.
     * @return A command.
     * @throws DukeException If the input gives an unacceptable command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try{
            if (fullCommand.contains(" ")) {
                return parseLongCommand(fullCommand);
            }else{
                return parseShortCommand(fullCommand);
            }
        } catch (DukeException e){
            Ui.showError();
            return new WrongCommand("wrong");
        }
    }

    /**
     * Parse an input given by the users which has only one word.
     *
     * @param fullCommand The input given by the users.
     * @return A command interprets users' inputs.
     * @throws DukeException If the input given is not acceptable by the program.
     */
    private static Command parseShortCommand(String fullCommand) throws DukeException {
        switch(fullCommand){
        case "list":
            return new ManageCommand(fullCommand,-1,null,null);
        case "bye":
            return new ExitCommand("bye");
        case "help":
            return new ManageCommand("help",-1,null,null);
        default:
            throw new DukeException();
        }
    }

    /**
     * Parse an input given by the users which has more than one word.
     *
     * @param fullCommand The input given by the users.
     * @return A command interprets users' inputs.
     * @throws DukeException If the input given is not acceptable by the program.
     */
    private static Command parseLongCommand(String fullCommand) throws DukeException {
        String type = fullCommand.substring(0, fullCommand.indexOf(" "));
        switch (type) {
        case "show":
            return parseShowCommand(fullCommand);
        case "delete":
            return parseDeleteCommand(fullCommand);
        case "done":
            return parseDoneCommand(fullCommand);
        case "todo":
            return parseToDoCommand(fullCommand);
        case "deadline":
            return parseDeadlineCommand(fullCommand);
        case "event":
            return parseEventCommand(fullCommand);
        case "find":
            return parseFindCommand(fullCommand);
        default:
            throw new DukeException();
        }
    }

    /**
     * Parse a long input which starts with "find".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseFindCommand(String fullCommand) {
        String description = fullCommand.substring(fullCommand.indexOf(" ")+1);
        return new ManageCommand("find",-1,description,null);
    }

    /**
     * Parse a long input which starts with "event".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseEventCommand(String fullCommand) throws DukeException{
        if (fullCommand.contains("at")){
            String description = fullCommand.substring(fullCommand.indexOf(" "),fullCommand.indexOf("/"));
            String period = fullCommand.substring(fullCommand.indexOf("/at")+4);
            return new AddCommand("event",description,period);
        }else{
            System.out.println("Please specify the time period of this task.");
            return new WrongCommand("wrong");
        }
    }

    /**
     * Parse a long input which starts with "deadline".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseDeadlineCommand(String fullCommand) {
        if (fullCommand.contains("by")){
            String description = fullCommand.substring(fullCommand.indexOf(" "),fullCommand.indexOf("/"));
            String by = fullCommand.substring(fullCommand.indexOf("/by")+4);
            return new AddCommand("deadline",description,by);
        }else{
            System.out.println("Please specify the deadline of this task.");
            return new WrongCommand("wrong");
        }
    }

    /**
     * Parse a long input which starts with "todo".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseToDoCommand(String fullCommand) {
        String description=fullCommand.substring(fullCommand.indexOf(" "));
        return new AddCommand("todo",description,null);
    }

    /**
     * Parse a long input which starts with "done".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseDoneCommand(String fullCommand) {
        int index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
        return new ManageCommand("done",index,null,null);
    }

    /**
     * Parse a long input which starts with "delete".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseDeleteCommand(String fullCommand) {
        int index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
        return new DeleteCommand("delete",index);
    }

    /**
     * Parse a long input which starts with "todo".
     *
     * @param fullCommand  The input given by the users.
     * @return A command interprets users' input.
     */
    private static Command parseShowCommand(String fullCommand) {
        String dateInString = fullCommand.substring(fullCommand.indexOf(" ")+1);
        LocalDate date = LocalDate.parse(dateInString);
        return new ManageCommand("show",-1,null,date);
    }
}
