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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;

public class Duke {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String T = "T";
    public static final String E = "E";
    public static final String D = "D";
    public static final int DESCRIPTION = 0;
    public static final int TIME = 1;
    public static final int TIME_INCLUDED = 2;
    public static final int TASK_TYPE = 0;

    public static void main(String[] args) {
        displayHello();
        ArrayList<Task> taskList = initializeTaskList();
        Scanner myInput = initializeScanner();
        String userInput = getUserInput(myInput);
        int numberOfTasks = getTaskListSize(taskList);
        while (isNotBye(userInput)) {
            String[] splitUserInput = splitTheUserInput(userInput);
            numberOfTasks = doTaskAndGetNewNumberOfTasks(taskList, numberOfTasks, userInput, splitUserInput);
            userInput = getNextUserInput(myInput);
        }
        saveList(taskList, numberOfTasks);
        displayGoodbye();
        closeScanner(myInput);
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

    private static ArrayList<Task> initializeTaskList() {
        Path path = getSaveDataPath();
        boolean isFileExists = checkIfDataFileExist(path);
        ArrayList<Task> taskList = new ArrayList<>();
        if (isFileExists) {
            File f = new File(String.valueOf(path));
            try {
                Scanner s = initializeFileScanner(f);
                int taskNumber = 0;
                while (s.hasNext()) {
                    String[] obtainedLine = extractData(s);
                    String[] splitTaskDescriptionArray = getDescriptionAndTime(obtainedLine);
                    taskNumber = importToList(taskList, taskNumber, splitTaskDescriptionArray, obtainedLine[0]);
                    markImportTaskDone(taskList, taskNumber, obtainedLine);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception occurred: " + e + "File not found!");
            }
        }
        return taskList;
    }

    private static Path getSaveDataPath() {
        return Paths.get("data", "duke.txt");
    }

    private static boolean checkIfDataFileExist(Path path) {
        return Files.exists(path);
    }

    private static Scanner initializeFileScanner(File f) throws FileNotFoundException {
        return new Scanner(f);
    }

    private static String[] extractData(Scanner s) {
        return s.nextLine().split("\\|");
    }

    private static String[] getDescriptionAndTime(String[] obtainedLine) {
        String[] splitTaskDescriptionArray = new String[2];
        splitTaskDescriptionArray[DESCRIPTION] = obtainedLine[2].trim();
        if (obtainedLine.length == 4) {
            splitTaskDescriptionArray[TIME] = obtainedLine[3].trim();
        } else {
            splitTaskDescriptionArray[TIME] = "";
        }
        return splitTaskDescriptionArray;
    }

    private static int importToList(ArrayList<Task> taskList, int taskNumber, String[] splitTaskDescriptionArray,
                                    String s) {
        Task newTask;
        int currentTaskNumber = taskNumber;
        switch (s.trim()){
        case T:
            newTask = new Todo(splitTaskDescriptionArray[0],splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case E:
            newTask = new Event(splitTaskDescriptionArray[0],splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case D:
            newTask = new Deadline(splitTaskDescriptionArray[0],splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        default:
            System.out.println("Error in importing this task! Task starts with: " + s.trim());
            break;
        }
        return currentTaskNumber;
    }

    private static void markImportTaskDone(ArrayList<Task> taskList, int taskNumber, String[] obtainedLine) {
        if (obtainedLine[1].trim().equals("1")) {
            taskList.get(taskNumber - 1).markAsDone();
        }
    }

    private static Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private static String getUserInput(Scanner myInput) {
        return myInput.nextLine();
    }

    private static int getTaskListSize(ArrayList<Task> taskList) {
        return taskList.size();
    }

    private static boolean isNotBye(String userInput) {
        return !userInput.equals("bye");
    }

    private static String[] splitTheUserInput(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    private static int doTaskAndGetNewNumberOfTasks(ArrayList<Task> taskList, int numberOfTasks, String userInput,
                                                    String[] splitUserInput) {
        int currentNumberOfTasks = numberOfTasks;
        try {
            validateTask(splitUserInput, userInput);
            switch (splitUserInput[TASK_TYPE].toLowerCase()) {
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

    private static void validateTask(String[] splitUserInput, String userInput)
            throws InvalidTaskException, MissingDescriptonException, MissingNumberFieldException,
            MissingTimeFieldException {

        String nameOfTask = splitUserInput[0].toLowerCase();
        if (!nameOfTask.equals(TODO) && !nameOfTask.equals(EVENT) &&
                !nameOfTask.equals(DEADLINE) && !nameOfTask.equals(DONE) &&
                !nameOfTask.equals(LIST) && !nameOfTask.equals(DELETE)) {
            throw new InvalidTaskException("Input is invalid. No such task");
        } else {
            String[] splitArray = splitTaskDescription(userInput);
            if ((nameOfTask.toLowerCase().equals(DONE) || nameOfTask.toLowerCase().equals(DELETE)) && splitUserInput.length == 1) {
                throw new MissingNumberFieldException(nameOfTask.toUpperCase() + "'s number field is empty!");
            } else if (isDescriptionBlank(splitArray) && !nameOfTask.equals(LIST)) {
                throw new MissingDescriptonException("Missing description!");
            } else if (nameOfTask.equals(TODO) || nameOfTask.equals(EVENT) || nameOfTask.toLowerCase().equals(DEADLINE)) {
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

    private static boolean isDescriptionBlank(String[] strings) {
        return strings[0].isBlank();
    }

    private static int addEventToList(ArrayList<Task> taskList, int numberOfTasks, String userInput) {
        Task newTask;
        String[] splitTaskDescriptionArray = splitTaskDescription(userInput);
        newTask = new Event(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
        int currentNumberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return currentNumberOfTasks;
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
                System.out.println(
                        "Noted. I removed this task: " + System.lineSeparator() + removedTask + System.lineSeparator() +
                                "Now you have " + currentNumberOfTasks + " tasks in the list");
            }
            return currentNumberOfTasks;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Delete number field is not a number!");
        }
    }


    private static String getNextUserInput(Scanner myInput) {
        System.out.println("What else do you want to do?");
        return myInput.nextLine();
    }

    private static void saveList(ArrayList<Task> taskList, int numberOfTasks) {
        if (numberOfTasks > 0) {
            Path directoryPath = Paths.get("data");
            boolean isFileExists = Files.exists(directoryPath);
            if (!isFileExists) {
                File directory = new File("data");
                directory.mkdir();
            }
            File f = new File("data/duke.txt");
            try {
                FileWriter fw = new FileWriter(f);
                for (int i = 0; i < numberOfTasks; i++) {
                    String[] getTaskInfo = taskList.get(i).getTaskInfo();
                    fw.write(getTaskInfo[0] + " | " + getTaskInfo[1] + " | " + getTaskInfo[2] + " | " + getTaskInfo[3] +
                            System.lineSeparator());
                }
                fw.close();

            } catch (IOException e) {
                System.out.println("Exception occurred: " + e + ": Error in writing file!");
            }

        }
    }

    private static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[3];
        if (!input.contains("/")) {
            String[] obtainedDescription = input.split(" ", 2);
            if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
                returnSplit[DESCRIPTION] = "";
            } else {
                returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
            }
            returnSplit[TIME] = "";
            returnSplit[TIME_INCLUDED] = "";
            return returnSplit;
        }
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);
        if (obtainedDescription.length == 1 || obtainedDescription[1].isBlank()) {
            returnSplit[DESCRIPTION] = "";
            returnSplit[TIME] = "";
        } else {
            returnSplit[DESCRIPTION] = obtainedDescription[1].trim();
            returnSplit[TIME] = obtainedSplit[1].trim();
            returnSplit[TIME_INCLUDED] = "yes";
        }
        return returnSplit;
    }

    private static void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    private static void closeScanner(Scanner myInput) {
        myInput.close();
    }



}
