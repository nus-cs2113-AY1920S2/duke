package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {
    private static final String STANDARD_SEPARATOR = "____________________________________________________________";
    private static final String SHANNON = "Shannon";
    public static final String filePath = "duke.txt";
    private static final String ioErrorMessage = " OOPS!! There is an error in input or output";
    private static final String unknownCommandMessage = " OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static ArrayList<Task> listOfTasks = new ArrayList<>(); // array of tasks that is <=100
    private static int sizeOfList = 0; // number of items in the list
    private static String inputLine = null; //most updated line of input


    public static void main(String[] args) {
        printWelcomeMessage();
        try {
            addExistingList(); // Reads offline list into current list
        } catch (IOException e) {
            System.out.println(ioErrorMessage);
        }

        runCommandLoop();  // Reads in the first command from the user
        saveListOffline(); // save current list to be offline
        printExitMessage();

    }

    /**
     * Goes into loop
     */
    private static void runCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        inputLine = scanner.nextLine();

        while (!inputLine.equals("bye")) {
            try {
                if (inputLine.substring(0, 4).equals("list")) {
                    printsOutTheList(); // prints out the list

                } else if (inputLine.substring(0, 4).equals("done")) {
                    indicateTaskAsDone(); // marks task in the stated index as done

                } else if (inputLine.substring(0, 6).equals("remove")) {
                    removeTaskFromList(); // removes task in the stated index

                } else {
                    addInNewTask();// adds a new task into the list
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println(STANDARD_SEPARATOR);
                System.out.println("OOPS!!! The description of a " + inputLine + " cannot be empty.");
                System.out.println(STANDARD_SEPARATOR);
            } catch (DukeException e) {
                System.out.println(STANDARD_SEPARATOR);
                System.out.println(unknownCommandMessage);
                System.out.println(STANDARD_SEPARATOR);
            }
            inputLine = scanner.nextLine();// take in the next command
        }

    }

    private static void saveListOffline() {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }

            writeToFile(); // overwrite old contents
            for (int i = 0; i < sizeOfList; i++) {
                String currentListLine = listOfTasks.get(i).toString();
                String typeOfTask = currentListLine.substring(1, 2);
                String markedOrUnmarked = listOfTasks.get(i).getStatusIcon();
                String description = listOfTasks.get(i).getDescription();
                String extra = listOfTasks.get(i).getExtra();
                String textToAppend;

                if (extra == null) { // if it does not have a /by or /at (it is a todo)
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description;
                } else { // it is a deadline or event
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description + " | " + extra;
                }

                appendToFile(textToAppend + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(ioErrorMessage);
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }



    /**
     * Adds the items in the existing list in
     * the file to system so that changes can
     * be made to existing list
     * @throws IOException
     */
    private static void addExistingList() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        } else { // if file does exist, add into current list
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String lineInList = scan.nextLine();
                String[] itemInLine = lineInList.split(" \\| ");

                if (itemInLine[0].contains("T")) { // add task as todo into list
                    Todo newTodo = new Todo(itemInLine[2]);
                    listOfTasks.add(newTodo);
                    if (itemInLine[1].contains("1")) {
                        listOfTasks.get(sizeOfList).markAsDone();
                    }
                    sizeOfList++;

                } else if (itemInLine[0].contains("D")) { // add task as deadline into list
                    Deadline newDeadline = new Deadline(itemInLine[2], itemInLine[3]);
                    listOfTasks.add(newDeadline);
                    if (itemInLine[1].contains("1")) {
                        listOfTasks.get(sizeOfList).markAsDone();
                    }
                    sizeOfList++;

                } else { // add task as event into list
                    Event newEvent = new Event(itemInLine[2], itemInLine[3]);
                    listOfTasks.add(newEvent);
                    if (itemInLine[1].contains("1")) {
                        listOfTasks.get(sizeOfList).markAsDone();
                    }
                    sizeOfList++;

                }
            }
        }
    }

    private static void removeTaskFromList() throws DukeException {

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
     */
    private static void addInNewTask() throws DukeException {
        String inputCommand = inputLine.substring(0, 4);
        // creates new task
        if (inputCommand.contains("todo")) {
            if (inputLine.equals("todo")) {
                throw new IndexOutOfBoundsException();
            }
            addToDo();

        } else if (inputCommand.contains("dead")) {
            if (inputLine.equals("deadline")) {
                throw new IndexOutOfBoundsException();
            }
            addDeadline();

        } else if (inputCommand.contains("even")) {
            if (inputLine.equals("event")) {
                throw new IndexOutOfBoundsException();
            }
            addEvent();

        } else {
            throw new DukeException();
        }

        // informs user that task has been added to list
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + listOfTasks.get(sizeOfList - 1).toString());
        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        System.out.println(STANDARD_SEPARATOR);
    }


    /**
     * Adds in new Event to the array
     */
    private static void addEvent() {
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
     */
    private static void addDeadline() {
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
     */
    private static void addToDo() {
        String description = inputLine.substring(5);
        Todo newToDo = new Todo(description);
        listOfTasks.add(newToDo);
        sizeOfList++;
    }


    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     */
    private static void indicateTaskAsDone() throws DukeException {
        String[] commandArray = inputLine.split(" ");
        if (commandArray.length < 2) { throw new IndexOutOfBoundsException(); }
        if (commandArray.length > 2) { throw new DukeException(); }
        int index = Integer.parseInt(commandArray[1]) - 1; // obtain index of task to set as done
        if (index >= sizeOfList) { throw new DukeException(); }
        listOfTasks.get(index).markAsDone(); // mark as done
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
