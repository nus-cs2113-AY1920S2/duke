import java.util.Scanner;

public class Duke {

    private static int numTask = 0;
    private static String line = "____________________________________________________________\n";

    public static void main(String[] args) {

        welcomeMessage();
        introMessage();

        Task[] Tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.equals("bye")) {
            if (s.equals(("list"))) {
                System.out.println(line);
                printList(Tasks);
                System.out.println(line);
            }
            else if (s.startsWith("done ")) {
                String subs = s.substring(5);
                int result = Integer.parseInt(subs) - 1;
                Tasks[result].setDone(true);
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + Tasks[result].getStatusIcon() + "] " + Tasks[result].getDescription());
                System.out.println(line);
            }
            else {
                System.out.println(line);
                System.out.println("added: " + s);
                System.out.println(line);
                Task temp = new Task(s);
                Tasks[numTask] = temp;
                numTask++;
            }
            in = new Scanner(System.in);
            s = in.nextLine();
        }

        outroMessage();
    }

    private static void outroMessage() {
        String outro = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(outro);
    }

    private static void introMessage() {
        String intro = "____________________________________________________________\n" +
                " It is I, Bob!\n" +
                " How may I spook you today?\n" +
                "____________________________________________________________\n";

        System.out.println(intro);
    }

    private static void welcomeMessage() {
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
    }

    public static void printList(Task[] Task) {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < numTask; i++) {
            System.out.println(i+1 + ". [" + Task[i].getStatusIcon() + "] " + Task[i].getDescription() + "\n");
        }
    }
}
