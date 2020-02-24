package duke;

import duke.excpetions.EmptyDescriptionException;

public class Parser {

    public static void executeCommand(String command,TaskList tasks) {
        String commandType = commandDivider(command);
        try{
            switch(commandType) {
            case "list":
                tasks.listTasks();
                break;
            case "done":
                tasks.doneTask(command);
                break;
            case "todo":
                tasks.addToDo(command);
                break;
            case "deadline":
                tasks.addDeadline(command);
                break;
            case "event":
                tasks.addEvent(command);
                break;
            default:
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Ui.printDividingLine();
            System.out.println("Do you have any other commands? ");
        } catch (EmptyDescriptionException e){
            System.out.println("Please re-enter your command with a description.");
        }
    }

    private static String commandDivider(String command){
        if (command.contains(" ")){
             return command.substring(0,command.indexOf(" "));
        }else{
            return command;
        }
    }
}
