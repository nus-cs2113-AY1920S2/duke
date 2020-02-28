package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ManageCommand;
import duke.command.WrongCommand;
import duke.excpetions.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        try{
            if (fullCommand.contains(" ")) {
                String type = fullCommand.substring(0, fullCommand.indexOf(" "));
                switch (type) {
                case "delete":
                    int index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
                    return new DeleteCommand(type,index);
                case "done":
                    index = Integer.parseInt(fullCommand.substring(fullCommand.indexOf(" ")+1));
                   return new ManageCommand(type,index);
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
                return new ManageCommand(fullCommand,-1);
            }else if (fullCommand.equals("bye")){
                return new ExitCommand("bye");
            }else{
                throw new DukeException();
            }
        } catch (DukeException e){
            System.out.println("The command is not correct.");
            return new WrongCommand("wrong");
        }
    }
}
