import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int TASK_NUMBER = 100;
    public static final String ADD_TASK = "1";
    public static final String PRINT_TASKS = "2";
    public static final String MARK_AS_DONE = "3";
    public static final String EXIT_COMMAND = "4";
    public static int Task_ind = 0;
    private static TaskManager[] manageTask = new TaskManager[TASK_NUMBER];
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        printIntro();
        printExeType();
        exeCommand(manageTask);
        printExit();
    }

    public static void exeCommand(TaskManager[] manageTask) {
        String exeCommand = getUserInput(userInput);// exeType = addTask||deleteTask||printTask
        while (!exeCommand.equals(EXIT_COMMAND)) {
            exeType(exeCommand); //to select the exeType and execute the command type
            printExeType(); //show the instructions after execution
            exeCommand = getUserInput(userInput); //get the next command
        }
    }

    public static void exeType(String exeCommand){
        String taskType; //taskType = ToDo||Event||Deadline
        switch (exeCommand) {
        case ADD_TASK:
            printTaskType();
            taskType = getUserInput(userInput);
            exeTask(taskType);
            break;
        case PRINT_TASKS:
            printTasks();
            break;
        case MARK_AS_DONE:
            doneTask();
            break;
        default:
            System.out.println("    Sorry I don't understand your command :(");
            break;
        }
    }

    public static void exeTask(String taskType){
        String task, by;
        switch (taskType) {
        case "1": //ToDo
            userInputTask();
            task = getUserInput(userInput);
            manageTask[Task_ind] = new ToDo(task);
            Task_ind++;
            System.out.println("    You have "+ Task_ind + " task(s) now in total");
            break;
        case "2": //Deadline
            userInputTask();
            task = getUserInput(userInput);
            System.out.println("    Please enter the deadline of your task: ");
            by = getUserInput(userInput);
            manageTask[Task_ind] = new Deadline(task, by);
            Task_ind++;
            System.out.println("    You have "+ Task_ind + " task(s) now in total");
            break;
        case "3": //Event
            userInputTask();
            task = getUserInput(userInput);
            System.out.println("    Please enter the venue of your task: (format at: )");
            by = getUserInput(userInput);
            manageTask[Task_ind] = new Deadline(task, by);
            Task_ind++;
            System.out.println("    You have "+ Task_ind + " task(s) now in total");
            break;
        default: System.out.println("    Sorry I don't understand your command :(");
        }
    }

    public static void doneTask() {
        System.out.println("    Please choose the task that you have completed (select the no)");
        printTasks();
        int doneTask = userInput.nextInt();
        if (doneTask < Task_ind) {
            manageTask[doneTask].markAsDone();
        }
        System.out.println("    Congrats! Task " + doneTask + ": " + manageTask[doneTask] +
                " has been completed!");
    }

    public static void userInputTask() {
        System.out.println("    Please enter the task: ");
    }

    private static String getUserInput(Scanner userInput) {
        return userInput.nextLine();
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
                "    Hi what can I do for you? (please key in the number): \n" +
                "    1. Add a new task, \n" +
                "    2. Show my tasks, or\n"+
                "    3. I've completed my task!\n" +
                "    4. See you next time! \n" +
                "    to end this conversation" +
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n");
    }

    public static void printTaskType() {
        System.out.println("    Which category does your task belong to? \n"+
                "   1. ToDos: tasks without any date/time attached to it (e.g., visit new theme park)\n" +
                "   2. Deadlines: tasks that need to be done before a specific date/time" +
                " (e.g., submit report by 11/10/2019 5pm)\n" +
                "   3. Events: tasks that start at a specific time and ends at a specific time" +
                " (e.g., team project meeting on 2/10/2019 2-4pm)") ;
    }

    public static void printTasks() {
        for (int i = 0; i < Task_ind; i++){
            System.out.println("Task " + i + ": " + manageTask[i]);
        }
    }

    public static void printExit() {
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁" );
    }
}

