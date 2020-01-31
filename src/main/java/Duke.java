import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int taskCounter = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int charLengthToSkip = 5;

    // let statement be printed at center
    public static void print(String str) {
        int left = (60 - str.length()) / 2;
        int right = 60 - left - str.length();
        String repeatedChar = " ";
        String buff = "\t║" + repeatedChar.repeat(Math.max(0, left)) +
                str + repeatedChar.repeat(Math.max(0, right - 1)) + "║";
        System.out.println(buff);
    }

    public static void greet() {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("  _   _    _   _ U _____ u             ");
        print(" |'| |'|U |\"|u| |\\| ___\"|/    ___      ");
        print("/| |_| |\\\\| |\\| | |  _|\"     |_\"_|     ");
        print("U|  _  |u | |_| | | |___      | |      ");
        print(" |_| |_| <<\\___/  |_____|   U/| |\\u    ");
        print(" //   \\\\(__) )(   <<   >>.-,_|___|_,-. ");
        print("(_\") (\"_)   (__) (__) (__)\\_)-' '-(_/  ");
        print("");
        print("Hello! I'm your chatbot - Huei.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void echo(String cmd) {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print(cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void add(String cmd) {
        Task todo = new Task(taskCounter, cmd, false);
        tasks.add(todo);
        taskCounter++;
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Added: " + cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    private static void printTask(Task t) {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Got it. I've added this task: ");
        print(t.toString());
        print("Now you have " + taskCounter + " tasks in the list.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void todo(String cmd) {
        int startPosition = cmd.indexOf(" ");
        String cmdTodo = cmd.substring(startPosition + 1, cmd.length());
        Task todo = new Todo(taskCounter, cmdTodo, false);
        tasks.add(todo);
        taskCounter++;
        printTask(todo);
    }

    public static void event(String cmd) {
        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /at ");
        String cmdEvent = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip, cmd.length());
        Task event = new Event(taskCounter, cmdEvent, false, cmdTime);
        tasks.add(event);
        taskCounter++;
        printTask(event);
    }

    public static void deadline(String cmd) {
        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /by ");
        String cmdDeadline = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip, cmd.length());
        Task deadline = new Deadline(taskCounter, cmdDeadline, false, cmdTime);
        tasks.add(deadline);
        taskCounter++;
        printTask(deadline);
    }

    public static void done(int taskNumber) {
        tasks.get(taskNumber).setStatus();
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Nice! I've marked this task as done: ");
        print(tasks.get(taskNumber).getTaskType() + tasks.get(taskNumber).getStatusIcon() + " " + tasks.get(taskNumber).description);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void list() {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Here are the tasks in your list:");
        for(Task t: tasks){
            if(t == null){
                break;
            }
            print(t.toString());
        }
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void exit() {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        print("Bye! See you next time :)");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.exit(0);
    }

    public static void main(String[] args) {
        greet();

        // read command-line input
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        while(!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else if (cmd.length() > 4 && cmd.substring(0, 4).equals("todo")) {
                todo(cmd);
            } else if (cmd.length() > 5 && cmd.substring(0, 5).equals("event")) {
                event(cmd);
            } else if (cmd.length() > 8 && cmd.substring(0, 8).equals("deadline")) {
                deadline(cmd);
            } else if (cmd.length() > 4 && cmd.substring(0, 4).equals("done")) {
                int spacePosition = cmd.indexOf(" ");
                int taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1, cmd.length())) - 1;
                if (taskNumber < taskCounter && taskNumber > -1) {
                    done(taskNumber);
                } else {
                    System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
                    print("Oops! Please enter again:");
                    System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
                    System.out.println("");
                }
            }
            cmd = scanner.nextLine();
        }
        exit();
    }
}
