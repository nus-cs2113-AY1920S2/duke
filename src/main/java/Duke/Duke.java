package Duke;

import Duke.Exception.InvalidTaskException;
import Duke.Exception.MissingDescriptonException;
import Duke.Exception.MissingNumberFieldException;
import Duke.Exception.MissingTimeFieldException;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final int INITIAL_NO_OF_TASKS = 0;

    public static void main(String[] args) {
        displayHello();
        ArrayList<Task> taskList = initializeTaskList();
        Scanner myInput = initializeScanner();
        String userInput = getUserInput(myInput);
        int numberOfTasks = INITIAL_NO_OF_TASKS;
        while (isNotBye(userInput)) {
            String[] splitUserInput = splitTheUserInput(userInput);
            numberOfTasks = doTaskAndGetNewNumberOfTasks(taskList, numberOfTasks, userInput, splitUserInput);
            userInput = getNextUserInput(myInput);
        }
        displayGoodbye();
        closeScanner(myInput);
    }

    private static boolean isNotBye(String userInput) {
        return !userInput.equals("bye");
    }

    private static String getUserInput(Scanner myInput) {
        return myInput.nextLine();
    }

    private static String getNextUserInput(Scanner myInput) {
        System.out.println("What else do you want to do?");
        return myInput.nextLine();
    }

    private static String[] splitTheUserInput(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    private static void closeScanner(Scanner myInput) {
        myInput.close();
    }

    private static Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private static ArrayList<Task> initializeTaskList() {
        return new ArrayList<>();
    }

    private static int doTaskAndGetNewNumberOfTasks(ArrayList<Task> taskList, int numberOfTasks, String userInput,
                                                    String[] splitUserInput) {

        int currentNumberOfTasks = numberOfTasks;
        try {
            validateTask(splitUserInput, userInput);
            switch (splitUserInput[0].toLowerCase()) {
            case TODO:
                currentNumberOfTasks = addTodoToList(taskList, numberOfTasks, userInput);
                break;
            case DEADLINE:
                currentNumberOfTasks = addDeadlineToList(taskList, numberOfTasks, userInput);
                break;
            case EVENT:
                currentNumberOfTasks = addEventToList(taskList, numberOfTasks, userInput);
                break;
            case LIST:
                displayList(taskList, numberOfTasks);
                break;
            case DONE:
                markTaskAsDone(taskList, numberOfTasks, splitUserInput[1]);
                break;
            case DELETE:
                currentNumberOfTasks = deleteTask(taskList, numberOfTasks, splitUserInput[1]);
                break;
            default:
                break;
            }
        } catch (InvalidTaskException | MissingDescriptonException | MissingNumberFieldException | MissingTimeFieldException | NumberFormatException m) {
            System.out.println("Exception occurred: " + m);
        }


        return currentNumberOfTasks;
    }

    private static int deleteTask(ArrayList<Task> taskList, int numberOfTasks, String s) throws NumberFormatException {
        try {
            int currentNumberOfTasks = numberOfTasks;
            int taskListNumber = Integer.parseInt(s);
            if (taskListNumber > numberOfTasks || taskListNumber == 0) {
                System.out.println("Out of range");
            } else {
                String removedTask = taskList.get(taskListNumber - 1).toString();
                taskList.remove(taskListNumber - 1);
                currentNumberOfTasks = currentNumberOfTasks - 1;
                //taskList.get(taskListNumber - 1).markAsDone();
                System.out.println(
                        "Noted. I removed this task: " + System.lineSeparator() + removedTask + System.lineSeparator() +
                                "Now you have " + currentNumberOfTasks + " tasks in the list");
            }
            return currentNumberOfTasks;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Delete number field is not a number!");
        }
    }

    private static void validateTask(String[] splitUserInput, String userInput)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {

        String temp = splitUserInput[0].toLowerCase();
        if (!temp.equals(TODO) && !temp.equals(EVENT) &&
                !temp.equals(DEADLINE) && !temp.equals(DONE) &&
                !temp.equals(LIST) && !temp.equals(DELETE)) {
            throw new InvalidTaskException("Input is invalid. No such task");
        } else {
            String[] splitArray = splitTaskDescription(userInput);
            if ((temp.toLowerCase().equals(DONE) || temp.toLowerCase().equals(DELETE)) && splitUserInput.length == 1) {
                throw new MissingNumberFieldException(temp.toLowerCase() + "'s number field is empty!");
            } else if (isDescriptionBlank(splitArray) && !temp.equals(LIST)) {
                throw new MissingDescriptonException("Missing description!");
            } else if (temp.equals(TODO) || temp.equals(EVENT) || temp.toLowerCase().equals(DEADLINE)) {
                if (splitArray[2].equals("yes")) {
                    String obtainedTime = splitArray[1];
                    String[] timeCheck = obtainedTime.split(" ", 2);
                    if (timeCheck.length == 1 || timeCheck[1].isBlank()) {
                        throw new MissingTimeFieldException("Missing time!");
                    }
                }

            }
        }


    }

    private static void markTaskAsDone(ArrayList<Task> taskList, int numberOfTasks, String s)
            throws NumberFormatException {
        try {
            int taskListNumber = Integer.parseInt(s);
            if (taskListNumber > numberOfTasks || taskListNumber == 0) {
                System.out.println("Out of range");
            } else {
                taskList.get(taskListNumber - 1).markAsDone();
                System.out.println(
                        "Nice! I marked this as done: " + taskList.get(taskListNumber - 1).toString());
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Done number field is not a number!");
        }
    }

    private static void displayList(ArrayList<Task> taskList, int numberOfActions) {
        if (numberOfActions > 0) {
            for (int i = 0; i < numberOfActions; i++) {
                System.out.println(
                        Integer.toString(i + 1) + ". " + taskList.get(i).toString());
            }
        } else if (numberOfActions == 0) {
            System.out.println("Nothing yet");
        }
    }

    private static int addEventToList(ArrayList<Task> taskList, int numberOfTasks, String userInput) {
        Task newTask;
        String[] splitTaskDescriptionArray = splitTaskDescription(userInput);
        newTask = new Event(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
        int currentNumberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return currentNumberOfTasks;
    }

    private static boolean isDescriptionBlank(String[] strings) {
        return strings[0].isBlank();
    }

    private static int addDeadlineToList(ArrayList<Task> taskList, int numberOfTasks, String userInput) {
        Task newTask;
        String[] splitTaskDescriptionArray = splitTaskDescription(userInput);
        newTask = new Deadline(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
        int currentNumberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return currentNumberOfTasks;
    }


    private static int addTodoToList(ArrayList<Task> taskList, int numberOfTasks, String userInput) {
        Task newTask;
        String[] splitTaskDescriptionArray = splitTaskDescription(userInput);
        newTask = new Todo(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
        int currentNumberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return currentNumberOfTasks;
    }

    private static int addToList(Task newTask, ArrayList<Task> taskList, int numberOfTasks) {
        taskList.add(newTask);
        int currentNumberOfTasks = numberOfTasks + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return currentNumberOfTasks;

    }

    private static void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    private static void displayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
    }

    private static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[3];
        if (!input.contains("/")) {
            String[] obtainedDescription = input.split(" ", 2);
            if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
                returnSplit[0] = "";
            } else {
                returnSplit[0] = obtainedDescription[1].trim();
            }
            returnSplit[1] = "";
            returnSplit[2] = "";
            return returnSplit;
        }
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);
        if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
            returnSplit[0] = "";
            returnSplit[1] = "";
        } else {
            returnSplit[0] = obtainedDescription[1].trim();
            returnSplit[1] = obtainedSplit[1].trim();
            returnSplit[2] = "yes";
        }
        return returnSplit;
    }


}
