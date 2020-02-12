import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;

public class Duke {

    private static int NUM_OF_TASK = 0;
    private static String LINE = "____________________________________________________________";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static int MAX_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatbot();
    }

    private static void runChatbot() {
        Task[] Tasks = new Task[MAX_TASK];
        Scanner in = new Scanner(System.in);
        String arr[] = getCommand(in);
        while (true) {
            runCommand(arr, Tasks, in);
            arr = getCommand(in);
        }
    }

    private static String[] getCommand(Scanner in) {
        String s = in.nextLine();
        String arr[] = s.split(" ", 2);
        return arr;
    }

    private static void runCommand(String[] arr, Task[] Tasks, Scanner in)  {
        System.out.println(LINE);
        switch (arr[0]) {
        case (BYE_COMMAND):
            printExitMessage();
        case (LIST_COMMAND):
            printList(Tasks);
            break;
        case (DONE_COMMAND):
            try {
                int taskNum = Integer.parseInt(arr[1]);
                taskNum--;
                Tasks[taskNum].setDone(true);
                printDone(Tasks[taskNum]);
            } catch (ArrayIndexOutOfBoundsException e) {
                //If arr[1] does not exist
                System.out.println("Oops! Please include the task number.");
            } catch (NumberFormatException e) {
                //If arr[1] cannot be parsed as an Integer
                System.out.println("Oops! Please include the task number instead of '" + arr[1] + "'.");
            } catch (NullPointerException e) {
                //If the task number given is more than num of tasks
                System.out.println("Sorry but that task does not exist! Please try again.");
            }
            break;
        case (TODO_COMMAND):
            try {
                Tasks[NUM_OF_TASK] = new Todo(arr[1]);
                printConfirm(Tasks[NUM_OF_TASK]);
            } catch (ArrayIndexOutOfBoundsException e) {
                //If arr[1] does not exist
                System.out.println("Oops! task.Task description cannot be empty!");
            }
            break;
        case (DEADLINE_COMMAND):
            try {
                String arr2[] = arr[1].split("/by ", 2);
                Tasks[NUM_OF_TASK] = new Deadline(arr2[0], arr2[1]);
                printConfirm(Tasks[NUM_OF_TASK]);
            } catch (ArrayIndexOutOfBoundsException e) {
                //If arr[1] does not exist
                System.out.println("Oops! task.Deadline description is incomplete!");
            }
            break;
        case (EVENT_COMMAND):
            try {
                String arr2[] = arr[1].split("/at ", 2);
                Tasks[NUM_OF_TASK] = new Event(arr2[0], arr2[1]);
                printConfirm(Tasks[NUM_OF_TASK]);
            } catch (ArrayIndexOutOfBoundsException e) {
                //If arr[1] does not exist
                System.out.println("Oops! task.Event description is incomplete!");
            }
            break;
        default:
            //unknown command
            System.out.println("Oops! I'm sorry but I don't know what that means :(");
            break;
        }
        System.out.println(LINE);
    }

    private static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
    }

    private static void printConfirm(Task task) {
        System.out.println("Got it! I've added this task:");
        System.out.println("   " + task);
        NUM_OF_TASK++;
        System.out.println("Now you have " + NUM_OF_TASK + " task(s) in the list.");
    }

    private static void printExitMessage() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
        System.exit(0);
    }

    private static void printWelcomeMessage() {
        String tos = "──────────▄▄▄▄▄▄▄▄▄▄▄──────────\n" +
                "─────▄▄▀▀▀▀──────────▀▀▄▄──────\n" +
                "───▄▀───────────────────▀▀▄────\n" +
                "──█────────────────────────█───\n" +
                "─█─────────────────────▄▀▀▀▀▀█▄\n" +
                "█▀────────────────────█────▄███\n" +
                "█─────────────────────█────▀███\n" +
                "█─────▄▀▀██▀▄─────────█───────█\n" +
                "█────█──████─█─────────▀▄▄▄▄▄█─\n" +
                "█────█──▀██▀─█───────────────█─\n" +
                "█────█───────█──────────────▄▀─\n" +
                "█────▀▄─────▄▀──▄▄▄▄▄▄▄▄▄───█──\n" +
                "█──────▀▀▀▀▀────█─█─█─█─█──▄▀──\n" +
                "─█──────────────▀▄█▄█▄█▀──▄▀───\n" +
                "──█──────────────────────▄▀────\n" +
                "───▀▀▀▄──────────▄▄▄▄▄▄▀▀──────\n" +
                "────▄▀─────────▀▀──▄▀──────────\n" +
                "──▄▀───────────────█───────────\n" +
                "─▄▀────────────────█──▄▀▀▀█▀▀▄─\n" +
                "─█────█──█▀▀▀▄─────█▀▀────█──█─\n" +
                "▄█────▀▀▀────█─────█────▀▀───█─\n" +
                "█▀▄──────────█─────█▄────────█─\n" +
                "█──▀▀▀▀▀█▄▄▄▄▀─────▀█▀▀▀▄▄▄▄▀──\n" +
                "█───────────────────▀▄─────────\n";

        System.out.println("What is up my dudes!\n" + tos);

        String intro = " It is I, Bob!\n" +
                " How may I spook you today?";

        System.out.println(LINE);
        System.out.println(intro);
        System.out.println(LINE);
    }

    public static void printList(Task[] Task) {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < NUM_OF_TASK; i++) {
            int num = i + 1;
            System.out.println(num + ". " + Task[i]);
        }
    }
}

