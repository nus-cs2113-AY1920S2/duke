import java.util.Scanner;

public class Duke {
    /** Prints the line divider */
    public static void br () {
        System.out.println("    ...................................................");
    }

    /** Prints the greeting message */
    public static void printStartMessage() {
        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                "         │││ ││ │├┴┐\n" +
                "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("\t Hello! I am \n" + logo);
        System.out.println("\t Does the human have a request today?");
        br();
    }

    /** Prints the goodbye message */
    public static void printEndMessage() {
        br();
        System.out.println("\t Goodbye, see you in the seventh dimension!");
        System.out.println("                   *       +\n" +
                "             '                  |\n" +
                "         ()    .-.,=\"``\"=.    - o -\n" +
                "               '=/_       \\     |\n" +
                "            *   |  '=._    |\n" +
                "                 \\     `=./`,        '\n" +
                "              .   '=.__.=' `='      *\n" +
                "     +                         +\n" +
                "          O      *        '       .");
        br();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printStartMessage();

        String command = " ";
        Task [] tasks = new Task [100];
        int numTasks = 0;

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            String[] words = command.split(" ");

            switch(words[0]) {
            case "list":
                br();
                System.out.println("\t Dook will list your tasks now:");
                for (int i=0; i<numTasks; i++) {
                    int num = i+1;
                    System.out.print("\t " + num + ". " + "[" + tasks[i].getStatusIcon() + "] ");
                    System.out.println(tasks[i].description);
                }
                br();
                break;
            case "done":
                br();
                System.out.println("\t Dun dun dun dun! This task is done:");
                int itemCode = Integer.parseInt(words[1]);
                // -1 for zero-based indexing
                itemCode--;
                tasks[itemCode].isDone = true;
                System.out.println("\t   [" + "✓" + "] " + tasks[itemCode].description);
                br();
                break;
            default:
                Task t = new Task(command);
                tasks[numTasks] = t;
                numTasks++;
                br();
                System.out.println("\t added: " + command);
                br();
                break;
            }
        }

        printEndMessage();
    }
}
