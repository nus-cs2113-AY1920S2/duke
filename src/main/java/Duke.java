import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int TASK_NUMBER = 100;
    public static final String ADD_TASK = "1";
    public static final String PRINT_TASKS = "2";
    public static final String EXIT_COMMAND = "3";
    public static int Task_ind = 0;
    private static TaskManager[] manageTask = new TaskManager[TASK_NUMBER];

    public static void main(String[] args) {
        printIntro();
        printExeType();
        selectTaskType(manageTask);
        printExit();
    }

    public static void selectTaskType(TaskManager[] manageTask) {
        Scanner userInput = new Scanner(System.in);
        String exeType = getUserInput(userInput);// exeType = addTask||deleteTask||printTask
        String task, by, taskType; //taskType = ToDo||Event||Deadline
        while (!exeType.equals(EXIT_COMMAND)) {
            if (exeType.equals(ADD_TASK)) {
                printTaskType();
                taskType = getUserInput(userInput);
                switch (taskType) {
                case "1": //ToDo
                    userInputTask();
                    task = getUserInput(userInput);
                    manageTask[Task_ind] = new ToDo(task);
                    break;
                case "2": //Deadline
                    userInputTask();
                    task = getUserInput(userInput);
                    System.out.println("    Please enter the deadline of your task: ");
                    by = getUserInput(userInput);
                    manageTask[Task_ind] = new Deadline(task, by);
                    break;
                case "3": //Event
                    userInputTask();
                    task = getUserInput(userInput);
                    System.out.println("    Please enter the venue of your task: (format at: )");
                    by = getUserInput(userInput);
                    manageTask[Task_ind] = new Deadline(task, by);
                    break;
                default: System.out.println("    Sorry I don't understand your command :(");
                }
            } else if (exeType.equals(PRINT_TASKS)) {
                printCommand();
            } else {
                System.out.println("    Sorry I don't understand your command :(");
            }
            printExeType();
            exeType = getUserInput(userInput);
            Task_ind++;
        }
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
                "    3. See you next time! \n" +
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
    public static void userInputTask() {
        System.out.println("    Please enter the task: ");
    }

    private static String getUserInput(Scanner userInput) {
        return userInput.nextLine();
    }

    public static void printCommand(){
        for (int i=0; i<Task_ind;i++){
            System.out.println(manageTask[i]);
        }
    }
    public static void printExit() {
        System.out.println("    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁\n" +
                        "    Bye. Hope to see you again soon!\n" +
                        "    ✻❊✽✼❉✱✲✾❃❋❈❆✿❀❁" );
    }
    Deadline d = new Deadline("Read textbook", "Nov 16");




}

