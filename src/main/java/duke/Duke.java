package duke;

<<<<<<< HEAD
import duke.Util.*;
import duke.taskmanager.Tasks;
=======
import duke.exceptions.IllegalDeleteException;
import duke.exceptions.IllegalDoneTaskException;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.TaskManager;
import duke.taskmanager.ToDo;
>>>>>>> branch-Level-9

import java.io.IOException;
import java.util.List;

public class Duke {
<<<<<<< HEAD
    private UI ui;
    private static List<Tasks> tasks;
    public Duke() throws IOException {
        ui = new UI();
        TaskList tasklist = new TaskList();
        tasks = tasklist.getTasks();
=======
    public static final int TASK_NUMBER = 100;
    public static final String ADD_TASK = "1";
    public static final String PRINT_TASKS = "2";
    public static final String MARK_AS_DONE = "3";
    public static final String DELETE_TASK = "4";
    public static final String FIND_TASK = "5";
    public static final String EXIT_COMMAND = "6";
    public static final String TODO = "1";
    public static final String DEADLINE = "2";
    public static final String EVENT = "3";
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
>>>>>>> branch-Level-9
    }
//    private TaskList tasks;
//
//    public Duke(String filePath) {
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList();
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }

    public static void main(String[] args) throws IOException {
<<<<<<< HEAD
        Duke duke = new Duke();
        duke.run();
    }
    public void run() throws IOException {
        String EXIT_COMMAND = "7";
        ui.printIntro();
        ui.printExeType();
        String exeCommand = ui.getStringInput();
        while (!exeCommand.equals(EXIT_COMMAND)) {
            Parser parser = new Parser(ui, tasks);
            parser.parseCommand(exeCommand); //to select the exeType and execute it
            ui.printExeType(); //user guide after execution of command
            exeCommand = ui.getStringInput(); //get the next command
        }
        ui.printExit();
=======
        printIntro();
        printExeType();
        String exeCommand = getStringInput(userInput);
        while (!exeCommand.equals(EXIT_COMMAND)) {
            exeType(exeCommand); //to select the exeType and execute it
            printExeType(); //user guide after execution of command
            saveFile();
            exeCommand = getStringInput(userInput); //get the next command
        }
        printExit();
        Duke_fw.close();
    }

    /*
    exeType receives the command from exeCommand and it executes the command received.
     */
    public static void exeType(String exeCommand)  {
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
            case FIND_TASK:
                findTask();
                break;
            default:
                System.out.println("    Sorry,we do not understand your command. " +
                        "Please follow the instructions below.");
            }
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

    /*
    addTask adds task to manageTask array.
    Firstly, addTask gets the type of task input from the user.
    Secondly, based on the type, it will prompt the user to key
    in the task (and by...).
    Lastly, it adds the task (and by...) to the manageTask array.
     */
    public static void addTask()  {
        String taskType; //taskType = ToDoo||Event||Deadline
        taskType = getStringInput(userInput);
        String task, by;
        printUserInputTask();
        task = getStringInput(userInput);
        boolean repeat = false;
        for (TaskManager i : manageTask) {
            if (i != null && i.task.equals(task)) {
                System.out.println("    The is a repeated task.");
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            switch (taskType) {
            case TODO:
                manageTask[Task_No] = new ToDo(task);
                Task_No++;
                printRespondToAddTask(task);
                break;
            case DEADLINE:
                System.out.println("    Please enter the deadline of your task: ");
                by = getStringInput(userInput);
                manageTask[Task_No] = new Deadline(task, by);
                Task_No++;
                printRespondToAddTask(task);
                break;
            case EVENT:
                System.out.println("    Please enter the venue of your task: ");
                by = getStringInput(userInput);
                manageTask[Task_No] = new Event(task, by);
                Task_No++;
                printRespondToAddTask(task);
                break;
            default:
                System.out.println("    Sorry,we do not understand your command. " +
                        "Please follow the instructions below.");
                exeType(ADD_TASK);
            }
        }
    }

    /*
    deleteTask deletes a certain task from manageTask array.
    It prompts the user to key in the task index and show task
    list. Then delete the element of respective index in the
    manageTask array.
     */
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

    /*
   doneTask mark a certain task in manageTask array as done.
   It prompts the user to key in the task index and show task
   list. Then mark the element of respective index in the
   manageTask array as a done task.
    */
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

    public static void findTask() {
        System.out.println("    Please enter the task that you want to find:");
        String task = getStringInput(userInput);
        boolean isfound = false;
        if (manageTask == null) {
            System.out.println("    You have no task in list.");
        } else {
            for (int i = 0; i < Task_No; i++) {
                if (manageTask[i] != null) {
                    String[] task_split = manageTask[i].task.split(" ", 5);
                    for (String j : task_split) {
                        if (j != null && j.equals(task)) {
                            System.out.println("    Task " + i + ". " + manageTask[i]);
                            isfound = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!isfound) {
            System.out.println("    No task found contains "+ task);
        }
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
        System.out.println("    ================================\n" +
                "    Hi what can I do for you? (please key in the number):" +
                "\n    1. Add a new task, \n" +
                "    2. Show my tasks,\n"+
                "    3. I've completed my task!\n" +
                "    4. Delete a task,  or \n" +
                "    5. Find a task,  or \n" +
                "    6. See you next time! \n" +
                "    to end this conversation \n" +
                "    ================================\n");
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
        System.out.println("    ================================\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ================================" );
    }

    public static void clearInput(){
        userInput.nextLine();
    }

    public static void saveFile() throws IOException {
        for (int i = 0; i < Task_No; i++){
            Duke_fw.write(manageTask[i].toString()+"\n");
        }
>>>>>>> branch-Level-9
    }
}
