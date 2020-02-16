package duke;

import duke.exceptions.IllegalCommandException;
import duke.exceptions.IllegalDeleteException;
import duke.exceptions.IllegalDoneTaskException;
import duke.exceptions.IllegalTypeException;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.TaskManager;
import duke.taskmanager.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final int TASK_NUMBER = 100;
    public static final String ADD_TASK = "1";
    public static final String PRINT_TASKS = "2";
    public static final String MARK_AS_DONE = "3";
    public static final String DELETE_TASK = "4";
    public static final String EXIT_COMMAND = "5";
    public static int Task_No = 0;
    private static TaskManager[] manageTask = new TaskManager[TASK_NUMBER];
    private static Scanner userInput = new Scanner(System.in);
    private static FileWriter Duke_fw;
    static {
        try {
            Duke_fw = new FileWriter("data/TaskManagement.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        printIntro();
        printExeType();
        exeCommands();
        printExit();
        saveFile();
    }

    /*
    exeCommand does the job to identify whether the programme will end
     */
    public static void exeCommands() {
        String exeCommand = getStringInput(userInput);// exeType = addTask||deleteTask||printTask
        try {
            while (!exeCommand.equals(EXIT_COMMAND)) {
                exeType(exeCommand); //to select the exeType and execute the command type
                printExeType(); //show the instructions after execution
                exeCommand = getStringInput(userInput); //get the next command
            }
        } catch (IllegalCommandException e) { //todo
            System.out.println("    Sorry,we do not understand your command. " +
                    "(IllegalCommandException). " +
                    "Please follow the instructions below.");
            printExeType();
            exeCommands();
        }
    }

    public static void exeType(String exeCommand) throws IllegalCommandException {
        try {
            switch (exeCommand) {
            case ADD_TASK:
                printTaskType();
                addTask();
                break;
            case PRINT_TASKS:
                printTasks();
                break;
            case MARK_AS_DONE:
                doneTask();
                break;
            case DELETE_TASK:
                deleteTask();
                break;
            default:
                throw new IllegalCommandException();
            }
        } catch (IllegalTypeException e) {
            System.out.println("    Sorry,we do not understand your command. " +
                    "(IllegalTypeException). " +
                    "Please follow the instructions below.");
            exeType(exeCommand);
        } catch (IllegalDeleteException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDeleteException). ");
            exeType(exeCommand);
        } catch (IllegalDoneTaskException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDoneTaskException). ");
            exeType(exeCommand);
        }
    }

    public static void addTask() throws IllegalTypeException {
        String taskType; //taskType = ToDoo||Event||Deadline
        taskType = getStringInput(userInput);
        String task, by;
        switch (taskType) {
        case "1": //ToDoo
            printUserInputTask();
            task = getStringInput(userInput);
            manageTask[Task_No] = new ToDo(task);
            Task_No++;
            printRespondToAddTask(task);
            break;
        case "2": //Deadline
            printUserInputTask();
            task = getStringInput(userInput);
            System.out.println("    Please enter the deadline of your task: ");
            by = getStringInput(userInput);
            manageTask[Task_No] = new Deadline(task, by);
            Task_No++;
            printRespondToAddTask(task);
            break;
        case "3": //Event
            printUserInputTask();
            task = getStringInput(userInput);
            System.out.println("    Please enter the venue of your task: " +
                    "(format at: )");
            by = getStringInput(userInput);
            manageTask[Task_No] = new Event(task, by);
            Task_No++;
            printRespondToAddTask(task);
            break;
        default: throw new IllegalTypeException();
        }
    }
    public static void deleteTask() throws IllegalDeleteException {
        int index;
        System.out.println("     Please enter the index of the task " +
                "you want to delete");
        printTasks();
        index = getIntegerInput(userInput);
        clearInput();
        if (index < Task_No){
            List <TaskManager> tempList = new ArrayList<>(Arrays.asList(manageTask));
            System.out.println("     You have successfully deleted the task "
                    + manageTask[index]);
            tempList.remove(index);
            Task_No --;
            manageTask = tempList.toArray(new TaskManager[0]);
            printTasks();
        } else {
            throw new IllegalDeleteException();
        }
    }
    public static void doneTask() throws IllegalDoneTaskException {
        System.out.println("    Please choose the task that you have completed " +
                "(select the no)");
        printTasks();
        int doneTask = getIntegerInput(userInput);
        clearInput();
        if (doneTask < Task_No) {
            manageTask[doneTask].markAsDone();
        } else {
            throw new IllegalDoneTaskException();
        }
        System.out.println("    Congrats! Task " + doneTask + ": " +
                manageTask[doneTask] +  " has been completed!");
    }
    
    public static void printRespondToAddTask(String task) {
        System.out.println("    You have successfully added " + task + "!");
        System.out.println("    You have "+ Task_No + " task(s) now in total");
    }

    public static void printUserInputTask() {
        System.out.println("    Please enter the task: ");
    }

    private static String getStringInput(Scanner userInput) {
        return userInput.nextLine();
    }
    private static Integer getIntegerInput(Scanner userInput) {
        return userInput.nextInt();
    }

    public static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
    }

    public static void printExeType() {
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n" +
                "    Hi what can I do for you? (please key in the number):" +
                "\n    1. Add a new task, \n" +
                "    2. Show my tasks,\n"+
                "    3. I've completed my task!\n" +
                "    4. Delete a task,  or \n" +
                "    5. See you next time! \n" +
                "    to end this conversation" +
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n");
    }

    public static void printTaskType() {
        System.out.println("    Which category does your task belong to? \n"+
                "   1. ToDos: tasks without any date/time attached to it " +
                "(e.g., visit new theme park)\n" +
                "   2. Deadlines: tasks that need to be done before a " +
                "specific date/time" +
                " (e.g., submit report by 11/10/2019 5pm)\n" +
                "   3. Events: tasks that start at a specific time and ends at " +
                "a specific time" +
                " (e.g., team project meeting on 2/10/2019 2-4pm)") ;
    }

    public static void printTasks() {
        System.out.println("    Your current task list:");
        if (Task_No == 0){
            System.out.println("    You have no ongoing task.");
        } else {
            for (int i = 0; i < Task_No; i++){
                System.out.println("    Task " + i + ": " + manageTask[i]);
            }
        }
    }

    public static void printExit() {
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁" );
    }

    public static void clearInput(){
        userInput.nextLine();
    }

    public static void saveFile() throws IOException {
        for (int i = 0; i < Task_No; i++){
            Duke_fw.write(manageTask[i].toString()+"\n");
        }
        Duke_fw.close();
    }
}

