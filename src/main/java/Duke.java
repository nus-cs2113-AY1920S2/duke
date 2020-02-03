import java.util.Scanner;

public class Duke {

    private static int NUM_OF_TASK = 0;
    private static String LINE = "____________________________________________________________\n";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatbot();
    }

    private static void runChatbot() {
        Task[] Tasks = new Task[100];

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

    private static void runCommand(String[] arr, Task[] Tasks, Scanner in) {
        switch (arr[0]) {
        case (BYE_COMMAND):
            printExitMessage();
        case (LIST_COMMAND):
            printList(Tasks);
            break;
        case (DONE_COMMAND):
            int taskNum = Integer.parseInt(arr[1]);
            taskNum--;
            Tasks[taskNum].setDone(true);
            printDone(Tasks[taskNum]);
            break;
        case (TODO_COMMAND):
            Tasks[NUM_OF_TASK] = new Todo(arr[1]);
            printConfirm(Tasks[NUM_OF_TASK]);
            break;
        case (DEADLINE_COMMAND):
            String arr2[] = arr[1].split("/by ", 2);
            Tasks[NUM_OF_TASK] = new Deadline(arr2[0], arr2[1]);
            printConfirm(Tasks[NUM_OF_TASK]);
            break;
        case (EVENT_COMMAND):
            arr2 = arr[1].split("/at ", 2);
            Tasks[NUM_OF_TASK] = new Event(arr2[0], arr2[1]);
            printConfirm(Tasks[NUM_OF_TASK]);
            break;
        }
    }

    private static void printDone(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    private static void printConfirm(Task task) {
        System.out.println(LINE);
        System.out.println("Got it! I've added this task:");
        System.out.println("   " + task);
        int num = NUM_OF_TASK + 1;
        System.out.println("Now you have " + num + " task(s) in the list.");
        System.out.println(LINE);
        NUM_OF_TASK++;
    }

    private static void printExitMessage() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(LINE);
        System.out.println(outro);
        System.out.println(LINE);
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

        String intro = "____________________________________________________________\n" +
                " It is I, Bob!\n" +
                " How may I spook you today?\n" +
                "____________________________________________________________\n";

        System.out.println(intro);
    }

    public static void printList(Task[] Task) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < NUM_OF_TASK; i++) {
            int num = i + 1;
            System.out.println(num + ". " + Task[i]);
        }
        System.out.println(LINE);
    }
}

