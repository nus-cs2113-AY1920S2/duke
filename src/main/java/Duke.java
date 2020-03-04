import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String COMMAND_CREATE_TODO_TASK = "todo";
    public static final String COMMAND_CREATE_DEADLINE_TASK = "deadline";
    public static final String COMMAND_CREATE_EVENT_TASK = "event";
    public static final String COMMAND_DISPLAY_HELP = "help";
    public static final String COMMAND_DISPLAY_LIST = "list";
    public static final String COMMAND_DELETE_TASK = "delete";
    public static final String COMMAND_END_PROGRAM = "bye";
    public static final String COMMAND_FIND_TASK = "find";
    public static final String COMMAND_SAVE_FILE = "save";
    public static final String COMMAND_MARK_AS_DONE = "done";
    public static final String FILE_PATH = "data/dukeData.txt";
    public static final String FOLDER_PATH = "data";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfIncompleteTasks = 0;

    public static void main(String[] args) {

        Storage.createNewFolder(FOLDER_PATH);
        Storage.createNewFile(FILE_PATH);
        Storage.loadFile(FILE_PATH, tasks);
        numberOfIncompleteTasks = getIncomplete(tasks);

        String command;
        printPageBreak();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        loop:  while(true) {
            Scanner input = new Scanner(System.in);
            command = input.next();
            printPageBreak();
            TaskType taskType;
            String taskDetails;
            Parser parsedTaskDetails;
            String taskIndex;

            switch(command) {
            case COMMAND_CREATE_DEADLINE_TASK:
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    parsedTaskDetails = new Parser(taskDetails);
                    taskType = TaskType.D;
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_CREATE_EVENT_TASK:
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    taskType = TaskType.E;
                    parsedTaskDetails = new Parser(taskDetails);
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_CREATE_TODO_TASK:
                taskDetails =  input.nextLine();
                if (taskDetails == null || taskDetails.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    parsedTaskDetails = new Parser(taskDetails);
                    taskType = TaskType.T;
                    parsedTaskDetails.userInput(taskType, tasks);
                    ++numberOfIncompleteTasks;
                }
                break;
            case COMMAND_DISPLAY_HELP:
                printHelp();
                break;
            case COMMAND_DISPLAY_LIST:
                printTaskList(tasks, numberOfIncompleteTasks);
                break;
            case COMMAND_FIND_TASK:
                taskDetails = input.nextLine();
                findTasks(tasks, taskDetails);
                break;
            case COMMAND_END_PROGRAM:
                System.out.println("Would you like to save your changes? (Y/N)");
                String reply = input.next();
                if (reply.equals("Y") || reply.equals("y")) {
                    Storage.saveFile(FILE_PATH, tasks);
                    System.out.println("Your changes have been saved successfully");
                }
                System.out.println("Bye. Hope to see you again soon!");
                break loop;

            case COMMAND_MARK_AS_DONE:
                taskIndex = input.next();
                if(taskIndex == null || taskIndex.isEmpty()) {
                    System.out.println("The task index number cannot be left empty!");
                } else {
                    int doneTaskIndex = Integer.parseInt(taskIndex) - 1;
                    if (tasks.get(doneTaskIndex).isDone == false) {
                        --numberOfIncompleteTasks;
                        tasks.get(doneTaskIndex).markTaskAsDone(numberOfIncompleteTasks);
                    } else {
                        System.out.println(tasks.get(doneTaskIndex).taskDetails + " is already done!");
                    }
                }
                break;
            case COMMAND_DELETE_TASK:
                taskIndex = input.next();
                if(taskIndex == null || taskIndex.isEmpty()) {
                    System.out.println("The task index number cannot be left empty!");
                } else {
                    int deleteTaskIndex = Integer.parseInt(taskIndex) - 1;
                    if (tasks.size() > deleteTaskIndex || deleteTaskIndex < 0) {
                        --numberOfIncompleteTasks;
                        deleteTask(tasks, deleteTaskIndex);
                    } else {
                        System.out.println("The task does not exist!");
                    }
                }
                break;
            case COMMAND_SAVE_FILE:
                Storage.saveFile(FILE_PATH, tasks);
                System.out.println("Your changes have been saved successfully");
                break;

            default:
                System.out.println("I don't understand what you are saying! Please enter a valid command!");
                break;
            }
        }
    }

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

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(tasks.get(taskIndex).taskDetails + " has been deleted!");
        tasks.remove(taskIndex);
    }

    public static void printHelp(){
        System.out.println("Duke supports the following commands\nPlease enter the keyword followed by details required in the <>\n");
        System.out.println("todo <task details> --------------------------- Create a new task");
        System.out.println("deadline <task details> /by: <timing details> - Create a new task with a time element");
        System.out.println("event <event details> /at: <timing details> --- Create a new event with a time element");
        System.out.println("done <task number> ---------------------------- Mark task as done");
        System.out.println("delete <task number> -------------------------- Delete task");
        System.out.println("list ------------------------------------------ List all available tasks and events");
        System.out.println("bye ------------------------------------------- Shutdown Duke\n");
    }

    public static void printPageBreak() {
        System.out.println("=====================================================================================================");
    }

    public static int getIncomplete(ArrayList<Task> tasks) {
        int numberOfIncompleteTasks = 0;
        for (Task task: tasks) {
            if (!task.isDone) {
                ++numberOfIncompleteTasks;
            }
        }
        return numberOfIncompleteTasks;
    }
    public static void findTasks(ArrayList<Task> tasks, String input){
        System.out.println("Here are the relevant tasks in your list:");
        int taskIndex = 1;
        for (Task task: tasks) {

            if (task.taskDetails.contains(input)) {
                System.out.println(taskIndex + ". ");
                task.printListMessage();
                ++taskIndex;
            }
        }
    }
}

