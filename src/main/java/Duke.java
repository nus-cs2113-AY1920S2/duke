import java.util.Scanner;

public class Duke {

    private static int numTask = 0;
    private static String line = "____________________________________________________________\n";

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatbot();
    }

    private static void runChatbot() {
        Task[] Tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String arr[] = s.split(" ", 2);

        while (true) {
            switch (arr[0]) {
            case ("bye"):
                printExitMessage();
            case ("list"):
                printList(Tasks);
                break;
            case ("done"):
                int taskNum = Integer.parseInt(arr[1]);
                taskNum--;
                Tasks[taskNum].setDone(true);
                printDone(Tasks[taskNum]);
                break;
            case ("todo"):
                Tasks[numTask] = new Todo(arr[1]);
                printConfirm(Tasks[numTask]);
                break;
            case ("deadline"):
                String arr2[] = arr[1].split("/by ", 2);
                Tasks[numTask] = new Deadline(arr2[0], arr2[1]);
                printConfirm(Tasks[numTask]);
                break;
            case ("event"):
                arr2 = arr[1].split("/at ", 2);
                Tasks[numTask] = new Event(arr2[0], arr2[1]);
                printConfirm(Tasks[numTask]);
                break;
            }
            s = in.nextLine();
            arr = s.split(" ", 2);
        }
    }

    private static void printDone(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
        System.out.println(line);
    }

    private static void printConfirm(Task task) {
        System.out.println(line);
        System.out.println("Got it! I've added this task:");
        System.out.println("   " + task);
        int num = numTask + 1;
        System.out.println("Now you have " + num + " task(s) in the list.");
        System.out.println(line);
        numTask++;
    }

    private static void printExitMessage() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(outro);
        System.out.println(line);
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
        System.out.println(line);
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < numTask; i++) {
            int num = i + 1;
            System.out.println(num + ". " + Task[i]);
        }
        System.out.println(line);
    }
}
