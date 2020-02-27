package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import javax.sql.rowset.serial.SerialStruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class Duke {
    private static final String STANDARD_SEPARATOR = "____________________________________________________________";
    private static final String SHANNON = "Shannon";
    public static final String filePath = "duke.txt";
    private static final String ioErrorMessage = " OOPS!! There is an error in input or output";
    private static final String unknownCommandMessage = " OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static Task[] listOfTasks = new Task[100]; // array of tasks that is <=100
    private static int sizeOfList = 0; // number of items in the list

    public static void main(String[] args) {
        printWelcomeMessage();
        try {
            addExistingList();
        } catch (IOException e) {
            System.out.println(ioErrorMessage);
        }


        // Reads in the first command from the user
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        String inputCommand = inputLine.substring(0, 3);

        while (!inputCommand.contains("bye")) {
            if (inputCommand.contains("lis")) {
                printsOutTheList(); // prints out the list
            } else if (inputCommand.contains("don")) {
                indicateTaskAsDone(inputLine); // marks task in the stated index as done

            } else {
                try {
                    addInNewTask(inputLine);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(STANDARD_SEPARATOR);
                    System.out.println("OOPS!!! The details of a " + inputLine + " cannot be empty.");
                    System.out.println(STANDARD_SEPARATOR);
                } catch (DukeException e) {
                    System.out.println(STANDARD_SEPARATOR);
                    System.out.println(unknownCommandMessage);
                    System.out.println(STANDARD_SEPARATOR);
                }

            }
            // take in the next command
            inputLine = scanner.nextLine();
            inputCommand = inputLine.substring(0, 3);
        }

        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            writeToFile("");
            for (int i = 0; i < sizeOfList; i++) {
                String currentListLine = listOfTasks[i].toString();
                String typeOfTask = currentListLine.substring(1, 2);
                String markedOrUnmarked = listOfTasks[i].getStatusIcon(); //currentListLine.substring(4, 5);
                String description = listOfTasks[i].getDescription();
                String extra = listOfTasks[i].getExtra();
                String textToAppend;
                if (extra == null) {
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description;
                } else {
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description + " | " + extra;
                }

                appendToFile(textToAppend + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(ioErrorMessage);
        }

        printExitMessage();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
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
            // when file does not exist in the first place
            f.createNewFile();
        }
        // now file exists
        Scanner scan = new Scanner(f);
        while (scan.hasNext()) {
            String lineInList = scan.nextLine();
            String[] itemInLine = lineInList.split(" \\| ");
            System.out.println(lineInList);

            if (itemInLine[0].contains("T")) {
                //System.out.println(itemInLine[2]);
                Todo newTodo = new Todo(itemInLine[2]);
                listOfTasks[sizeOfList] = newTodo;
                if (itemInLine[1].contains("1")) { listOfTasks[sizeOfList].markAsDone(); }
                sizeOfList++;

            } else if (itemInLine[0].contains("D")) {
                //System.out.println(itemInLine);
                Deadline newDeadline = new Deadline(itemInLine[2], itemInLine[3]);
                listOfTasks[sizeOfList] = newDeadline;
                if (itemInLine[1].contains("1")) { listOfTasks[sizeOfList].markAsDone(); }
                sizeOfList++;

            } else {
                Event newEvent = new Event(itemInLine[2], itemInLine[3]);
                listOfTasks[sizeOfList] = newEvent;
                if (itemInLine[1].contains("1")) { listOfTasks[sizeOfList].markAsDone(); }
                sizeOfList++;

            }
        }
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
        System.out.println("  " + listOfTasks[sizeOfList - 1].toString());
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
        listOfTasks[sizeOfList] = newEvent;
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
        listOfTasks[sizeOfList] = newDeadline;
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
        listOfTasks[sizeOfList] = newToDo;
        sizeOfList++;
    }


    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     *
     * @param inputLine name of task to set as done
     */
    private static void indicateTaskAsDone(String inputLine) {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        // obtain index of task to set as done
        String indexInString = inputLine.substring(5);
        int index = Integer.parseInt(indexInString) - 1;
        // mark as done
        listOfTasks[index].markAsDone();
        System.out.println("   " + listOfTasks[index].toString());
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
            System.out.println(" " + k + "." + listOfTasks[i].toString());
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
