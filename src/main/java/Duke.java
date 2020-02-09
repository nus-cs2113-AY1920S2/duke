import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "\t__________________________________________________________";
    public static final String[] COMMAND= {"todo", "deadline", "event", "done", "bye", "list", "help"};
    public static final String WRONG_INPUT="\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String OUT_OF_BOUND_INDEX = "\t Task number provided is not valid. Press \"list\" to see\n" +
            "\t available list of task numbers";
    public static final String LIST_EMPTY= "\t Oops! No task has been assigned yet! Please enter a task\n\t before" +
            " listing";
    public  static final String NO_TASK_NUMBER = "\t Please enter the task number to be mark as done!";

    public static void printError(String message){
        System.err.println(LINE + System.lineSeparator()+ message + System.lineSeparator() + LINE);
    }
    public static String[] getCommand(String inCommand) {
        inCommand = inCommand.trim();
        String[] temp = inCommand.split(" ");
        return temp;
    }
    public static void listCommands() {
        System.out.println(LINE);
        System.out.println("\t "+ Arrays.toString(COMMAND));
        System.out.println(LINE);
    }

    public static void executeBye() {
        System.out.println(LINE);
        System.out.println("\t Bye.Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void executeList(ArrayList<Task>l1) throws IllegalStateException{
        if(l1.isEmpty()) {
            throw new IllegalStateException(LIST_EMPTY);
        }
        int i = 0;
        System.out.println(LINE);
        System.out.print("\t Here are the tasks in your list:\n");
        for (i = 0; i < l1.size(); i++) {
            int count = i + 1;
            Task temp = l1.get(i);
            System.out.println("\t " + count + "." + temp.toString());
        }
        System.out.println(LINE);
    }

    public static void executeDone(String userIn, ArrayList<Task> l1) throws ArrayIndexOutOfBoundsException,
            IllegalDukeException{
        String[] temp = getCommand(userIn);
        if(temp.length>1) {
            int number = Integer.parseInt(temp[1]) - 1;
            if(number>=l1.size() || number<0) {
                throw new ArrayIndexOutOfBoundsException(OUT_OF_BOUND_INDEX);
            }
            Task tempTask = l1.get(number);
            tempTask.done();
            System.out.println(LINE);
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t   "+tempTask.toString());
            System.out.println(LINE);
        } else {
            throw new IllegalDukeException(NO_TASK_NUMBER);
        }
    }
    public static String[] getTaskInfo(String userIn) throws IllegalDukeException{
        String[] temp = getCommand(userIn);
        if(temp.length==1){
            throw new IllegalDukeException("\t ☹ OOPS!!! The description of a " + temp[0] +" cannot be empty.");
        }
        String action = "";
        String timing = "";
        boolean flip = false;
        for (int i = 1; i < (temp.length); i++) {
            if (temp[i].charAt(0) == '/') {
                flip = true;
            }
            if (flip) {
                timing += temp[i] + " ";
            } else {
                action += temp[i] + " ";
            }
        }
        action = action.trim();
        timing = timing.trim();
        timing=timing.replace('/',' ');
        String[] temp2 = new String[2];
        temp2[0] = action;
        temp2[1] = timing;
        return temp2;
    }
    public static void addTask(ArrayList<Task>l1, Task task){
        System.out.println(LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   "+task.toString());
        System.out.println("\t Now you have " + l1.size()+ " tasks in the list.");
        System.out.println(LINE);
    }

    public static void addTodo(ArrayList<Task> l1, String[] taskInfo){
        Todo todo = new Todo(taskInfo[0]);
        l1.add(todo);
        addTask(l1, todo);
    }
    public static void addDeadline(ArrayList<Task> l1, String[] taskInfo){
        Deadline deadline= new Deadline(taskInfo[0]);
        deadline.setBy(taskInfo[1]);
        l1.add(deadline);
        addTask(l1, deadline);
    }
    public static void addEvent(ArrayList<Task> l1, String[] taskInfo){
        Event event = new Event(taskInfo[0]);
        event.setAt(taskInfo[1]);
        l1.add(event);
        addTask(l1, event);
    }

    public static int validateCommand(String inCommand, ArrayList<Task> l1) throws IllegalDukeException {
        String[] userCommand = getCommand(inCommand);
        String[] taskInfo= new String[2];
        int status;
            switch (userCommand[0]) {
                case "bye":
                    status = 1;
                    executeBye();
                    break;
                case "list":
                    status = 2;
                    executeList(l1);
                    break;
                case "done":
                    status = 3;
                    executeDone(inCommand, l1);
                    break;
                case "todo":
                    status = 4;
                    taskInfo = getTaskInfo(inCommand);
                    addTodo(l1, taskInfo);
                    break;
                case "deadline":
                    status = 5;
                    taskInfo = getTaskInfo(inCommand);
                    Deadline deadline = new Deadline(taskInfo[0]);
                    addDeadline(l1, taskInfo);
                    break;
                case "event":
                    status = 6;
                    taskInfo = getTaskInfo(inCommand);
                    addEvent(l1, taskInfo);
                    break;
                case "help":
                    status = 7;
                    listCommands();
                    break;
                default:
                    status = -1;
                    throw new IllegalDukeException(WRONG_INPUT);
            }
            return status;
    }

    public static void printWelcomeMessage() {
        String banner = "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = "\t~~~~~N N N N  N N N~~~I I I~~~N N N N  N N N~~~I I I~~~~~\n"
                + "\t~~~~~N      N N   N~~~I   I~~~N      N N   N~~~I   I~~~~~\n"
                + "\t~~~~~N   N   NN   N~~~I   I~~~N   N   NN   N~~~I   I~~~~~\n"
                + "\t~~~~~N   NN   N   N~~~I   I~~~N   NN   N   N~~~I   I~~~~~\n"
                + "\t~~~~~N   N N      N~~~I   I~~~N   N N      N~~~I   I~~~~~\n"
                +"\t~~~~~N N N  N N N N~~~I I I~~~N N N  N N N N~~~I I I~~~~~\n";
        System.out.println(LINE);
        System.out.println("\t" + "Hello from\n" + banner + logo + banner);
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> l1 = new ArrayList<Task>();
        int status = 0;
        printWelcomeMessage();
            while (status != 1) {
                try {
                    String userIn = in.nextLine();
                    status = validateCommand(userIn, l1);
                } catch (IllegalDukeException | ArrayIndexOutOfBoundsException| IllegalStateException e ) {
                    printError(e.getMessage());
                }
            }
    }
}
