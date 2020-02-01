import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "\t__________________________________________________________";
    public static final String[] COMMAND= {"todo", "deadline", "event", "done", "bye", "help"};

    public static String[] getCommand(String inCommand) {
        inCommand = inCommand.trim();
        String[] temp = inCommand.split(" ");
        return temp;
    }

    public static void throwException() {
        System.out.println(LINE);
        System.out.println("\t Input command is wrong. Enter \"help\" for list of accepted");
        System.out.println("\t commands");
        System.out.println(LINE);
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

    public static void executeList(ArrayList<Task>l1) {
        if(l1.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\t Oops! No task has been assigned yet! Please enter a task");
            System.out.println("\t before listing");
            System.out.println(LINE);
            return;
        }
        int i = 0;
        System.out.println(LINE);
        System.out.print("\t Here are the tasks in your list:\n");
        for (i = 0; i < l1.size(); i++) {
            int count = i + 1;
            Task temp = l1.get(i);
            System.out.println("\t " + count +"."+ temp.getTaskDescription().trim());
        }
        System.out.println(LINE);
    }

    public static void executeDone(String userIn, ArrayList<Task> l1) {
        String[] temp = getCommand(userIn);
        int number = Integer.parseInt(temp[1]) - 1;
        if(number>=l1.size()) {
            System.out.println(LINE);
            System.out.println("\t Task number provided is more than available tasks. Press ");
            System.out.println("\"list\" to see the list of available task numbers");
            System.out.println(LINE);
            return;
        }
        Task tempTask = l1.get(number);
        tempTask.done();
        System.out.println(LINE);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   "+tempTask.getTaskDescription());
        System.out.println(LINE);
    }

    public static void updateTask(ArrayList<Task> l1, String userIn, String timing) {
        String[] temp=getCommand(userIn);
        String action="";
        boolean flip=false;
        for(int i=1; i<(temp.length); i++) {
            if (temp[i].charAt(0) == '/') {
                flip=true;
            }
            if(flip){
                timing += temp[i] + " ";
            }else{
                action += temp[i] + " ";
            }
        }
        action=action.trim();
        timing=timing.trim();
        timing=timing.replace('/',' ');
        if(!timing.equals("")){
            timing+=")";
        }
        Task task = new Task(action, temp[0], timing);
        System.out.println(LINE);
        l1.add(task);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   "+task.getTaskDescription());
        System.out.println("\t Now you have " + l1.size()+ " tasks in the list.");
        System.out.println(LINE);
    }

    public static int validateCommand(String inCommand, ArrayList<Task> l1) {
        String[] userCommand = getCommand(inCommand);
        int status;
        String timing="";
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
                executeDone(inCommand,l1);
                break;
            case "todo":
                status = 4;
                updateTask(l1, inCommand, timing);
                break;
            case "deadline":
                status = 5;
                timing="(by:";
                updateTask(l1, inCommand, timing);
                break;
            case "event":
                status=6;
                timing="(at:";
                updateTask(l1, inCommand, timing);
                break;
            case "help":
                status=7;
                listCommands();
                break;
            default:
                status = -1;
        }
        return status;
    }

    public static void printWelcomeMessage() {
        String banner = "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = "\t~~~~~~~~~~~~~~~~~~ ____        _        ~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~|  _ \\ _   _| | _____ ~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~| | | | | | | |/ / _ \\~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~| |_| | |_| |   <  __/~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~|____/ \\__,_|_|\\_\\___|~~~~~~~~~~~~~~~~~~\n";
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
            String userIn = in.nextLine();
            status = validateCommand(userIn, l1);
            if(status==-1) {
                throwException();
            }
        }
    }
}
