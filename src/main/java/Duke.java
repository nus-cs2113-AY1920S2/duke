import java.util.Scanner;
import java.util.ArrayList;

import parser.ParserUser;
import task.Task;
import storage.Storage;
import task.Task;
import task.TaskType;
import ui.Ui;

public class Duke {

    public static final String COMMAND_CLEAR_ALL_TASKS = "clear";
    public static final String COMMAND_CREATE_TODO_TASK = "todo";
    public static final String COMMAND_CREATE_DEADLINE_TASK = "deadline";
    public static final String COMMAND_CREATE_EVENT_TASK = "event";
    public static final String COMMAND_DELETE_TASK = "delete";
    public static final String COMMAND_DISPLAY_HELP = "help";
    public static final String COMMAND_DISPLAY_LIST = "list";
    public static final String COMMAND_END_PROGRAM = "bye";
    public static final String COMMAND_FIND_TASK = "find";
    public static final String COMMAND_MARK_AS_DONE = "done";
    public static final String COMMAND_SAVE_FILE = "save";
    public static final String FILE_PATH = "data/dukeData.txt";
    public static final String FOLDER_PATH = "data";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfIncompleteTasks = 0;

    /**
     * The main program of Duke.
     * Detect and load memory from the memory file when Duke first starts.
     * Execute methods based on the input entered by the user.
     * @param args Not used
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage.createNewFolder(FOLDER_PATH);
        Storage.createNewFile(FILE_PATH);
        Storage.loadFile(FILE_PATH, tasks);
        numberOfIncompleteTasks = getIncomplete(tasks);

        String command;
        Ui.printPageBreak();
        ui.printWelcomeMessage();
        loop:  while(true) {
            Scanner input = new Scanner(System.in);
            command = input.next();
            Ui.printPageBreak();
            TaskType taskType;
            String taskDetails;
            ParserUser parsedTaskDetails;
            String taskIndex;

            switch(command) {
            case COMMAND_CLEAR_ALL_TASKS: //Delete all tasks stored in the tasks ArrayList
                System.out.println("Warning! This command will delete all your tasks, proceed?(Y/N)");
                String reply = input.next();
                if (reply.equals("Y") || reply.equals("y")) {
                    Storage.saveFile(FILE_PATH, tasks);
                    System.out.println("Your tasks have been cleared");
                    clearTasks(tasks);
                } else {
                    System.out.println("Command has been cancelled");
                }
                break;
            case COMMAND_CREATE_DEADLINE_TASK: //Create a task with Deadline taskType
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    parsedTaskDetails = new ParserUser(taskDetails);
                    taskType = TaskType.D;
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_CREATE_EVENT_TASK: //Create a task with Event taskType
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    taskType = TaskType.E;
                    parsedTaskDetails = new ParserUser(taskDetails);
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_CREATE_TODO_TASK: //Create a task with Todo taskType
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    parsedTaskDetails = new ParserUser(taskDetails);
                    taskType = TaskType.T;
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_DISPLAY_HELP: //Display the available commands for user to refer to
                ui.printHelp();
                break;
            case COMMAND_DISPLAY_LIST: //Display a list of tasks stored in the current tasks ArrayList
                printTaskList(tasks, numberOfIncompleteTasks);
                break;
            case COMMAND_FIND_TASK: //Find a task based on the search term entered
                taskDetails = input.nextLine().trim();
                findTasks(tasks, taskDetails);
                break;
            case COMMAND_END_PROGRAM: //Terminate Duke
                System.out.println("Would you like to save your changes? (Y/N)");
                reply = input.next();
                if (reply.equals("Y") || reply.equals("y")) {
                    Storage.saveFile(FILE_PATH, tasks);
                    System.out.println("Your changes have been saved successfully");
                }
                System.out.println("Bye. Hope to see you again soon!");
                break loop;
            case COMMAND_MARK_AS_DONE: //Mark the task as done based on its index number shown in the "list" command
                taskIndex = input.next();
                if(taskIndex == null || taskIndex.isEmpty()) {
                    System.out.println("The task index number cannot be left empty!");
                } else {
                    int doneTaskIndex = Integer.parseInt(taskIndex) - 1;
                    if (!tasks.get(doneTaskIndex).isDone()) {
                        --numberOfIncompleteTasks;
                        tasks.get(doneTaskIndex).markTaskAsDone(numberOfIncompleteTasks);
                    } else {
                        System.out.println(tasks.get(doneTaskIndex).getTaskDetails() + " is already done!");
                    }
                }
                break;
            case COMMAND_DELETE_TASK: //Delete the task based on its index number shown in the "list" command
                taskIndex = input.next();
                if(taskIndex == null || taskIndex.isEmpty()) {
                    System.out.println("The task index number cannot be left empty!");
                } else {
                    int deleteTaskIndex = Integer.parseInt(taskIndex) - 1;
                    if (tasks.size() > deleteTaskIndex || deleteTaskIndex < 0) {
                        if (!tasks.get(deleteTaskIndex).isDone()) {
                            --numberOfIncompleteTasks;
                        }
                        deleteTask(tasks, deleteTaskIndex);
                    } else {
                        System.out.println("The task does not exist!");
                    }
                }
                break;
            case COMMAND_SAVE_FILE: //Write the current ArrayList into the memory file
                Storage.saveFile(FILE_PATH, tasks);
                System.out.println("Your changes have been saved successfully");
                break;

            default: //Display an error message when the user input is not a valid command
                System.out.println("I don't understand what you are saying! Please enter a valid command!");
                break;
            }
        }
    }

    /**
     * Print the list of tasks in the ArrayList when the "list" command is entered
     * @param tasks  ArrayList of tasks in Duke
     * @param numberOfIncompleteTasks the number of incomplete tasks in the tasks ArrayList
     */
    public static void printTaskList(ArrayList<Task> tasks, int numberOfIncompleteTasks) {
        System.out.println("Here is a list of all your tasks:");
        int taskIndex = 1;
        for (Task task: tasks){
            System.out.print(taskIndex + ". ");
            task.printListMessage();
            ++taskIndex;
        }
        System.out.println("Total number of incomplete tasks: " + numberOfIncompleteTasks);
    }

