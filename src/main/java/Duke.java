import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int charLengthToSkip = 5;

    // let statement be printed in center
    public static void printInCenter (String str) {
        int left = (60 - str.length()) / 2;
        int right = 60 - left - str.length();
        String repeatedChar = " ";
        String buff = "\t║" + repeatedChar.repeat(Math.max(0, left)) +
                str + repeatedChar.repeat(Math.max(0, right - 1)) + "║";
        System.out.println(buff);
    }

    public static void greet () {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("  _   _    _   _ U _____ u             ");
        printInCenter(" |'| |'|U |\"|u| |\\| ___\"|/    ___      ");
        printInCenter("/| |_| |\\\\| |\\| | |  _|\"     |_\"_|     ");
        printInCenter("U|  _  |u | |_| | | |___      | |      ");
        printInCenter(" |_| |_| <<\\___/  |_____|   U/| |\\u    ");
        printInCenter(" //   \\\\(__) )(   <<   >>.-,_|___|_,-. ");
        printInCenter("(_\") (\"_)   (__) (__) (__)\\_)-' '-(_/  ");
        printInCenter("");
        printInCenter("Hello! I'm your chatbot - Huei.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void echo (String cmd) {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter(cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void add (String cmd) {
        Task todo = new Task(tasks.size(), cmd, false);
        tasks.add(todo);
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Added: " + cmd);
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    private static void printTask (Task t) {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Got it. I've added this task: ");
        printInCenter(t.toString());
        printInCenter("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void todo (String cmd) {
        int startPosition = cmd.indexOf(" ");
        String cmdTodo = cmd.substring(startPosition + 1, cmd.length());
        Task todo = new Todo(tasks.size(), cmdTodo, false);
        tasks.add(todo);
        printTask(todo);
    }

    public static void event (String cmd) {
        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /at ");
        String cmdEvent = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip, cmd.length());
        Task event = new Event(tasks.size(), cmdEvent, false, cmdTime);
        tasks.add(event);
        printTask(event);
    }

    public static void deadline (String cmd) {
        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /by ");
        String cmdDeadline = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip, cmd.length());
        Task deadline = new Deadline(tasks.size(), cmdDeadline, false, cmdTime);
        tasks.add(deadline);
        printTask(deadline);
    }

    public static void done (int taskNumber) {
        tasks.get(taskNumber).setStatus();
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Nice! I've marked this task as done: ");
        printInCenter(tasks.get(taskNumber).toString());
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void list () {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Here are the tasks in your list:");
        for (Task t: tasks){
            if (t == null){
                break;
            }
            printInCenter(t.toString());
        }
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void exit () {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Bye! See you next time :)");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.exit(0);
    }

    public static void main (String[] args) {
        greet();

        // read command-line input
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        while (!cmd.equals("bye")) {
            String[] cmdSplit = cmd.split(" ");
            switch (cmdSplit[0]) {
            case "list":
                list();
                break;
            case "todo":
                todo(cmd);
                break;
            case "event":
                event(cmd);
                break;
            case "deadline":
                deadline(cmd);
                break;
            case "done":
                int spacePosition = cmd.indexOf(" ");
                int taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1, cmd.length())) - 1;
                done(taskNumber);
                break;
            default:
                break;
            }
            cmd = scanner.nextLine();
        }
        exit();
    }
}
