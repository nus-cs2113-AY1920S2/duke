package Parser;

import Exceptions.NoParameterException;
import Exceptions.emptyListException;

public class Parser {

    public Command parseCommand( String userCommand) {
        while (!userCommand.equals("bye")){
            String[] words = userCommand.split(" ");
            int wordLength = words.length;
            switch (words[0]) {
                case "list":
                    printList(tasks);
                    break;
                case "done":
                    if (wordLength != SIZE_DONE_COMMAND) {
                        System.out.println("Wrong format for command \"done\"");
                        break;
                    }
                    try {
                        int index = Integer.parseInt(words[1]);
                        completeTask(tasks, index);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a valid number\n");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Duke.Task not found, please try again");
                    }
                    break;
                case "help":
                    printHelp();
                    break;
                case "delete":
                    if (wordLength != SIZE_DELETE_COMMAND) {
                        System.out.println("Wrong format for command \"Delete\"\n");
                        break;
                    }
                    try {
                        int index = Integer.parseInt(words[1]);
                        deleteTask(tasks, index);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a valid number\n");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found, please try again\n");
                    } catch (emptyListException e) {
                        System.out.println("List is empty");
                    }
                    break;
                case "todo":
                    try {
                        int taskCounter = tasks.size() + 1;
                        addTodo(tasks, userCommand, taskCounter, wordLength);
                    } catch (NoParameterException e) {
                        System.out.println("Missing Parameters detected!\n");
                    }
                    break;
                case "event":

                    try {
                        int taskCounter = tasks.size() + 1;
                        addEvent(tasks, userCommand, taskCounter, wordLength);
                    } catch (NoParameterException e){
                        System.out.println("Missing Parameters detected!\n");
                    }
                    break;
                case "deadline":

                    try {
                        int taskCounter = tasks.size() + 1;
                        addDeadline(tasks, userCommand, taskCounter, wordLength);
                    } catch (NoParameterException e){
                        System.out.println("Missing Parameters detected!\n");
                    }
                    break;
                default:
                    System.out.println("Command not recognised\n");
            }
            // end of current listening loop, preparing next command
            userCommand = input.nextLine();
        }
        System.out.println("LISA: Bye, hope to see you again!");
    }
    }

}
