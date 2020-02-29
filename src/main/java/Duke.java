import Exceptions.EmptyStringException;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static int todoSize = 0;

    public static void printSeparator() {
        System.out.println("----------------------------------------------");
    }

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        printSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("1. \"list\" to display To Do List\n2. \"done [index]\" to mark as done\n3. \"todo\" or "
                + "\"deadline /by [date/time]\" or \"event /at [date/time]\" to add to list\n4. \"delete [index]\""
                + "to delete\n5. \"bye\" to exit");
        printSeparator();
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        String userInput;
        ArrayList<Task> toDoList = new ArrayList<>();
        Storage storage = new Storage();
        try {
            toDoList = storage.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            userInput = userInput.trim();
            //user checks list
            if (userInput.equalsIgnoreCase("list")) {
                if (toDoList.size() > 0) {
                    printSeparator();
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(i + 1 + ". " + toDoList.get(i).toString());
                    }
                    printSeparator();
                } else {
                    printSeparator();
                    System.out.println("Your list is empty bruhh");
                    printSeparator();
                }

            } else if (userInput.startsWith("done")) {
                try {
                    int indexCompleted = Integer.parseInt(userInput.substring(5));
                    toDoList.get(indexCompleted - 1).markAsDone();
                    printSeparator();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(toDoList.get(indexCompleted - 1).toString());
                    printSeparator();
                } catch (NumberFormatException e) {
                    printSeparator();
                    System.out.println("You did not enter a number. Try again!!");
                    printSeparator();
                } catch (IndexOutOfBoundsException e) {
                    printSeparator();
                    System.out.println("The index you entered does not exist??? Look at ur list properly lah");
                    printSeparator();
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int indexToDelete = Integer.parseInt(userInput.substring(7));
                    printSeparator();
                    System.out.println("Noted with thanks. This task is deleted liao: ");
                    System.out.println(toDoList.get(indexToDelete - 1).toString());
                    System.out.println("Now there are " + (todoSize - 1) + " task(s) in your list");
                    printSeparator();
                    toDoList.remove(indexToDelete - 1);
                    todoSize--;
                } catch (NumberFormatException e) {
                    printSeparator();
                    System.out.println("You did not enter a number. Try again!!");
                    printSeparator();
                } catch (IndexOutOfBoundsException e) {
                    printSeparator();
                    System.out.println("The index you entered does not exist??? Look at ur list properly lah");
                    printSeparator();
                }
            } else if (userInput.startsWith("todo")) {
                try {
                    userInput = userInput.substring(5);
                    if (userInput.trim().equals("")) {
                        throw new EmptyStringException();
                    }
                    ToDo t = new ToDo(userInput);
                    toDoList.add(t);
                    printSeparator();
                    System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                    System.out.println(t.toString());
                    System.out.println("Now there are " + (todoSize + 1) + " task(s) in your list");
                    printSeparator();
                    todoSize++;
                } catch (EmptyStringException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (userInput.startsWith("deadline")) {

                try {
                    userInput = userInput.substring(9);
                    if (userInput.trim().equals("")) {
                        throw new EmptyStringException();
                    }
                    Deadline d = new Deadline(userInput);
                    toDoList.add(d);
                    printSeparator();

                    System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                    System.out.println(d.toString());
                    System.out.println("Now there are " + (todoSize + 1) + " task(s) in your list");
                    printSeparator();
                    todoSize++;
                } catch (EmptyStringException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.startsWith("event")) {
                try {
                    userInput = userInput.substring(6);
                    if (userInput.trim().equals("")) {
                        throw new EmptyStringException();
                    }
                    Events e = new Events(userInput);
                    toDoList.add(e);
                    printSeparator();
                    System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                    System.out.println(e.toString());
                    System.out.println("Now there are " + (todoSize + 1) + " task(s) in your list");
                    printSeparator();
                    todoSize++;
                } catch (EmptyStringException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else {
                printSeparator();
                System.out.println("OOPS!!! IDGI THAT");
                printSeparator();
            }
            storage.save(toDoList);
        } while (!userInput.equalsIgnoreCase("bye"));

        printSeparator();
        System.out.println("Bye. I hope to see u again soon ^^");
        printSeparator();
    }
}
