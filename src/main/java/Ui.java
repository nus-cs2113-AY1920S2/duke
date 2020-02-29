import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static String LINE = "____________________________________________________________";


    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void printWelcomeMessage() {
        String logo = "     _____          ___           ___           ___     \n" +
                "    /  /::\\        /__/\\         /__/|         /  /\\    \n" +
                "   /  /:/\\:\\       \\  \\:\\       |  |:|        /  /:/_   \n" +
                "  /  /:/  \\:\\       \\  \\:\\      |  |:|       /  /:/ /\\  \n" +
                " /__/:/ \\__\\:|  ___  \\  \\:\\   __|  |:|      /  /:/ /:/_ \n" +
                " \\  \\:\\ /  /:/ /__/\\  \\__\\:\\ /__/\\_|:|____ /__/:/ /:/ /\\\n" +
                "  \\  \\:\\  /:/  \\  \\:\\ /  /:/ \\  \\:\\/:::::/ \\  \\:\\/:/ /:/\n" +
                "   \\  \\:\\/:/    \\  \\:\\  /:/   \\  \\::/~~~~   \\  \\::/ /:/ \n" +
                "    \\  \\::/      \\  \\:\\/:/     \\  \\:\\        \\  \\:\\/:/  \n" +
                "     \\__\\/        \\  \\::/       \\  \\:\\        \\  \\::/   \n" +
                "                   \\__\\/         \\__\\/         \\__\\/    \n";

        System.out.println("What is up my dudes!\n" + logo);

        String intro = " Hello there, welcome to DUKE!\n" +
                " How may I help you today?";

        showLine();
        System.out.println(intro);
        showLine();
    }

    public static void printDelete(Task task, int numOfTasks) {
        showLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("   " + task);
        numOfTasks--;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
        showLine();
    }

    public static void printDone(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
        showLine();
    }

    public static void printConfirm(Task task, int numOfTasks) {
        showLine();
        System.out.println("Got it! I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
        showLine();
    }

    public static void printExitMessage() {
        showLine();
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
        showLine();
    }

    public static void printList(ArrayList<Task> Task, int numOfTasks) {
        showLine();
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < numOfTasks; i++) {
            int num = i + 1;
            System.out.println(num + ". " + Task.get(i));
        }
        showLine();
    }

    public static void showError(String error) {
        showLine();
        System.out.println(error);
        showLine();
    }
}
