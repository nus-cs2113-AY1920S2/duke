import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printStartMessage();

        String userInput;
        Task [] tasks = new Task [100];
        int numTasks = 0;

        while (true) {
            // Get and parse user input
            userInput = input.nextLine();
            // Exit if user says bye
            if (userInput.equals("bye")) {
                break;
            }
            String[] parsedInput = userInput.split(" ", 2);
            String command = parsedInput[0];
            String words = "";
            if (parsedInput.length != 1) {
                words = parsedInput[1];
            }

            switch(command) {
            // List all the tasks
            case "list":
                listTasks(tasks, numTasks);
                break;
            // Mark a task as done
            case "done":
                br();
                System.out.println("\t Dun dun dun dun! This task is done:");
                int taskIdx = Integer.parseInt(words);
                taskIdx--; // -1 for zero-based indexing
                tasks[taskIdx].isDone = true;
                System.out.println("\t   " + tasks[taskIdx]);
                br();
                break;
            // Add a task
            default:
                Task t;

                // Parse input to obtain text and timeDescriptor
                String timeDescriptor = "";
                String text = "";
                int slashPos = words.indexOf('/');
                if (slashPos != -1) {
                    timeDescriptor = words.substring(slashPos+4);
                    text = words.substring(0, slashPos);
                }

                if (command.equals("todo")) {
                    t = new Todo(words);
                } else if (command.equals("deadline")) {
                    t = new Deadline(text, timeDescriptor);
                } else {
                    t = new Event(text, timeDescriptor);
                }
                tasks[numTasks] = t;
                numTasks++;

                printAddedTaskMessage(t, numTasks);
                break;
            }
        }

        printEndMessage();
    }

    /** Prints the message that is displayed after a task is added */
    private static void printAddedTaskMessage(Task t, int numTasks) {
        br();
        System.out.println("\t Dook has added task: ");
        System.out.println("\t  " + t);
        System.out.println("\t " + numTasks + " task(s) in the list now!");
        br();
    }

    /** Prints all tasks in the list */
    private static void listTasks(Task[] tasks, int numTasks) {
        br();
        System.out.println("\t Dook will list your tasks now:");
        for (int i=0; i<numTasks; i++) {
            int taskNum = i+1;
            System.out.println("\t " + taskNum + ". " + tasks[i]);
        }
        br();
    }

    /** Prints line divider */
    private static void br () {
        System.out.println("    ...................................................");
    }

    /** Prints the greeting message */
    private static void printStartMessage() {
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
    private static void printEndMessage() {
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
}
