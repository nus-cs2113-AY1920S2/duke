package src.main.java;

import src.main.java.duke.exceptions.*;
import src.main.java.duke.task.Task;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            loadListFromFile("duke.txt", taskList);
            Duke_Static_Methods.greetUser();
            inputAndExecute(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (UnknownLineFromSavedFileException e) {
            System.out.println("Unknown line read from Save File");
        }
    }

    private static void inputAndExecute(ArrayList<Task> taskList) {
        String userInput;
        do {
            userInput = scanInput();
            String[] userCommand = userInput.split(" ", 2);
            try {
                executeCommand(taskList, userCommand);
                Duke_Static_Methods.writeToFile(taskList);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of " + userCommand[0] + " cannot be empty.");
            } catch (InvalidDateException e) {
                System.out.println("Please re-enter command with the time and/or date.");
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-<");
            } catch (InvalidDoneException e) {
                System.out.println("Unable to process done statement");
            } catch (AlreadyDoneException e) {
                System.out.println("Task has already been completed.");
                System.out.println("  " + taskList.get(Integer.parseInt(userCommand[1]) - 1).toString());
            } catch (IOException e) {
                System.out.println("Unable to find duke.txt");
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private static void executeCommand(ArrayList<Task> taskList, String[] userCommand) throws InvalidCommandException, InvalidDateException, InvalidDoneException, AlreadyDoneException, IOException {
        switch (userCommand[0]) {
            case "list":
                Duke_Static_Methods.listTasks(taskList); //List out task list
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!"); //terminate method
                break;
            case "todo":
                Duke_Static_Methods.addNewTodo(taskList, userCommand[1]);
                break;
            case "deadline":
                Duke_Static_Methods.addNewDeadline(taskList, userCommand[1]);
                break;
            case "event":
                Duke_Static_Methods.addNewEvent(taskList, userCommand[1]);
                break;
            case "done":
                Duke_Static_Methods.markedAsDone(taskList, userCommand[1]);
                break;
            case "delete":
                Duke_Static_Methods.deleteTask(taskList, userCommand[1]);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    private static String scanInput(){
        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        return userInput;
    }

    private static void loadListFromFile(String filePath, ArrayList<Task> taskList) throws FileNotFoundException, UnknownLineFromSavedFileException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String[] savedTaskDetails; //at most 4 parts
        while (s.hasNext()) {
            savedTaskDetails = s.nextLine().split(" \\| ");
            Duke_Static_Methods.addSavedTasks(taskList, savedTaskDetails);
        }
    }
}
