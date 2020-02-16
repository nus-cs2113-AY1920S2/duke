import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

import duke.task.Todo;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.FindDukeException;

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

    public static void delete (int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(task);

        Iterator itr = tasks.iterator();
        int counter = 0;
        while (itr.hasNext())
        {
            Task t = (Task)itr.next();
            t.taskID = counter;
            counter++;
        }

        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Noted. I've removed this task: ");
        printInCenter(task.toString());
        printInCenter("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    private static void printTask (@NotNull Task t) {
        System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
        printInCenter("Got it. I've added this task: ");
        printInCenter(t.toString());
        printInCenter("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("    What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void todo (String cmd) throws DukeException {
        FindDukeException findTodoException = new FindDukeException(cmd);
        findTodoException.toDoException();

        int startPosition = cmd.indexOf(" ");
        String cmdTodo = cmd.substring(startPosition + 1);
        Task todo = new Todo(tasks.size(), cmdTodo, false);
        tasks.add(todo);
        printTask(todo);
    }

    public static void event (String cmd) throws DukeException {
        FindDukeException findEventException = new FindDukeException(cmd);
        findEventException.eventException();

        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /at ");
        String cmdEvent = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip);
        Task event = new Event(tasks.size(), cmdEvent, false, cmdTime);
        tasks.add(event);
        printTask(event);
    }

    public static void deadline (String cmd) throws DukeException {
        FindDukeException findDeadlineException = new FindDukeException(cmd);
        findDeadlineException.deadlineException();

        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /by ");
        String cmdDeadline = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip);
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
        printInCenter("Bye! See you next time \uD83E\uDD17");
        System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
        System.exit(0);
    }

    public static void main (String[] args) {
        int spacePosition = -1;
        int taskNumber = -1;
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
            case "delete":
                spacePosition = cmd.indexOf(" ");
                taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1, cmd.length())) - 1;
                delete(taskNumber);
                break;
            case "todo":
                try {
                    todo(cmd);
                } catch (DukeException dukeException) {
                    System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
                    printInCenter(dukeException.getMessage());
                    System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
                }
                break;
            case "event":
                try {
                    event(cmd);
                } catch (DukeException dukeException) {
                    System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
                    printInCenter(dukeException.getMessage());
                    System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
                }
                break;
            case "deadline":
                try {
                    deadline(cmd);
                } catch (DukeException dukeException) {
                    System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
                    printInCenter(dukeException.getMessage());
                    System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
                }
                break;
            case "done":
                spacePosition = cmd.indexOf(" ");
                taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1, cmd.length())) - 1;
                done(taskNumber);
                break;
            default:
                try {
                    FindDukeException findDukeException = new FindDukeException(cmd);
                    findDukeException.undefinedTypeException();
                } catch (DukeException dukeException) {
                    System.out.println("    ╔═══════════════════════════════════════════════════════════╗");
                    printInCenter(dukeException.getMessage());
                    System.out.println("    ╚═══════════════════════════════════════════════════════════╝");
                }
                break;
            }
            cmd = scanner.nextLine();
        }
        exit();
    }
}
