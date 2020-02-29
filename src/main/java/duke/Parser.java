package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
                String type = fullCommand.substring(0, fullCommand.indexOf(" "));
                switch (type) {
                case "show":
                    String dateInString=fullCommand.substring(fullCommand.indexOf(" ")+1);
                    LocalDate date = LocalDate.parse(dateInString);
                    return new ManageCommand(type,-1,date);
                case "delete":
                    int index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
                    return new DeleteCommand(type,index);
                case "done":
                    index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
                   return new ManageCommand(type,index,null);
                case "todo":
                    String description=fullCommand.substring(fullCommand.indexOf(" "));
                    return new AddCommand(type,description,null);
                case "deadline":
                    description=fullCommand.substring(fullCommand.indexOf(" "),fullCommand.indexOf("/"));
                    String by=fullCommand.substring(fullCommand.indexOf("/by")+4);
                    return new AddCommand(type,description,by);
                case "event":
                    description=fullCommand.substring(fullCommand.indexOf(" "),fullCommand.indexOf("/"));
                    String period=fullCommand.substring(fullCommand.indexOf("/at")+4);
                    return new AddCommand(type,description,period);
                default:
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    return new WrongCommand("wrong");
                }
            }else if(fullCommand.equals("list")){
                return new ManageCommand(fullCommand,-1,null);
            }else if (fullCommand.equals("bye")){
                return new ExitCommand("bye");
            }else{
                throw new DukeException();
            }
        } catch (DukeException e){
            Ui.showError();
            return new WrongCommand("wrong");
        }
    }
}
