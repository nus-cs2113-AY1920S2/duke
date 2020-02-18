package Duke;

import Exceptions.NoParameterException;
import Exceptions.emptyListException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /*
    List of exceptions handled:

    1. General commands
        a. No recognised command given
        b. No follow up parameters in command

    2. Done command
        a. Out of range
        b. Not integer

     */


public class Duke {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;
    public static final int SIZE_DONE_COMMAND = 2;
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Save");
    public static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Save", "data.txt");
    public static final int SIZE_DELETE_COMMAND = 2;
    public static final int LIST_TO_INDEX = 1;


    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static void completeTask(ArrayList<Task> tasks, int taskIndex) {
        taskIndex--; // index starts from 0, unlike listing number
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            if (currentTask.getStatus()) { // check if already completed
                System.out.println("Duke.Task already completed!\n");
            } else {
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( "["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) throws emptyListException {

        if(tasks.isEmpty()) {
            throw  new emptyListException();
        }
        taskIndex -= LIST_TO_INDEX;
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            tasks.remove(taskIndex);
            System.out.println("Now you have " + tasks.size() +" tasks in the list.\n");
            rebuildTaskFile(tasks);
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                currentTask.printListDetails(count);
                count++;
            }
        }
        System.out.println("");
    }

    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("Todo: Tasks without date/time \nUsage: todo <task> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Event: Duke.Event including date/time \nUsage: event <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Deadline: Tasks including date/time \nUsage: deadline <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("");
    }

    private static void printWelcomeMessage() {
        String logo =   "  ,--,       ,---,   .--.--.       ,---,\n"
                + ",--.'|    ,`--.' |  /  /    '.    '  .' \\\n"
                + "|  | :    |   :  : |  :  /`. /   /  ;    '.\n"
                + ":  : '    :   |  ' ;  |  |--`   :  :       \\\n"
                + "|  ' |    |   :  | |  :  ;_     :  |   /\\   \\\n"
                + "'  | |    '   '  ;  \\  \\    `.  |  :  ' ;.   :\n"
                + "|  | :    |   |  |   `----.   \\ |  |  ;/  \\   \\\n"
                + "'  : |__  '   :  ;   __ \\  \\  | '  :  | \\  \\ ,'\n"
                + "|  | '.'| |   |  '  /  /`--'  / |  |  '  '--'\n"
                + ";  :    ; '   :  | '--'.     /  |  :  :\n"
                + "|  ,   /  ;   |.'    `--'---'   |  | ,'\n"
                + "---`-'   '---'                 `--''\n";

        System.out.println("\n" + logo + "\nYour Lifestyle Scheduling Assistant\n");
        System.out.println("type \"help\" for list of commands");
        System.out.println("____________________________________________________________\n");
        System.out.println("Everyday is a sunny day!");
        /*
        System.out.println("____________________________________________________________\n\nCurrent time: ");
        getDateTime(); // due to testing purposes, will fail when comparing timestamps
        */
        System.out.println("____________________________________________________________");
    }

    public static String[] processDatedTasks(String taskDescription, int taskCounter, int wordLength, int commandLength) throws NoParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        try {
            String itemName = taskDescription.substring(commandLength);
            String[] words = itemName.split("/"); // potential problem is not accepting date format with '/' inside, throw err if more than 2 len
            for (int i = 0; i < words.length; i++) { // checking for blank tasks, including one char of space
                if (words[i].isBlank()){
                    throw new NoParameterException();
                }
            }
            return words;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
            taskCounter--;
        }
        System.out.println("Ugh null");
        return null;
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        try {
            String[] words = processDatedTasks(taskDescription, taskCounter, wordLength, LENGTH_DEADLINE);
            Task newTask = new Deadline(words[0].trim(), words[1].trim());
            tasks.add(newTask);
            newTask.printAddDetails(taskCounter);
            saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        try {
            String[] words = processDatedTasks(taskDescription, taskCounter, wordLength, LENGTH_EVENT);
            Task newTask = new Event(words[0].trim(), words[1].trim());
            tasks.add(newTask);
            newTask.printAddDetails(taskCounter);
            saveTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the date using the specified format");
        }
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {
        if (wordLength <= 1) { // empty parameter
            throw new NoParameterException();
        }
        String itemName = taskDescription.substring(LENGTH_TODO);
        if (itemName.isBlank()) { // task is a space/blank char
            throw new NoParameterException();
        }
        Task newTask = new ToDo(itemName.trim());
        tasks.add(newTask);
        newTask.printAddDetails(taskCounter);
        saveTask(newTask);
    }

    public static void checkFolderPath() {
        // Locate folder location, if missing create folder
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        try {
            if (!directoryExists) {
                Files.createDirectory(FOLDER_PATH);
                System.out.println("Directory created");
            }
        } catch (IOException e) {
            System.out.println("Error creating folder!\n");
        }
    }

    private static void writeTaskToFile(Task newTask, FileWriter myWriter) throws IOException {
        if (newTask.getDate().isEmpty()) {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + "\n");
        } else {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + " | " + newTask.getDate() + "\n");
        }
    }

    public static void saveTask(Task newTask) {
        // Append new task to file
        try {
            File file = new File(String.valueOf(FILE_PATH));
            FileWriter myWriter = new FileWriter(file, true);

            writeTaskToFile(newTask, myWriter);
            myWriter.close();
            System.out.println("Successfully updated data file!\n");
        } catch (IOException e) {
            System.out.println("Error updating file!\n");
        }
    }

    public static void rebuildTaskFile(ArrayList<Task> tasks) {
        // replace current list with new updated list
        try {
            File file = new File(String.valueOf(FILE_PATH));
            FileWriter myWriter = new FileWriter(file, false);
            for (Task newTask : tasks) {
                writeTaskToFile(newTask, myWriter);
            }
            myWriter.close();
            System.out.println("Successfully updated data file!\n");
        } catch (IOException e) {
            System.out.println("Error updating file!\n");
        }
    }

    public static void uploadTodo(ArrayList<Task> tasks, String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        Task newTask = new ToDo(taskDescription);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public static void uploadDeadline(ArrayList<Task> tasks, String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        String taskDate = words[3];
        Task newTask = new Deadline(taskDescription, taskDate);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);

    }

    public static void uploadEvent(ArrayList<Task> tasks, String[] words ) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);;
        String taskDescription = words[2];
        String taskDate = words[3];
        Task newTask = new Event(taskDescription, taskDate);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public static void processDataFile(String oneLine, ArrayList<Task> tasks) {
        String[] words = oneLine.split(" \\| ");

        switch (words[0]) {
        case "T":
            uploadTodo(tasks, words);
            break;
        case "D":
            uploadDeadline(tasks, words);
            break;
        case "E":
            uploadEvent(tasks, words);
            break;
        default:
            // in case user touches txt file and fills with random data
            System.out.println("Line not added: " + oneLine + "\n");
            break;
        }

    }

    public static void populateList(ArrayList<Task> tasks) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(String.valueOf(FILE_PATH)));
            for (String oneLine : allLines) {
                processDataFile(oneLine, tasks);
            }
        } catch (IOException e) {
            System.out.println("Error reading data file! \n");
        }
    }

    public static void main(String[] args) {

        printWelcomeMessage();
        checkFolderPath();
        ArrayList<Task> tasks = new ArrayList<Task>();
        populateList(tasks);

        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

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
