package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String STANDARD_SEPARATOR = "____________________________________________________________";
    private static final String SHANNON = "Shannon";
    private static ArrayList<Task> listOfTasks = new ArrayList<>(); // array of tasks that is <=100
    private static int sizeOfList = 0; // number of items in the list
    private static String inputLine = null;

    public static void main(String[] args) {
        printWelcomeMessage();
        runCommandLoop();  // Reads in the first command from the user
        printExitMessage();

    }

    private static void runCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        while (!inputLine.equals("bye")) {
            try {
                String inputCommand = inputLine.substring(0, 3);
                if (inputLine.substring(0, 4).equals("list")) {
                    printsOutTheList(); // prints out the list

                } else if (inputLine.substring(0, 4).equals("done")) {
                    indicateTaskAsDone(inputLine); // marks task in the stated index as done

                } else if (inputLine.substring(0, 6).equals("remove")) {
                    removeTaskFromList(inputLine); // removes task in the stated index

                } else {
                    addInNewTask(inputLine);// adds a new task into the list
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println(STANDARD_SEPARATOR);
                System.out.println("\u2639 OOPS!!! The description of a " + inputLine + " cannot be empty.");
                System.out.println(STANDARD_SEPARATOR);
            } catch (DukeException e) {
                System.out.println(STANDARD_SEPARATOR);
                System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(STANDARD_SEPARATOR);
            }

            inputLine = scanner.nextLine(); // take in the next command
        }
    }

    private static void removeTaskFromList(String inputLine) throws DukeException {

        String[] commandArray = inputLine.split(" ");
        if (commandArray.length < 2) { throw new IndexOutOfBoundsException(); }
        if (commandArray.length > 2) { throw new DukeException(); }
        int indexToRemove = Integer.parseInt(commandArray[1]) - 1; // obtain index of task to remove
        if (indexToRemove >= sizeOfList) { throw new DukeException(); }
        Task taskToDelete = listOfTasks.get(indexToRemove);

        listOfTasks.remove(taskToDelete);
        sizeOfList--;

        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + taskToDelete.toString());
        System.out.println(" Now you have " + sizeOfList + " in the list.");
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Prints the exit message
     */
    private static void printExitMessage() {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Differentiates task and adds into array
     *
     * @param inputLine name of the task to add
     */
    private static void addInNewTask(String inputLine) throws DukeException {

        String inputCommand = inputLine.substring(0, 4);

        if (inputCommand.contains("todo")) {
            if (inputLine.equals("todo")) {
                throw new IndexOutOfBoundsException();
            }
            addToDo(inputLine);

        } else if (inputCommand.contains("dead")) {
            if (inputLine.equals("deadline")) {
                throw new IndexOutOfBoundsException();
            }
            addDeadline(inputLine);

        } else if (inputCommand.contains("even")) {
            if (inputLine.equals("event")) {
                throw new IndexOutOfBoundsException();
            }
            addEvent(inputLine);

        } else {
            throw new DukeException();
        }

        // creates new task
        // informs user that task has been added to list
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + listOfTasks.get(sizeOfList - 1).toString());
        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Adds in new Event to the array,
     *
     * @param inputLine name of the task to add
     */
    private static void addEvent(String inputLine) {
        String description;
        int indexOfEvent = inputLine.indexOf("/at") + 4;
        int descriptionStart = 6;
        int descriptionEnd = inputLine.indexOf(" /at");

        description = inputLine.substring(descriptionStart, descriptionEnd);
        String at = inputLine.substring(indexOfEvent);
        Event newEvent = new Event(description, at);
        listOfTasks.add(newEvent);
        sizeOfList++;
    }


    /**
     * Adds in new Deadline to the array,
     *
     * @param inputLine name of the task to add
     */
    private static void addDeadline(String inputLine) {
        String description;
        int indexOfDeadline = inputLine.indexOf("/by") + 4;
        int descriptionStart = 9;
        int descriptionEnd = inputLine.indexOf(" /by");

        description = inputLine.substring(descriptionStart, descriptionEnd);
        String by = inputLine.substring(indexOfDeadline);
        Deadline newDeadline = new Deadline(description, by);
        listOfTasks.add(newDeadline);
        sizeOfList++;
    }


    /**
     * Adds in new Todo to array,
     *
     * @param inputLine name of the task to add
     */
    private static void addToDo(String inputLine) {
        String description = inputLine.substring(5);
        Todo newToDo = new Todo(description);
        listOfTasks.add(newToDo);
        sizeOfList++;
    }


    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     *
     * @param inputLine name of task to set as done
     */
    private static void indicateTaskAsDone(String inputLine) throws DukeException {
        String[] commandArray = inputLine.split(" ");
        if (commandArray.length < 2) { throw new IndexOutOfBoundsException(); }
        if (commandArray.length > 2) { throw new DukeException(); }
        // obtain index of task to set as done
        //String indexInString = inputLine.substring(5);
        int index = Integer.parseInt(commandArray[1]) - 1;
        if (index >= sizeOfList) { throw new DukeException(); }
        // mark as done
        listOfTasks.get(index).markAsDone();
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + listOfTasks.get(index).toString());
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Prints out the entire list
     */
    private static void printsOutTheList() {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < sizeOfList; i++) {
            int k = i + 1;
            System.out.println(" " + k + "." + listOfTasks.get(i).toString());
        }
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Prints out the welcome message
     */
    private static void printWelcomeMessage() {
        System.out.println("Hello from " + SHANNON);

        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Hello! I'm " + SHANNON + System.lineSeparator() + " What can I do for you?");
        System.out.println(STANDARD_SEPARATOR);
    }
}