    /**
     * Delete a task in the tasks ArrayList when the "delete" command is entered.
     * @param tasks  ArrayList of tasks in Duke
     * @param taskIndex the index number of the task to be deleted
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(tasks.get(taskIndex).getTaskDetails() + " has been deleted!");
        tasks.remove(taskIndex);
    }

    /**
     * Get the number of tasks in the ArrayList that are marked as "Not Done".
     * @param tasks  ArrayList of tasks in Duke
     * @return the number of incomplete tasks in the tasks ArrayList
     */
    public static int getIncomplete(ArrayList<Task> tasks) {
        int numberOfIncompleteTasks = 0;
        for (Task task: tasks) {
            if (!task.isDone()) {
                ++numberOfIncompleteTasks;
            }
        }
        return numberOfIncompleteTasks;
    }

    /**
     *Find all tasks that contains the search term within its taskDetails when the "find" command is entered.
     * @param tasks ArrayList of tasks in Duke
     * @param input the search term input from the user
     */
    public static void findTasks(ArrayList<Task> tasks, String input){
        System.out.println("Here are the relevant tasks in your list:");
        int taskIndex = 1;
        for (Task task: tasks) {
            String[] details = task.getTaskDetails().split(" ");
            for (String detail: details) {
                if (detail.equals(input)) {
                    System.out.print(taskIndex + ". ");
                    task.printListMessage();
                    ++taskIndex;
                }
            }
        }
    }

    /**
     * Delete all tasks when the "clear" command is entered.
     * @param tasks ArrayList of tasks in Duke
     */
    public static void clearTasks(ArrayList<Task> tasks) {
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            deleteTask(tasks, 0);
        }
    }
}

